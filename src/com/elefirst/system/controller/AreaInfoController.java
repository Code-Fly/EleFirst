package com.elefirst.system.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.system.po.AreaInfo;
import com.elefirst.system.service.iface.IAreaInfoService;
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
@RequestMapping("/system/area")
@Api(value = "area", description = "区域操作")
public class AreaInfoController extends BaseController {
    @Autowired
    private IAreaInfoService areaInfoService;

    @RequestMapping(value = "/info/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getAreaInfoList(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestParam(value = "areaId", required = false) String areaId,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "rows", required = false) Integer rows
    ) {
        AreaInfo template = new AreaInfo();

        if (null != areaId) {
            template.setAreaId(areaId);
        }

        if (null != name) {
            template.setName(areaId);
        }

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<AreaInfo> result = areaInfoService.getAreaInfoList(template);

            DataGrid dg = new DataGrid();
            int count = areaInfoService.getAreaInfoListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<AreaInfo> result = areaInfoService.getAreaInfoList(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

    }
}
