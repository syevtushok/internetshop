package mate.academy.internetshop.service.imp;

import java.util.List;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
    @Inject
    private static ItemDao itemDao;

    @Override
    public Item create(Item item) throws DataProcessingException {
        return itemDao.create(item);
    }

    @Override
    public Item get(Long id) throws DataProcessingException {
        return itemDao.get(id).orElseThrow();
    }

    @Override
    public Item update(Item item) throws DataProcessingException {
        return itemDao.update(item);
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        return itemDao.deleteById(id);
    }

    @Override
    public boolean delete(Item item) throws DataProcessingException {
        return itemDao.delete(item);
    }

    @Override
    public List<Item> getAll() throws DataProcessingException {
        return itemDao.getAll();
    }
}
