package org.brandao.brutos.paymentgateway.registry;

import java.util.List;

import org.brandao.brutos.paymentgateway.entity.Payment;

public interface PaymentRegistry {

	void registerPayment(Payment payment) throws PaymentRegistryException;

	void registerPaymentRequest(Payment payment) throws PaymentRegistryException;
	
	void removePayment(int id) throws PaymentRegistryException;
	
	Payment findPaymentById(int id) throws PaymentRegistryException;
	
	List<Payment> findAllPayment() throws PaymentRegistryException;
	
}
