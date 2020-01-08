package mate.academy.internetshop.service;

import java.util.Optional;

public interface GenericService<T, N> {
    T create(T entity);

    Optional<T> get(N entityN);

    T update(T entity);

    boolean delete(T entity);

    boolean deleteById(N entityN);
}
