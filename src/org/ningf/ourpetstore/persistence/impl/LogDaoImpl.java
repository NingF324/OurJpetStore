package org.ningf.ourpetstore.persistence.impl;

import org.ningf.ourpetstore.persistence.DBUtil;
import org.ningf.ourpetstore.persistence.LogDao;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/8 8:00
 */
public class LogDaoImpl implements LogDao {

    private static final String insertLogString = "INSERT INTO LOG (LOGUSERID, LOGINFO) VALUES (?, ?)";

    @Override
    public void insertLog(String username, String logInfo) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertLogString);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, logInfo);

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}