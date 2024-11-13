package alcachofa;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class JugadorDAO extends GenericDaoImpl<Jugador, Integer>{

	SessionFactory sessionFactory;
    
    public JugadorDAO() {
        // Iniciamos el EntityManagerFactory usando la configuración de persistencia
    	this.sessionFactory = Utils.getSessionFactory();
    }

    // Método genérico para insertar un jugador en la base de datos
    public void insert(Jugador jugador) {
    	Session session = sessionFactory.getCurrentSession();
        try {
            session.getTransaction().begin();
            session.persist(jugador); // Realiza la inserción
            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            if (session.getTransaction().isActive()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                session.getTransaction().rollback();
            }
        } finally {
        	session.close();
        }
    }

}