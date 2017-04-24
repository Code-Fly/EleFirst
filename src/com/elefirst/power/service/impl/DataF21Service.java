package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF21DAO;
import com.elefirst.power.po.DataF21;
import com.elefirst.power.po.DataF21Example;
import com.elefirst.power.service.iface.IDataF21Service;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.service.iface.IPnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Service
public class DataF21Service extends BaseService implements IDataF21Service {
    @Autowired
    private IDataF21DAO dataF21DAO;

    @Autowired
    private IPnInfoService pnInfoService;

    @Override
    public List<DataF21> getDataF21List(DataF21 template) {
        DataF21Example condition = new DataF21Example();
        DataF21Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`sendTime` ASC");
        return dataF21DAO.getDataF21List(condition);
    }

    @Override
    public List<DataF21> getDataF21List(List<DataF21> nodes, String startDate, String endDate) {
        DataF21Example condition = new DataF21Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataF21 node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andFrozenMonthGreaterThanOrEqualTo(startDate)
                    .andFrozenMonthLessThan(endDate)
            ;
        }
        condition.setOrderByClause("`frozen_month` ASC");
        return dataF21DAO.getDataF21List(condition);
    }

    @Override
    public List<DataF21> getDataF21SumList(DataF21 template) {
        DataF21Example condition = new DataF21Example();
        DataF21Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        criteria.andFrozenMonthIsNotNull();
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`frozen_month` ASC");
        return dataF21DAO.getDataF21SumList(condition);
    }

    @Override
    public List<DataF21> getDataF21SumList(List<DataF21> nodes, String startTime, String endTime) {
        DataF21Example condition = new DataF21Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataF21 node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andFrozenMonthGreaterThanOrEqualTo(startTime)
                    .andFrozenMonthLessThan(endTime)
                    .andFrozenMonthIsNotNull()
                    //
                    .andTotalpositiveactivepowerIsNotNull()
            ;
        }
        condition.setOrderByClause("`frozen_month` ASC");
        return dataF21DAO.getDataF21SumList(condition);
    }

    @Override
    public long getDataF21ListCount(DataF21 template) {
        DataF21Example condition = new DataF21Example();
        DataF21Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        return dataF21DAO.getDataF21ListCount(condition);
    }

    @Override
    public List<DataF21> getDataF21Detail(String id) {
        DataF21Example condition = new DataF21Example();
        DataF21Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF21DAO.getDataF21List(condition);
    }

    @Override
    public int addDataF21(DataF21 template) {
        return dataF21DAO.addDataF21(template);
    }

    @Override
    public int updateDataF21(DataF21 template) {
        DataF21Example condition = new DataF21Example();
        DataF21Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return dataF21DAO.updateDataF21(condition, template);
    }

    @Override
    public int delDataF21(String id) {
        DataF21Example condition = new DataF21Example();
        DataF21Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF21DAO.delDataF21(condition);
    }

    @Override
    public String getDifferTotalPositiveActivePower(List<DataF21> nodes, String startTime, String endTime) {
        Double total = null;

        List<DataF21> totalActivePowerList = format(getDataF21SumList(nodes, startTime, endTime));

        for (int i = 0; i < totalActivePowerList.size(); i++) {
            if (null == total) {
                total = 0D;
            }
            total += Double.valueOf(totalActivePowerList.get(i).getTotalpositiveactivepower());
        }

        return null == total ? null : String.valueOf(total);
    }

    @Override
    public List<DataF21> format(List<DataF21> data) {
        PnInfo template = new PnInfo();
        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(template);
        List<DataF21> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DataF21 item = data.get(i);
            PnInfo pnInfo = getPnInfo(pnInfos, item);
            if (null != pnInfo) {
                item.setTotalpositiveactivepower(calc(item.getTotalpositiveactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
            }
            result.add(item);
        }
        return result;
    }

    @Override
    public PnInfo getPnInfo(List<PnInfo> pnInfos, DataF21 item) {
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
                return String.valueOf(String.format("%." + precision + "f", Double.valueOf(org) * num));
            }
        }
        return null;
    }
}