package org.ningf.ourpetstore.service;

import org.ningf.ourpetstore.domain.Account;
import org.ningf.ourpetstore.persistence.AccountDao;
import org.ningf.ourpetstore.persistence.impl.AccountDaoImpl;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/6 22:12
 */
public class AccountService {
    private AccountDao accountDao;
    public AccountService(){
        this.accountDao=new AccountDaoImpl();
    }

    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountDao.getAccountByUsernameAndPassword(account);
    }
}