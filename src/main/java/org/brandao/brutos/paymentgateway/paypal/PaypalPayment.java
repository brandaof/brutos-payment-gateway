package org.brandao.brutos.paymentgateway.paypal;

import org.brandao.brutos.paymentgateway.entity.Payment;
import org.brandao.brutos.paymentgateway.payment.PaymentType;

@PaymentType(PaypalPaymentGateway.PAYMENT_TYPE)
public class PaypalPayment extends Payment{

	private String token;

	private String correlationID;
	
	private String payerid;
	
	private String transactionID;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCorrelationID() {
		return correlationID;
	}

	public void setCorrelationID(String correlationID) {
		this.correlationID = correlationID;
	}

	public String getPayerid() {
		return payerid;
	}

	public void setPayerid(String payerid) {
		this.payerid = payerid;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	
}
