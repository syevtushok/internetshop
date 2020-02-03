package mate.academy.internetshop.service;

import java.util.List;

import mate.academy.internetshop.exceptions.DataProcessingException;

public interface GenericService<T, N> {
    T create(T entity) throws DataProcessingException;

    T get(N entityN) throws DataProcessingException;

    T update(T entity) throws DataProcessingException;

    boolean delete(T entity) throws DataProcessingException;

    boolean deleteById(N entityN) throws DataProcessingException;

    List<T> getAll() throws DataProcessingException;
}
