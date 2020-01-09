package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;

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
        return Optional.ofNullable(Storage.buckets
                .stream()
                .filter(bucket -> bucket.getBucketId().equals(bucketId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        "Can't find bucket with id " + bucketId)));
    }

    @Override
    public Bucket update(Bucket bucket) {
        Bucket updatedBucked = get(bucket.getBucketId()).get();
        updatedBucked.setBucketId(bucket.getBucketId());
        updatedBucked.setUserId(bucket.getUserId());
        updatedBucked.setItems(bucket.getItems());
        return bucket;
    }

    @Override
    public boolean deleteById(Long bucketId) {
        Optional<Bucket> bucket = Optional.ofNullable(Storage.buckets.stream()
                .filter(bucket1 -> bucket1.getBucketId().equals(bucketId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        "Can't find bucket with id " + bucketId)));
        return Storage.buckets.remove(bucket.get());
    }

    @Override
    public boolean delete(Bucket bucket) {
        return Storage.buckets.remove(bucket.getBucketId());
    }

    @Override
    public List<Bucket> getAll() {
        return Storage.buckets;
    }
}
