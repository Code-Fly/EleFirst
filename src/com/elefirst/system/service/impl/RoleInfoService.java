package com.elefirst.system.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.system.dao.iface.IRoleInfoDAO;
import com.elefirst.system.mapper.UserRoleMapMapper;
import com.elefirst.system.po.RoleInfo;
import com.elefirst.system.po.RoleInfoExample;
import com.elefirst.system.po.UserRoleMap;
import com.elefirst.system.po.UserRoleMapExample;
import com.elefirst.system.service.iface.IRoleInfoService;

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
public class RoleInfoService extends BaseService implements IRoleInfoService {
    @Autowired
    private IRoleInfoDAO roleInfoDAO;
    
    @Resource(name = "userRoleMapMapper")
	private UserRoleMapMapper userRoleMapMapper;
    
    @Autowired
	private DataSourceTransactionManager transactionManager;

    @Override
    public List<RoleInfo> getRoleInfoList(RoleInfo template) {
        RoleInfoExample condition = new RoleInfoExample();
        RoleInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getRoleCode()) {
            criteria.andRoleCodeEqualTo(template.getRoleCode());
        }
        if (null != template && null != template.getRoleName()) {
            criteria.andRoleNameEqualTo(template.getRoleName());
        }
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`create_date` ASC");
        return roleInfoDAO.getRoleInfoList(condition);
    }

    @Override
    public List<RoleInfo> getRoleInfoListByInfos(List<RoleInfo> templates) {
        RoleInfoExample condition = new RoleInfoExample();
        for (int i = 0; i < templates.size(); i++) {
            RoleInfoExample.Criteria criteria = condition.createCriteria();
            RoleInfo template = templates.get(i);

            if (null != template && null != template.getId()) {
                criteria.andIdEqualTo(template.getId());
            }
            if (null != template && null != template.getRoleCode()) {
                criteria.andRoleCodeEqualTo(template.getRoleCode());
            }
            if (null != template && null != template.getRoleName()) {
                criteria.andRoleNameEqualTo(template.getRoleName());
            }
            if (template.getRows() > 0 && template.getPage() > 0) {
                condition.setLimitStart((template.getPage() - 1) * template.getRows());
                condition.setLimitEnd(template.getRows());
            }
            condition.or(criteria);
        }

        condition.setOrderByClause("`create_date` ASC");
        return roleInfoDAO.getRoleInfoList(condition);
    }

    @Override
    public long getRoleInfoListCount(RoleInfo template) {
        RoleInfoExample condition = new RoleInfoExample();
        RoleInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getRoleCode()) {
            criteria.andRoleCodeEqualTo(template.getRoleCode());
        }
        if (null != template && null != template.getRoleName()) {
            criteria.andRoleNameEqualTo(template.getRoleName());
        }
        return roleInfoDAO.getRoleInfoListCount(condition);
    }

    @Override
    public List<RoleInfo> getRoleInfoDetail(String id) {
        RoleInfoExample condition = new RoleInfoExample();
        RoleInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return roleInfoDAO.getRoleInfoList(condition);
    }

    @Override
    public int addRoleInfo(RoleInfo template) {
        return roleInfoDAO.addRoleInfo(template);
    }

    @Override
    public int updateRoleInfo(RoleInfo template) {
        RoleInfoExample condition = new RoleInfoExample();
        RoleInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return roleInfoDAO.updateRoleInfo(condition, template);
    }

    @Override
    public int delRoleInfo(String id) {
        RoleInfoExample condition = new RoleInfoExample();
        RoleInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return roleInfoDAO.delRoleInfo(condition);
    }

	@Override
	public List<RoleInfo> fetchRoleInfoByUserId(RoleInfo roleInfo,
			boolean isPagination, String userId) throws Exception {
		//先根据角色id查询角色与菜单关系表，找出已经存在关联的菜单项
				List<UserRoleMap> userRoleMaps = fetchUserRoleMapsByUid(userId);
				//List<String> roleIds = new ArrayList<String>();
				Set<String> roleIds = new HashSet<String>();  
				if(userRoleMaps !=null && userRoleMaps.size() > 0){
					for (UserRoleMap userRoleMap : userRoleMaps) {
						roleIds.add(userRoleMap.getRoleId());
					}
				}
				
				RoleInfoExample condition = new RoleInfoExample();
				RoleInfoExample.Criteria criteria = condition.createCriteria();
				condition.setOrderByClause("create_date DESC");
				if(true == isPagination){
					if (roleInfo.getRows() > 0 && roleInfo.getPage() > 0) {
			            condition.setLimitStart((roleInfo.getPage() - 1) * roleInfo.getRows());
			            condition.setLimitEnd(roleInfo.getRows());
			        }
				}
				List<RoleInfo> rds = roleInfoDAO.getRoleInfoList(condition);
				for (RoleInfo tmproleInfo : rds) {
					if(roleIds !=null && roleIds.size()>0 && roleIds.contains(tmproleInfo.getId())){
						tmproleInfo.setChecked(true);
					}else{
						tmproleInfo.setChecked(false);
					}
				}
				logger.info("根据条件查询角色信息实例成功！");
				return rds;
	}
	
	private List<UserRoleMap> fetchUserRoleMapsByUid(String userId) throws Exception {
		UserRoleMapExample condition = new UserRoleMapExample();
		UserRoleMapExample.Criteria criteria = condition.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<UserRoleMap> userRoleMaps = userRoleMapMapper.selectByExample(condition);
		logger.info("查询角色与用户关系实例成功！");
		return userRoleMaps;
	}
	
	@Override
	public List<RoleInfo> fetchRoleInfoByCond(RoleInfo roleInfo, boolean isPagination) throws Exception {
		RoleInfoExample condition = new RoleInfoExample();
		RoleInfoExample.Criteria criteria = condition.createCriteria();
		if(roleInfo.getRoleName()!= null &&roleInfo.getRoleName().length() > 0){
			criteria.andRoleNameEqualTo(roleInfo.getRoleName());
		}
		condition.setOrderByClause("create_date DESC");
		if(true == isPagination){
			if (roleInfo.getRows() > 0 && roleInfo.getPage() > 0) {
	            condition.setLimitStart((roleInfo.getPage() - 1) * roleInfo.getRows());
	            condition.setLimitEnd(roleInfo.getRows());
	        }
		}
		List<RoleInfo> rds = roleInfoDAO.getRoleInfoList(condition);
		logger.info("根据条件查询角色信息实例成功！");
		return rds;
	}
	
	@Override
	public void saveUserRoleMaps(List<UserRoleMap> userRoleMaps, String personId) throws Exception {
		TransactionStatus status = null;
		try {
			//开启新事务
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			// 事物隔离级别，开启新事务
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); 
			status = transactionManager.getTransaction(def);
			delUserRoleMaps(personId);
			if(userRoleMaps !=null && userRoleMaps.size() > 0){
				for (UserRoleMap userRoleMap : userRoleMaps) {
					userRoleMapMapper.insert(userRoleMap);
				}
			}
			transactionManager.commit(status);
			logger.info("保存角色与用户实例成功！");
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
	}
	
	//根据角色role删除角色与用户关系实例
	public void delUserRoleMaps(String personId) throws Exception {
		UserRoleMapExample condition = new UserRoleMapExample();
		UserRoleMapExample.Criteria criteria = condition.createCriteria();
		criteria.andUserIdEqualTo(personId);
		userRoleMapMapper.deleteByExample(condition);
		logger.info("删除角色与用户实例成功！");
	}
}
