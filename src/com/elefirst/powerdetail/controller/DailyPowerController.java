package com.elefirst.powerdetail.controller;

import com.alibaba.fastjson.JSON;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.entity.GeneralMessage;
import com.elefirst.base.utils.Arith;
import com.elefirst.powerdetail.po.*;
import com.elefirst.powerdetail.service.IDailyPowerService;
import com.elefirst.powerdetail.service.IMonthlyPowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dailypower/")
public class DailyPowerController {
    private static final Logger logger = LoggerFactory.getLogger(DailyPowerController.class);

    @Resource(name = "dailyPowerServiceImpl")
    private IDailyPowerService dailyPowerServiceImpl;

    @Resource(name = "monthlyPowerServiceImpl")
    private IMonthlyPowerService monthlyPowerServiceImpl;

    /**
     * 根据日期查询相关的按日负荷统计数据
     *
     * @param date
     * @param page
     * @param rows
     * @param jasonStr
     * @return
     * @throws Exception
     */
    @RequestMapping("listDailyLoad.do")
    public
    @ResponseBody
    ErrorMsg queryDailyLoad(String date, String page, String rows, String jasonStr)
            throws Exception {
        DataGrid dg = new DataGrid();
        GeneralMessage gm = new GeneralMessage();
        List<String> ctrIds = new ArrayList<String>();

        try {
            int pageNum = Integer.valueOf(page == null ? "1" : page);
            int rowsNum = Integer.valueOf(rows == null ? "10" : rows);

            Area area = JSON.parseObject(jasonStr, Area.class);

            List<Concentrator> concentrators = area.getConcentrators();
            if (concentrators == null || concentrators.size() == 0) {
                return null;
            }
            for (Concentrator concentrator : concentrators) {
                String tmpCId = concentrator.getConcentratorId();
                ctrIds.add(tmpCId);
            }
            String areaId = area.getAreaId();

            List<DailyLoad> dailyLoads = dailyPowerServiceImpl.fetchAllDailyLoad(date, areaId, ctrIds, rowsNum, pageNum, true);
            int total = dailyPowerServiceImpl.fetchAllDailyLoad(date, areaId, ctrIds, rowsNum, pageNum, false).size();
            gm.setFlag(GeneralMessage.Result.SUCCESS);
            gm.setMsg("查询相关的按日负荷统计数据成功！");
            dg.setRows(dailyLoads);
            dg.setTotal(total);
            dg.setGm(gm);
            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } catch (Exception e) {
            logger.error("查询相关的按日负荷统计数据失败！", e);
            return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
        }
    }

    /**
     * 根据日期查询相关的按电压荷统计数据
     *
     * @param date
     * @param page
     * @param rows
     * @param jasonStr
     * @return
     * @throws Exception
     */
    @RequestMapping("listDailyVoltage.do")
    public
    @ResponseBody
    ErrorMsg queryDailyVoltage(String date, String page, String rows, String jasonStr)
            throws Exception {
        DataGrid dg = new DataGrid();
        GeneralMessage gm = new GeneralMessage();
        List<String> ctrIds = new ArrayList<String>();

        try {
            int pageNum = Integer.valueOf(page == null ? "1" : page);
            int rowsNum = Integer.valueOf(rows == null ? "10" : rows);

            Area area = JSON.parseObject(jasonStr, Area.class);

            List<Concentrator> concentrators = area.getConcentrators();
            if (concentrators == null || concentrators.size() == 0) {
                return null;
            }
            for (Concentrator concentrator : concentrators) {
                String tmpCId = concentrator.getConcentratorId();
                ctrIds.add(tmpCId);
            }
            String areaId = area.getAreaId();

            List<DailyVoltage> dailyVoltages = dailyPowerServiceImpl.fetchAllDailyVoltage(date, areaId, ctrIds, rowsNum, pageNum, true);
            int total = dailyPowerServiceImpl.fetchAllDailyVoltage(date, areaId, ctrIds, rowsNum, pageNum, false).size();
            gm.setFlag(GeneralMessage.Result.SUCCESS);
            gm.setMsg("查询相关的按日电压统计数据成功！");
            dg.setRows(dailyVoltages);
            dg.setTotal(total);
            dg.setGm(gm);
            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } catch (Exception e) {
            logger.error("查询相关的按电压荷统计数据失败！", e);
            return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
        }
    }

    /**
     * 根据日期查询相关的按电流统计数据
     *
     * @param date
     * @param page
     * @param rows
     * @param jasonStr
     * @return
     * @throws Exception
     */
    @RequestMapping("listDailyCurrent.do")
    public
    @ResponseBody
    ErrorMsg queryDailyCurrent(String date, String page, String rows, String jasonStr)
            throws Exception {
        DataGrid dg = new DataGrid();
        GeneralMessage gm = new GeneralMessage();
        List<String> ctrIds = new ArrayList<String>();

        try {
            int pageNum = Integer.valueOf(page == null ? "1" : page);
            int rowsNum = Integer.valueOf(rows == null ? "10" : rows);

            Area area = JSON.parseObject(jasonStr, Area.class);

            List<Concentrator> concentrators = area.getConcentrators();
            if (concentrators == null || concentrators.size() == 0) {
                return null;
            }
            for (Concentrator concentrator : concentrators) {
                String tmpCId = concentrator.getConcentratorId();
                ctrIds.add(tmpCId);
            }
            String areaId = area.getAreaId();

            List<DailyCurrent> dailyCurrents = dailyPowerServiceImpl.fetchAllDailyCurrent(date, areaId, ctrIds, rowsNum, pageNum, true);
            int total = dailyPowerServiceImpl.fetchAllDailyCurrent(date, areaId, ctrIds, rowsNum, pageNum, false).size();
            gm.setFlag(GeneralMessage.Result.SUCCESS);
            gm.setMsg("查询相关的按日电流统计数据成功！");
            dg.setRows(dailyCurrents);
            dg.setTotal(total);
            dg.setGm(gm);
            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } catch (Exception e) {
            logger.error("查询相关的按电流统计数据失败！", e);
            return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
        }
    }

    /**
     * 根据日期查询相关的按功率因数统计数据
     *
     * @param date
     * @param page
     * @param rows
     * @param jasonStr
     * @return
     * @throws Exception
     */
    @RequestMapping("listDailyPowerFactor.do")
    public
    @ResponseBody
    ErrorMsg queryDailyPowerFactor(String date, String page, String rows, String jasonStr)
            throws Exception {
        DataGrid dg = new DataGrid();
        GeneralMessage gm = new GeneralMessage();
        List<String> ctrIds = new ArrayList<String>();

        try {
            int pageNum = Integer.valueOf(page == null ? "1" : page);
            int rowsNum = Integer.valueOf(rows == null ? "10" : rows);

            Area area = JSON.parseObject(jasonStr, Area.class);

            List<Concentrator> concentrators = area.getConcentrators();
            if (concentrators == null || concentrators.size() == 0) {
                return null;
            }
            for (Concentrator concentrator : concentrators) {
                String tmpCId = concentrator.getConcentratorId();
                ctrIds.add(tmpCId);
            }
            String areaId = area.getAreaId();

            List<DailyPowerFactor> dailyPowerFactors = dailyPowerServiceImpl.fetchAllDailyPowerFactor(date, areaId, ctrIds, rowsNum, pageNum, true);
            int total = dailyPowerServiceImpl.fetchAllDailyPowerFactor(date, areaId, ctrIds, rowsNum, pageNum, false).size();
            gm.setFlag(GeneralMessage.Result.SUCCESS);
            gm.setMsg("查询相关的按日功率因数统计数据成功！");
            dg.setRows(dailyPowerFactors);
            dg.setTotal(total);
            dg.setGm(gm);
            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } catch (Exception e) {
            logger.error("查询相关的按功率因数统计数据失败！", e);
            return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
        }
    }

    /**
     * 根据日期查询相关的电量统计数据
     *
     * @param date
     * @param page
     * @param rows
     * @param jasonStr
     * @return
     * @throws Exception
     */
    @RequestMapping("listDailyElectricity.do")
    public
    @ResponseBody
    ErrorMsg queryDailyElectricity(String date, String page, String rows, String jasonStr)
            throws Exception {
        DataGrid dg = new DataGrid();
        GeneralMessage gm = new GeneralMessage();
        List<String> ctrIds = new ArrayList<String>();

        try {
            int pageNum = Integer.valueOf(page == null ? "1" : page);
            int rowsNum = Integer.valueOf(rows == null ? "10" : rows);

            Area area = JSON.parseObject(jasonStr, Area.class);

            List<Concentrator> concentrators = area.getConcentrators();
            if (concentrators == null || concentrators.size() == 0) {
                return null;
            }
            for (Concentrator concentrator : concentrators) {
                String tmpCId = concentrator.getConcentratorId();
                ctrIds.add(tmpCId);
            }
            String areaId = area.getAreaId();

            List<DailyElectricity> dailyElectricity = dailyPowerServiceImpl.fetchAllDailyElectricity(date, areaId, ctrIds, rowsNum, pageNum);
            int total = dailyPowerServiceImpl.fetchAllDailyElectricityCount(date, areaId, ctrIds);
            gm.setFlag(GeneralMessage.Result.SUCCESS);
            gm.setMsg("查询相关的按日的电量统计数据成功！");
            dg.setRows(dailyElectricity);
            dg.setTotal(total);
            dg.setGm(gm);
            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } catch (Exception e) {
            logger.error("查询相关的按日的电量统计数据失败！", e);
            return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
        }
    }

    /**
     * 根据日期查询相关的按示数统计数据
     *
     * @param date
     * @param page
     * @param rows
     * @param jasonStr
     * @return
     * @throws Exception
     */
    @RequestMapping("listDailyDisPlay.do")
    public
    @ResponseBody
    ErrorMsg queryDailyDisPlay(String date, String page, String rows, String jasonStr)
            throws Exception {
        DataGrid dg = new DataGrid();
        GeneralMessage gm = new GeneralMessage();
        List<String> ctrIds = new ArrayList<String>();

        try {
            int pageNum = Integer.valueOf(page == null ? "1" : page);
            int rowsNum = Integer.valueOf(rows == null ? "10" : rows);

            Area area = JSON.parseObject(jasonStr, Area.class);

            List<Concentrator> concentrators = area.getConcentrators();
            if (concentrators == null || concentrators.size() == 0) {
                return null;
            }
            for (Concentrator concentrator : concentrators) {
                String tmpCId = concentrator.getConcentratorId();
                ctrIds.add(tmpCId);
            }
            String areaId = area.getAreaId();

            String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");

            List<MonthlyDemandDetail> monthlyDemandDetails = monthlyPowerServiceImpl.fetchAllMonthlyDetailDemand(vdate, areaId, ctrIds, rowsNum, pageNum, null);
            int total = monthlyPowerServiceImpl.fetchAllDailyDetailDemandCount(vdate, areaId, ctrIds, null);

            gm.setFlag(GeneralMessage.Result.SUCCESS);
            gm.setMsg("查询相关的按日示数统计数据成功！");
            dg.setRows(monthlyDemandDetails);
            dg.setTotal(total);
            dg.setGm(gm);
            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } catch (Exception e) {
            logger.error("查询相关的按示数统计数据失败！", e);
            return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
        }
    }


    @RequestMapping("queryDailyPower.do")
    public
    @ResponseBody
    ErrorMsg queryAllDailyPower(String tabName, String areaId, String concentratorId, String pn, String date)
            throws Exception {
        Map<String, String> paramMap = new HashMap<String, String>();
        try {
            //处理日期
            String dateStr = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");
            if ("负荷".equals(tabName)) {
                setTotalActivePowerDetail(areaId, concentratorId, pn, paramMap, dateStr);
            } else if ("电压".equals(tabName)) {
                setVoltageDetail(areaId, concentratorId, pn, paramMap, dateStr);
            } else if ("电流".equals(tabName)) {
                setCurrentDetail(areaId, concentratorId, pn, paramMap, dateStr);
            } else if ("功率因数".equals(tabName)) {
                setPowerFactorDetail(areaId, concentratorId, pn, paramMap, dateStr);
            } else if ("电量".equals(tabName)) {
                setElectricityDetail(areaId, concentratorId, pn, paramMap, dateStr);
            }
            logger.error("查询日用电信息成功！");
            return new ErrorMsg(Error.SUCCESS, "success", paramMap);
        } catch (Exception e) {
            logger.error("查询日用电信息失败！", e);
            return new ErrorMsg(Error.UNKNOW_EXCEPTION, "failed", null);
        }
    }

    private void setElectricityDetail(String areaId, String concentratorId,
                                      String pn, Map<String, String> paramMap, String date) throws Exception {
        List<String> ctrIds = new ArrayList<String>();
        ctrIds.add(concentratorId);
        DailyElectricity dailyElectricity = dailyPowerServiceImpl.fetchSingleDailyElectricity(date, areaId, ctrIds, pn);
        paramMap.put("totalpositiveactivePower", dailyElectricity.getTotalpositiveactivePower() + "");
        paramMap.put("rateseq1", dailyElectricity.getRateseq1() + "");
        paramMap.put("rateseq2", dailyElectricity.getRateseq2() + "");
        paramMap.put("rateseq3", dailyElectricity.getRateseq3() + "");
        paramMap.put("rateseq4", dailyElectricity.getRateseq4() + "");
        paramMap.put("type", "electricity");
    }

    private void setTotalActivePowerDetail(String areaId,
                                           String concentratorId, String pn, Map<String, String> paramMap, String date)
            throws Exception {
        //maxTotalActivePower minTotalActivePower  avgTotalActivePower
        DailyLoad dailyload = dailyPowerServiceImpl.fetchSingleDailyLoad(date, areaId, concentratorId, pn);
        //最大负荷
        paramMap.put("maxTotalActivePower", dailyload.getMaxactivepower() + "");
        paramMap.put("maxTotalActivePowerTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyload.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        //最小负荷
        paramMap.put("minTotalActivePower", dailyload.getMinactivepower() + "");
        paramMap.put("minTotalActivePowerTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyload.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        //平均负荷
        paramMap.put("avgTotalActivePower", dailyload.getAvgactivepower() + "");

        //峰谷差:最高负荷与最低负荷之差  peak-valley difference
        double peakValleyDifference = Arith.sub(dailyload.getMaxactivepower(), dailyload.getMinactivepower());
        paramMap.put("peakValleyDifference", "" + peakValleyDifference + "");

        //峰谷差率:峰谷差与最高负荷的比率
        paramMap.put("peakValleyDifferenceRate", "" + dailyload.getPeakrate() + "");
        //负荷率:平均负荷与最高负荷的比率 load factor
        paramMap.put("loadFactorRate", "" + dailyload.getLoadrate() + "");
        paramMap.put("type", "totalactivepower");
    }

    private void setPowerFactorDetail(String areaId, String concentratorId,
                                      String pn, Map<String, String> paramMap, String date) throws Exception {
        DailyPowerFactor dailyPowerFactor = dailyPowerServiceImpl.fetchSingleDailyPowerFactor(date, areaId, concentratorId, pn);
        paramMap.put("maxAPowerFactor", dailyPowerFactor.getAmaxpowerfactor() + "");
        paramMap.put("maxAPowerFactorTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyPowerFactor.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("maxBPowerFactor", dailyPowerFactor.getBmaxpowerfactor() + "");
        paramMap.put("maxBPowerFactorTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyPowerFactor.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("maxCPowerFactor", dailyPowerFactor.getCmaxpowerfactor() + "");
        paramMap.put("maxCPowerFactorTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyPowerFactor.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("maxTotalPowerFactor", dailyPowerFactor.getMaxtotalpowerfactor() + "");
        paramMap.put("maxTotalPowerFactorTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyPowerFactor.getDays(), "yyyyMMdd", "yyyy-MM-dd"));


        paramMap.put("minAPowerFactor", dailyPowerFactor.getAminpowerfactor() + "");
        paramMap.put("minAPowerFactorTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyPowerFactor.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("minBPowerFactor", dailyPowerFactor.getBminpowerfactor() + "");
        paramMap.put("minBPowerFactorTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyPowerFactor.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("minCPowerFactor", dailyPowerFactor.getCminpowerfactor() + "");
        paramMap.put("minCPowerFactorTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyPowerFactor.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("minTotalPowerFactor", dailyPowerFactor.getMintotalpowerfactor() + "");
        paramMap.put("minTotalPowerFactorTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyPowerFactor.getDays(), "yyyyMMdd", "yyyy-MM-dd"));
        paramMap.put("type", "powerfactor");
    }

    private void setCurrentDetail(String areaId, String concentratorId,
                                  String pn, Map<String, String> paramMap, String date) throws Exception {
        DailyCurrent dailyCurrent = dailyPowerServiceImpl.fetchSingleDailyCurrent(date, areaId, concentratorId, pn);

        paramMap.put("maxACurrent", dailyCurrent.getMaxacurrent() + "");
        paramMap.put("maxACurrentTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyCurrent.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("maxBCurrent", dailyCurrent.getMaxbcurrent() + "");
        paramMap.put("maxBCurrentTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyCurrent.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("maxCCurrent", dailyCurrent.getMaxccurrent() + "");
        paramMap.put("maxCCurrentTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyCurrent.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("minACurrent", dailyCurrent.getMinacurrent() + "");
        paramMap.put("minACurrentTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyCurrent.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("minBCurrent", dailyCurrent.getMinbcurrent() + "");
        paramMap.put("minBCurrentTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyCurrent.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("minCCurrent", dailyCurrent.getMinccurrent() + "");
        paramMap.put("minCCurrentTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyCurrent.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("type", "current");
    }

    private void setVoltageDetail(String areaId, String concentratorId,
                                  String pn, Map<String, String> paramMap, String date) throws Exception {
        DailyVoltage dailyVoltage = dailyPowerServiceImpl.fetchSingleVoltage(date, areaId, concentratorId, pn);

        paramMap.put("maxAVoltage", dailyVoltage.getMaxavoltage() + "");
        paramMap.put("maxAVoltageTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyVoltage.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("maxBVoltage", dailyVoltage.getMaxbvoltage() + "");
        paramMap.put("maxBVoltageTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyVoltage.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("maxCVoltage", dailyVoltage.getMaxcvoltage() + "");
        paramMap.put("maxCVoltageTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyVoltage.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("minAVoltage", dailyVoltage.getMinavoltage() + "");
        paramMap.put("minAVoltageTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyVoltage.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("minBVoltage", dailyVoltage.getMinbvoltage() + "");
        paramMap.put("minBVoltageTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyVoltage.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("minCVoltage", dailyVoltage.getMincvoltage() + "");
        paramMap.put("minCVoltage", com.elefirst.base.utils.DateUtil.StringPattern(dailyVoltage.getDays(), "yyyyMMdd", "yyyy-MM-dd"));

        paramMap.put("type", "voltage");
    }

}
