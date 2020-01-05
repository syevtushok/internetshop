package mate.academy.internetshop.model;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private Long orderId;
    private List<Item> itemList;
    private Long userId;
    private BigDecimal sumOfMoney;

    public Order(List<Item> items, Long userId) {
        itemList = items;
        this.userId = userId;
    }

    public Order() {

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "Order{"
                + "orderId=" + orderId
                + ", itemList=" + itemList
                + ", userId=" + userId
                + ", sumOfMoney=" + sumOfMoney
                + '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getSumOfMoney() {
        return sumOfMoney;
    }

    public void setSumOfMoney(BigDecimal sumOfMoney) {
        this.sumOfMoney = sumOfMoney;
    }
}
