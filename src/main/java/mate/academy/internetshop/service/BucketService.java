package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

public interface BucketService extends GenericService<Bucket, Long> {

    void addItem(Bucket bucket, Optional<Item> item);

    void deleteItem(Bucket bucket, Item item);

    void clear(Bucket bucket);

    List<Item> getAllItems(Long id);

    List<Bucket> getAll();
}
