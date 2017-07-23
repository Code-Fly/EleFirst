package com.elefirst.power.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.DataT031;
import com.elefirst.power.service.iface.IDataT031Service;
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
public class DataT031Controller extends BaseController {
    @Autowired
    private IDataT031Service dataT031Service;

    @RequestMapping(value = "/t031/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataT031List(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestParam(value = "areaId", required = false) String areaId,
                                    @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                    @RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "rows", required = false) Integer rows
    ) {
        DataT031 template = new DataT031();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<DataT031> result = dataT031Service.getDataT031List(template);

            DataGrid dg = new DataGrid();
            long count = dataT031Service.getDataT031ListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<DataT031> result = dataT031Service.getDataT031List(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

    }

    @RequestMapping(value = "/t031/node/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataT031ListByNodes(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @RequestParam(value = "node", required = false) String node,
                                           @RequestParam(value = "startTime", required = false) String startTime,
                                           @RequestParam(value = "endTime", required = false) String endTime
    ) {
        List<DataT031> nodes = new Gson().fromJson(node, new TypeToken<List<DataT031>>() {
        }.getType());

        List<DataT031> result = dataT031Service.getDataT031List(nodes, startTime, endTime);

        return new ErrorMsg(Error.SUCCESS, "success", dataT031Service.format(result));
    }
}