package org.ningf.ourpetstore.persistence.impl;

import org.ningf.ourpetstore.domain.Order;
import org.ningf.ourpetstore.persistence.DBUtil;
import org.ningf.ourpetstore.persistence.OrderDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/7 16:27
 */
public class OrderDaoImpl implements OrderDao {
    private static final String GET_ORDER =
            "select BILLADDR1 AS billAddress1,BILLADDR2 AS billAddress2,BILLCITY,BILLCOUNTRY,BILLSTATE,BILLTOFIRSTNAME,BILLTOLASTNAME," +
                    "BILLZIP,SHIPADDR1 AS shipAddress1,SHIPADDR2 AS shipAddress2,SHIPCITY,SHIPCOUNTRY,SHIPSTATE,SHIPTOFIRSTNAME," +
                    "SHIPTOLASTNAME,SHIPZIP,CARDTYPE,COURIER,CREDITCARD,EXPRDATE AS expiryDate,LOCALE,ORDERDATE,ORDERS.ORDERID," +
                    "TOTALPRICE,USERID AS username,STATUS " +
                    "FROM ORDERS, ORDERSTATUS " +
                    "WHERE ORDERS.ORDERID = ? AND ORDERS.ORDERID = ORDERSTATUS.ORDERID";
    private static final String GET_ORDER_BY_USERNAME =
            "select BILLADDR1 AS billAddress1,BILLADDR2 AS billAddress2,BILLCITY,BILLCOUNTRY,BILLSTATE,BILLTOFIRSTNAME,BILLTOLASTNAME," +
                    "BILLZIP,SHIPADDR1 AS shipAddress1,SHIPADDR2 AS shipAddress2,SHIPCITY,SHIPCOUNTRY,SHIPSTATE,SHIPTOFIRSTNAME," +
                    "SHIPTOLASTNAME,SHIPZIP,CARDTYPE,COURIER,CREDITCARD,EXPRDATE AS expiryDate,LOCALE,ORDERDATE,ORDERS.ORDERID," +
                    "TOTALPRICE,USERID AS username,STATUS " +
                    "FROM ORDERS, ORDERSTATUS " +
                    "WHERE ORDERS.USERID = ? AND ORDERS.ORDERID = ORDERSTATUS.ORDERID ORDER BY ORDERDATE";
    private static final String INSERT_ORDER =
            "INSERT INTO ORDERS (ORDERID, USERID, ORDERDATE, SHIPADDR1, SHIPADDR2, SHIPCITY, SHIPSTATE," +
                    "SHIPZIP, SHIPCOUNTRY, BILLADDR1, BILLADDR2, BILLCITY, BILLSTATE, BILLZIP, BILLCOUNTRY," +
                    "COURIER, TOTALPRICE, BILLTOFIRSTNAME, BILLTOLASTNAME, SHIPTOFIRSTNAME, SHIPTOLASTNAME," +
                    "CREDITCARD, EXPRDATE, CARDTYPE, LOCALE) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_ORDER_STATUS =
            "INSERT INTO ORDERSTATUS (ORDERID, LINENUM, TIMESTAMP, STATUS) VALUES (?, ?, ?, ?)";

    @Override
    public List<Order> getOrdersByUsername(String username) {
        List<Order> result = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_USERNAME);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setBillAddress1(resultSet.getString("billAddress1"));
                order.setBillAddress2(resultSet.getString("billAddress2"));
                order.setBillCity(resultSet.getString("BILLCITY"));
                order.setBillCountry(resultSet.getString("BILLCOUNTRY"));
                order.setBillState(resultSet.getString("BILLSTATE"));
                order.setBillToFirstName(resultSet.getString("BILLTOFIRSTNAME"));
                order.setBillToLastName(resultSet.getString("BILLTOLASTNAME"));
                order.setBillZip(resultSet.getString("BILLZIP"));

                order.setShipAddress1(resultSet.getString("shipAddress1"));
                order.setShipAddress2(resultSet.getString("shipAddress2"));
                order.setShipCity(resultSet.getString("SHIPCITY"));
                order.setShipCountry(resultSet.getString("SHIPCOUNTRY"));
                order.setShipState(resultSet.getString("SHIPSTATE"));
                order.setShipToFirstName(resultSet.getString("SHIPTOFIRSTNAME"));
                order.setShipToLastName(resultSet.getString("SHIPTOLASTNAME"));
                order.setShipZip(resultSet.getString("SHIPZIP"));

                order.setCardType(resultSet.getString("CARDTYPE"));
                order.setCourier(resultSet.getString("COURIER"));
                order.setCreditCard(resultSet.getString("CREDITCARD"));
                order.setExpiryDate(resultSet.getString("expiryDate"));
                order.setLocale(resultSet.getString("LOCALE"));
                order.setOrderDate(resultSet.getTimestamp("ORDERDATE"));
                order.setOrderId(resultSet.getInt("ORDERID"));
                order.setTotalPrice(resultSet.getBigDecimal("TOTALPRICE"));
                order.setUsername(resultSet.getString("username"));
                order.setStatus(resultSet.getString("STATUS"));
                result.add(order);
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
    public Order getOrder(int orderId) {
        Order result = new Order();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER);
            preparedStatement.setInt(1,orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result.setBillAddress1(resultSet.getString("billAddress1"));
                result.setBillAddress2(resultSet.getString("billAddress2"));
                result.setBillCity(resultSet.getString("BILLCITY"));
                result.setBillCountry(resultSet.getString("BILLCOUNTRY"));
                result.setBillState(resultSet.getString("BILLSTATE"));
                result.setBillToFirstName(resultSet.getString("BILLTOFIRSTNAME"));
                result.setBillToLastName(resultSet.getString("BILLTOLASTNAME"));
                result.setBillZip(resultSet.getString("BILLZIP"));

                result.setShipAddress1(resultSet.getString("shipAddress1"));
                result.setShipAddress2(resultSet.getString("shipAddress2"));
                result.setShipCity(resultSet.getString("SHIPCITY"));
                result.setShipCountry(resultSet.getString("SHIPCOUNTRY"));
                result.setShipState(resultSet.getString("SHIPSTATE"));
                result.setShipToFirstName(resultSet.getString("SHIPTOFIRSTNAME"));
                result.setShipToLastName(resultSet.getString("SHIPTOLASTNAME"));
                result.setShipZip(resultSet.getString("SHIPZIP"));

                result.setCardType(resultSet.getString("CARDTYPE"));
                result.setCourier(resultSet.getString("COURIER"));
                result.setCreditCard(resultSet.getString("CREDITCARD"));
                result.setExpiryDate(resultSet.getString("expiryDate"));
                result.setLocale(resultSet.getString("LOCALE"));
                result.setOrderDate(resultSet.getTimestamp("ORDERDATE"));
                result.setOrderId(resultSet.getInt("ORDERID"));
                result.setTotalPrice(resultSet.getBigDecimal("TOTALPRICE"));
                result.setUsername(resultSet.getString("username"));
                result.setStatus(resultSet.getString("STATUS"));
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
    public void insertOrder(Order order) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
            preparedStatement.setInt(1,order.getOrderId());
            preparedStatement.setString(2,order.getUsername());
            preparedStatement.setTimestamp(3,new java.sql.Timestamp(order.getOrderDate().getTime()));

            preparedStatement.setString(4,order.getShipAddress1());
            preparedStatement.setString(5,order.getShipAddress2());
            preparedStatement.setString(6,order.getShipCity());
            preparedStatement.setString(7,order.getShipState());
            preparedStatement.setString(8,order.getShipZip());
            preparedStatement.setString(9,order.getShipCountry());

            preparedStatement.setString(10,order.getBillAddress1());
            preparedStatement.setString(11,order.getBillAddress2());
            preparedStatement.setString(12,order.getBillCity());
            preparedStatement.setString(13,order.getBillState());
            preparedStatement.setString(14,order.getBillZip());
            preparedStatement.setString(15,order.getBillCountry());

            preparedStatement.setString(16,order.getCourier());
            preparedStatement.setBigDecimal(17,order.getTotalPrice());

            preparedStatement.setString(18,order.getBillToFirstName());
            preparedStatement.setString(19,order.getBillToLastName());
            preparedStatement.setString(20,order.getShipToFirstName());
            preparedStatement.setString(21,order.getShipToLastName());

            preparedStatement.setString(22,order.getCreditCard());
            preparedStatement.setString(23,order.getExpiryDate());
            preparedStatement.setString(24,order.getCardType());
            preparedStatement.setString(25,order.getLocale());
            preparedStatement.execute();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertOrderStatus(Order order) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_STATUS);
            preparedStatement.setInt(1,order.getOrderId());
            preparedStatement.setInt(2,order.getOrderId());
            preparedStatement.setDate(3,new java.sql.Date(order.getOrderDate().getTime()));
            preparedStatement.setString(4,order.getStatus());
            preparedStatement.execute();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
