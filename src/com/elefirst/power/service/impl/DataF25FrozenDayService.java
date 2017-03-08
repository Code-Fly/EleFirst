package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF25FrozenDayDAO;
import com.elefirst.power.po.DataF25FrozenDay;
import com.elefirst.power.po.DataF25FrozenDayExample;
import com.elefirst.power.service.iface.IDataF25FrozenDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}