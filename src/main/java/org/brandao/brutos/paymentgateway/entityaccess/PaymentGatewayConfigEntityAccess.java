package org.brandao.brutos.paymentgateway.entityaccess;

import java.util.List;

import org.brandao.brutos.paymentgateway.entity.PaymentGatewayConfig;

public interface PaymentGatewayConfigEntityAccess 
	extends EntityAccess<PaymentGatewayConfig>{
	
	PaymentGatewayConfig findByType(String type) throws EntityAccessException;
	
	List<PaymentGatewayConfig> findAll(boolean enabled) throws EntityAccessException;
}
