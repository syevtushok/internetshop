package mate.academy.internetshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private User user;
    private List<Item> items;
    private Long bucketId;
    private BigDecimal sumOfMoney;

    public Bucket() {
        items = new ArrayList<>();
    }

    public BigDecimal getSumOfMoney() {
        return sumOfMoney;
    }

    public void setSumOfMoney(BigDecimal sumOfMoney) {
        this.sumOfMoney = sumOfMoney;
    }

    @Override
    public String toString() {
        return "Bucket{"
                + "user=" + user
                + ", items=" + items
                + ", bucketId=" + bucketId
                + ", sumOfMoney=" + sumOfMoney
                + '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
