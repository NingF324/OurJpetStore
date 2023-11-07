package org.ningf.ourpetstore.persistence.impl;

import org.ningf.ourpetstore.domain.Sequence;
import org.ningf.ourpetstore.persistence.DBUtil;
import org.ningf.ourpetstore.persistence.SequenceDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/7 16:31
 */
public class SequenceDaoImpl implements SequenceDao {
    private static final String GET_SEQUENCE =
            "SELECT name, nextid FROM SEQUENCE WHERE NAME = ?";
    private static final String UPDATE_SEQUENCE =
            "UPDATE SEQUENCE SET NEXTID = ? WHERE NAME = ?";

    @Override
    public Sequence getSequence(Sequence sequence) {
        Sequence result =new Sequence();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SEQUENCE);
            preparedStatement.setString(1,sequence.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result.setName(resultSet.getString("name"));
                result.setNextId(resultSet.getInt("nextid"));
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
    public void updateSequence(Sequence sequence) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SEQUENCE);
            preparedStatement.setInt(1,sequence.getNextId());
            preparedStatement.setString(2,sequence.getName());
            preparedStatement.execute();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
