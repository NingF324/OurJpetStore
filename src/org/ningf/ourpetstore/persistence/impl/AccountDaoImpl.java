package org.ningf.ourpetstore.persistence.impl;

import org.ningf.ourpetstore.domain.Account;
import org.ningf.ourpetstore.persistence.AccountDao;
import org.ningf.ourpetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/6 21:41
 */
public class AccountDaoImpl implements AccountDao {
    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD =
            "SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS,ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2," +
            "ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE,PROFILE.LANGPREF AS languagePreference,PROFILE.FAVCATEGORY AS favouriteCategoryId," +
            "PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption,BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? AND SIGNON.PASSWORD = ? AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String GET_ACCOUNT_BY_USERNAME=
            "SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS,ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2," +
            "ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE,PROFILE.LANGPREF AS languagePreference,PROFILE.FAVCATEGORY AS favouriteCategoryId," +
            "PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption,BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";


    private static final String INSERT_ACCOUNT="INSERT INTO ACCOUNT\n" +
            "      (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID)\n" +
            "      VALUES\n" +
            "      (?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_SIGN_ON="INSERT INTO SIGNON (PASSWORD,USERNAME)\n" +
            "    VALUES (?, ?)";

    private static final String INSERT_PROFILE =
            "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID) VALUES (?, ?, ?)";

    private static final String UPDATE_ACCOUNT =
            "UPDATE ACCOUNT SET EMAIL = ?,FIRSTNAME = ?,LASTNAME = ?,STATUS = ?,ADDR1 = ?,ADDR2 = ?,CITY = ?,STATE = ?,ZIP = ?,COUNTRY = ?,PHONE = ? " +
                    "WHERE USERID = ?";

    private static final String UPDATE_PROFILE =
            "UPDATE PROFILE SET LANGPREF = ?, FAVCATEGORY = ? WHERE USERID = ?";

    private static final String UPDATE_SIGNON =
            "UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?";
    public Account getAccountByUsername(String username) {
        Account result = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = new Account();
                result.setUsername(resultSet.getString("USERNAME"));
                result.setEmail(resultSet.getString("EMAIL"));
                result.setFirstName(resultSet.getString("FIRSTNAME"));
                result.setLastName(resultSet.getString("LASTNAME"));
                result.setStatus(resultSet.getString("STATUS"));
                result.setAddress1(resultSet.getString("address1"));
                result.setAddress2(resultSet.getString("address2"));
                result.setCity(resultSet.getString("CITY"));
                result.setState(resultSet.getString("STATE"));
                result.setZip(resultSet.getString("ZIP"));
                result.setCountry(resultSet.getString("COUNTRY"));
                result.setPhone(resultSet.getString("PHONE"));
                result.setLanguagePreference(resultSet.getString("languagePreference"));
                result.setFavouriteCategoryId(resultSet.getString("favouriteCategoryId"));
                result.setListOption(resultSet.getInt("listOption")==1?true:false);
                result.setBannerOption(resultSet.getInt("bannerOption")==1?true:false);
                result.setBannerName(resultSet.getString("BANNERNAME"));
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
    public Account getAccountByUsernameAndPassword(Account account) {
        Account result = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = new Account();
                result.setUsername(resultSet.getString("USERNAME"));
                result.setEmail(resultSet.getString("EMAIL"));
                result.setFirstName(resultSet.getString("FIRSTNAME"));
                result.setLastName(resultSet.getString("LASTNAME"));
                result.setStatus(resultSet.getString("STATUS"));
                result.setAddress1(resultSet.getString("address1"));
                result.setAddress2(resultSet.getString("address2"));
                result.setCity(resultSet.getString("CITY"));
                result.setState(resultSet.getString("STATE"));
                result.setZip(resultSet.getString("ZIP"));
                result.setCountry(resultSet.getString("COUNTRY"));
                result.setPhone(resultSet.getString("PHONE"));
                result.setLanguagePreference(resultSet.getString("languagePreference"));
                result.setFavouriteCategoryId(resultSet.getString("favouriteCategoryId"));
                result.setListOption(resultSet.getInt("listOption")==1?true:false);
                result.setBannerOption(resultSet.getInt("bannerOption")==1?true:false);
                result.setBannerName(resultSet.getString("BANNERNAME"));
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
    public void insertAccount(Account account) {
        try {
            Connection connection = DBUtil.getConnection(); // 获取数据库连接
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT); // 准备SQL语句
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());

            // 执行INSERT语句
            preparedStatement.executeUpdate();

            // 关闭资源
            DBUtil.closePreparedStatement(preparedStatement); // 关闭PreparedStatement
            DBUtil.closeConnection(connection); // 关闭数据库连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void insertProfile(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROFILE);
            preparedStatement.setString(1,account.getLanguagePreference());
            preparedStatement.setString(2,account.getFavouriteCategoryId());
            preparedStatement.setString(3,account.getUsername());
            preparedStatement.execute();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(Account account) {
        try {
            Connection connection = DBUtil.getConnection(); // 获取数据库连接
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SIGN_ON); // 准备SQL语句
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getUsername());

            // 执行INSERT语句
            preparedStatement.executeUpdate();

            // 关闭资源
            DBUtil.closePreparedStatement(preparedStatement); // 关闭PreparedStatement
            DBUtil.closeConnection(connection); // 关闭数据库连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT);
            preparedStatement.setString(1,account.getEmail());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3,account.getLastName());
            preparedStatement.setString(4,account.getStatus());
            preparedStatement.setString(5,account.getAddress1());
            preparedStatement.setString(6,account.getAddress2());
            preparedStatement.setString(7,account.getCity());
            preparedStatement.setString(8,account.getState());
            preparedStatement.setString(9,account.getZip());
            preparedStatement.setString(10,account.getCountry());
            preparedStatement.setString(11,account.getPhone());
            preparedStatement.setString(12,account.getUsername());
            preparedStatement.execute();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfile(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFILE);
            preparedStatement.setString(1,account.getLanguagePreference());
            preparedStatement.setString(2,account.getFavouriteCategoryId());
            preparedStatement.setString(3,account.getUsername());
            preparedStatement.execute();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateSignon(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SIGNON);
            preparedStatement.setString(1,account.getPassword());
            preparedStatement.setString(2,account.getUsername());
            preparedStatement.execute();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
