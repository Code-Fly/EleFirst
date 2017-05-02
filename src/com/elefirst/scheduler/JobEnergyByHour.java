package com.elefirst.scheduler;

import com.elefirst.base.utils.ConfigUtil;
import com.elefirst.base.utils.Const;
import com.elefirst.base.utils.TimeUtil;
import com.elefirst.power.po.DataF105;
import com.elefirst.power.po.DataF25FrozenMinute;
import com.elefirst.power.service.iface.IDataF105Service;
import com.elefirst.power.service.iface.IDataF25FrozenMinuteService;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.service.iface.IPnInfoService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by barrie on 2017/3/13.
 */
@Component
public class JobEnergyByHour {
    @Autowired
    private IDataF105Service dataF105Service;

    @Autowired
    private IPnInfoService pnInfoService;

//    @Scheduled(cron = "0/30 * * * * ?")
    public void job() {
        PnInfo pnTemplate = new PnInfo();

        List<PnInfo> pnInfoList = pnInfoService.getPnInfoList(pnTemplate);

        Integer interval = Integer.valueOf(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_REPORT_MAX_DAY_INTERVAL));


        for (int i = 0; i < pnInfoList.size(); i++) {
            for (int j = 0; j < interval + 1; j++) {
                List<DataF105> list = new ArrayList<>();

                PnInfo item = pnInfoList.get(i);
                //
                item.setAreaId("1");
                item.setConcentratorId("3838");
                item.setPn("1");
                //

                DataF105 node = new DataF105();
                node.setAreaId(item.getAreaId());
                node.setConcentratorId(item.getConcentratorId());
                node.setPn(item.getPn());
                list.add(node);

                Date startDate = TimeUtil.getDate(new Date(1493136000000L), 0, 0, 0 - j);
                Date endDate = TimeUtil.getDate(new Date(1493136000000L), 0, 0, 1 - j);

                List<DataF105> result = dataF105Service.getDataF105ByHourList(list, TimeUtil.formatDbDate(startDate, "yyyyMMdd"), TimeUtil.formatDbDate(endDate, "yyyyMMdd"));

                System.err.println(JSONArray.fromObject(result).toString());
            }
        }


    }
}
