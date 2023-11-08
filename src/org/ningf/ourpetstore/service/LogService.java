package org.ningf.ourpetstore.service;

import org.ningf.ourpetstore.domain.Log;
import org.ningf.ourpetstore.persistence.LogDao;
import org.ningf.ourpetstore.persistence.impl.LogDaoImpl;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/8 8:04
 */
public class LogService {
    Log log;
    private LogDao logDao;

    public LogService(){
        log = new Log();
        logDao = new LogDaoImpl();
    }

    public String logInfo(Object ...s){
        return log.logInfomation(s);
    }

    public void insertLogInfo(String username, String logInfo){
        logDao.insertLog(username, logInfo);
    }
}
