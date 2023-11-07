package org.ningf.ourpetstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/7 19:30
 */
public class CartLineItem implements Serializable {
    private String userId;
    private int quantity;
    private String itemId;
    private BigDecimal unitPrice;
    private BigDecimal listPrice;
    private String productId;
    private String description;

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Item item;

    public CartLineItem() {
    }

    public CartLineItem(String userId, int lineNumber, int quantity, String itemId, BigDecimal unitPrice, Item item) {
        this.userId = userId;
        this.quantity = quantity;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.item = item;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
