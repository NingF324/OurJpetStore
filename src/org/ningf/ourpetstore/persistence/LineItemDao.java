package org.ningf.ourpetstore.persistence;

import org.ningf.ourpetstore.domain.LineItem;

import java.util.List;

public interface LineItemDao {
    List<LineItem> getLineItemsByOrderId(int orderId);

    void insertLineItem(LineItem lineItem);
}
