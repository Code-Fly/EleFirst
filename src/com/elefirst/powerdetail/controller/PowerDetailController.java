package com.elefirst.powerdetail.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.GeneralMessage;
import com.elefirst.powerdetail.po.Area;
import com.elefirst.powerdetail.po.Concentrator;
import com.elefirst.powerdetail.po.PowerDetailF25;
import com.elefirst.powerdetail.service.IPowerDetailF25Service;


@Controller
@RequestMapping("/powerdetail/")
public class PowerDetailController {
	
	private static final Logger logger = LoggerFactory.getLogger(PowerDetailController.class);
	
	@Resource(name = "powerDetailF25ServiceImpl")
	private IPowerDetailF25Service powerDetailF25ServiceImpl;
	
	
	/**
	 * 根据传入的条件,获取每个区域下相关集线器对应的监测点最新一条电力记录数(根据发送时间)
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listCurrentDetailPower.do")
	public @ResponseBody DataGrid queryCurrentDetailPower(String page, String rows,String jasonStr)
			throws Exception {
		DataGrid dg = new DataGrid();
		GeneralMessage gm = new GeneralMessage();
		StringBuffer sb = new StringBuffer();
		List<String> ctrIds = new ArrayList<String>();
		try {
			int pageNum = Integer.valueOf(page == null ? "1" : page);
			int rowsNum = Integer.valueOf(rows == null ? "10" : rows);
			
			Area area = JSON.parseObject(jasonStr,Area.class);
			
			List<Concentrator> concentrators = area.getConcentrators();
			if(concentrators == null && concentrators.size() == 0){
				return null;
			}
			for (Concentrator concentrator : concentrators) {
				String tmpCId = concentrator.getConcentratorId();
				ctrIds.add(tmpCId);
			}
			String areaId = area.getAreaId();
//			String ctrIds = sb.toString();
//			ctrIds = ctrIds.substring(0,ctrIds.length()-1);
			
			List<PowerDetailF25> powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25ByCtrId(areaId, ctrIds, rowsNum, pageNum);
			int total = powerDetailF25ServiceImpl.fetchLastPowerDetailF25CountByCtrId(areaId, ctrIds);
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询实时监测点用电数据成功！");
			dg.setRows(powerDetailF25);
			dg.setTotal(total);
			dg.setGm(gm);
		} catch (Exception e) {
			dg = null;
			logger.error("查询实时监测点用电数据失败！", e);
		}
		return dg;
	}
}
