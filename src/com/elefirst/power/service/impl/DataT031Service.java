package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataT031DAO;
import com.elefirst.power.po.DataT031;
import com.elefirst.power.po.DataT031Example;
import com.elefirst.power.service.iface.IDataT031Service;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.service.iface.IPnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Service
public class DataT031Service extends BaseService implements IDataT031Service {
    @Autowired
    private IDataT031DAO dataT031DAO;

    @Autowired
    private IPnInfoService pnInfoService;

    @Override
    public List<DataT031> getDataT031List(DataT031 template) {
        DataT031Example condition = new DataT031Example();
        DataT031Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        if (null != template && null != template.getClientoperationtime()) {
            criteria.andClientoperationtimeEqualTo(template.getClientoperationtime());
        }
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataT031DAO.getDataT031List(condition);
    }

    @Override
    public List<DataT031> getDataT031List(List<DataT031> nodes, String startDate, String endDate) {
        DataT031Example condition = new DataT031Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataT031 node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andClientoperationtimeGreaterThanOrEqualTo(startDate)
                    .andClientoperationtimeLessThan(endDate)
            ;
        }
        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataT031DAO.getDataT031List(condition);
    }


    @Override
    public long getDataT031ListCount(DataT031 template) {
        DataT031Example condition = new DataT031Example();
        DataT031Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        if (null != template && null != template.getClientoperationtime()) {
            criteria.andClientoperationtimeEqualTo(template.getClientoperationtime());
        }
        return dataT031DAO.getDataT031ListCount(condition);
    }

    @Override
    public List<DataT031> getDataT031Detail(String id) {
        DataT031Example condition = new DataT031Example();
        DataT031Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataT031DAO.getDataT031List(condition);
    }

    @Override
    public int addDataT031(DataT031 template) {
        return dataT031DAO.addDataT031(template);
    }

    @Override
    public int updateDataT031(DataT031 template) {
        DataT031Example condition = new DataT031Example();
        DataT031Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return dataT031DAO.updateDataT031(condition, template);
    }

    @Override
    public int delDataT031(String id) {
        DataT031Example condition = new DataT031Example();
        DataT031Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataT031DAO.delDataT031(condition);
    }


    @Override
    public List<DataT031> format(List<DataT031> data) {
        PnInfo template = new PnInfo();
        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(template);
        List<DataT031> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DataT031 item = data.get(i);
            PnInfo pnInfo = getPnInfo(pnInfos, item);
            if (null != pnInfo) {
                item.setTotalpositiveactivepower(calc(item.getTotalpositiveactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
            }
            result.add(item);
        }
        return result;
    }

    @Override
    public PnInfo getPnInfo(List<PnInfo> pnInfos, DataT031 item) {
        for (int i = 0; i < pnInfos.size(); i++) {
            PnInfo pnInfo = pnInfos.get(i);
            if (pnInfo.getAreaId().equals(item.getAreaId()) && pnInfo.getConcentratorId().equals(item.getConcentratorId()) && pnInfo.getPn().equals(item.getPn())) {
                return pnInfo;
            }
        }
        return null;
    }

    @Override
    public String calc(String org, Double num, Integer precision) {
        if (null != org) {
            if (null == precision) {
                return String.valueOf(Double.valueOf(org) * num);
            } else {
                BigDecimal n1 = new BigDecimal(Double.valueOf(org));
                BigDecimal n2 = new BigDecimal(num);
                double d = n1.multiply(n2).setScale(precision, RoundingMode.HALF_UP).doubleValue();
                return String.valueOf(d);
            }
        }
        return null;
    }
}