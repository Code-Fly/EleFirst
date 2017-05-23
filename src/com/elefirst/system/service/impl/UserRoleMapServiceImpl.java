package com.elefirst.system.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.elefirst.system.mapper.UserRoleMapMapper;
import com.elefirst.system.po.UserRoleMap;
import com.elefirst.system.service.iface.IUserRoleMap;
@Service
public class UserRoleMapServiceImpl implements IUserRoleMap{

	private static final Logger logger = LoggerFactory
			.getLogger(UserRoleMapServiceImpl.class);
	
	@Resource(name = "userRoleMapMapper")
	private UserRoleMapMapper userRoleMapMapper;

	@Override
	public void delUserRoleMapByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean saveUserRoleMap(UserRoleMap userRoleMap) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	

}
