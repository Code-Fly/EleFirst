package com.elefirst.system.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.system.po.TreeInfo;
import com.elefirst.system.po.TreeNode;
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
            node.setIconcls(dataRecord.getIconcls());
            node.setState(dataRecord.getState());
            node.setAttributes(dataRecord.getAttributes());
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
        root.sortChildren();
        // 输出有序的树形菜单的JSON字符串
//        System.out.println(root.toString());

        JSONArray treeArr = new JSONArray();
        treeArr.add(JSONObject.fromObject(root.toString()));

        return new ErrorMsg(Error.SUCCESS, "success", treeArr);

    }
}