package org.brandao.brutos.paymentgateway.registry;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.brandao.brutos.paymentgateway.entity.Payment;
import org.brandao.brutos.paymentgateway.entity.PaymentStatus;
import org.brandao.brutos.paymentgateway.entityaccess.EntityAccessException;
import org.brandao.brutos.paymentgateway.entityaccess.PaymentEntityAccess;
import org.brandao.brutos.paymentgateway.payment.PaymentGateway;
import org.brandao.brutos.paymentgateway.payment.PaymentGatewayException;
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
	public void registerPayment(Payment payment)
			throws PaymentRegistryException {
		try{
			if(payment.getId() <= 0){
				PaymentGateway paymentGateway = 
					this.paymentGatewayProvider.getPaymentGateway(payment.getPaymentType());
				
				if(paymentGateway == null){
					throw new PaymentRegistryException("invalid payment type: " + payment.getPaymentType());
				}
				
				this.registerNewPayment(payment);
				this.registryPayment(payment, paymentGateway);
			}
			else{
				this.paymentEntityAccess.update(payment);
				this.paymentEntityAccess.flush();
			}
			
		}
		catch(PaymentRegistryException e){
			throw e;
		}
		catch(Throwable e){
			throw new PaymentRegistryException(e);
		}
	}

	private void registerNewPayment(Payment payment) throws EntityAccessException{
		payment.setPaymentDate(new Date());
		payment.setPaymentStatus(PaymentStatus.PENDING_PAYMENT);
		this.paymentEntityAccess.save(payment);
		this.paymentEntityAccess.flush();
	}
	
	private void registryPayment(Payment payment, PaymentGateway paymentGateway
			) throws PaymentGatewayException, EntityAccessException{
		paymentGateway.payment(payment);
		payment.setPaymentStatus(PaymentStatus.PAYMENT_RECEIVED);
		this.paymentEntityAccess.update(payment);
		this.paymentEntityAccess.flush();
	}
	
	@Override
	public void removePayment(int id) throws PaymentRegistryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Payment findPaymentById(int id) throws PaymentRegistryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> findAllPayment() throws PaymentRegistryException {
		// TODO Auto-generated method stub
		return null;
	}

}
