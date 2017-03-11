package com.elefirst.power.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.DataF33;
import com.elefirst.power.service.iface.IDataF33Service;
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
 * Created by VM on 2/11/2017.
 */
@Controller
@RequestMapping("/power/data")
@Api(value = "data", description = "区域操作")
public class DataF33Controller extends BaseController {
    @Autowired
    private IDataF33Service dataF33Service;

    @RequestMapping(value = "/f33/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF33List(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "areaId", required = false) String areaId,
                                   @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                   @RequestParam(value = "pn", required = false) String pn,
                                   @RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "rows", required = false) Integer rows
    ) {
        DataF33 template = new DataF33();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);
        template.setPn(pn);

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<DataF33> result = dataF33Service.getDataF33List(template);

            DataGrid dg = new DataGrid();
            long count = dataF33Service.getDataF33ListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<DataF33> result = dataF33Service.getDataF33List(template);
            return new ErrorMsg(Error.SUCCESS, "success", dataF33Service.format(dataF33Service.getInterval(result)));
        }

    }

    @RequestMapping(value = "/f33/node/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF33ListByNodes(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestParam(value = "node", required = false) String node,
                                          @RequestParam(value = "startTime", required = false) String startTime,
                                          @RequestParam(value = "endTime", required = false) String endTime
    ) {
        List<DataF33> nodes = new Gson().fromJson(node, new TypeToken<List<DataF33>>() {
        }.getType());

        List<DataF33> result = dataF33Service.getDataF33List(nodes, startTime, endTime);

        return new ErrorMsg(Error.SUCCESS, "success", dataF33Service.format(dataF33Service.getInterval(result)));
    }
}