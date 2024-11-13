package alcachofa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Utils {
	static Session session;
	static SessionFactory sessionFactory;
	
	
	public static synchronized SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
		
			sessionFactory = new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return sessionFactory;
	}
	
}
