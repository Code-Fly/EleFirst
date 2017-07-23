package com.elefirst.report.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.DataT031;
import com.elefirst.power.service.iface.IDataT031Service;
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
 * Created by barrie on 2017/7/23.
 */
@Controller
@RequestMapping("/report/t031")
@Api(value = "data", description = "示数操作")
public class ReportT031Controller extends BaseController {
    @Autowired
    private IDataT031Service dataT031Service;

    @RequestMapping(value = "/daily/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDailyList(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(value = "areaId", required = false) String areaId,
                                 @RequestParam(value = "startTime", required = false) String startTime,
                                 @RequestParam(value = "endTime", required = false) String endTime,
                                 @RequestParam(value = "hour", required = false) String hour,
                                 @RequestParam(value = "minute", required = false) String minute,
                                 @RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "rows", required = false) Integer rows
    ) throws Exception {
        DataT031 template = new DataT031();
        template.setAreaId(areaId);
        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
        }

        List<DataT031> result = dataT031Service.getDataT031List(template, startTime, endTime, hour, minute);


        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
}
