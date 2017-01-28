package com.elefirst.system.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.system.po.ConcentratorInfo;
import com.elefirst.system.service.iface.IConcentratorInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
@Controller
@RequestMapping("/system/concentrator")
@Api(value = "concentrator", description = "监测点操作")
public class ConcentratorInfoController extends BaseController {
    @Autowired
    private IConcentratorInfoService concentratorInfoService;

    @RequestMapping(value = "/info/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getConcentratorInfoList(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "rows", required = false) Integer rows
    ) {
        ConcentratorInfo template = new ConcentratorInfo();

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<ConcentratorInfo> result = concentratorInfoService.getConcentratorInfoList(template);

            DataGrid dg = new DataGrid();
            int count = concentratorInfoService.getConcentratorInfoListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<ConcentratorInfo> result = concentratorInfoService.getConcentratorInfoList(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

    }
}
