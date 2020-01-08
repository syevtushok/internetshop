package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, N> {
    T create(T entity);

    Optional<T> get(N entityN);

    T update(T entity);

    boolean deleteById(N entityN);

    boolean delete(T entity);

    List<T> getAll();
}
