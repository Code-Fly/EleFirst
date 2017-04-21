package com.elefirst.powerdetail.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.elefirst.powerdetail.mapper.PowerDetailF25Mapper;
import com.elefirst.powerdetail.mapper.TwoRealtimeDisplayMapper;
import com.elefirst.powerdetail.po.Concentrator;
import com.elefirst.powerdetail.po.CurrentDetail;
import com.elefirst.powerdetail.po.PowerDetailF25;
import com.elefirst.powerdetail.po.PowerDetailF25Example;
import com.elefirst.powerdetail.po.PowerFactorDetail;
import com.elefirst.powerdetail.po.TotalActivePowerDetail;
import com.elefirst.powerdetail.po.TwoRealtimeDisplay;
import com.elefirst.powerdetail.po.TwoRealtimeDisplayExample;
import com.elefirst.powerdetail.po.VoltageDetail;
import com.elefirst.powerdetail.service.IPowerDetailF25Service;

@Service
public class PowerDetailF25ServiceImpl implements IPowerDetailF25Service {

	private static final Logger logger = LoggerFactory.getLogger(PowerDetailF25ServiceImpl.class);

	@Resource(name = "powerDetailF25Mapper")
	private PowerDetailF25Mapper powerDetailF25Mapper;
	
	@Resource(name = "twoRealtimeDisplayMapper")
	private TwoRealtimeDisplayMapper twoRealtimeDisplayMapper;
	
	@Override
	public List<PowerDetailF25> fetchLastPowerDetailF25ByAreaId(String areaId,int rows,int page)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PowerDetailF25> fetchLastPowerDetailF25ByCtrId(String areaId,
			List<Concentrator> concentrators,int rows,int page) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorIds", concentrators);
		params.put("areaId", areaId);
		if (rows > 0 && page > 0) {
			params.put("limitStart", (page - 1) * rows);
			params.put("limitEnd", rows);
		}
		List<PowerDetailF25> powerDetailF25s = powerDetailF25Mapper.myselectByExample(params);
		return powerDetailF25s;
	}

	@Override
	public int fetchLastPowerDetailF25CountByCtrId(String areaId,
			List<Concentrator> concentrators) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorIds", concentrators);
		params.put("areaId", areaId);
		int num = powerDetailF25Mapper.myselectByExampleCount(params);
		return num;
	}

	@Override
	public List<TwoRealtimeDisplay> fetchLastDisplayDetailByCtrId(
			String areaId, List<Concentrator> concentrators, int rows, int page)
			throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorIds", concentrators);
		params.put("areaId", areaId);
		if (rows > 0 && page > 0) {
			params.put("limitStart", (page - 1) * rows);
			params.put("limitEnd", rows);
		}
		List<TwoRealtimeDisplay> twoRealtimeDisplays = twoRealtimeDisplayMapper.myselectByExample(params);
		return twoRealtimeDisplays;
	}

	@Override
	public int fetchLastDisplayDetailCountByCtrId(String areaId,
			List<Concentrator> concentrators) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorIds", concentrators);
		params.put("areaId", areaId);
		int num = twoRealtimeDisplayMapper.myselectByExampleCount(params);
		return num;
	}

	@Override
	public VoltageDetail fetchVoltageDetail(String areaId, String ctrId,
			String pnId,String date) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorId", ctrId);
		params.put("areaId", areaId);
		params.put("pn", pnId);
		String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");
		params.put("date", vdate);
		VoltageDetail voltageDetail = powerDetailF25Mapper.queryVoltageDetail(params);
		return voltageDetail;
	}

	@Override
	public PowerDetailF25 fetchLastPowerDetailF25(String date,String areaId,String ctrId,String pnId,String ua,String ub,String uc,String ia,String ib,String ic,String pfa,String pfb,String pfc,String tpf,String tap){
		PowerDetailF25Example condition = new PowerDetailF25Example();
		PowerDetailF25Example.Criteria criteria = condition.createCriteria();
		if(ua != null && ua.length() > 0){
			criteria.andAVoltageEqualTo(ua);
		}
		if(ub != null && ub.length() > 0){
			criteria.andBVoltageEqualTo(ub);
		}
		if(uc != null && uc.length() > 0){
			criteria.andCVoltageEqualTo(uc);
		}
		if(ia != null && ia.length() > 0){
			criteria.andACurrentEqualTo(ia);
		}
		if(ib != null && ib.length() > 0){
			criteria.andBCurrentEqualTo(ib);
		}
		if(ic != null && ic.length() > 0){
			criteria.andCCurrentEqualTo(ic);
		}
		if(pfa != null && pfa.length() > 0){
			criteria.andAPowerfactorEqualTo(pfa);
		}
		if(pfb != null && pfb.length() > 0){
			criteria.andBPowerfactorEqualTo(pfb);
		}
		if(pfc != null && pfc.length() > 0){
			criteria.andCPowerfactorEqualTo(pfc);
		}
		if(tpf != null && tpf.length() > 0){
			criteria.andTotalpowerfactorEqualTo(tpf);
		}
		if(tap != null && tap.length() > 0){
			criteria.andTotalactivepowerEqualTo(tap);
		}
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");
			criteria.andClientoperationtimeLike(vdate+"%");
		}
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdEqualTo(ctrId);
		criteria.andPnEqualTo(pnId);
		//排序后只取第一条记录返回
		//condition.setOrderByClause("clientOperationTime DESC");
		condition.setLimitStart(0);
		condition.setLimitEnd(1);
		List<PowerDetailF25> powerDetailF25 = powerDetailF25Mapper.selectByExample(condition);
		return powerDetailF25.get(0);
	}

	@Override
	public CurrentDetail fetchCurrentDetail(String areaId, String ctrId,
			String pnId,String date) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorId", ctrId);
		params.put("areaId", areaId);
		params.put("pn", pnId);
		String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");
		params.put("date", vdate);
		CurrentDetail currentDetail = powerDetailF25Mapper.queryCurrentDetail(params);
		return currentDetail;
	}

	@Override
	public PowerFactorDetail fetchPowerFactorDetail(String areaId,
			String ctrId, String pnId,String date) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorId", ctrId);
		params.put("areaId", areaId);
		params.put("pn", pnId);
		String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");
		params.put("date", vdate);
		PowerFactorDetail powerFactorDetail = powerDetailF25Mapper.queryPowerFactorDetail(params);
		return powerFactorDetail;
	}

	@Override
	public TotalActivePowerDetail fetchTotalActivePowerDetail(String areaId,
			String ctrId, String pnId,String date) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorId", ctrId);
		params.put("areaId", areaId);
		params.put("pn", pnId);
		String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");
		params.put("date", vdate);
		TotalActivePowerDetail totalActivePowerDetail = powerDetailF25Mapper.queryTotalActivePowerDetail(params);
		return totalActivePowerDetail;
	}

	@Override
	public List<TwoRealtimeDisplay> fetchAllDisplayDetailByPn(String date,String areaId,
			String ctrId, String pn, int rows, int page)
			throws Exception {
		TwoRealtimeDisplayExample condition = new TwoRealtimeDisplayExample();
		TwoRealtimeDisplayExample.Criteria criteria = condition.createCriteria();
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");
			criteria.andClientoperationtimeLike(vdate+"%");
		}
		criteria.andConcentratorIdEqualTo(ctrId);
		criteria.andAreaIdEqualTo(areaId);
		criteria.andPnEqualTo(pn);
		condition.setOrderByClause("clientOperationTime DESC");
		if(rows > 0 && page > 0){
			condition.setLimitStart((page - 1) * rows);
            condition.setLimitEnd(rows);
		}
		List<TwoRealtimeDisplay>  twoRealtimeDisplays = twoRealtimeDisplayMapper.selectByExample(condition);
		return twoRealtimeDisplays;
	}

	@Override
	public int fetchAllDisplayDetailCountByPn(String date,String areaId,
			String ctrId, String pn) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorId", ctrId);
		params.put("areaId", areaId);
		params.put("pn", pn);
		String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");
		params.put("date", vdate);
		int num = twoRealtimeDisplayMapper.displayDetailCount(params);
		return num;
	}
}
