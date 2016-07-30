package com.teamset.colcap.service.account;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamset.colcap.domain.dao.account.AccountDao;
import com.teamset.colcap.domain.entity.account.Account;

@Service
public class AccountServiceImpl implements AccountService {

	@Inject
	private AccountDao accountDao;

	@Override
	@Transactional(readOnly = true)
	public Account get(Long acctId) {
		return accountDao.get(acctId);
	}

}
