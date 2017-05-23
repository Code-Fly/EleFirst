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

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public List<DataF25> getDataF25SumList(DataF25 template) {
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
        criteria.andClientoperationtimeIsNotNull();
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`sendTime` ASC");
        return dataF25DAO.getDataF25SumList(condition);
    }

    @Override
    public List<DataF25> getDataF25SumList(List<DataF25> nodes, String startTime, String endTime) {
        DataF25Example condition = new DataF25Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataF25 node = nodes.get(i);
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
        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF25DAO.getDataF25SumList(condition);
    }

    @Override
    public List<DataF25> getDataF25SumList(DataF25 node, String time) throws ParseException {
        DataF25Example condition = new DataF25Example();

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

        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF25DAO.getDataF25SumList(condition);
    }

    @Override
    public List<DataF25> getDataF25SumList(List<DataF25> nodes, List<String> times) throws ParseException {
        DataF25Example condition = new DataF25Example();

        for (int i = 0; i < nodes.size(); i++) {
            DataF25 node = nodes.get(i);
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
        }

        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF25DAO.getDataF25SumList(condition);
    }

    @Override
    public List<DataF25> getDataF25SumList(List<DataF25> nodes, String time) throws ParseException {
        DataF25Example condition = new DataF25Example();

        for (int i = 0; i < nodes.size(); i++) {
            DataF25 node = nodes.get(i);
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

        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF25DAO.getDataF25SumList(condition);
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
    public List<DataF25> getMaxValue(List<DataF25> nodes, String startTime, String endTime, String key) {
        DataF25Example condition = new DataF25Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataF25 node = nodes.get(i);
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
        return dataF25DAO.getDataF25SumList(condition);
    }

    @Override
    public List<DataF25> getMinValue(List<DataF25> nodes, String startTime, String endTime, String key) {
        DataF25Example condition = new DataF25Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataF25 node = nodes.get(i);
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
        return dataF25DAO.getDataF25SumList(condition);
    }

    @Override
    public PnInfo getPnInfo(List<PnInfo> pnInfos, DataF25 item) {
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