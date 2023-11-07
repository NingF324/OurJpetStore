package org.ningf.ourpetstore.persistence.impl;

import org.ningf.ourpetstore.domain.LineItem;
import org.ningf.ourpetstore.persistence.DBUtil;
import org.ningf.ourpetstore.persistence.LineItemDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/7 16:35
 */
public class LineItemDaoImpl implements LineItemDao {
    private static final String GET_LINE_ITEMS_BY_ORDER_ID =
            "SELECT ORDERID,LINENUM AS lineNumber,ITEMID,QUANTITY,UNITPRICE FROM LINEITEM WHERE ORDERID = ?";
    private static final String INSERT_LINEITEM =
            "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE) VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List result = new ArrayList();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_LINE_ITEMS_BY_ORDER_ID);
            preparedStatement.setInt(1,orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                LineItem lineItem = new LineItem();
                lineItem.setOrderId(resultSet.getInt("ORDERID"));
                lineItem.setLineNumber(resultSet.getInt("lineNumber"));
                lineItem.setItemId(resultSet.getString("ITEMID"));
                lineItem.setQuantity(resultSet.getInt("QUANTITY"));
                lineItem.setUnitPrice(resultSet.getBigDecimal("UNITPRICE"));
                result.add(lineItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void insertLineItem(LineItem lineItem) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LINEITEM);
            preparedStatement.setInt(1,lineItem.getOrderId());
            preparedStatement.setInt(2,lineItem.getLineNumber());
            preparedStatement.setString(3,lineItem.getItemId());
            preparedStatement.setInt(4,lineItem.getQuantity());
            preparedStatement.setBigDecimal(5,lineItem.getUnitPrice());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
