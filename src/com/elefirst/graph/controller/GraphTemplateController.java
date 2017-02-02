package com.elefirst.graph.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.graph.po.GraphTemplateWithBLOBs;
import com.elefirst.graph.service.iface.IGraphTemplateService;
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
@RequestMapping("/system/graph")
@Api(value = "graph", description = "区域操作")
public class GraphTemplateController extends BaseController {
    @Autowired
    private IGraphTemplateService graphTemplateService;

    @RequestMapping(value = "/template/list.do")
    @ApiOperation(value = "模板", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getGraphTemplateList(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "rows", required = false) Integer rows
    ) {
        GraphTemplateWithBLOBs template = new GraphTemplateWithBLOBs();

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<GraphTemplateWithBLOBs> result = graphTemplateService.getGraphTemplateList(template);

            DataGrid dg = new DataGrid();
            int count = graphTemplateService.getGraphTemplateListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<GraphTemplateWithBLOBs> result = graphTemplateService.getGraphTemplateList(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

    }
}