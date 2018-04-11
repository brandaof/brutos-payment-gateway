package org.brandao.brutos.paymentgateway.registry;

import java.util.List;

import org.brandao.brutos.paymentgateway.entity.PaymentGatewayConfig;

public interface PaymentGatewayConfigRegistry {

	void registerPaymentConfig(PaymentGatewayConfig value) throws PaymentGatewayConfigRegistryException;

	void removePaymentConfig(int id) throws PaymentGatewayConfigRegistryException;
	
	PaymentGatewayConfig findPaymentConfigById(int id) throws PaymentGatewayConfigRegistryException;
	
	PaymentGatewayConfig findPaymentConfigByType(String type) throws PaymentGatewayConfigRegistryException;
	
	List<PaymentGatewayConfig> findAllPaymentConfig() throws PaymentGatewayConfigRegistryException;
	
	List<PaymentGatewayConfig> findAllPaymentConfig(boolean enabled) throws PaymentGatewayConfigRegistryException;
	
}
