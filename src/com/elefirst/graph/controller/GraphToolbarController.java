package com.elefirst.graph.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.graph.po.GraphToolbarWithBLOBs;
import com.elefirst.graph.service.iface.IGraphToolbarService;
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
 * Created by barrie on 17/2/1.
 */
@Controller
@RequestMapping("/system/graph")
@Api(value = "graph", description = "区域操作")
public class GraphToolbarController extends BaseController {
    @Autowired
    private IGraphToolbarService graphToolbarService;

    @RequestMapping(value = "/toolbar/list.do")
    @ApiOperation(value = "自定义图形列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getGraphToolbarList(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "rows", required = false) Integer rows
    ) {
        GraphToolbarWithBLOBs template = new GraphToolbarWithBLOBs();

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<GraphToolbarWithBLOBs> result = graphToolbarService.getGraphToolbarList(template);

            DataGrid dg = new DataGrid();
            int count = graphToolbarService.getGraphToolbarListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<GraphToolbarWithBLOBs> result = graphToolbarService.getGraphToolbarList(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

    }
}
