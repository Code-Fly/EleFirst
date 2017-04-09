package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF25FrozenMinuteDAO;
import com.elefirst.power.po.DataF25FrozenMinute;
import com.elefirst.power.po.DataF25FrozenMinuteExample;
import com.elefirst.power.service.iface.IDataF25FrozenMinuteService;
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
    public List<DataF25FrozenMinute> getDataF25FrozenMinuteList(DataF25FrozenMinute node, String time) throws ParseException {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();

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
        ;

        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteList(condition);
    }

    @Override
    public List<DataF25FrozenMinute> getDataF25FrozenMinuteList(List<DataF25FrozenMinute> nodes, List<String> times) throws ParseException {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();

        for (int i = 0; i < nodes.size(); i++) {
            DataF25FrozenMinute node = nodes.get(i);
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
                ;
            }
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
        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteSumList(condition);
    }

    @Override
    public List<DataF25FrozenMinute> getDataF25FrozenMinuteSumList(DataF25FrozenMinute node, String time) throws ParseException {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();

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
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteSumList(condition);
    }

    @Override
    public List<DataF25FrozenMinute> getDataF25FrozenMinuteSumList(List<DataF25FrozenMinute> nodes, List<String> times) throws ParseException {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();

        for (int i = 0; i < nodes.size(); i++) {
            DataF25FrozenMinute node = nodes.get(i);
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
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteSumList(condition);
    }

    @Override
    public List<DataF25FrozenMinute> getDataF25FrozenMinuteSumList(List<DataF25FrozenMinute> nodes, String time) throws ParseException {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();

        for (int i = 0; i < nodes.size(); i++) {
            DataF25FrozenMinute node = nodes.get(i);
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
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteSumList(condition);
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
                return String.valueOf(String.format("%." + precision + "f", Double.valueOf(org) * num));
            }
        }
        return null;
    }
}