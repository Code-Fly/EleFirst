package com.elefirst.system.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.exception.SessionExpiredException;
import com.elefirst.system.po.TreeInfo;
import com.elefirst.system.po.TreeNode;
import com.elefirst.system.po.UserInfo;
import com.elefirst.system.service.iface.ITreeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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
                                    @RequestParam(value = "pid") String pid,
                                    @RequestParam(value = "treeId") String treeId,
                                    @RequestParam(value = "areaId") String areaId,
                                    @RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "rows", required = false) Integer rows
    ) {
        TreeInfo template = new TreeInfo();
        if (null != areaId && !areaId.isEmpty()) {
            template.setAreaId(areaId);
        }
        if (null != treeId && !treeId.isEmpty()) {
            template.setTreeId(treeId);
        }

        if (null != pid && !pid.isEmpty()) {
            template.setPid(pid);
        }

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

    @RequestMapping(value = "/info/update.do")
    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg updateTreeInfo(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "id") String id,
                                   @RequestParam(value = "iconCls", required = false) String iconCls,
                                   @RequestParam(value = "state", required = false) String state,
                                   @RequestParam(value = "attributes", required = false) String attributes,
                                   @RequestParam(value = "name") String name
    ) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }
        
        TreeInfo template = new TreeInfo();

        if (null != name && !name.isEmpty()) {

            template.setId(id);
            if (null != iconCls && !iconCls.isEmpty()) {
                template.setIconCls(iconCls);
            }
            if (null != state && !state.isEmpty()) {
                template.setState(state);
            }
            if (null != attributes && !attributes.isEmpty()) {
                template.setAttributes(attributes);
            }

            template.setName(name);
            template.setUpdatePerson(userInfo.getUserName());
            template.setUpdateDate(new Date());
            int result = treeInfoService.updateTreeInfo(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        } else {
            return new ErrorMsg(Error.SUCCESS, "success");
        }

    }

    @RequestMapping(value = "/info/add.do")
    @ApiOperation(value = "添加", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg addTreeInfo(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam(value = "pid") String pid,
                                @RequestParam(value = "treeId") String treeId,
                                @RequestParam(value = "areaId") String areaId,
                                @RequestParam(value = "iconCls") String iconCls,
                                @RequestParam(value = "state", required = false) String state,
                                @RequestParam(value = "attributes") String attributes,
                                @RequestParam(value = "name") String name
    ) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }
        
        TreeInfo template = new TreeInfo();
        template.setId(UUID.randomUUID().toString());
        template.setPid(pid);
        template.setAreaId(areaId);
        template.setTreeId(treeId);
        template.setIconCls(iconCls);
        if (null != state && !state.isEmpty()) {
            template.setState(state);
        }
        template.setAttributes(attributes);
        template.setName(name);
        template.setCreatePerson(userInfo.getUserName());
        template.setCreateDate(new Date());
        int result = treeInfoService.addTreeInfo(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/info/delete.do")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg deleteTreeInfo(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "id") String id
    ) {
        int result = treeInfoService.delTreeInfo(id);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/info/node.do")
    @ApiOperation(value = "树形", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getTreeInfoNode(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestParam(value = "treeId", required = false) String treeId
    ) {
        TreeInfo template = new TreeInfo();
        if (null != treeId) {
            template.setTreeId(treeId);
        }

        // 读取层次数据结果集列表
        List<TreeInfo> dataList = treeInfoService.getTreeInfoList(template);

        // 节点列表（散列表，用于临时存储节点对象）
        HashMap nodeList = new HashMap();
        // 根节点
        TreeNode root = null;
        // 根据结果集构造节点列表（存入散列表）
        for (Iterator it = dataList.iterator(); it.hasNext(); ) {
            TreeInfo dataRecord = (TreeInfo) it.next();
            TreeNode node = new TreeNode();
            node.setId(dataRecord.getId());
            node.setPid(dataRecord.getPid());
            node.setText(dataRecord.getName());
            node.setIconCls(dataRecord.getIconCls());
            node.setState(dataRecord.getState());
            node.setAttributes(dataRecord.getAttributes());
            node.setCreateDate(dataRecord.getCreateDate());
            nodeList.put(node.getId(), node);
        }
        // 构造无序的多叉树
        Set entrySet = nodeList.entrySet();
        for (Iterator it = entrySet.iterator(); it.hasNext(); ) {
            TreeNode node = (TreeNode) ((Map.Entry) it.next()).getValue();
            if (node.getPid() == null || node.getPid().equals("0")) {
                root = node;
            } else {
                ((TreeNode) nodeList.get(node.getPid())).addChild(node);
            }
        }
        // 输出无序的树形菜单的JSON字符串
//        System.out.println(root.toString());
        // 对多叉树进行横向排序
        JSONArray treeArr = new JSONArray();

        if (null != root) {
            root.sortChildren();
            treeArr.add(JSONObject.fromObject(root.toString()));
        }
        // 输出有序的树形菜单的JSON字符串
//        System.out.println(root.toString());


        return new ErrorMsg(Error.SUCCESS, "success", treeArr);

    }
}