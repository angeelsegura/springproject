package alcachofa;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID>{
	
	SessionFactory sessionFactory;
	
	public GenericDaoImpl() {
		this.sessionFactory = Utils.getSessionFactory();
	}
	

	@Override
	public T getById(ID id) {
		return null;
	}

	@Override
	public List<T> getAll() {

		return null;
	}

	@Override
	public void delete(T entity) {
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			session.remove(entity);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void saveOrUpdate(T entity) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.merge(entity);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	@Override
	public void insert(T entity) {
	    Session session = sessionFactory.getCurrentSession();
	    try {
	        session.beginTransaction();
	        session.persist(entity);
	        session.getTransaction().commit();

	    } catch (HibernateException e) {
	        e.printStackTrace();
	        if (session != null && session.getTransaction() != null) {
	            System.out.println("\n.......Transaction Is Being Rolled Back.......");
	            session.getTransaction().rollback();
	        }
	    }
	}

}
