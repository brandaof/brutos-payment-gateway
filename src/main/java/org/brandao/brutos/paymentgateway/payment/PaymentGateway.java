package org.brandao.brutos.paymentgateway.payment;

import org.brandao.brutos.paymentgateway.entity.Payment;
import org.brandao.brutos.paymentgateway.entity.PaymentGatewayConfig;

public interface PaymentGateway {

	void configure(PaymentGatewayConfig config) throws PaymentGatewayException;
	
	String getProperty(String name) throws PaymentGatewayException;
	
	void payment(Payment payment) throws PaymentGatewayException;
	
}
