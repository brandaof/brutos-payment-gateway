package org.brandao.brutos.paymentgateway.web.entity;

import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.paymentgateway.entity.Payment;
import org.brandao.brutos.paymentgateway.entity.PaymentStatus;

public class RegistryPaymentPubEntity 
	extends Payment{

	@Transient
	public void setId(int id){
		super.setId(id);
	}
	
	@Transient
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		super.setPaymentStatus(paymentStatus);
	}
	
}
