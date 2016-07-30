package com.teamset.colcap.domain.dao.account;

import org.springframework.stereotype.Repository;

import com.teamset.colcap.domain.dao.GenericDaoImpl;
import com.teamset.colcap.domain.entity.account.Account;

@Repository
public class AccountDaoImpl extends GenericDaoImpl<Account, Long> implements AccountDao {

}
