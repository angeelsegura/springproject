package alcachofa;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CartaDAO extends GenericDao<Carta, Integer> {

    public CartaDAO() {
        super(); // Llama al constructor de GenericDao para inicializar sessionFactory
    }

    // Método para insertar una carta usando el método genérico saveOrUpdate de GenericDao
    public void insertCarta(Carta carta) {
        saveOrUpdate(carta); // Utiliza el método heredado para insertar o actualizar
    }

    // Método para eliminar una carta por su ID usando el método genérico delete de GenericDao
    public void eliminarCarta(Integer idCarta) {
        deleteById(idCarta); // Utiliza el método heredado para eliminar
    }
    
    
    public void agregarTextoADescripcionPorTipo(Carta.TipoCarta tipoCarta, String textoExtra) {
        Session session = sessionFactory.getCurrentSession();
        
        try {
            session.beginTransaction();

            // Consulta HQL para obtener la carta por tipo
            String hql = "FROM Carta WHERE tipo = :tipoCarta";
            Query<Carta> query = session.createQuery(hql, Carta.class);
            query.setParameter("tipoCarta", tipoCarta);

            // Obtener la primera carta encontrada con el tipo especificado
            Carta carta = query.uniqueResult();
            
            if (carta != null) {
                // Agregar el texto extra a la descripción actual
                carta.setDescripcio(carta.getDescripcio() + " " + textoExtra);
                
                // Guardar los cambios
                session.merge(carta);
            } else {
                System.out.println("No se encontró una carta con el tipo " + tipoCarta);
            }
            
            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            if (session.getTransaction() != null) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                session.getTransaction().rollback();
            }
        }
    }
}