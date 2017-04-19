package com.elefirst.system.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.utils.ConfigUtil;
import com.elefirst.base.utils.Const;
import com.elefirst.system.po.AreaInfoWithBLOBs;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by     xxxxx barrie on 17/1/29.
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
            info.setAreaId("-1");
            info.setName(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_AREA_NAME));
            info.setIcp(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_ICP));
            info.setIndexLogoPath(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_INDEX_LOGO_PATH));
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
            info.setIndexLogoPath(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_INDEX_LOGO_PATH));
            info.setLoginLogoPath(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_LOGIN_LOGO_PATH));

            return new ErrorMsg(Error.SUCCESS, "success", info);
        }
    }

    @RequestMapping(value = "/info/update.do")
    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg updateTreeInfo(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestBody String sData
    ) {
        AreaInfoWithBLOBs template = new Gson().fromJson(sData, AreaInfoWithBLOBs.class);
        template.setUpdatePerson("admin");
        template.setUpdateDate(new Date());
        int result = areaInfoService.updateAreaInfo(template);
        ConfigUtil.setProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_AREA_NAME, template.getName());
        ConfigUtil.setProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_ICP, template.getIcp());
        ConfigUtil.setProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_INDEX_LOGO_PATH, template.getIndexLogoPath());
        ConfigUtil.setProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_LOGIN_LOGO_PATH, template.getLoginLogoPath());
        return new ErrorMsg(Error.SUCCESS, "success", result);

    }

    @RequestMapping(value = "/info/add.do")
    @ApiOperation(value = "添加", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg addPnfo(HttpServletRequest request,
                            HttpServletResponse response,
                            @RequestBody String sData
    ) {
        AreaInfoWithBLOBs template = new Gson().fromJson(sData, AreaInfoWithBLOBs.class);
        template.setId(UUID.randomUUID().toString());
        template.setCreatePerson("admin");
        template.setCreateDate(new Date());
        int result = areaInfoService.addAreaInfo(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/info/delete.do")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg deletePnInfo(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(value = "id") String id
    ) {
        int result = areaInfoService.delAreaInfo(id);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
}
