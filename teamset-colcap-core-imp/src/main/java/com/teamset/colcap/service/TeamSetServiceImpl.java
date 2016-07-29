package com.teamset.colcap.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamset.colcap.domain.dao.TeamSetDao;
import com.teamset.colcap.domain.entity.TeamSet;

@Service
public class TeamSetServiceImpl implements TeamSetService{

	@Inject
	private TeamSetDao teamSetDao;
	@Override
	@Transactional(readOnly=true)
	public List<TeamSet> findAll(){
		return teamSetDao.findAll();
	}
}
