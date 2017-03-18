package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF33DAO;
import com.elefirst.power.po.DataF33;
import com.elefirst.power.po.DataF33Example;
import com.elefirst.power.service.iface.IDataF33Service;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.service.iface.IPnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VM on 2/11/2017.
 */
@Service
public class DataF33Service extends BaseService implements IDataF33Service {
    @Autowired
    private IDataF33DAO dataF33DAO;

    @Autowired
    private IPnInfoService pnInfoService;

    @Override
    public List<DataF33> getDataF33List(DataF33 template) {
        DataF33Example condition = new DataF33Example();
        DataF33Example.Criteria criteria = condition.createCriteria();

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
        return dataF33DAO.getDataF33List(condition);
    }

    @Override
    public List<DataF33> getDataF33List(List<DataF33> nodes, String startDate, String endDate) {
        DataF33Example condition = new DataF33Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataF33 node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andClientoperationtimeGreaterThanOrEqualTo(startDate)
                    .andClientoperationtimeLessThan(endDate)
            ;
        }
        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF33DAO.getDataF33List(condition);
    }

    @Override
    public long getDataF33ListCount(DataF33 template) {
        DataF33Example condition = new DataF33Example();
        DataF33Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        return dataF33DAO.getDataF33ListCount(condition);
    }

    @Override
    public List<DataF33> getDataF33Detail(String id) {
        DataF33Example condition = new DataF33Example();
        DataF33Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF33DAO.getDataF33List(condition);
    }

    @Override
    public int addDataF33(DataF33 template) {
        return dataF33DAO.addDataF33(template);
    }

    @Override
    public int updateDataF33(DataF33 template) {
        DataF33Example condition = new DataF33Example();
        DataF33Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return dataF33DAO.updateDataF33(condition, template);
    }

    @Override
    public int delDataF33(String id) {
        DataF33Example condition = new DataF33Example();
        DataF33Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF33DAO.delDataF33(condition);
    }

    @Override
    public List<DataF33> format(List<DataF33> data) {
        PnInfo template = new PnInfo();
        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(template);
        List<DataF33> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DataF33 item = data.get(i);
            PnInfo pnInfo = getPnInfo(pnInfos, item);
            if (null != pnInfo) {
                item.setTotalpositiveactivepower(calc(item.getTotalpositiveactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setTotalpositivereactivepower(calc(item.getTotalpositivereactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setQuadrant1Totalreactivepower(calc(item.getQuadrant1Totalreactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setQuadrant4Totalreactivepower(calc(item.getQuadrant4Totalreactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
            }
            result.add(item);
        }
        return result;
    }

    @Override
    public List<DataF33> getInterval(List<DataF33> data) {
        for (int i = 0; i < data.size() - 1; i++) {
            DataF33 first = data.get(i);
            DataF33 second = data.get(i + 1);
            if (null == second.getTotalpositiveactivepower()) {
                second.setTotalpositiveactivepower(first.getTotalpositiveactivepower());
            }
            if (null == second.getTotalpositivereactivepower()) {
                second.setTotalpositivereactivepower(first.getTotalpositivereactivepower());
            }
            if (null == second.getQuadrant1Totalreactivepower()) {
                second.setQuadrant1Totalreactivepower(first.getQuadrant1Totalreactivepower());
            }
            if (null == second.getQuadrant4Totalreactivepower()) {
                second.setQuadrant4Totalreactivepower(first.getQuadrant4Totalreactivepower());
            }
            data.set(i + 1, second);
        }

        for (int i = data.size() - 1; i > 0; i--) {
            DataF33 first = data.get(i - 1);
            DataF33 second = data.get(i);
            if (null == first.getTotalpositiveactivepower()) {
                first.setTotalpositiveactivepower(second.getTotalpositiveactivepower());
            }
            if (null == second.getTotalpositivereactivepower()) {
                first.setTotalpositivereactivepower(second.getTotalpositivereactivepower());
            }
            if (null == second.getQuadrant1Totalreactivepower()) {
                first.setQuadrant1Totalreactivepower(second.getQuadrant1Totalreactivepower());
            }
            if (null == second.getQuadrant4Totalreactivepower()) {
                first.setQuadrant4Totalreactivepower(second.getQuadrant4Totalreactivepower());
            }
            data.set(i, second);
        }

        List<DataF33> result = new ArrayList<>();
        for (int i = 0; i < data.size() - 1; i++) {
            DataF33 first = data.get(i);
            DataF33 second = data.get(i + 1);

            first.setTotalpositiveactivepower(interval(first.getTotalpositiveactivepower(), second.getTotalpositiveactivepower()));
            first.setTotalpositivereactivepower(interval(first.getTotalpositivereactivepower(), second.getTotalpositivereactivepower()));
            first.setQuadrant1Totalreactivepower(interval(first.getQuadrant1Totalreactivepower(), second.getQuadrant1Totalreactivepower()));
            first.setQuadrant4Totalreactivepower(interval(first.getQuadrant4Totalreactivepower(), second.getQuadrant4Totalreactivepower()));

            result.add(first);
        }
        return result;
    }

    private PnInfo getPnInfo(List<PnInfo> pnInfos, DataF33 item) {
        for (int i = 0; i < pnInfos.size(); i++) {
            PnInfo pnInfo = pnInfos.get(i);
            if (pnInfo.getAreaId().equals(item.getAreaId()) && pnInfo.getConcentratorId().equals(item.getConcentratorId()) && pnInfo.getPn().equals(item.getPn())) {
                return pnInfo;
            }
        }
        return null;
    }

    private String calc(String org, Double num, Integer precision) {
        if (null != org) {
            if (null == precision) {
                return String.valueOf(Double.valueOf(org) * num);
            } else {
                return String.valueOf(String.format("%." + precision + "f", Double.valueOf(org) * num));
            }
        }
        return null;
    }

    private String interval(String first, String second) {
        if (null != first && null != second) {
            return String.valueOf(Double.valueOf(second) - Double.valueOf(first));
        }
        return null;
    }
}
