package org.ningf.ourpetstore.persistence.impl;

import org.ningf.ourpetstore.domain.Product;
import org.ningf.ourpetstore.persistence.DBUtil;
import org.ningf.ourpetstore.persistence.ProductDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/5 14:50
 */
public class ProductDaoImpl implements ProductDao {
    private static final String GET_PRODUCT =
            "SELECT PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId FROM PRODUCT WHERE PRODUCTID =?";
    private static final String GET_PRODUCT_LIST =
            "SELECT PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId FROM PRODUCT WHERE CATEGORY = ?";
    private static final String SEARCH_PRODUCT_LIST =
            "SELECT PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId FROM PRODUCT WHERE lower(name) like ?";

    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> productList=new ArrayList<>();
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedstatement =connection.prepareStatement(GET_PRODUCT_LIST);
            preparedstatement.setString(1, categoryId);
            ResultSet resultSet= preparedstatement.executeQuery();
            while(resultSet.next()){
                Product product=new Product();
                product.setCategoryId(resultSet.getString("categoryId"));
                product.setProductId(resultSet.getString("PRODUCTID"));
                product.setDescription(resultSet.getString("description"));
                product.setName(resultSet.getString("NAME"));
                productList.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedstatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product getProduct(String productId) {
        Product product=null;
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement =connection.prepareStatement(GET_PRODUCT);
            preparedStatement.setString(1, productId);
            ResultSet resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                product=new Product();
                product.setCategoryId(resultSet.getString("categoryId"));
                product.setProductId(resultSet.getString("PRODUCTID"));
                product.setDescription(resultSet.getString("description"));
                product.setName(resultSet.getString("NAME"));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> productList = new ArrayList<Product>();

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection
                    .prepareStatement(SEARCH_PRODUCT_LIST);
            pStatement.setString(1, keywords);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                productList.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }
}
