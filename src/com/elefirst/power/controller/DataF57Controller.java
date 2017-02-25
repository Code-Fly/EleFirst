package com.elefirst.power.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.connector.entity.PageResults;
import com.elefirst.power.service.iface.IDataF57Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by barrie on 17/2/24.
 */
@Controller
@RequestMapping("/power/data")
@Api(value = "data", description = "区域操作")
public class DataF57Controller extends BaseController {
    @Autowired
    private IDataF57Service dataF57Service;

    @RequestMapping(value = "/f57/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF33List(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "areaId", required = false) String areaId,
                                   @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                   @RequestParam(value = "node", required = false) String node,
                                   @RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "rows", required = false) Integer rows
    ) {

        PageResults result = dataF57Service.getDataF57List();
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
}