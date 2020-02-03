package mate.academy.internetshop.service.imp;

import java.math.BigDecimal;
import java.util.List;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private static BucketDao bucketDao;

    @Override
    public Bucket create(Bucket bucket) throws DataProcessingException {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long bucketId) throws DataProcessingException {
        return bucketDao.get(bucketId).orElseThrow();
    }

    @Override
    public Bucket update(Bucket bucket) throws DataProcessingException {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean delete(Bucket bucket) throws DataProcessingException {
        return bucketDao.delete(bucket);
    }

    @Override
    public boolean deleteById(Long bucketId) throws DataProcessingException {
        return bucketDao.deleteById(bucketId);
    }

    @Override
    public void addItem(Bucket bucket, Item item) throws DataProcessingException {
        bucket.getItems().add(item);
        update(bucket);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) throws DataProcessingException {
        bucketDao.deleteItem(bucket, item);
    }

    @Override
    public void clear(Bucket bucket) throws DataProcessingException {
        bucketDao.clear(bucket);
    }

    @Override
    public List<Bucket> getAll() throws DataProcessingException {
        return bucketDao.getAll();
    }

    @Override
    public Bucket getByUserId(Long userId) throws DataProcessingException {
        Bucket bucket = bucketDao.getByUserId(userId).orElseThrow();
        BigDecimal totalPrice = new BigDecimal(0);
        for (Item item : bucket.getItems()) {
            totalPrice = totalPrice.add(item.getPrice());
        }
        bucket.setPrice(totalPrice);
        return bucket;
    }
}
