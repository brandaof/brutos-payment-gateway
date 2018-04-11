package org.brandao.brutos.paymentgateway.paypal;

import org.brandao.brutos.paymentgateway.entity.Payment;
import org.brandao.brutos.paymentgateway.entity.PaymentGatewayConfig;
import org.brandao.brutos.paymentgateway.payment.PaymentGateway;
import org.brandao.brutos.paymentgateway.payment.PaymentGatewayException;
import org.brandao.brutos.paymentgateway.payment.PaymentType;

@PaymentType(PaypalPaymentGateway.PAYMENT_TYPE)
public class PaypalPaymentGateway 
	implements PaymentGateway{

	public static final String PAYMENT_TYPE = "paypal";
	
	@Override
	public void configure(PaymentGatewayConfig config)
			throws PaymentGatewayException {
	}

	@Override
	public String getProperty(String name) throws PaymentGatewayException {
		return null;
	}

	@Override
	public void payment(Payment payment) throws PaymentGatewayException {
	}

}
