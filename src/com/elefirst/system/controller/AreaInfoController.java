package com.elefirst.system.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.exception.SessionExpiredException;
import com.elefirst.base.utils.ConfigUtil;
import com.elefirst.base.utils.Const;
import com.elefirst.base.utils.UUIDKeyGenerator;
import com.elefirst.system.po.AreaInfo;
import com.elefirst.system.po.AreaInfoWithBLOBs;
import com.elefirst.system.po.RoleAreaMap;
import com.elefirst.system.po.UserInfo;
import com.elefirst.system.service.iface.IAreaInfoService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        AreaInfoWithBLOBs template = new AreaInfoWithBLOBs();

        if (null != areaId) {
            template.setAreaId(areaId);
        }

        if (null != name) {
            template.setName(areaId);
        }

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<AreaInfoWithBLOBs> result = areaInfoService.getAreaInfoList(template);

            DataGrid dg = new DataGrid();
            long count = areaInfoService.getAreaInfoListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<AreaInfoWithBLOBs> result = areaInfoService.getAreaInfoList(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

    }

    @RequestMapping(value = "/info/detail.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getAreaInfoDetail(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestParam(value = "id") String id
    ) {
        List<AreaInfoWithBLOBs> result = areaInfoService.getAreaInfoDetail(id);
        return new ErrorMsg(Error.SUCCESS, "success", result);

    }

    @RequestMapping(value = "/info/detailByAreaId.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getAreaInfoDetailByAreaId(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @RequestParam(value = "areaId") String areaId
    ) {
        AreaInfoWithBLOBs template = new AreaInfoWithBLOBs();
        template.setAreaId(areaId);
        List<AreaInfoWithBLOBs> result = areaInfoService.getAreaInfoList(template);
        if (result.size() > 0) {
            return new ErrorMsg(Error.SUCCESS, "success", result.get(0));
        } else {
            AreaInfoWithBLOBs info = new AreaInfoWithBLOBs();
            info.setAreaId(areaId);
            info.setName(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_AREA_NAME));
            info.setIcp(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_ICP));
//            info.setIndexLogoPath(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_INDEX_LOGO_PATH));
            info.setLoginLogoPath(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_LOGIN_LOGO_PATH));

            return new ErrorMsg(Error.SUCCESS, "success", info);
        }
    }

    @RequestMapping(value = "/info/local/detail.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getAreaInfoDetailLocal(HttpServletRequest request,
                                           HttpServletResponse response
    ) {
        String areaId = ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_AREA_ID);
        AreaInfoWithBLOBs template = new AreaInfoWithBLOBs();
        template.setAreaId(areaId);
        List<AreaInfoWithBLOBs> result = areaInfoService.getAreaInfoList(template);
        if (result.size() > 0) {
            return new ErrorMsg(Error.SUCCESS, "success", result.get(0));
        } else {
            AreaInfoWithBLOBs info = new AreaInfoWithBLOBs();
            info.setAreaId(areaId);
            info.setName(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_AREA_NAME));
            info.setIcp(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_ICP));
//            info.setIndexLogoPath(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_INDEX_LOGO_PATH));
            info.setLoginLogoPath(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_LOGIN_LOGO_PATH));

            return new ErrorMsg(Error.SUCCESS, "success", info);
        }
    }

    @RequestMapping(value = "/info/update.do")
    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg updateAreaInfo(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestBody String sData
    ) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }

        AreaInfoWithBLOBs template = new Gson().fromJson(sData, AreaInfoWithBLOBs.class);
        template.setUpdatePerson(userInfo.getUserName());
        template.setUpdateDate(new Date());
        int result = areaInfoService.updateAreaInfo(template);
        String localAreaId = ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_AREA_ID);
        if (localAreaId.equals(template.getAreaId())) {
            ConfigUtil.setProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_AREA_NAME, template.getName());
            ConfigUtil.setProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_ICP, template.getIcp());
//            ConfigUtil.setProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_INDEX_LOGO_PATH, template.getIndexLogoPath());
            ConfigUtil.setProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_LOGIN_LOGO_PATH, template.getLoginLogoPath());
        }
        return new ErrorMsg(Error.SUCCESS, "success", result);

    }

    @RequestMapping(value = "/info/add.do")
    @ApiOperation(value = "添加", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg addAreaInfo(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestBody String sData
    ) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }

        AreaInfoWithBLOBs template = new Gson().fromJson(sData, AreaInfoWithBLOBs.class);
        template.setId(UUID.randomUUID().toString());
        template.setCreatePerson(userInfo.getUserName());
        template.setCreateDate(new Date());
        int result = areaInfoService.addAreaInfo(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/info/delete.do")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg deleteAreaInfo(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "id") String id
    ) {
        int result = areaInfoService.delAreaInfo(id);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/info/queryAreaByRoleId.do")
    public @ResponseBody
    ErrorMsg queryAreaByRoleId(String page, String rows, String roleId, HttpSession session) {
        DataGrid dg = new DataGrid();
        try {
            int pageNum = Integer.valueOf(page == null ? "1" : page);
            int rowsNum = Integer.valueOf(rows == null ? "10" : rows);
            UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
            if (null == userInfo || null == userInfo.getUserName()) {
                logger.error("用户Session过期");
                return null;
            }
            AreaInfo areaInfo = new AreaInfo();
            areaInfo.setPage(pageNum);
            areaInfo.setRows(rowsNum);
            List<AreaInfoWithBLOBs> areaInfos = areaInfoService.fetchAreaInfoByRoleId(areaInfo, true, roleId);
            // 返回分页对象数
            dg.setRows(areaInfos);
            int totalNum = areaInfoService.fetchAreaInfoByCond(areaInfo, false).size();
            dg.setTotal(totalNum);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } catch (Exception e) {
            dg = null;
            logger.error("根据条件查看区域信息失败！", e);
            return new ErrorMsg(Error.API_RESPONSE_EXCEPTION, "faile", "");
        }
    }

    /**
     * 新增角色与区域关系
     *
     * @param user
     * @return
     */
    @RequestMapping("/info/addRoleAreaMap.do")
    public @ResponseBody
    ErrorMsg addRoleAreaMap(String roleId, String areaIdsStr) {
        //保存前先根据roleId清空角色与区域关系表
        List<RoleAreaMap> roleAreaMaps = null;
        try {
            if (areaIdsStr != null && areaIdsStr.length() > 0) {
                roleAreaMaps = new ArrayList<RoleAreaMap>();
                String[] aIds = areaIdsStr.split(",");
                for (int i = 0; i < aIds.length; i++) {
                    RoleAreaMap tmpRoleAreaMap = new RoleAreaMap();
                    tmpRoleAreaMap.setId(UUIDKeyGenerator.getUUID());
                    tmpRoleAreaMap.setRoleId(roleId);
                    tmpRoleAreaMap.setAreaId(aIds[i]);
                    roleAreaMaps.add(tmpRoleAreaMap);
                }
            }
            areaInfoService.saveRoleAreaMaps(roleAreaMaps, roleId);
            logger.info("添加角色与区域关系实例成功!");
            return new ErrorMsg(Error.SUCCESS, "success", "");
        } catch (Exception e) {
            logger.error("{}", e);
            return new ErrorMsg(Error.API_RESPONSE_EXCEPTION, "faile", "");
        }
    }
}
