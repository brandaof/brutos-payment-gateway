package org.brandao.brutos.paymentgateway.payment;

public class PaymentGatewayMetadata {

	private String id;
	
	private Class<?> paymentGatewayClass;
	
	private Class<?> paymentEntity;
	
	private Class<?> paymentConfigurationEntity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Class<?> getPaymentGatewayClass() {
		return paymentGatewayClass;
	}

	public void setPaymentGatewayClass(Class<?> paymentGatewayClass) {
		this.paymentGatewayClass = paymentGatewayClass;
	}

	public Class<?> getPaymentEntity() {
		return paymentEntity;
	}

	public void setPaymentEntity(Class<?> paymentEntity) {
		this.paymentEntity = paymentEntity;
	}

	public Class<?> getPaymentConfigurationEntity() {
		return paymentConfigurationEntity;
	}

	public void setPaymentConfigurationEntity(Class<?> paymentConfigurationEntity) {
		this.paymentConfigurationEntity = paymentConfigurationEntity;
	}
	
}
