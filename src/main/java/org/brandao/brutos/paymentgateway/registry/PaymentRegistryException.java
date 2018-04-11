package org.brandao.brutos.paymentgateway.registry;

public class PaymentRegistryException extends Exception{

	private static final long serialVersionUID = -4800481453724870910L;

	public PaymentRegistryException() {
		super();
	}

	public PaymentRegistryException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaymentRegistryException(String message) {
		super(message);
	}

	public PaymentRegistryException(Throwable cause) {
		super(cause);
	}

}
