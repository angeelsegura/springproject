package alcachofa;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;



public class Utils {
	
	static Session session;
	static SessionFactory sessionFactory;

	public static synchronized SessionFactory getSessionFactory() {
	    if ( sessionFactory == null ) {

	    	sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	                
	    }
	    return sessionFactory;
	}

}

