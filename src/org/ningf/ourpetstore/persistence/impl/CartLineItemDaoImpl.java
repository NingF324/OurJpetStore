package org.ningf.ourpetstore.persistence.impl;

import org.ningf.ourpetstore.domain.CartLineItem;
import org.ningf.ourpetstore.domain.LineItem;
import org.ningf.ourpetstore.persistence.CartLineItemDao;
import org.ningf.ourpetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/7 19:29
 */
public class CartLineItemDaoImpl implements CartLineItemDao {
    private static final String GET_CART_LINE_ITEM_BY_USER_ID=
            "SELECT USERID,ITEMID,QUANTITY,UNITPRICE,PRODUCTID,DESCRIPTION,LISTPRICE FROM CARTLINEITEM WHERE USERID =?";
    private static final String INSERT_CART_LINE_ITEM =
            "INSERT INTO CARTLINEITEM (USERID, ITEMID, QUANTITY, UNITPRICE, PRODUCTID, DESCRIPTION, LISTPRICE) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_CART_LINE_ITEM =
            "UPDATE CARTLINEITEM SET USERID = ?, ITEMID = ?, QUANTITY = ?, UNITPRICE = ?, PRODUCTID = ?, DESCRIPTION = ?, LISTPRICE = ? WHERE USERID = ? AND ITEMID = ?";
    private static final String GET_QUANTITY_BY_USER_ID_AND_ITEM_ID=
            "SELECT QUANTITY FROM CARTLINEITEM WHERE USERID = ? AND ITEMID = ?";
    private static final String DELETE_CART_LINE_ITEM =
            "DELETE FROM CARTLINEITEM WHERE USERID = ? AND ITEMID = ?";
    private static final String IS_CONTAIN_USER_ID_AND_ITEM_ID=
            "SELECT QUANTITY FROM CARTLINEITEM WHERE USERID = ? AND ITEMID = ?";
    @Override
    public List<CartLineItem> getCartLineItemByUserId(String userId) {
        List result = new ArrayList();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CART_LINE_ITEM_BY_USER_ID);
            preparedStatement.setString(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                CartLineItem cartLineItem=new CartLineItem();
                cartLineItem.setUserId(resultSet.getString("USERID"));
                cartLineItem.setItemId(resultSet.getString("ITEMID"));
                cartLineItem.setQuantity(resultSet.getInt("QUANTITY"));
                cartLineItem.setUnitPrice(resultSet.getBigDecimal("UNITPRICE"));
                cartLineItem.setProductId(resultSet.getString("PRODUCTID"));
                cartLineItem.setDescription(resultSet.getString("DESCRIPTION"));
                cartLineItem.setListPrice(resultSet.getBigDecimal("LISTPRICE"));
                result.add(cartLineItem);
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
    public void insertCartLineItem(CartLineItem cartLineItem) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART_LINE_ITEM);
            preparedStatement.setString(1, cartLineItem.getUserId());
            preparedStatement.setString(2, cartLineItem.getItemId());
            preparedStatement.setInt(3, cartLineItem.getQuantity());
            preparedStatement.setBigDecimal(4, cartLineItem.getUnitPrice());
            preparedStatement.setString(5, cartLineItem.getProductId());
            preparedStatement.setString(6, cartLineItem.getDescription());
            preparedStatement.setBigDecimal(7, cartLineItem.getListPrice());
            preparedStatement.execute();

            // 关闭资源
            DBUtil.closePreparedStatement(preparedStatement); // 关闭PreparedStatement
            DBUtil.closeConnection(connection); // 关闭数据库连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCartLineItem(CartLineItem cartLineItem) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART_LINE_ITEM);
            preparedStatement.setString(1, cartLineItem.getUserId());
            preparedStatement.setString(2, cartLineItem.getItemId());
            preparedStatement.setInt(3, cartLineItem.getQuantity());
            preparedStatement.setBigDecimal(4, cartLineItem.getUnitPrice());
            preparedStatement.setString(5, cartLineItem.getProductId());
            preparedStatement.setString(6, cartLineItem.getDescription());
            preparedStatement.setBigDecimal(7, cartLineItem.getListPrice());
            preparedStatement.setString(8, cartLineItem.getUserId()); // Add WHERE condition
            preparedStatement.setString(9, cartLineItem.getItemId()); // Add WHERE condition
            preparedStatement.execute();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getQuantityByItemIdAndUserId(String userId, String itemId) {
        int result = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_QUANTITY_BY_USER_ID_AND_ITEM_ID);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = resultSet.getInt("QUANTITY");
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void removeCartLineItem(String userId, String itemId) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CART_LINE_ITEM);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, itemId);
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isContainUserIdAndItemId(String userId, String itemId) {
        boolean result=false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(IS_CONTAIN_USER_ID_AND_ITEM_ID);
            preparedStatement.setString(1,userId);
            preparedStatement.setString(2,itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result=true;
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
