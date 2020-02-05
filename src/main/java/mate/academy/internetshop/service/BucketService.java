package mate.academy.internetshop.service;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

public interface BucketService extends GenericService<Bucket, Long> {

    boolean delete(Bucket bucket) throws DataProcessingException;

    boolean deleteById(Long bucketId) throws DataProcessingException;

    void addItem(Bucket bucket, Item item) throws DataProcessingException;

    void deleteItem(Bucket bucket, Item item) throws DataProcessingException;

    void clear(Bucket bucket) throws DataProcessingException;

    Bucket getByUserId(Long userId) throws DataProcessingException;
}
