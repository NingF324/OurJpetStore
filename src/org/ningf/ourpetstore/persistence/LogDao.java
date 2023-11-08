package org.ningf.ourpetstore.persistence;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/8 8:00
 */
public interface LogDao {
    void insertLog(String username, String logInfo);
}
