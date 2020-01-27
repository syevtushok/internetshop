package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

@Dao
public class BucketDaoImpl implements BucketDao {
    private static Long generatedBucketId = 0L;

    @Override
    public Bucket create(Bucket bucket) {
        bucket.setBucketId(generatedBucketId++);
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long bucketId) {
        return Storage.buckets
                .stream()
                .filter(bucket -> bucket.getBucketId().equals(bucketId))
                .findFirst();
    }

    @Override
    public Bucket update(Bucket bucket) {
        Bucket updatedBucked = get(bucket.getBucketId()).orElseThrow();
        updatedBucked.setBucketId(bucket.getBucketId());
        updatedBucked.setUserId(bucket.getUserId());
        updatedBucked.setItems(bucket.getItems());
        return bucket;
    }

    @Override
    public boolean deleteById(Long bucketId) {
        return Storage.buckets.removeIf(bucket -> bucket.getBucketId().equals(bucketId));
    }

    @Override
    public boolean delete(Bucket bucket) {
        return Storage.buckets.remove(bucket);
    }

    @Override
    public Optional<Bucket> getByUserId(Long userId) {
        return Storage.buckets.stream()
                .filter(bucket1 -> bucket1.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) {

    }

    @Override
    public void clear(Bucket bucket) {

    }

    @Override
    public List<Item> getAllItems(Long id) {
        return null;
    }

    @Override
    public List<Bucket> getAll() {
        return Storage.buckets;
    }
}
