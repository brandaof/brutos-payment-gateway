package org.brandao.brutos.paymentgateway.paypal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.brandao.brutos.paymentgateway.entity.PaymentGatewayConfig;
import org.brandao.brutos.paymentgateway.payment.PaymentType;
import org.hibernate.validator.constraints.Length;

@PaymentType(PaypalPaymentGateway.PAYMENT_TYPE)
public class PaypalPaymentGatewayConfig 
	extends PaymentGatewayConfig{

	@NotNull
	@Length(min=3, max=60)
	private String user;
	
	@Length(min=3, max=60)
	private String pass;
	
	@Length(min=3, max=128)
	private String signature;
	
	@Length(min=3, max=256)
	private String endpoint;
	
	@Email
	private String email;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
