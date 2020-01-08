package mate.academy.internetshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long userId;
    private List<Item> items;
    private Long bucketId;
    private BigDecimal price;

    public Bucket() {
        items = new ArrayList<>();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Bucket{"
                + "user=" + userId
                + ", items=" + items
                + ", bucketId=" + bucketId
                + ", sumOfMoney=" + price
                + '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }
}
