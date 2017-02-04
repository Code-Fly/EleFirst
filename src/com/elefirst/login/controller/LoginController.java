package com.elefirst.login.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.system.po.MenuInfo;
import com.elefirst.system.service.iface.IMenuInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by barrie on 17/1/14.
 */
@Controller
@RequestMapping("/user/")
public class LoginController extends BaseController {

    @Resource(name = "menuInfoServiceImpl")
    private IMenuInfoService menuInfoServiceImpl;

    @RequestMapping("login.do")
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

        //获取一级菜单
        List<String> oneLevelMenuIds = new ArrayList<String>();
        oneLevelMenuIds.add("1");
        oneLevelMenuIds.add("2");
        oneLevelMenuIds.add("3");
        oneLevelMenuIds.add("4");
        List<MenuInfo> oneLevelmenuInfos = menuInfoServiceImpl.fetchOneLevelMenuInfo(oneLevelMenuIds);
        //根据一级菜单编码获取对应的二级菜单
        List<MenuInfo> twoLevelmenuInos01 = menuInfoServiceImpl.fetchTwoLevelMenuInfo("01");
        List<MenuInfo> twoLevelmenuInos02 = menuInfoServiceImpl.fetchTwoLevelMenuInfo("02");
        List<MenuInfo> twoLevelmenuInos03 = menuInfoServiceImpl.fetchTwoLevelMenuInfo("03");
        List<MenuInfo> twoLevelmenuInos04 = menuInfoServiceImpl.fetchTwoLevelMenuInfo("04");
        session.setAttribute("oneLevelmenuInfos", oneLevelmenuInfos);
        session.setAttribute("twoLevelmenuInos01", twoLevelmenuInos01);
        session.setAttribute("twoLevelmenuInos02", twoLevelmenuInos02);
        session.setAttribute("twoLevelmenuInos03", twoLevelmenuInos03);
        session.setAttribute("twoLevelmenuInos04", twoLevelmenuInos04);
        return "redirect:/index.jsp";
    }

}
