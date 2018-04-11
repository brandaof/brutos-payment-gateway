package org.brandao.brutos.paymentgateway.payment;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.brandao.brutos.ClassUtil;
import org.brandao.brutos.annotation.scanner.DefaultScanner;
import org.brandao.brutos.annotation.scanner.filter.AssignableTypeFilter;
import org.brandao.brutos.paymentgateway.entity.Payment;
import org.brandao.brutos.paymentgateway.entity.PaymentGatewayConfig;
import org.brandao.brutos.paymentgateway.registry.PaymentGatewayConfigRegistry;

@Singleton
public class PaymentGatewayProvider {

	private ConcurrentMap<String,PaymentGatewayMetadata> payments;
	
	@Inject
	private PaymentGatewayConfigRegistry paymentGatewayConfigRegistry;
	
	@Inject
	public PaymentGatewayProvider(){
		this.init();
	}
	
	public List<PaymentGatewayMetadata> getPaymentGatewayMetadata(){
		return this.payments.values().stream().collect(Collectors.toList());
	}
	
	public PaymentGateway getPaymentGateway(String paymentType) throws PaymentGatewayProviderException{
		try{
			paymentType = paymentType.toLowerCase();
			PaymentGatewayMetadata metadata = this.payments.get(paymentType);
			
			if(metadata == null){
				return null;
			}
			
			PaymentGatewayConfig config = this.paymentGatewayConfigRegistry.findPaymentConfigByType(paymentType);
			
			if(config == null){
				return null;
			}
			
			PaymentGateway pg = 
					(PaymentGateway) ClassUtil.getInstance(metadata.getPaymentGatewayClass());
			
			pg.configure(config);
			
			return pg;
		}
		catch(Throwable e){
			throw new PaymentGatewayProviderException(e);
		}
	}
	
	private void init() throws PaymentGatewayProviderException{
		this.loadPaymentGatewayMetadata();
		this.validatePaymentGatewayMetadata();
	}
	
	private void loadPaymentGatewayMetadata() throws PaymentGatewayProviderException{
		
		this.payments = new ConcurrentHashMap<String, PaymentGatewayMetadata>();
		
		List<Class<?>> paymentGatewayClassList 			= this.getPaymentGatewayClassList();
		List<Class<?>> paymentClassList 				= this.getPaymentClassList();
		List<Class<?>> paymentGatewayConfigClassList 	= this.getPaymentGatewayConfigClassList();
		
		BiConsumer<Class<?>,BiConsumer<PaymentGatewayMetadata,Class<?>>> action = (a,b)->{
			
			PaymentType payment = a.getAnnotation(PaymentType.class);
			
			if(payment == null || payment.value().trim().isEmpty()){
				throw new PaymentGatewayProviderException("invalid paymentType: " + a.getName());
			}
			
			String paymentType = payment.value().toLowerCase();
			
			PaymentGatewayMetadata metadata = payments.get(payment.value());
			
			if(metadata == null){
				metadata = new PaymentGatewayMetadata();
				payments.put(paymentType, metadata);
			}
			
			b.accept(metadata, a);
		};
		
		paymentGatewayClassList.stream().forEach((a)->{
			action.accept(a, (b,c)->{
				b.setPaymentGatewayClass(c);
			});
		});
		
		paymentClassList.stream().forEach((a)->{
			action.accept(a, (b,c)->{
				b.setPaymentEntity(c);
			});
		});
		
		paymentGatewayConfigClassList.stream().forEach((a)->{
			action.accept(a, (b,c)->{
				b.setPaymentConfigurationEntity(c);
			});
		});
		
	}
	
	private void validatePaymentGatewayMetadata(){
		payments.values().forEach((x)->{
			
			if(x.getPaymentConfigurationEntity() == null){
				throw new PaymentGatewayProviderException("payment configuration entity not found: " + x.getId());
			}
			
			if(x.getPaymentGatewayClass() == null){
				throw new PaymentGatewayProviderException("payment gateway not found: " + x.getId());
			}
			
			if(x.getPaymentEntity() == null){
				throw new PaymentGatewayProviderException("payment entity not found: " + x.getId());
			}
			
		});
	}
	
	private List<Class<?>> getPaymentGatewayClassList(){
		return this.getClassByType(PaymentGateway.class);
	}

	private List<Class<?>> getPaymentClassList(){
		return this.getClassByType(Payment.class);
	}
	
	private List<Class<?>> getPaymentGatewayConfigClassList(){
		return this.getClassByType(PaymentGatewayConfig.class);
	}
	
	@SuppressWarnings("unchecked")
	private List<Class<?>> getClassByType(Class<?> type){
		AssignableTypeFilter filter = new AssignableTypeFilter();
		
		filter.setExpression(Arrays
				.asList(type.getName()));

		DefaultScanner s = new DefaultScanner();
		s.setBasePackage(new String[] { "org.brandao.brutos.paymentgateway" });
		s.addIncludeFilter(filter);
		s.scan();
		return s.getClassList();
	}
	
}
