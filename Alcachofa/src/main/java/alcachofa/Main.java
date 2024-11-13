package alcachofa;

import java.util.HashSet;
import java.util.Set;

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
	
	/*public static synchronized SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			// exception handling omitted for brevityaa
			// serviceRegistry = new
			// StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			// sessionFactory = new
			// MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return sessionFactory;
	}*/
	
	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		
		/*try {
			session = getSessionFactory().openSession();
            session.beginTransaction();

            Carta carta1 = new Carta();
            carta1.setTipo(TipoCarta.CARXOFA);
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
        }*/
		
		
		
		Jugador jugador = new Jugador("Jugador1");
		Set<Jugador> jugadores = new HashSet<>();
		jugadores.add(jugador);
		
		Partida partida = new Partida(jugadores);
		partida.generarBaraja();
		
		
		partida.mezclarBaraja();
		
		jugador.inicializarBaraja();
		jugador.llenarMano();
		jugador.mostrarMano();
		
		System.out.println("-------------------");
		
		
		
		partida.generarTienda();
		partida.mostrarTienda();
		
		partida.escogerVerdura(4, 0);
		
		jugador.descartarMano();
		jugador.mostrarMano();
		System.out.println("-------------------");
		jugador.mostrarDescartes();
		
		
		  try {
	            // Crear un nuevo jugador
	            Jugador nuevoJugador = new Jugador("nombre");
	            nuevoJugador.setNumeroPartides(0);
	            nuevoJugador.setGuanyar(false);

	            JugadorDAO jugadorDAO = new JugadorDAO();
	            
	            CartaDAO cartaDAO = new CartaDAO();
	            
	            
	            jugadorDAO.insertJugador(nuevoJugador);
	            System.out.println("Jugador insertado con ID: " + nuevoJugador.getIdJugador());
	            
	           
	            cartaDAO.insertCarta( partida.getCartas().get(0));
	            System.out.println("Jugador eliminado con ID: " + nuevoJugador.getIdJugador());

	            
	            

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    
	}

}
