package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF25FrozenMinuteDAO;
import com.elefirst.power.po.*;
import com.elefirst.power.service.iface.IDataF25FrozenMinuteService;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.service.iface.IPnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Service
public class DataF25FrozenMinuteService extends BaseService implements IDataF25FrozenMinuteService {
    @Autowired
    private IDataF25FrozenMinuteDAO dataF25FrozenMinuteDAO;

    @Autowired
    private IPnInfoService pnInfoService;

    @Override
    public List<DataF25FrozenMinute> getDataF25FrozenMinuteList(DataF25FrozenMinute template) {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();
        DataF25FrozenMinuteExample.Criteria criteria = condition.createCriteria();

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
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteList(condition);
    }

    @Override
    public List<DataF25FrozenMinute> getDataF25FrozenMinuteList(List<DataF25FrozenMinute> nodes, String startTime, String endTime) {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();
        for (int i = 0; i < nodes.size(); i++) {
            DataF25FrozenMinute node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andClientoperationtimeGreaterThanOrEqualTo(startTime)
                    .andClientoperationtimeLessThan(endTime)
                    .andClientoperationtimeIsNotNull()
            ;
        }
        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteList(condition);
    }

    @Override
    public List<DataF25FrozenMinute> getDataF25FrozenMinuteSumList(DataF25FrozenMinute template) {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();
        DataF25FrozenMinuteExample.Criteria criteria = condition.createCriteria();

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
        condition.setOrderByClause("`sendTime` ASC");
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteSumList(condition);
    }

    @Override
    public List<DataF25FrozenMinute> getDataF25FrozenMinuteSumList(List<DataF25FrozenMinute> nodes, String startTime, String endTime) {
        return getDataF25FrozenMinuteSumList(nodes, startTime, endTime, "clientOperationTime", "ASC");
    }

    @Override
    public List<DataF25FrozenMinute> getDataF25FrozenMinuteSumList(List<DataF25FrozenMinute> nodes, String startTime, String endTime, String orderBy, String order) {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();
        for (int i = 0; i < nodes.size(); i++) {
            DataF25FrozenMinute node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andClientoperationtimeGreaterThanOrEqualTo(startTime)
                    .andClientoperationtimeLessThan(endTime)
                    .andClientoperationtimeIsNotNull()
                    //
                    .andTotalactivepowerIsNotNull()
                    .andAActivepowerIsNotNull()
                    .andBActivepowerIsNotNull()
                    .andCActivepowerIsNotNull()
                    .andAVoltageIsNotNull()
                    .andBVoltageIsNotNull()
                    .andCVoltageIsNotNull()
                    .andACurrentIsNotNull()
                    .andBCurrentIsNotNull()
                    .andCCurrentIsNotNull()
                    .andAPowerfactorIsNotNull()
                    .andBPowerfactorIsNotNull()
                    .andBPowerfactorIsNotNull()
            ;
        }
        condition.setOrderByClause("`" + orderBy + "` " + order);
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteSumList(condition);
    }

    @Override
    public List<DataF25FrozenMinuteWithF5> getDataF25FrozenMinuteSumWithF5List(List<DataF25FrozenMinute> nodes, String startTime, String endTime) {
        return getDataF25FrozenMinuteSumWithF5List(nodes, startTime, endTime, "clientOperationTime", "ASC");
    }

    @Override
    public List<DataF25FrozenMinuteWithF5> getDataF25FrozenMinuteStatisticsWithF5DailyList(DataF25FrozenMinute template, String startTime, String endTime) {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();
        DataF25FrozenMinuteExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }

        if (null != startTime) {
            criteria.andClientoperationtimeGreaterThanOrEqualTo(startTime);
        }

        if (null != endTime) {
            criteria.andClientoperationtimeLessThan(endTime);
        }

        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteStatisticsWithF5DailyList(condition);
    }

    @Override
    public List<DataF25FrozenMinuteWithF21> getDataF25FrozenMinuteStatisticsWithF21MonthlyList(DataF25FrozenMinute template, String startTime, String endTime) {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();
        DataF25FrozenMinuteExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }

        if (null != startTime) {
            criteria.andClientoperationtimeGreaterThanOrEqualTo(startTime);
        }

        if (null != endTime) {
            criteria.andClientoperationtimeLessThan(endTime);
        }

        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteStatisticsWithF21MonthlyList(condition);
    }

    @Override
    public List<DataF25FrozenMinuteWithF5> getDataF25FrozenMinuteSumWithF5List(List<DataF25FrozenMinute> nodes, String startTime, String endTime, String orderBy, String order) {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();
        for (int i = 0; i < nodes.size(); i++) {
            DataF25FrozenMinute node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andClientoperationtimeGreaterThanOrEqualTo(startTime)
                    .andClientoperationtimeLessThan(endTime)
                    .andClientoperationtimeIsNotNull()
                    //
                    .andTotalactivepowerIsNotNull()
                    .andAActivepowerIsNotNull()
                    .andBActivepowerIsNotNull()
                    .andCActivepowerIsNotNull()
                    .andAVoltageIsNotNull()
                    .andBVoltageIsNotNull()
                    .andCVoltageIsNotNull()
                    .andACurrentIsNotNull()
                    .andBCurrentIsNotNull()
                    .andCCurrentIsNotNull()
                    .andAPowerfactorIsNotNull()
                    .andBPowerfactorIsNotNull()
                    .andBPowerfactorIsNotNull()
            ;
        }
        condition.setOrderByClause("`" + orderBy + "` " + order);
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteSumWithF5List(condition);
    }

    @Override
    public List<DataF25FrozenMinuteWithF21> getDataF25FrozenMinuteSumWithF21List(List<DataF25FrozenMinute> nodes, String startTime, String endTime) {
        return getDataF25FrozenMinuteSumWithF21List(nodes, startTime, endTime, "clientOperationTime", "ASC");
    }

    @Override
    public List<DataF25FrozenMinuteWithF21> getDataF25FrozenMinuteSumWithF21List(List<DataF25FrozenMinute> nodes, String startTime, String endTime, String orderBy, String order) {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();
        for (int i = 0; i < nodes.size(); i++) {
            DataF25FrozenMinute node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andClientoperationtimeGreaterThanOrEqualTo(startTime)
                    .andClientoperationtimeLessThan(endTime)
                    .andClientoperationtimeIsNotNull()
                    //
                    .andTotalactivepowerIsNotNull()
                    .andAActivepowerIsNotNull()
                    .andBActivepowerIsNotNull()
                    .andCActivepowerIsNotNull()
                    .andAVoltageIsNotNull()
                    .andBVoltageIsNotNull()
                    .andCVoltageIsNotNull()
                    .andACurrentIsNotNull()
                    .andBCurrentIsNotNull()
                    .andCCurrentIsNotNull()
                    .andAPowerfactorIsNotNull()
                    .andBPowerfactorIsNotNull()
                    .andBPowerfactorIsNotNull()
            ;
        }
        condition.setOrderByClause("`" + orderBy + "` " + order);
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteSumWithF21List(condition);
    }


    @Override
    public int getDataF25FrozenMinuteListCount(DataF25FrozenMinute template) {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();
        DataF25FrozenMinuteExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteListCount(condition);
    }

    @Override
    public List<DataF25FrozenMinute> getDataF25FrozenMinuteDetail(String id) {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();
        DataF25FrozenMinuteExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteList(condition);
    }

    @Override
    public int addDataF25FrozenMinute(DataF25FrozenMinute template) {
        return dataF25FrozenMinuteDAO.addDataF25FrozenMinute(template);
    }

    @Override
    public int updateDataF25FrozenMinute(DataF25FrozenMinute template) {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();
        DataF25FrozenMinuteExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return dataF25FrozenMinuteDAO.updateDataF25FrozenMinute(condition, template);
    }

    @Override
    public int delDataF25FrozenMinute(String id) {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();
        DataF25FrozenMinuteExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF25FrozenMinuteDAO.delDataF25FrozenMinute(condition);
    }

    @Override
    public List<DataF25FrozenMinute> format(List<DataF25FrozenMinute> data) {
        PnInfo template = new PnInfo();
        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(template);
        List<DataF25FrozenMinute> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DataF25FrozenMinute item = data.get(i);
            PnInfo pnInfo = getPnInfo(pnInfos, item);
            if (null != pnInfo) {
                item.setTotalactivepower(calc(item.getTotalactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setaActivepower(calc(item.getaActivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setbActivepower(calc(item.getbActivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setcActivepower(calc(item.getcActivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));

                item.setTotalreactivepower(calc(item.getTotalreactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setaReactivepower(calc(item.getaReactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setbReactivepower(calc(item.getbReactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setcReactivepower(calc(item.getcReactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));

                item.setaVoltage(calc(item.getaVoltage(), pnInfo.getPt(), 1));
                item.setbVoltage(calc(item.getbVoltage(), pnInfo.getPt(), 1));
                item.setcVoltage(calc(item.getcVoltage(), pnInfo.getPt(), 1));

                item.setaCurrent(calc(item.getaCurrent(), pnInfo.getCt(), 3));
                item.setbCurrent(calc(item.getbCurrent(), pnInfo.getCt(), 3));
                item.setcCurrent(calc(item.getcCurrent(), pnInfo.getCt(), 3));

                item.setZeroSequenceCurrent(calc(item.getZeroSequenceCurrent(), pnInfo.getCt(), 3));

                item.setTotalapparentpower(calc(item.getTotalapparentpower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setaApparentpower(calc(item.getaApparentpower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setbApparentpower(calc(item.getbApparentpower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setcApparentpower(calc(item.getcApparentpower(), pnInfo.getCt() * pnInfo.getPt(), 3));

            }
            result.add(item);
        }
        return result;
    }

    @Override
    public List<DataF25FrozenMinuteWithF5> formatWithF5(List<DataF25FrozenMinuteWithF5> data) {
        PnInfo template = new PnInfo();
        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(template);
        List<DataF25FrozenMinuteWithF5> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DataF25FrozenMinuteWithF5 item = data.get(i);
            PnInfo pnInfo = getPnInfo(pnInfos, item);
            if (null != pnInfo) {
                item.setTotalactivepower(calc(item.getTotalactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setaActivepower(calc(item.getaActivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setbActivepower(calc(item.getbActivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setcActivepower(calc(item.getcActivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));

                item.setTotalreactivepower(calc(item.getTotalreactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setaReactivepower(calc(item.getaReactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setbReactivepower(calc(item.getbReactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setcReactivepower(calc(item.getcReactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));

                item.setaVoltage(calc(item.getaVoltage(), pnInfo.getPt(), 1));
                item.setbVoltage(calc(item.getbVoltage(), pnInfo.getPt(), 1));
                item.setcVoltage(calc(item.getcVoltage(), pnInfo.getPt(), 1));

                item.setaCurrent(calc(item.getaCurrent(), pnInfo.getCt(), 3));
                item.setbCurrent(calc(item.getbCurrent(), pnInfo.getCt(), 3));
                item.setcCurrent(calc(item.getcCurrent(), pnInfo.getCt(), 3));

                item.setZeroSequenceCurrent(calc(item.getZeroSequenceCurrent(), pnInfo.getCt(), 3));

                item.setTotalapparentpower(calc(item.getTotalapparentpower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setaApparentpower(calc(item.getaApparentpower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setbApparentpower(calc(item.getbApparentpower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setcApparentpower(calc(item.getcApparentpower(), pnInfo.getCt() * pnInfo.getPt(), 3));

                item.setTotalpositiveactivepower(calc(item.getTotalpositiveactivepower(), pnInfo.getCt() * pnInfo.getPt(), 4));
            }
            result.add(item);
        }
        return result;
    }

    @Override
    public List<DataF25FrozenMinuteWithF21> formatWithF21(List<DataF25FrozenMinuteWithF21> data) {
        PnInfo template = new PnInfo();
        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(template);
        List<DataF25FrozenMinuteWithF21> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DataF25FrozenMinuteWithF21 item = data.get(i);
            PnInfo pnInfo = getPnInfo(pnInfos, item);
            if (null != pnInfo) {
                item.setTotalactivepower(calc(item.getTotalactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setaActivepower(calc(item.getaActivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setbActivepower(calc(item.getbActivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setcActivepower(calc(item.getcActivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));

                item.setTotalreactivepower(calc(item.getTotalreactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setaReactivepower(calc(item.getaReactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setbReactivepower(calc(item.getbReactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setcReactivepower(calc(item.getcReactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));

                item.setaVoltage(calc(item.getaVoltage(), pnInfo.getPt(), 1));
                item.setbVoltage(calc(item.getbVoltage(), pnInfo.getPt(), 1));
                item.setcVoltage(calc(item.getcVoltage(), pnInfo.getPt(), 1));

                item.setaCurrent(calc(item.getaCurrent(), pnInfo.getCt(), 3));
                item.setbCurrent(calc(item.getbCurrent(), pnInfo.getCt(), 3));
                item.setcCurrent(calc(item.getcCurrent(), pnInfo.getCt(), 3));

                item.setZeroSequenceCurrent(calc(item.getZeroSequenceCurrent(), pnInfo.getCt(), 3));

                item.setTotalapparentpower(calc(item.getTotalapparentpower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setaApparentpower(calc(item.getaApparentpower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setbApparentpower(calc(item.getbApparentpower(), pnInfo.getCt() * pnInfo.getPt(), 3));
                item.setcApparentpower(calc(item.getcApparentpower(), pnInfo.getCt() * pnInfo.getPt(), 3));

                item.setTotalpositiveactivepower(calc(item.getTotalpositiveactivepower(), pnInfo.getCt() * pnInfo.getPt(), 4));
            }
            result.add(item);
        }
        return result;
    }

    @Override
    public List<DataF25FrozenMinute> getMaxValue(List<DataF25FrozenMinute> nodes, String startTime, String endTime, String key) {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();
        for (int i = 0; i < nodes.size(); i++) {
            DataF25FrozenMinute node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andClientoperationtimeGreaterThanOrEqualTo(startTime)
                    .andClientoperationtimeLessThan(endTime)
                    .andClientoperationtimeIsNotNull()
                    //
                    .andTotalactivepowerIsNotNull()
                    .andAActivepowerIsNotNull()
                    .andBActivepowerIsNotNull()
                    .andCActivepowerIsNotNull()
                    .andAVoltageIsNotNull()
                    .andBVoltageIsNotNull()
                    .andCVoltageIsNotNull()
                    .andACurrentIsNotNull()
                    .andBCurrentIsNotNull()
                    .andCCurrentIsNotNull()
                    .andAPowerfactorIsNotNull()
                    .andBPowerfactorIsNotNull()
                    .andBPowerfactorIsNotNull()
            ;
        }
        condition.setOrderByClause("`" + key + "` DESC");
        condition.setLimitStart(0);
        condition.setLimitEnd(1);
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteSumList(condition);
    }

    @Override
    public List<DataF25FrozenMinute> getMinValue(List<DataF25FrozenMinute> nodes, String startTime, String endTime, String key) {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();
        for (int i = 0; i < nodes.size(); i++) {
            DataF25FrozenMinute node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andClientoperationtimeGreaterThanOrEqualTo(startTime)
                    .andClientoperationtimeLessThan(endTime)
                    .andClientoperationtimeIsNotNull()
                    //
                    .andTotalactivepowerIsNotNull()
                    .andAActivepowerIsNotNull()
                    .andBActivepowerIsNotNull()
                    .andCActivepowerIsNotNull()
                    .andAVoltageIsNotNull()
                    .andBVoltageIsNotNull()
                    .andCVoltageIsNotNull()
                    .andACurrentIsNotNull()
                    .andBCurrentIsNotNull()
                    .andCCurrentIsNotNull()
                    .andAPowerfactorIsNotNull()
                    .andBPowerfactorIsNotNull()
                    .andBPowerfactorIsNotNull()
            ;
        }
        condition.setOrderByClause("`" + key + "` ASC");
        condition.setLimitStart(0);
        condition.setLimitEnd(1);
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteSumList(condition);
    }

    @Override
    public PnInfo getPnInfo(List<PnInfo> pnInfos, DataF25FrozenMinute item) {
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

    @Override
    public StatisticF25TotalActivePower getF5StatisticTotalActivePower(List<DataF25FrozenMinute> nodes, String startTime, String endTime) {
        List<DataF25FrozenMinuteWithF5> totalActivePowerList = formatWithF5(getDataF25FrozenMinuteSumWithF5List(nodes, startTime, endTime, "totalActivePower", "ASC"));

        DataF25FrozenMinuteWithF5 maxTotalActivePower = new DataF25FrozenMinuteWithF5();
        if (totalActivePowerList.size() > 0) {
            maxTotalActivePower = totalActivePowerList.get(totalActivePowerList.size() - 1);
        }


        DataF25FrozenMinuteWithF5 minTotalActivePower = new DataF25FrozenMinuteWithF5();
        if (totalActivePowerList.size() > 0) {
            minTotalActivePower = totalActivePowerList.get(0);
        }

        DataF25FrozenMinuteWithF5 avgTotalActivePower = new DataF25FrozenMinuteWithF5();
        List<DataF25FrozenMinuteWithF5> avgTotalActivePowerList = totalActivePowerList;

        Double sum = null;
        int count = 0;
        for (int i = 0; i < avgTotalActivePowerList.size(); i++) {
            if (null != avgTotalActivePowerList.get(i).getTotalpositiveactivepower()) {
                if (null == sum) {
                    sum = 0D;
                }
                sum += Double.valueOf(avgTotalActivePowerList.get(i).getTotalpositiveactivepower());
                count++;
            }
        }

        String avg = null == sum ? null : String.valueOf(sum / (24 * count));
        if (avgTotalActivePowerList.size() == 0) {
            avg = null;
        }

        avgTotalActivePower.setTotalactivepower(calc(avg, 1D, 3));

        StatisticF25TotalActivePower result = new StatisticF25TotalActivePower();
        result.setMaxTotalActivePower(maxTotalActivePower.getTotalactivepower());
        result.setMaxTotalActivePowerTime(maxTotalActivePower.getClientoperationtime());
        result.setMinTotalActivePower(minTotalActivePower.getTotalactivepower());
        result.setMinTotalActivePowerTime(minTotalActivePower.getClientoperationtime());
        result.setAvgTotalActivePower(avgTotalActivePower.getTotalactivepower());

        Double differ = null;
        if (null != maxTotalActivePower.getTotalactivepower() && null != minTotalActivePower.getTotalactivepower()) {
            differ = Double.valueOf(maxTotalActivePower.getTotalactivepower()) - Double.valueOf(minTotalActivePower.getTotalactivepower());
        }

        result.setDiffer(calc(null == differ ? null : String.valueOf(differ), 1D, 3));

        Double differRate = null;
        if (null != maxTotalActivePower.getTotalactivepower() && null != minTotalActivePower.getTotalactivepower()) {
            differRate = (differ / Double.valueOf(maxTotalActivePower.getTotalactivepower())) * 100;
        }

        result.setDifferRate(calc(null == differRate ? null : String.valueOf(differRate), 1D, 1));


        Double loadRate = null;
        if (null != maxTotalActivePower.getTotalactivepower() && null != avgTotalActivePower.getTotalactivepower()) {
            loadRate = (Double.valueOf(avgTotalActivePower.getTotalactivepower()) / Double.valueOf(maxTotalActivePower.getTotalactivepower())) * 100;
        }

        result.setLoadRate(calc(null == loadRate ? null : String.valueOf(loadRate), 1D, 1));

        return result;
    }

    @Override
    public StatisticF25TotalActivePower getF21StatisticTotalActivePower(List<DataF25FrozenMinute> nodes, String startTime, String endTime) throws ParseException {
        List<DataF25FrozenMinuteWithF21> totalActivePowerList = formatWithF21(getDataF25FrozenMinuteSumWithF21List(nodes, startTime, endTime, "totalActivePower", "ASC"));


        DataF25FrozenMinuteWithF21 maxTotalActivePower = new DataF25FrozenMinuteWithF21();
        if (totalActivePowerList.size() > 0) {
            maxTotalActivePower = totalActivePowerList.get(totalActivePowerList.size() - 1);
        }


        DataF25FrozenMinuteWithF21 minTotalActivePower = new DataF25FrozenMinuteWithF21();
        if (totalActivePowerList.size() > 0) {
            minTotalActivePower = totalActivePowerList.get(0);
        }

        DataF25FrozenMinuteWithF21 avgTotalActivePower = new DataF25FrozenMinuteWithF21();
        List<DataF25FrozenMinuteWithF21> avgTotalActivePowerList = totalActivePowerList;


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        Calendar calendar = Calendar.getInstance();

        Double sum = null;
        int count = 0;
        for (int i = 0; i < avgTotalActivePowerList.size(); i++) {
            if (null != avgTotalActivePowerList.get(i).getTotalpositiveactivepower()) {
                if (null == sum) {
                    sum = 0D;
                }
                calendar.setTime(sdf.parse(avgTotalActivePowerList.get(i).getClientoperationtime()));

                sum += Double.valueOf(avgTotalActivePowerList.get(i).getTotalpositiveactivepower());
                count += calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
        }


        if (avgTotalActivePowerList.size() > 0) {
            calendar.setTime(sdf.parse(avgTotalActivePowerList.get(0).getClientoperationtime()));
        }

        String avg = null == sum ? null : String.valueOf(sum / (24 * count));
        if (avgTotalActivePowerList.size() == 0) {
            avg = null;
        }

        avgTotalActivePower.setTotalactivepower(calc(avg, 1D, 3));

        StatisticF25TotalActivePower result = new StatisticF25TotalActivePower();
        result.setMaxTotalActivePower(maxTotalActivePower.getTotalactivepower());
        result.setMaxTotalActivePowerTime(maxTotalActivePower.getClientoperationtime());
        result.setMinTotalActivePower(minTotalActivePower.getTotalactivepower());
        result.setMinTotalActivePowerTime(minTotalActivePower.getClientoperationtime());
        result.setAvgTotalActivePower(avgTotalActivePower.getTotalactivepower());

        Double differ = null;
        if (null != maxTotalActivePower.getTotalactivepower() && null != minTotalActivePower.getTotalactivepower()) {
            differ = Double.valueOf(maxTotalActivePower.getTotalactivepower()) - Double.valueOf(minTotalActivePower.getTotalactivepower());
        }

        result.setDiffer(calc(null == differ ? null : String.valueOf(differ), 1D, 3));

        Double differRate = null;
        if (null != maxTotalActivePower.getTotalactivepower() && null != minTotalActivePower.getTotalactivepower()) {
            differRate = (differ / Double.valueOf(maxTotalActivePower.getTotalactivepower())) * 100;
        }

        result.setDifferRate(calc(null == differRate ? null : String.valueOf(differRate), 1D, 1));


        Double loadRate = null;
        if (null != maxTotalActivePower.getTotalactivepower() && null != avgTotalActivePower.getTotalactivepower()) {
            loadRate = (Double.valueOf(avgTotalActivePower.getTotalactivepower()) / Double.valueOf(maxTotalActivePower.getTotalactivepower())) * 100;
        }

        result.setLoadRate(calc(null == loadRate ? null : String.valueOf(loadRate), 1D, 1));

        return result;
    }
}