package alcachofa;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
    T getById(ID id);
    List<T> getAll();
    void saveOrUpdate(T entity);
    void delete(T entity);
    void insert(T entity);
}
