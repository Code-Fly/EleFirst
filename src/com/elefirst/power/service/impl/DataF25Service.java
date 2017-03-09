package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF25DAO;
import com.elefirst.power.po.DataF25;
import com.elefirst.power.po.DataF25Example;
import com.elefirst.power.service.iface.IDataF25Service;
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
public class DataF25Service extends BaseService implements IDataF25Service {
    @Autowired
    private IDataF25DAO dataF25DAO;

    @Autowired
    private IPnInfoService pnInfoService;

    @Override
    public List<DataF25> getDataF25List(DataF25 template) {
        DataF25Example condition = new DataF25Example();
        DataF25Example.Criteria criteria = condition.createCriteria();

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
        return dataF25DAO.getDataF25List(condition);
    }

    @Override
    public List<DataF25> getDataF25List(List<DataF25> nodes, String startDate, String endDate) {
        DataF25Example condition = new DataF25Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataF25 node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andClientoperationtimeGreaterThanOrEqualTo(startDate)
                    .andClientoperationtimeLessThan(endDate)
            ;
        }
        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF25DAO.getDataF25List(condition);
    }

    @Override
    public int getDataF25ListCount(DataF25 template) {
        DataF25Example condition = new DataF25Example();
        DataF25Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        return dataF25DAO.getDataF25ListCount(condition);
    }

    @Override
    public List<DataF25> getDataF25Detail(String id) {
        DataF25Example condition = new DataF25Example();
        DataF25Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF25DAO.getDataF25List(condition);
    }

    @Override
    public int addDataF25(DataF25 template) {
        return dataF25DAO.addDataF25(template);
    }

    @Override
    public int updateDataF25(DataF25 template) {
        DataF25Example condition = new DataF25Example();
        DataF25Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return dataF25DAO.updateDataF25(condition, template);
    }

    @Override
    public int delDataF25(String id) {
        DataF25Example condition = new DataF25Example();
        DataF25Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF25DAO.delDataF25(condition);
    }

    @Override
    public List<DataF25> format(List<DataF25> data) {
        PnInfo template = new PnInfo();
        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(template);
        List<DataF25> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DataF25 item = data.get(i);
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

    private PnInfo getPnInfo(List<PnInfo> pnInfos, DataF25 item) {
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