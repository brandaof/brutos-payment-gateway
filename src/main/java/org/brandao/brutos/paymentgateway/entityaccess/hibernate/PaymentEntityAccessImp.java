package org.brandao.brutos.paymentgateway.entityaccess.hibernate;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.brandao.brutos.paymentgateway.entity.Payment;
import org.brandao.brutos.paymentgateway.entityaccess.EntityAccessException;
import org.brandao.brutos.paymentgateway.entityaccess.PaymentEntityAccess;
import org.hibernate.Session;

@RequestScoped
public class PaymentEntityAccessImp 
	implements PaymentEntityAccess{
	
	private Session session;
	
	public PaymentEntityAccessImp(){
	}
	
	@Inject
	public PaymentEntityAccessImp(Session session){
		this.session = session;
	}
		
	@Override
	public void save(Payment entity) throws EntityAccessException{
		try{
			this.session.save(entity);
		}
		catch(Throwable e){
			throw new EntityAccessException(e);
		}
	}

	@Override
	public void update(Payment entity) throws EntityAccessException{
		try{
			entity = (Payment) this.session.merge(entity);
			this.session.update(entity);
		}
		catch(Throwable e){
			throw new EntityAccessException(e);
		}
	}

	@Override
	public void delete(int id) throws EntityAccessException{
		try{
			Payment user = this.findById(id);
			if(user != null){
				this.session.delete(user);
			}
		}
		catch(Throwable e){
			throw new EntityAccessException(e);
		}
	}

	@Override
	public Payment findById(int id) throws EntityAccessException{
		try{
			return (Payment)this.session.get(Payment.class, id);
		}
		catch(Throwable e){
			throw new EntityAccessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> findAll() throws EntityAccessException{
		try{
			return (List<Payment>)this.session.createCriteria(Payment.class).list();
		}
		catch(Throwable e){
			e.printStackTrace();
			throw new EntityAccessException(e);
		}
	}

	@Override
	public void flush() throws EntityAccessException{
		try{
			this.session.flush();
		}
		catch(Throwable e){
			throw new EntityAccessException(e);
		}
	}

}
