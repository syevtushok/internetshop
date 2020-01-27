package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

public interface BucketDao extends GenericDao<Bucket, Long> {
    Optional<Bucket> getByUserId(Long userId);

    void deleteItem(Bucket bucket, Item item);

    void clear(Bucket bucket);

    List<Item> getAllItems(Long id);

    List<Bucket> getAll();
}
