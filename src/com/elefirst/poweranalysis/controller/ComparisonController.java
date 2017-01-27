package com.elefirst.poweranalysis.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.poweranalysis.po.PowerAnalysisComparisonF25;
import com.elefirst.poweranalysis.po.PowerAnalysisF25;
import com.elefirst.poweranalysis.service.iface.IPowerAnalysisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by barrie on 17/1/28.
 */
@Controller
@RequestMapping("/poweranalysis/comparison")
@Api(value = "poweranalysis", description = "对比分析")
public class ComparisonController extends BaseController {
    @Autowired
    private IPowerAnalysisService powerAnalysisService;

    @RequestMapping(value = "/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getComparisonChart(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "rows", required = false) Integer rows
    ) {
        List<PowerAnalysisF25> param = new ArrayList<>();
        PowerAnalysisF25 item1 = new PowerAnalysisF25();
        item1.setAreaId("1");
        item1.setConcentratorId("417");
        item1.setPn("1");
        param.add(item1);

        PowerAnalysisF25 item2 = new PowerAnalysisF25();
        item2.setAreaId("1");
        item2.setConcentratorId("417");
        item2.setPn("2");
        param.add(item2);

        List<PowerAnalysisComparisonF25> list = powerAnalysisService.getComparisonChart(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }
}
