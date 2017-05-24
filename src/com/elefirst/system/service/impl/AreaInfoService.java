package com.elefirst.system.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.system.dao.iface.IAreaInfoDAO;
import com.elefirst.system.mapper.RoleAreaMapMapper;
import com.elefirst.system.mapper.UserRoleMapMapper;
import com.elefirst.system.po.AreaInfo;
import com.elefirst.system.po.AreaInfoExample;
import com.elefirst.system.po.AreaInfoWithBLOBs;
import com.elefirst.system.po.RoleAreaMap;
import com.elefirst.system.po.RoleAreaMapExample;
import com.elefirst.system.po.RoleInfo;
import com.elefirst.system.po.RoleInfoExample;
import com.elefirst.system.po.UserRoleMap;
import com.elefirst.system.po.UserRoleMapExample;
import com.elefirst.system.service.iface.IAreaInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

/**
 * Created by barrie on 17/1/29.
 */
@Service
public class AreaInfoService extends BaseService implements IAreaInfoService {
	@Autowired
	private IAreaInfoDAO areaInfoDAO;
	
	@Resource(name = "roleAreaMapMapper")
	private RoleAreaMapMapper roleAreaMapMapper;
    
    @Autowired
	private DataSourceTransactionManager transactionManager;

	@Override
	public List<AreaInfoWithBLOBs> getAreaInfoList(AreaInfoWithBLOBs template) {
		AreaInfoExample condition = new AreaInfoExample();
		AreaInfoExample.Criteria criteria = condition.createCriteria();

		if (null != template && null != template.getAreaId()) {
			criteria.andAreaIdEqualTo(template.getAreaId());
		}
		if (null != template && null != template.getName()) {
			criteria.andNameLike("%" + template.getName() + "%");
		}
		if (template.getRows() > 0 && template.getPage() > 0) {
			condition.setLimitStart((template.getPage() - 1)
					* template.getRows());
			condition.setLimitEnd(template.getRows());
		}
		condition.setOrderByClause("`create_date` ASC");
		return areaInfoDAO.getAreaInfoList(condition);
	}

	@Override
	public long getAreaInfoListCount(AreaInfoWithBLOBs template) {
		AreaInfoExample condition = new AreaInfoExample();
		AreaInfoExample.Criteria criteria = condition.createCriteria();

		if (null != template && null != template.getAreaId()) {
			criteria.andAreaIdEqualTo(template.getAreaId());
		}
		if (null != template && null != template.getName()) {
			criteria.andNameLike("%" + template.getName() + "%");
		}
		return areaInfoDAO.getAreaInfoListCount(condition);
	}

	@Override
	public List<AreaInfoWithBLOBs> getAreaInfoDetail(String id) {
		AreaInfoExample condition = new AreaInfoExample();
		AreaInfoExample.Criteria criteria = condition.createCriteria();
		criteria.andIdEqualTo(id);
		return areaInfoDAO.getAreaInfoList(condition);
	}

	@Override
	public int addAreaInfo(AreaInfoWithBLOBs template) {
		return areaInfoDAO.addAreaInfo(template);
	}

	@Override
	public int updateAreaInfo(AreaInfoWithBLOBs template) {
		AreaInfoExample condition = new AreaInfoExample();
		AreaInfoExample.Criteria criteria = condition.createCriteria();
		criteria.andIdEqualTo(template.getId());
		return areaInfoDAO.updateAreaInfo(condition, template);
	}

	@Override
	public int delAreaInfo(String id) {
		AreaInfoExample condition = new AreaInfoExample();
		AreaInfoExample.Criteria criteria = condition.createCriteria();
		criteria.andIdEqualTo(id);
		return areaInfoDAO.delAreaInfo(condition);
	}

	@Override
	public List<AreaInfoWithBLOBs> fetchAreaInfoByRoleId(AreaInfo areaInfo,
			boolean isPagination, String roleId) throws Exception {
		// 先根据角色id查询角色与区域关系表，找出已经存在关联的区域项
		List<RoleAreaMap> roleAreaMaps = fetchAreaRoleMapsByRid(roleId);
		Set<String> areaIds = new HashSet<String>();
		if (roleAreaMaps != null && roleAreaMaps.size() > 0) {
			for (RoleAreaMap roleAreaMap : roleAreaMaps) {
				areaIds.add(roleAreaMap.getAreaId());
			}
		}

		AreaInfoExample condition = new AreaInfoExample();
		AreaInfoExample.Criteria criteria = condition.createCriteria();
		condition.setOrderByClause("create_date DESC");
		if (true == isPagination) {
			if (areaInfo.getRows() > 0 && areaInfo.getPage() > 0) {
				condition.setLimitStart((areaInfo.getPage() - 1)
						* areaInfo.getRows());
				condition.setLimitEnd(areaInfo.getRows());
			}
		}
		List<AreaInfoWithBLOBs> rds = areaInfoDAO.getAreaInfoList(condition);
		for (AreaInfoWithBLOBs tmproleInfo : rds) {
			if (areaIds != null && areaIds.size() > 0
					&& areaIds.contains(tmproleInfo.getAreaId())) {
				tmproleInfo.setChecked(true);
			} else {
				tmproleInfo.setChecked(false);
			}
		}
		logger.info("根据条件查询区域信息实例成功！");
		return rds;
	}

	private List<RoleAreaMap> fetchAreaRoleMapsByRid(String roleId)
			throws Exception {
		RoleAreaMapExample condition = new RoleAreaMapExample();
		RoleAreaMapExample.Criteria criteria = condition.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		List<RoleAreaMap> roleAreaMaps = roleAreaMapMapper
				.selectByExample(condition);
		logger.info("查询角色与区域关系实例成功！");
		return roleAreaMaps;
	}

	@Override
	public List<AreaInfoWithBLOBs> fetchAreaInfoByCond(AreaInfo areaInfo,
			boolean isPagination) throws Exception {
		AreaInfoExample condition = new AreaInfoExample();
		AreaInfoExample.Criteria criteria = condition.createCriteria();
		if (areaInfo.getName() != null
				&& areaInfo.getName().length() > 0) {
			criteria.andNameEqualTo(areaInfo.getName());
		}
		condition.setOrderByClause("create_date DESC");
		if (true == isPagination) {
			if (areaInfo.getRows() > 0 && areaInfo.getPage() > 0) {
				condition.setLimitStart((areaInfo.getPage() - 1)
						* areaInfo.getRows());
				condition.setLimitEnd(areaInfo.getRows());
			}
		}
		List<AreaInfoWithBLOBs> rds = areaInfoDAO.getAreaInfoList(condition);
		logger.info("根据条件查询区域信息实例成功！");
		return rds;
	}

	@Override 
	public void saveRoleAreaMaps(List<RoleAreaMap> roleAreaMaps, String roleId)
			throws Exception {
		TransactionStatus status = null;
		try {
			// 开启新事务
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			// 事物隔离级别，开启新事务
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
			status = transactionManager.getTransaction(def);
			delRoleAreaMaps(roleId);
			if (roleAreaMaps != null && roleAreaMaps.size() > 0) {
				for (RoleAreaMap roleAreaMap : roleAreaMaps) {
					roleAreaMapMapper.insert(roleAreaMap);
				}
			}
			transactionManager.commit(status);
			logger.info("保存角色与区域实例成功！");
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
	}

	// 根据角色role删除角色与区域关系实例
	public void delRoleAreaMaps(String roleId) throws Exception {
		RoleAreaMapExample condition = new RoleAreaMapExample();
		RoleAreaMapExample.Criteria criteria = condition.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		roleAreaMapMapper.deleteByExample(condition);
		logger.info("删除角色与区域实例成功！");
	}
}
