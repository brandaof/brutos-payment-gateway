package org.brandao.brutos.paymentgateway.entityaccess;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

public class EntityContext implements Extension{

	private static BeanManager beanManager;
	
	@SuppressWarnings("unchecked")
	public static <T> T getEntity(Class<T> type){
		
		Set<Bean<?>> beans = beanManager.getBeans(type);
		
		if(beans == null || beans.isEmpty()){
			throw new IllegalStateException("entidade não encontrada: " + type);
		}
		
		Bean<T> bean = (Bean<T>)beans.iterator().next();
		
        CreationalContext<T> ctx = beanManager.createCreationalContext(bean);
        Object obj = beanManager.getReference(bean, type, ctx);
        return (T) obj;
	}

	protected void beforeBeanDiscovery(@Observes BeforeBeanDiscovery beforeBeanDiscovery, 
			BeanManager aBeanManager) {
		beanManager = aBeanManager;
	}
	
}
