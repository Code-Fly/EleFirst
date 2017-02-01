package com.elefirst.system.service.iface;

import java.util.List;

import com.elefirst.system.po.MenuInfo;

public interface IMenuInfoService {

	
	
	/**
	 * 根据id修改菜单实例
	 * @param menuInfo
	 * @return
	 * @throws Exception
	 */
	public boolean modifyMenuInfo(MenuInfo menuInfo,List<String> ids) throws Exception;

	/**
	 * 根据条件查询所有菜单实例
	 * @param menuInfo
	 * @return
	 * @throws Exception
	 */
	public List<MenuInfo> fetchAllMenuInfo(int rows, int page, boolean isPagination) throws Exception;
	
	/**
	 * 根据查询所有第一级菜单实例
	 * @param menuInfo
	 * @return
	 * @throws Exception
	 */
	public List<MenuInfo> fetchOneLevelMenuInfo(List<String> ids) throws Exception;
	
	/**
	 * 根据查询一级菜单编号查询对应的子菜单实例
	 * @param menuInfo
	 * @return
	 * @throws Exception
	 */
	public List<MenuInfo> fetchTwoLevelMenuInfo(String MenuCode) throws Exception;
}
