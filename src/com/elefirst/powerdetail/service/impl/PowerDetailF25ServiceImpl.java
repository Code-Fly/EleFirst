package com.elefirst.powerdetail.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.elefirst.powerdetail.mapper.PowerDetailF25Mapper;
import com.elefirst.powerdetail.po.PowerDetailF25;
import com.elefirst.powerdetail.po.PowerDetailF25Example;
import com.elefirst.powerdetail.service.IPowerDetailF25Service;

@Service
public class PowerDetailF25ServiceImpl implements IPowerDetailF25Service {

	private static final Logger logger = LoggerFactory.getLogger(PowerDetailF25ServiceImpl.class);

	@Resource(name = "powerDetailF25Mapper")
	private PowerDetailF25Mapper powerDetailF25Mapper;
	
	@Override
	public List<PowerDetailF25> fetchLastPowerDetailF25ByAreaId(String areaId,int rows,int page)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PowerDetailF25> fetchLastPowerDetailF25ByCtrId(String areaId,
			List<String> ctrIds,int rows,int page) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorIds", ctrIds);
		params.put("areaId", areaId);
		if (rows > 0 && page > 0) {
			params.put("limitStart", (page - 1) * rows);
			params.put("limitEnd", rows);
		}
		List<PowerDetailF25> powerDetailF25s = powerDetailF25Mapper.myselectByExample(params);
		return powerDetailF25s;
	}

	@Override
	public List<PowerDetailF25> fetchAllPnPowerDetail(String areaId,
			String ctrId, String pnId,int rows,int page) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int fetchLastPowerDetailF25CountByCtrId(String areaId,
			List<String> ctrIds) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorIds", ctrIds);
		params.put("areaId", areaId);
		int num = powerDetailF25Mapper.myselectByExampleCount(params);
		return num;
	}

}
