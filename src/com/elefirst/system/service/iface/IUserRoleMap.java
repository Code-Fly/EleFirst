package com.elefirst.system.service.iface;

import com.elefirst.system.po.UserRoleMap;


public interface IUserRoleMap {

	/**
	 * 根据id删除用户与角色关系实例
	 * @param menuid
	 * @throws Exception
	 */
	public void delUserRoleMapByUserId(String userId) throws Exception;
	
	/**
	 * 保存用户与角色关系实例
	 * @param menuInfo
	 * @return
	 * @throws Exception
	 */
	public boolean saveUserRoleMap(UserRoleMap userRoleMap) throws Exception;
}
