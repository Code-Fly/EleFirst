package com.elefirst.scheduler;

import com.elefirst.power.po.DataF25FrozenDay;
import com.elefirst.power.service.iface.IDataF25FrozenDayService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by barrie on 2017/3/13.
 */
@Component
public class TestJob {
    @Autowired
    private IDataF25FrozenDayService dataF25FrozenDayService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void job1() {
        DataF25FrozenDay template = new DataF25FrozenDay();
        template.setAreaId("1");
        template.setConcentratorId("417");
        template.setPn("1");
        template.setPage(1);
        template.setRows(10);

        List<DataF25FrozenDay> result = dataF25FrozenDayService.getDataF25FrozenDayList(template);

        System.err.println(JSONArray.fromObject(result).toString());
    }
}
