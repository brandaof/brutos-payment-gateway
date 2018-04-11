package org.brandao.brutos.paymentgateway.paypal;

import org.brandao.brutos.paymentgateway.entity.PaymentGatewayConfig;
import org.brandao.brutos.paymentgateway.payment.PaymentType;

@PaymentType(PaypalPaymentGateway.PAYMENT_TYPE)
public class PaypalPaymentGatewayConfig 
	extends PaymentGatewayConfig{

}
