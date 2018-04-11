package org.brandao.brutos.paymentgateway.web;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;

import org.brandao.brutos.ResultAction;
import org.brandao.brutos.ResultActionImp;
import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.Any;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.View;
import org.brandao.brutos.annotation.web.RequestMethod;
import org.brandao.brutos.annotation.web.ResponseError;
import org.brandao.brutos.annotation.web.ResponseErrors;
import org.brandao.brutos.paymentgateway.entity.Payment;
import org.brandao.brutos.paymentgateway.registry.PaymentRegistry;
import org.brandao.brutos.paymentgateway.registry.PaymentRegistryException;
import org.brandao.brutos.paymentgateway.web.entity.PaymentMetaValuesDefinition;
import org.brandao.brutos.web.RequestMethodTypes;

@Action(value="/payment", view=@View("payment/paymentForm"))
public class PaymentController {

	@Transient
	@Inject
	private PaymentRegistry paymentRegistry;
	
	@RequestMethod(RequestMethodTypes.POST)
	@Action("/payment")
	@ResponseErrors(
			code=200, 
			view="payment/paymentForm",
			exceptions=
				@ResponseError(
					code=200, 
					name="errors",
					view="payment/paymentForm",
					target=ValidationException.class
			)
	)
	public void registerPayment(
			@Valid
			@NotNull
			@Any(
				metaBean=@Basic(bean="paymentType"),
				metaValuesDefinition=PaymentMetaValuesDefinition.class
			)
			@Basic(bean="payment")
			Payment payment) throws ValidationException, PaymentRegistryException{
		
		this.paymentRegistry.registerPayment(payment);
	}
	
	@Action("/payment/list")
	@View("payment/paymentList")
	public List<Payment> listPayment() throws PaymentRegistryException{
		return this.paymentRegistry.findAllPayment();
	}

	@Action("/payment/{paymentType:[a-z]{1,30}}")
	public ResultAction showPaymentForm(
			@Basic(bean="paymentType")
			String paymentType){
		ResultAction result = new ResultActionImp();
		result.setView("payment/gateway/" + paymentType + "Form");
		return result;
	}
	
	@Action("/payment/{paymentType:[a-z]{1,30}}/view/{id:\\d{1,5}}")
	public ResultAction showPayment(
			@Basic(bean="paymentType")
			String paymentType,
			@Basic(bean="id")
			Integer id) throws PaymentRegistryException{
		
		ResultAction result = new ResultActionImp();
		result.setView("payment/gateway/" + paymentType);
		result.add("payment", this.paymentRegistry.findPaymentById(id));
		return result;
	}
	
}
