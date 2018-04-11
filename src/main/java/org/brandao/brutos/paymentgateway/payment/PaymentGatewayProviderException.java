package org.brandao.brutos.paymentgateway.payment;

public class PaymentGatewayProviderException 
	extends RuntimeException{

	private static final long serialVersionUID = 4937681125718739144L;

	public PaymentGatewayProviderException() {
		super();
	}

	public PaymentGatewayProviderException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaymentGatewayProviderException(String message) {
		super(message);
	}

	public PaymentGatewayProviderException(Throwable cause) {
		super(cause);
	}

}
