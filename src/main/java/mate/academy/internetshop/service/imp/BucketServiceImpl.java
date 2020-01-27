package mate.academy.internetshop.service.imp;

import java.util.List;

import mate.academy.internetshop.dao.BucketDao;
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
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long bucketId) {
        return bucketDao.get(bucketId).orElseThrow();
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean delete(Bucket bucket) {
        return bucketDao.delete(bucket);
    }

    @Override
    public boolean deleteById(Long bucketId) {
        return bucketDao.deleteById(bucketId);
    }

    @Override
    public void addItem(Bucket bucket, Item item) {
        bucket.getItems().add(item);
        update(bucket);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) {
        bucketDao.deleteItem(bucket, item);
    }

    @Override
    public void clear(Bucket bucket) {
        bucketDao.clear(bucket);
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        return bucketDao.getAllItems(bucketId);
    }

    @Override
    public List<Bucket> getAll() {
        return bucketDao.getAll();
    }

    @Override
    public Bucket getByUserId(Long userId) {
        return bucketDao.getByUserId(userId).orElseThrow();
    }
}
