package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF33FrozenDayDAO;
import com.elefirst.power.po.DataF33FrozenDay;
import com.elefirst.power.po.DataF33FrozenDayExample;
import com.elefirst.power.service.iface.IDataF33FrozenDayService;
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
public class DataF33FrozenDayService extends BaseService implements IDataF33FrozenDayService {
    @Autowired
    private IDataF33FrozenDayDAO dataF33FrozenDayDAO;

    @Autowired
    private IPnInfoService pnInfoService;

    @Override
    public List<DataF33FrozenDay> getDataF33FrozenDayList(DataF33FrozenDay template) {
        DataF33FrozenDayExample condition = new DataF33FrozenDayExample();
        DataF33FrozenDayExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        criteria.andClientoperationtimeIsNotNull();
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF33FrozenDayDAO.getDataF33FrozenDayList(condition);
    }

    @Override
    public List<DataF33FrozenDay> getDataF33FrozenDayList(List<DataF33FrozenDay> nodes, String startTime, String endTime) {
        DataF33FrozenDayExample condition = new DataF33FrozenDayExample();
        for (int i = 0; i < nodes.size(); i++) {
            DataF33FrozenDay node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andClientoperationtimeGreaterThanOrEqualTo(startTime)
                    .andClientoperationtimeLessThanOrEqualTo(endTime)
                    .andClientoperationtimeIsNotNull()
            ;
        }
        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF33FrozenDayDAO.getDataF33FrozenDayList(condition);
    }

    @Override
    public List<DataF33FrozenDay> getDataF33FrozenDaySumList(List<DataF33FrozenDay> nodes, String startTime, String endTime) {
        DataF33FrozenDayExample condition = new DataF33FrozenDayExample();
        for (int i = 0; i < nodes.size(); i++) {
            DataF33FrozenDay node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andClientoperationtimeGreaterThanOrEqualTo(startTime)
                    .andClientoperationtimeLessThanOrEqualTo(endTime)
                    .andClientoperationtimeIsNotNull()
                    //
                    .andTotalpositiveactivepowerIsNotNull()
                    .andTotalpositivereactivepowerIsNotNull()
                    .andQuadrant1TotalreactivepowerIsNotNull()
                    .andQuadrant4TotalreactivepowerIsNotNull()
            ;
        }
        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF33FrozenDayDAO.getDataF33FrozenDaySumList(condition);
    }

    @Override
    public int getDataF33FrozenDayListCount(DataF33FrozenDay template) {
        DataF33FrozenDayExample condition = new DataF33FrozenDayExample();
        DataF33FrozenDayExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        return dataF33FrozenDayDAO.getDataF33FrozenDayListCount(condition);
    }

    @Override
    public List<DataF33FrozenDay> getDataF33FrozenDayDetail(String id) {
        DataF33FrozenDayExample condition = new DataF33FrozenDayExample();
        DataF33FrozenDayExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF33FrozenDayDAO.getDataF33FrozenDayList(condition);
    }

    @Override
    public int addDataF33FrozenDay(DataF33FrozenDay template) {
        return dataF33FrozenDayDAO.addDataF33FrozenDay(template);
    }

    @Override
    public int updateDataF33FrozenDay(DataF33FrozenDay template) {
        DataF33FrozenDayExample condition = new DataF33FrozenDayExample();
        DataF33FrozenDayExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return dataF33FrozenDayDAO.updateDataF33FrozenDay(condition, template);
    }

    @Override
    public int delDataF33FrozenDay(String id) {
        DataF33FrozenDayExample condition = new DataF33FrozenDayExample();
        DataF33FrozenDayExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF33FrozenDayDAO.delDataF33FrozenDay(condition);
    }

    @Override
    public List<DataF33FrozenDay> format(List<DataF33FrozenDay> data) {
        PnInfo template = new PnInfo();
        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(template);
        List<DataF33FrozenDay> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DataF33FrozenDay item = data.get(i);
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
    public List<DataF33FrozenDay> getInterval(List<DataF33FrozenDay> data) {
        for (int i = 0; i < data.size() - 1; i++) {
            DataF33FrozenDay first = data.get(i);
            DataF33FrozenDay second = data.get(i + 1);
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
            DataF33FrozenDay first = data.get(i - 1);
            DataF33FrozenDay second = data.get(i);
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

        List<DataF33FrozenDay> result = new ArrayList<>();
        for (int i = 0; i < data.size() - 1; i++) {
            DataF33FrozenDay first = data.get(i);
            DataF33FrozenDay second = data.get(i + 1);

            first.setTotalpositiveactivepower(interval(first.getTotalpositiveactivepower(), second.getTotalpositiveactivepower()));
            first.setTotalpositivereactivepower(interval(first.getTotalpositivereactivepower(), second.getTotalpositivereactivepower()));
            first.setQuadrant1Totalreactivepower(interval(first.getQuadrant1Totalreactivepower(), second.getQuadrant1Totalreactivepower()));
            first.setQuadrant4Totalreactivepower(interval(first.getQuadrant4Totalreactivepower(), second.getQuadrant4Totalreactivepower()));

            result.add(first);
        }
        return result;
    }

    @Override
    public List<DataF33FrozenDay> getMaxValue(List<DataF33FrozenDay> nodes, String startTime, String endTime, String key) {
        DataF33FrozenDayExample condition = new DataF33FrozenDayExample();
        for (int i = 0; i < nodes.size(); i++) {
            DataF33FrozenDay node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andClientoperationtimeGreaterThanOrEqualTo(startTime)
                    .andClientoperationtimeLessThanOrEqualTo(endTime)
                    .andClientoperationtimeIsNotNull()
                    //
                    .andTotalpositiveactivepowerIsNotNull()
                    .andTotalpositivereactivepowerIsNotNull()
                    .andQuadrant1TotalreactivepowerIsNotNull()
                    .andQuadrant4TotalreactivepowerIsNotNull()
            ;
        }
        condition.setOrderByClause("`" + key + "` DESC");
        condition.setLimitStart(0);
        condition.setLimitEnd(1);
        return dataF33FrozenDayDAO.getDataF33FrozenDaySumList(condition);
    }

    @Override
    public List<DataF33FrozenDay> getMinValue(List<DataF33FrozenDay> nodes, String startTime, String endTime, String key) {
        DataF33FrozenDayExample condition = new DataF33FrozenDayExample();
        for (int i = 0; i < nodes.size(); i++) {
            DataF33FrozenDay node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andClientoperationtimeGreaterThanOrEqualTo(startTime)
                    .andClientoperationtimeLessThanOrEqualTo(endTime)
                    .andClientoperationtimeIsNotNull()
                    //
                    .andTotalpositiveactivepowerIsNotNull()
                    .andTotalpositivereactivepowerIsNotNull()
                    .andQuadrant1TotalreactivepowerIsNotNull()
                    .andQuadrant4TotalreactivepowerIsNotNull()
            ;
        }
        condition.setOrderByClause("`" + key + "` ASC");
        condition.setLimitStart(0);
        condition.setLimitEnd(1);
        return dataF33FrozenDayDAO.getDataF33FrozenDaySumList(condition);
    }

    @Override
    public String getDifferTotalPositiveActivePower(List<DataF33FrozenDay> nodes, String startTime, String endTime) {
        String differ = null;

        List<DataF33FrozenDay> totalActivePowerList = format(getDataF33FrozenDaySumList(nodes, startTime, endTime));

        if (totalActivePowerList.size() >= 2) {
            String maxTotalActivePower = totalActivePowerList.get(totalActivePowerList.size() - 1).getTotalpositiveactivepower();
            String minTotalActivePower = totalActivePowerList.get(0).getTotalpositiveactivepower();

            if (null != maxTotalActivePower && null != minTotalActivePower) {
                differ = String.valueOf(Double.valueOf(maxTotalActivePower) - Double.valueOf(minTotalActivePower));
            }
        }

        return differ;
    }

    private PnInfo getPnInfo(List<PnInfo> pnInfos, DataF33FrozenDay item) {
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

    private String interval(String first, String second) {
        if (null != first && null != second) {
            return String.valueOf(Double.valueOf(second) - Double.valueOf(first));
        }
        return null;
    }
}