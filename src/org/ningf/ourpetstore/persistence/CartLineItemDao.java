package org.ningf.ourpetstore.persistence;

import org.ningf.ourpetstore.domain.CartLineItem;
import org.ningf.ourpetstore.domain.LineItem;

import java.util.List;

public interface CartLineItemDao {
    List<CartLineItem> getCartLineItemByUserId(String userId);

    void insertCartLineItem(CartLineItem cartLineItem);
    void updateCartLineItem(CartLineItem cartLineItem);
    int getQuantityByItemIdAndUserId(String userId,String itemId);
    void removeCartLineItem(String userId, String itemId);
    boolean isContainUserIdAndItemId(String userId,String itemId);
}
