package org.brandao.brutos.paymentgateway.registry;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.brandao.brutos.paymentgateway.entity.Payment;
import org.brandao.brutos.paymentgateway.entityaccess.PaymentEntityAccess;
import org.brandao.brutos.paymentgateway.payment.PaymentGateway;
import org.brandao.brutos.paymentgateway.payment.PaymentGatewayProvider;

@Singleton
public class PaymentRegistryImp 
	implements PaymentRegistry{

	@Inject
	private PaymentGatewayProvider paymentGatewayProvider;
	
	@Inject
	private PaymentEntityAccess paymentEntityAccess;
	
	@Override
	@Transactional
	public void registerPaymentRequest(Payment payment)
			throws PaymentRegistryException {
		
		if(payment.getId() > 0){
			throw new PaymentRegistryException("invalid payment");
		}
		
		try{
			PaymentGateway paymentGateway = 
				this.paymentGatewayProvider.getPaymentGateway(payment.getPaymentType());
			
			if(paymentGateway == null){
				throw new PaymentRegistryException("invalid payment type: " + payment.getPaymentType());
			}
			
			paymentGateway.payment(payment);
			payment.setPaymentDate(new Date());
			this.paymentEntityAccess.save(payment);
			this.paymentEntityAccess.flush();
		}
		catch(PaymentRegistryException e){
			throw e;
		}
		catch(Throwable e){
			throw new PaymentRegistryException(e);
		}
	}	
	@Override
	@Transactional
	public void registerPayment(Payment payment)
			throws PaymentRegistryException {
		try{
			if(payment.getId() <= 0){
				this.paymentEntityAccess.save(payment);
			}
			else{
				this.paymentEntityAccess.update(payment);
			}
			this.paymentEntityAccess.flush();
			
		}
		catch(Throwable e){
			throw new PaymentRegistryException(e);
		}
	}

	@Override
	public void removePayment(int id) throws PaymentRegistryException {
		try{
			this.paymentEntityAccess.delete(id);
			this.paymentEntityAccess.flush();
		}
		catch(Throwable e){
			throw new PaymentRegistryException(e);
		}
	}

	@Override
	public Payment findPaymentById(int id) throws PaymentRegistryException {
		try{
			return this.paymentEntityAccess.findById(id);
		}
		catch(Throwable e){
			throw new PaymentRegistryException(e);
		}
	}

	@Override
	public List<Payment> findAllPayment() throws PaymentRegistryException {
		try{
			return this.paymentEntityAccess.findAll();
		}
		catch(Throwable e){
			throw new PaymentRegistryException(e);
		}
	}

}
