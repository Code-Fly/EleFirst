package com.elefirst.graph.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.exception.SessionExpiredException;
import com.elefirst.graph.po.GraphTemplateWithBLOBs;
import com.elefirst.graph.service.iface.IGraphTemplateService;
import com.elefirst.system.po.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @RequestMapping(value = "/template/detail.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getGraphTemplatedetail(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @RequestParam(value = "id") String id
    ) {
        List<GraphTemplateWithBLOBs> result = graphTemplateService.getGraphTemplateDetail(id);
        return new ErrorMsg(Error.SUCCESS, "success", result);

    }

    @RequestMapping(value = "/template/update.do")
    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg updateTreeInfo(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "id") String id,
                                   @RequestParam(value = "config") String config,
                                   @RequestParam(value = "content") String content
    ) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }

        GraphTemplateWithBLOBs template = new GraphTemplateWithBLOBs();
        template.setId(id);
        template.setConfig(config);
        template.setContent(content);
        template.setCreatePerson(userInfo.getUserName());
        template.setCreateDate(new Date());
        int result = graphTemplateService.updateGraphTemplate(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);

    }

    @RequestMapping(value = "/template/add.do")
    @ApiOperation(value = "添加", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg addGraphTemplate(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestParam(value = "config") String config,
                                     @RequestParam(value = "content") String content,
                                     @RequestParam(value = "name") String name
    ) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }

        GraphTemplateWithBLOBs template = new GraphTemplateWithBLOBs();
        template.setId(UUID.randomUUID().toString());
        template.setConfig(config);
        template.setContent(content);
        template.setName(name);
        template.setCreatePerson(userInfo.getUserName());
        template.setCreateDate(new Date());
        int result = graphTemplateService.addGraphTemplate(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/template/delete.do")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg deletePnInfo(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(value = "id") String id
    ) {
        int result = graphTemplateService.delGraphTemplate(id);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
}