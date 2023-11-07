package org.ningf.ourpetstore.service;

import org.ningf.ourpetstore.domain.CartLineItem;
import org.ningf.ourpetstore.persistence.CartLineItemDao;
import org.ningf.ourpetstore.persistence.impl.CartLineItemDaoImpl;

import java.util.List;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/7 19:55
 */
public class CartService {
    private CartLineItemDao cartLineItemDao;

    public CartService() {
        cartLineItemDao=new CartLineItemDaoImpl();
    }

    public List<CartLineItem> getCartLineItem(String userId){
        return cartLineItemDao.getCartLineItemByUserId(userId);
    }

    public void insertCartLineItem(CartLineItem cartLineItem){
        cartLineItemDao.insertCartLineItem(cartLineItem);
    }

    public void updateCartLineItem(CartLineItem cartLineItem){
        cartLineItemDao.updateCartLineItem(cartLineItem);
    }

    public int getQuantityByItemIdAndUserId(String userId,String itemId){
        return cartLineItemDao.getQuantityByItemIdAndUserId(userId,itemId);
    }

    public void removeCartLineItem(String userId, String itemId){
        cartLineItemDao.removeCartLineItem(userId, itemId);
    }
}
