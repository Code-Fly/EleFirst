package com.elefirst.graph.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.graph.service.iface.IGraphUpdateService;
import com.elefirst.power.po.DataF25FrozenMinute;
import com.elefirst.power.po.PnStat;
import com.elefirst.power.service.iface.IPnStatService;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.service.iface.IPnInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Controller
@RequestMapping("/system/graph")
@Api(value = "graph", description = "更新操作")
public class GraphUpdateController extends BaseController {
    @Autowired
    private IGraphUpdateService graphUpdateService;

    @Autowired
    private IPnInfoService pnInfoService;

    @Autowired
    private IPnStatService pnStatService;

    @RequestMapping(value = "/latest/current/list.do")
    @ApiOperation(value = "自定义图形列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getGraphUpdateList(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestBody String sData
    ) {
        JSONObject jParam = JSONObject.fromObject(sData);

        JSONObject result = new JSONObject();

        JSONArray jCurrent = jParam.getJSONArray("current");
        JSONArray newCurrent = new JSONArray();

        for (int i = 0; i < jCurrent.size(); i++) {
            JSONObject jItem = jCurrent.getJSONObject(i);
            DataF25FrozenMinute template = new DataF25FrozenMinute();
            template.setAreaId(jItem.getString("areaId"));
            template.setConcentratorId(jItem.getString("concentratorId"));
            template.setPn(jItem.getString("pn"));
            template.setPage(1);
            template.setRows(1);
            List<DataF25FrozenMinute> currentPnList = graphUpdateService.getLatestCurrentPnList(template);

            PnInfo pnTemplate = new PnInfo();
            pnTemplate.setAreaId(jItem.getString("areaId"));
            pnTemplate.setConcentratorId(jItem.getString("concentratorId"));
            pnTemplate.setPn(jItem.getString("pn"));
            List<PnInfo> pnList = pnInfoService.getPnInfoList(pnTemplate);
            if (currentPnList.size() > 0 && pnList.size() > 0) {
                jItem.put("aCurrent", calc(currentPnList.get(0).getaCurrent(), pnList.get(0).getCt(), 3));
                jItem.put("bCurrent", calc(currentPnList.get(0).getbCurrent(), pnList.get(0).getCt(), 3));
                jItem.put("cCurrent", calc(currentPnList.get(0).getcCurrent(), pnList.get(0).getCt(), 3));
                jItem.put("clientOperationTime", currentPnList.get(0).getClientoperationtime());
                newCurrent.add(jItem);
            }
        }

        result.put("current", newCurrent);

        JSONArray jSwitchState = jParam.getJSONArray("switch_state");
        JSONArray newSwitchState = new JSONArray();
        for (int i = 0; i < jSwitchState.size(); i++) {
            JSONObject jItem = jSwitchState.getJSONObject(i);
            PnStat template = new PnStat();
            template.setAreaId(jItem.getString("areaId"));
            template.setConcentratorId(jItem.getString("concentratorId"));
            template.setPn(jItem.getString("pn"));
            template.setPage(1);
            template.setRows(1);
            List<PnStat> pnStatList = graphUpdateService.getLatestPnStatList(template);

            PnInfo pnTemplate = new PnInfo();
            pnTemplate.setAreaId(jItem.getString("areaId"));
            pnTemplate.setConcentratorId(jItem.getString("concentratorId"));
            pnTemplate.setPn(jItem.getString("pn"));
            List<PnInfo> pnList = pnInfoService.getPnInfoList(pnTemplate);

            if (pnStatList.size() > 0 && pnList.size() > 0) {
                jItem.put("state", Integer.valueOf(pnStatList.get(0).getStat()));
                newSwitchState.add(jItem);
            }

        }
        result.put("switch_state", newSwitchState);


        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

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
