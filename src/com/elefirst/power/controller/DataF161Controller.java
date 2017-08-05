package com.elefirst.power.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.DataF161;
import com.elefirst.power.service.iface.IDataF161Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
 * Created by barrie on 17/2/2.
 */
@Controller
@RequestMapping("/power/data")
@Api(value = "data", description = "区域操作")
public class DataF161Controller extends BaseController {
    @Autowired
    private IDataF161Service dataF161Service;

    @RequestMapping(value = "/f161/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF161List(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestParam(value = "areaId", required = false) String areaId,
                                    @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                    @RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "rows", required = false) Integer rows
    ) {
        DataF161 template = new DataF161();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<DataF161> result = dataF161Service.getDataF161List(template);

            DataGrid dg = new DataGrid();
            long count = dataF161Service.getDataF161ListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<DataF161> result = dataF161Service.getDataF161List(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

    }

    @RequestMapping(value = "/f161/node/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF161ListByNodes(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @RequestParam(value = "node", required = false) String node,
                                           @RequestParam(value = "startTime", required = false) String startTime,
                                           @RequestParam(value = "endTime", required = false) String endTime
    ) {
        List<DataF161> nodes = new Gson().fromJson(node, new TypeToken<List<DataF161>>() {
        }.getType());

        List<DataF161> result = dataF161Service.getDataF161List(nodes, startTime, endTime);

        return new ErrorMsg(Error.SUCCESS, "success", dataF161Service.format(result));
    }
}