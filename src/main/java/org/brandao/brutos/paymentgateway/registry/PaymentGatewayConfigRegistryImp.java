package org.brandao.brutos.paymentgateway.registry;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.brandao.brutos.paymentgateway.entity.PaymentGatewayConfig;
import org.brandao.brutos.paymentgateway.entityaccess.PaymentGatewayConfigEntityAccess;

@Singleton
public class PaymentGatewayConfigRegistryImp 
	implements PaymentGatewayConfigRegistry{

	@Inject
	private PaymentGatewayConfigEntityAccess entityAccess;
	
	@Override
	public void registerPaymentConfig(PaymentGatewayConfig value)
			throws PaymentGatewayConfigRegistryException {
		try{
			if(value.getId() <= 0){
				this.entityAccess.save(value);
			}
			else{
				this.entityAccess.update(value);
			}
			this.entityAccess.flush();
		}
		catch(Throwable e){
			throw new PaymentGatewayConfigRegistryException(e);
		}
	}

	@Override
	public void removePaymentConfig(int id)
			throws PaymentGatewayConfigRegistryException {
		try{
			this.entityAccess.delete(id);
		}
		catch(Throwable e){
			throw new PaymentGatewayConfigRegistryException(e);
		}
	}

	@Override
	public PaymentGatewayConfig findPaymentConfigById(int id)
			throws PaymentGatewayConfigRegistryException {
		try{
			return this.entityAccess.findById(id);
		}
		catch(Throwable e){
			throw new PaymentGatewayConfigRegistryException(e);
		}
	}

	@Override
	public PaymentGatewayConfig findPaymentConfigByType(String type)
			throws PaymentGatewayConfigRegistryException {
		try{
			return this.entityAccess.findByType(type);
		}
		catch(Throwable e){
			throw new PaymentGatewayConfigRegistryException(e);
		}
	}

	@Override
	public List<PaymentGatewayConfig> findAllPaymentConfig()
			throws PaymentGatewayConfigRegistryException {
		try{
			return this.entityAccess.findAll();
		}
		catch(Throwable e){
			throw new PaymentGatewayConfigRegistryException(e);
		}
	}

	@Override
	public List<PaymentGatewayConfig> findAllPaymentConfig(boolean enabled)
			throws PaymentGatewayConfigRegistryException {
		try{
			return this.entityAccess.findAll(enabled);
		}
		catch(Throwable e){
			throw new PaymentGatewayConfigRegistryException(e);
		}
	}

}
