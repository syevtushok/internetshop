package mate.academy.internetshop.service;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.model.Item;

public interface ItemService extends GenericService<Item, Long> {
    boolean deleteById(Long itemId) throws DataProcessingException;

    boolean delete(Item item) throws DataProcessingException;
}
