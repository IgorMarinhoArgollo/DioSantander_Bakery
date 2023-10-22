package dioBootcamp.bakery.service.interfaces;

import java.util.List;

public interface CrudService<T> {
    List<T> findAll();
    T findById(Long id);
    T create(T entity);
    Boolean delete(Long id);
    T update(T entity);
}
