package mate.academy.internetshop.service;

public interface GenericService<T, N> {
    T create(T entity);

    T get(N entityN);

    T update(T entity);

    boolean delete(T entity);

    boolean deleteById(N entityN);
}
