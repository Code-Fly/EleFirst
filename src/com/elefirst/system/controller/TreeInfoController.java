package com.elefirst.system.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.system.po.TreeInfo;
import com.elefirst.system.service.iface.ITreeInfoService;
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
 * Created by barrie on 17/1/30.
 */
@Controller
@RequestMapping("/system/tree")
@Api(value = "tree", description = "树形目录操作")
public class TreeInfoController extends BaseController {
    @Autowired
    private ITreeInfoService treeInfoService;

    @RequestMapping(value = "/info/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getTreeInfoList(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "rows", required = false) Integer rows
    ) {
        TreeInfo template = new TreeInfo();

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<TreeInfo> result = treeInfoService.getTreeInfoList(template);

            DataGrid dg = new DataGrid();
            int count = treeInfoService.getTreeInfoListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<TreeInfo> result = treeInfoService.getTreeInfoList(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

    }
}