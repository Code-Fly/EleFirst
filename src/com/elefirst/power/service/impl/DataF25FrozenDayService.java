package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF25FrozenDayDAO;
import com.elefirst.power.po.DataF25FrozenDay;
import com.elefirst.power.po.DataF25FrozenDayExample;
import com.elefirst.power.service.iface.IDataF25FrozenDayService;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.service.iface.IPnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Service
public class DataF25FrozenDayService extends BaseService implements IDataF25FrozenDayService {
    @Autowired
    private IDataF25FrozenDayDAO dataF25FrozenDayDAO;

    @Autowired
    private IPnInfoService pnInfoService;

    @Override
    public List<DataF25FrozenDay> getDataF25FrozenDayList(DataF25FrozenDay template) {
        DataF25FrozenDayExample condition = new DataF25FrozenDayExample();
        DataF25FrozenDayExample.Criteria criteria = condition.createCriteria();

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
        return dataF25FrozenDayDAO.getDataF25FrozenDayList(condition);
    }

    @Override
    public List<DataF25FrozenDay> getDataF25FrozenDayList(List<DataF25FrozenDay> nodes, String startTime, String endTime) {
        DataF25FrozenDayExample condition = new DataF25FrozenDayExample();
        for (int i = 0; i < nodes.size(); i++) {
            DataF25FrozenDay node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andClientoperationtimeGreaterThanOrEqualTo(startTime)
                    .andClientoperationtimeLessThan(endTime)
            ;
        }
        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF25FrozenDayDAO.getDataF25FrozenDayList(condition);
    }

    @Override
    public List<DataF25FrozenDay> getDataF25FrozenDayList(DataF25FrozenDay node, String time) throws ParseException {
        DataF25FrozenDayExample condition = new DataF25FrozenDayExample();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = formatter.parse(time);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        String endTime = formatter.format(cal.getTime());

        condition.or()
                .andAreaIdEqualTo(node.getAreaId())
                .andConcentratorIdEqualTo(node.getConcentratorId())
                .andPnEqualTo(node.getPn())
                .andClientoperationtimeGreaterThanOrEqualTo(time)
                .andClientoperationtimeLessThan(endTime)
        ;

        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF25FrozenDayDAO.getDataF25FrozenDayList(condition);
    }

    @Override
    public List<DataF25FrozenDay> getDataF25FrozenDayList(List<DataF25FrozenDay> nodes, List<String> times) throws ParseException {
        DataF25FrozenDayExample condition = new DataF25FrozenDayExample();

        for (int i = 0; i < nodes.size(); i++) {
            DataF25FrozenDay node = nodes.get(i);
            for (int j = 0; j < times.size(); j++) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                Date time = formatter.parse(times.get(j));

                Calendar cal = Calendar.getInstance();
                cal.setTime(time);
                cal.add(Calendar.DAY_OF_MONTH, 1);
                String endTime = formatter.format(cal.getTime());

                condition.or()
                        .andAreaIdEqualTo(node.getAreaId())
                        .andConcentratorIdEqualTo(node.getConcentratorId())
                        .andPnEqualTo(node.getPn())
                        .andClientoperationtimeGreaterThanOrEqualTo(times.get(j))
                        .andClientoperationtimeLessThan(endTime)
                ;
            }
        }

        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF25FrozenDayDAO.getDataF25FrozenDayList(condition);
    }

    @Override
    public int getDataF25FrozenDayListCount(DataF25FrozenDay template) {
        DataF25FrozenDayExample condition = new DataF25FrozenDayExample();
        DataF25FrozenDayExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        return dataF25FrozenDayDAO.getDataF25FrozenDayListCount(condition);
    }

    @Override
    public List<DataF25FrozenDay> getDataF25FrozenDayDetail(String id) {
        DataF25FrozenDayExample condition = new DataF25FrozenDayExample();
        DataF25FrozenDayExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF25FrozenDayDAO.getDataF25FrozenDayList(condition);
    }

    @Override
    public int addDataF25FrozenDay(DataF25FrozenDay template) {
        return dataF25FrozenDayDAO.addDataF25FrozenDay(template);
    }

    @Override
    public int updateDataF25FrozenDay(DataF25FrozenDay template) {
        DataF25FrozenDayExample condition = new DataF25FrozenDayExample();
        DataF25FrozenDayExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return dataF25FrozenDayDAO.updateDataF25FrozenDay(condition, template);
    }

    @Override
    public int delDataF25FrozenDay(String id) {
        DataF25FrozenDayExample condition = new DataF25FrozenDayExample();
        DataF25FrozenDayExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF25FrozenDayDAO.delDataF25FrozenDay(condition);
    }

    @Override
    public List<DataF25FrozenDay> format(List<DataF25FrozenDay> data) {
        PnInfo template = new PnInfo();
        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(template);
        List<DataF25FrozenDay> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DataF25FrozenDay item = data.get(i);
            PnInfo pnInfo = getPnInfo(pnInfos, item);
            if (null != pnInfo) {
                item.setTotalactivepower(calc(item.getTotalactivepower(), pnInfo.getCt() * pnInfo.getPt()));
                item.setaActivepower(calc(item.getaActivepower(), pnInfo.getCt() * pnInfo.getPt()));
                item.setbActivepower(calc(item.getbActivepower(), pnInfo.getCt() * pnInfo.getPt()));
                item.setcActivepower(calc(item.getcActivepower(), pnInfo.getCt() * pnInfo.getPt()));

                item.setTotalreactivepower(calc(item.getTotalreactivepower(), pnInfo.getCt() * pnInfo.getPt()));
                item.setaReactivepower(calc(item.getaReactivepower(), pnInfo.getCt() * pnInfo.getPt()));
                item.setbReactivepower(calc(item.getbReactivepower(), pnInfo.getCt() * pnInfo.getPt()));
                item.setcReactivepower(calc(item.getcReactivepower(), pnInfo.getCt() * pnInfo.getPt()));

                item.setaVoltage(calc(item.getaVoltage(), pnInfo.getPt()));
                item.setbVoltage(calc(item.getbVoltage(), pnInfo.getPt()));
                item.setcVoltage(calc(item.getcVoltage(), pnInfo.getPt()));

                item.setaCurrent(calc(item.getaCurrent(), pnInfo.getCt()));
                item.setbCurrent(calc(item.getbCurrent(), pnInfo.getCt()));
                item.setcCurrent(calc(item.getcCurrent(), pnInfo.getCt()));

                item.setZeroSequenceCurrent(calc(item.getZeroSequenceCurrent(), pnInfo.getCt()));

                item.setTotalapparentpower(calc(item.getTotalapparentpower(), pnInfo.getCt() * pnInfo.getPt()));
                item.setaApparentpower(calc(item.getaApparentpower(), pnInfo.getCt() * pnInfo.getPt()));
                item.setbApparentpower(calc(item.getbApparentpower(), pnInfo.getCt() * pnInfo.getPt()));
                item.setcApparentpower(calc(item.getcApparentpower(), pnInfo.getCt() * pnInfo.getPt()));

            }
            result.add(item);
        }
        return result;
    }

    private PnInfo getPnInfo(List<PnInfo> pnInfos, DataF25FrozenDay item) {
        for (int i = 0; i < pnInfos.size(); i++) {
            PnInfo pnInfo = pnInfos.get(i);
            if (pnInfo.getAreaId().equals(item.getAreaId()) && pnInfo.getConcentratorId().equals(item.getConcentratorId()) && pnInfo.getPn().equals(item.getPn())) {
                return pnInfo;
            }
        }
        return null;
    }

    private String calc(String org, Double num) {
        if (null != org) {
            return String.valueOf(Double.valueOf(org) * num);
        }
        return null;
    }
}