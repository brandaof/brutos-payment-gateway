package org.brandao.brutos.paymentgateway.web.entity;

import java.util.List;
import java.util.stream.Collectors;

import org.brandao.brutos.annotation.configuration.MetaValueDefinition;
import org.brandao.brutos.annotation.configuration.MetaValuesDefinition;
import org.brandao.brutos.paymentgateway.entityaccess.EntityContext;
import org.brandao.brutos.paymentgateway.payment.PaymentGatewayMetadata;
import org.brandao.brutos.paymentgateway.payment.PaymentGatewayProvider;

public class PaymentMetaValuesDefinition 
	implements MetaValuesDefinition{

	public PaymentMetaValuesDefinition(){
	}
	
	@Override
	public List<MetaValueDefinition> getMetaValues() {
		
		PaymentGatewayProvider pgp = EntityContext.getEntity(PaymentGatewayProvider.class);
		
		List<PaymentGatewayMetadata> metadata = pgp.getPaymentGatewayMetadata();
		
		return metadata.stream()
			.map(a->new MetaValueDefinition(a.getId(),a.getPaymentEntity()))
			.collect(Collectors.toList());
	}

}
