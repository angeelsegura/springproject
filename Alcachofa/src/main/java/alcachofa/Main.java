package alcachofa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import alcachofa.Carta.TipoCarta;
import alcachofa.Jugar.EstatJugar;

public class Main {
	static Session session;
	static SessionFactory sessionFactory;
	static ServiceRegistry serviceRegistry;
	
	public static synchronized SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			// exception handling omitted for brevityaa
			// serviceRegistry = new
			// StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			// sessionFactory = new
			// MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return sessionFactory;
	}
	
	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = getSessionFactory().openSession();
            session.beginTransaction();

            Carta carta1 = new Carta();
            carta1.setNom(TipoCarta.CARXOFA);
            carta1.setDescripcio("No fa cap acció");

            Jugador jugador1 = new Jugador();
            jugador1.setAlias("Jugador1");

            Partida partida1 = new Partida();
            partida1.setNumJugadors(1);

            Jugar jugar1 = new Jugar();
            jugar1.setEstat(EstatJugar.MA_JUGADOR);
            jugar1.setJugador(jugador1);
            jugar1.setPartida(partida1);

            jugador1.getCartas().add(carta1);
            partida1.getJugadores().add(jugador1);
            partida1.getCartas().add(carta1);

            session.persist(carta1);
            session.persist(jugador1);
            session.persist(partida1);
            session.persist(jugar1);

            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    
	}

}
