package com.elefirst.login.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.utils.ConfigUtil;
import com.elefirst.base.utils.Const;
import com.elefirst.system.po.AreaInfoWithBLOBs;
import com.elefirst.system.po.MenuInfo;
import com.elefirst.system.po.UserInfo;
import com.elefirst.system.po.UserInfoCustom;
import com.elefirst.system.service.iface.IAreaInfoService;
import com.elefirst.system.service.iface.IMenuInfoService;
import com.elefirst.system.service.iface.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
@RequestMapping("/")
public class LoginController extends BaseController {

    @Resource(name = "menuInfoServiceImpl")
    private IMenuInfoService menuInfoServiceImpl;

    @Autowired
    private IAreaInfoService areaInfoService;

    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping("index.do")
    public String index(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        UserInfoCustom userInfo = loadUserInfo(session);
        loadMenu(session);
        String areaId = request.getParameter("id");
        if (null != areaId && !areaId.isEmpty()) {
            AreaInfoWithBLOBs template = new AreaInfoWithBLOBs();
            template.setAreaId(areaId);
            List<AreaInfoWithBLOBs> result = areaInfoService.getAreaInfoList(template);
            if (result.size() > 0) {
                session.setAttribute("areaInfo", result.get(0));
                session.setAttribute("treeId", areaId);
            }
        } else {
            AreaInfoWithBLOBs template = new AreaInfoWithBLOBs();
            template.setAreaId(userInfo.getAreaId());
            List<AreaInfoWithBLOBs> result = areaInfoService.getAreaInfoList(template);
            if (result.size() > 0) {
                session.setAttribute("areaInfo", result.get(0));
                session.setAttribute("treeId", userInfo.getAreaId());
            }
        }
        return "index";
    }

    @RequestMapping("login.do")
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        loadAreaInfo(session);
        return "login";
    }

    public UserInfoCustom loadUserInfo(HttpSession session) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        UserInfo template = new UserInfo();
        template.setUserName(user.getUsername());
        List<UserInfoCustom> users = userInfoService.getUserInfoExtends(template);
        if (users.size() > 0) {
            session.setAttribute("userInfo", users.get(0));
            return users.get(0);
        }
        return null;
    }

    public void loadAreaInfo(HttpSession session) {

        AreaInfoWithBLOBs template = new AreaInfoWithBLOBs();
        template.setAreaId(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_AREA_ID));
        List<AreaInfoWithBLOBs> result = areaInfoService.getAreaInfoList(template);
        if (result.size() > 0) {
            session.setAttribute("areaInfo", result.get(0));
            session.setAttribute("areaLocal", result.get(0));

        } else {
            template.setName(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_AREA_NAME));
            template.setIcp(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_ICP));
//            template.setIndexLogoPath(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_INDEX_LOGO_PATH));
            template.setLoginLogoPath(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_LOGIN_LOGO_PATH));
            session.setAttribute("areaInfo", template);
            session.setAttribute("areaLocal", template);
        }
        session.setAttribute("treeId", template.getAreaId());
    }

    public void loadMenu(HttpSession session) throws Exception {
        String userName = ((UserInfo) session.getAttribute("userInfo")).getUserName();
        //获取一级菜单
        List<String> oneLevelMenuIds = new ArrayList<String>();
        oneLevelMenuIds.add("1");
        oneLevelMenuIds.add("2");
        oneLevelMenuIds.add("3");
        oneLevelMenuIds.add("21");
        if (userName != null && "admin".equals(userName)) {
            oneLevelMenuIds.add("4");
        }
        List<MenuInfo> oneLevelmenuInfos = menuInfoServiceImpl.fetchOneLevelMenuInfo(oneLevelMenuIds);
        //根据一级菜单编码获取对应的二级菜单
        List<MenuInfo> twoLevelmenuInos01 = menuInfoServiceImpl.fetchTwoLevelMenuInfo("01");
        List<MenuInfo> twoLevelmenuInos02 = menuInfoServiceImpl.fetchTwoLevelMenuInfo("02");
        List<MenuInfo> twoLevelmenuInos03 = menuInfoServiceImpl.fetchTwoLevelMenuInfo("03");
        List<MenuInfo> twoLevelmenuInos04 = menuInfoServiceImpl.fetchTwoLevelMenuInfo("04");
        List<MenuInfo> twoLevelmenuInos21 = menuInfoServiceImpl.fetchTwoLevelMenuInfo("21");
        session.setAttribute("oneLevelmenuInfos", oneLevelmenuInfos);
        session.setAttribute("twoLevelmenuInos01", twoLevelmenuInos01);
        session.setAttribute("twoLevelmenuInos02", twoLevelmenuInos02);
        session.setAttribute("twoLevelmenuInos03", twoLevelmenuInos03);
        session.setAttribute("twoLevelmenuInos21", twoLevelmenuInos21);
        if (userName != null && "admin".equals(userName)) {
            session.setAttribute("twoLevelmenuInos04", twoLevelmenuInos04);
        }
    }

}
