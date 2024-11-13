package alcachofa;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

 
	public class JugadorDAO extends GenericDao<Jugador, Integer> {

	    public JugadorDAO() {
	        super(); // Llama al constructor de GenericDao para inicializar sessionFactory
	    }

	    // Este método aprovecha el `saveOrUpdate` de `GenericDao` para insertar el jugador
	    public void insertJugador(Jugador jugador) {
	        saveOrUpdate(jugador); // Usa el método heredado
	    }
	    
	    
	    // Método para eliminar un jugador usando el método `delete` de `GenericDao`
	    public void eliminarJugador(Integer idJugador) {
	        deleteById(idJugador); // Usa el método heredado
	    }
	    
	    
	    public Jugador getJugadorPorNombre(String nombre) {
	        Session session = sessionFactory.getCurrentSession();
	        Jugador jugador = null;

	        try {
	            session.beginTransaction();

	            // Consulta HQL para obtener el jugador por nombre
	            String hql = "FROM Jugador WHERE nombre = :nombre";
	            Query<Jugador> query = session.createQuery(hql, Jugador.class);
	            query.setParameter("nombre", nombre);

	            // Obtener el primer resultado, ya que se asume que los nombres son únicos
	            jugador = query.uniqueResult();
	            session.getTransaction().commit();

	        } catch (HibernateException e) {
	            e.printStackTrace();
	            if (session.getTransaction() != null) {
	                System.out.println("\n.......Transaction Is Being Rolled Back.......");
	                session.getTransaction().rollback();
	            }
	        }
	        return jugador;
	    }
	}
