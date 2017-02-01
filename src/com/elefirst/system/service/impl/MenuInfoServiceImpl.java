package com.elefirst.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.elefirst.powerdetail.po.DailyLoad;
import com.elefirst.powerdetail.po.DailyLoadExample;
import com.elefirst.system.mapper.MenuInfoMapper;
import com.elefirst.system.po.MenuInfo;
import com.elefirst.system.po.MenuInfoExample;
import com.elefirst.system.service.iface.IMenuInfoService;


@Service
public class MenuInfoServiceImpl implements IMenuInfoService {

	private static final Logger logger = LoggerFactory
			.getLogger(MenuInfoServiceImpl.class);
	
	@Resource(name = "menuInfoMapper")
	private MenuInfoMapper menuInfoMapper;

	@Override
	public boolean modifyMenuInfo(MenuInfo menuInfo,List<String> ids) throws Exception {
		boolean result = false;
		MenuInfoExample condition = new MenuInfoExample();
		MenuInfoExample.Criteria criteria = condition.createCriteria();
		criteria.andMenuidIn(ids);
		int num = menuInfoMapper.updateByExampleSelective(menuInfo, condition);
		if(num > 0){
			logger.info("更新菜单信息实例成功！");
			result = true;
		}
		return result;
	}

	@Override
	public List<MenuInfo> fetchAllMenuInfo(int rows, int page, boolean isPagination) throws Exception {
		MenuInfoExample condition = new MenuInfoExample();
		MenuInfoExample.Criteria criteria = condition.createCriteria();
		//criteria.andCreatepersonEqualTo("admin");
		criteria.andMenuidNotEqualTo("0");
		condition.setOrderByClause("MenuSeq ASC ");
		//是否分页
		if(true == isPagination){
			if (rows > 0 && page > 0) {
				condition.setLimitStart((page - 1) * rows);
				condition.setLimitEnd(rows);
			}
		}
		List<MenuInfo> menuInfos = menuInfoMapper.selectByExample(condition);
		return menuInfos;
	}

	@Override
	public List<MenuInfo> fetchOneLevelMenuInfo(List<String> ids)
			throws Exception {
		MenuInfoExample condition = new MenuInfoExample();
		MenuInfoExample.Criteria criteria = condition.createCriteria();
		criteria.andCreatepersonEqualTo("admin");
		criteria.andMenuidIn(ids);
		//安菜单进行排序
		condition.setOrderByClause("MenuSeq DESC");
		List<MenuInfo> menuInfos = menuInfoMapper.selectByExample(condition);
		return menuInfos;
	}

	@Override
	public List<MenuInfo> fetchTwoLevelMenuInfo(String menuCode)
			throws Exception {
		MenuInfoExample condition = new MenuInfoExample();
		MenuInfoExample.Criteria criteria = condition.createCriteria();
		criteria.andCreatepersonEqualTo("admin");
		criteria.andMenucodeEqualTo(menuCode);
		//安菜单进行排序
		condition.setOrderByClause("MenuSeq DESC");
		List<MenuInfo> menuInfos = menuInfoMapper.selectByExample(condition);
		return menuInfos;
	}
}
