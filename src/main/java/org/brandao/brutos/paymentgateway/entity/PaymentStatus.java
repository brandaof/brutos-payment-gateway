package org.brandao.brutos.paymentgateway.entity;

public enum PaymentStatus {

	PENDING_PAYMENT("Pending payment"),
	
	PAYMENT_RECEIVED("Payment received"),

	REFOUND("Refound"),

	CANCELED_REFOUND("Canceled refound");

	private String name;
	
	private PaymentStatus(String name){
		this.name = name;
	}
	
	public String getFriendlyName(){
		return this.name;
	}
	
}
