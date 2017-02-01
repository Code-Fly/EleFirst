package com.elefirst.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.entity.GeneralMessage;
import com.elefirst.powerdetail.po.Area;
import com.elefirst.powerdetail.po.Concentrator;
import com.elefirst.powerdetail.po.MonthlyLoad;
import com.elefirst.system.po.MenuInfo;
import com.elefirst.system.service.iface.IMenuInfoService;

/**
 * 用户信息管理的控制器
 * 
 * @author 金路
 *
 */
@Controller
@RequestMapping("/system/menuinfo/")
public class MenuInfoController{

	private static final Logger logger = LoggerFactory.getLogger(MenuInfoController.class);
	
	@Resource(name = "menuInfoServiceImpl")
	private IMenuInfoService menuInfoServiceImpl;

	@RequestMapping("updatemenuinfo.do")
	public @ResponseBody ErrorMsg updateMenuInfo(String isEnable,String idsJason) {
		try {
			MenuInfo menuInfo = new MenuInfo();
			menuInfo.setIsenable(isEnable);
			String[] strs = idsJason.split(",");
			List<String> ids = new ArrayList<String>();
			for (String str : strs) {
				ids.add(str);
			}
			menuInfoServiceImpl.modifyMenuInfo(menuInfo,ids);
			logger.info("菜单状态更新成功!");
			return new ErrorMsg(Error.SUCCESS, "success", null);
		} catch (Exception e) {
			logger.error("{}", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	@RequestMapping("listAllMenu.do")
	public @ResponseBody ErrorMsg queryAllMenuInfo(String page, String rows)
			throws Exception {
		DataGrid dg = new DataGrid();
		GeneralMessage gm = new GeneralMessage();
		List<String> ctrIds = new ArrayList<String>();
		
		try {
			int pageNum = Integer.valueOf(page == null ? "1" : page);
			int rowsNum = Integer.valueOf(rows == null ? "10" : rows);
			
			List<MenuInfo> menuInfos = menuInfoServiceImpl.fetchAllMenuInfo(rowsNum, pageNum, true);
			int total = menuInfoServiceImpl.fetchAllMenuInfo(rowsNum, pageNum, false).size();
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询所有菜单数据成功！");
			dg.setRows(menuInfos);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询所有菜单数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
}
