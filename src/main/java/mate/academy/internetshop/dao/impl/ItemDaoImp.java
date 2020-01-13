package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoImp implements ItemDao {
    private static Long generatedItemId = 0L;

    @Override
    public Item create(Item item) {
        item.setItemId(generatedItemId++);
        Storage.items.add(item);
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        return Optional.ofNullable(Storage.items
                .stream()
                .filter(item -> item.getItemId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find item with id: " + id)));
    }

    @Override
    public Item update(Item item) {
        Item updatedItem = get(item.getItemId()).orElseThrow();
        updatedItem.setName(item.getName());
        updatedItem.setPrice(item.getPrice());
        return item;
    }

    @Override
    public boolean deleteById(Long id) {
        Item deletedItem = Storage.items.stream()
                .filter(item -> item.getItemId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        "Can't find item with id " + id));
        return Storage.items.remove(deletedItem);
    }

    @Override
    public boolean delete(Item item) {
        return Storage.items.remove(item);
    }

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }
}
