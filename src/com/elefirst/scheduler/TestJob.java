package com.elefirst.scheduler;

import com.elefirst.power.po.DataF25;
import com.elefirst.power.service.iface.IDataF25Service;
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
    private IDataF25Service dataF25Service;

    @Scheduled(cron = "0/5 * * * * ?")
    public void job1() {
        DataF25 template = new DataF25();
        template.setAreaId("1");
        template.setConcentratorId("417");
        template.setPn("1");
        template.setPage(1);
        template.setRows(10);

        List<DataF25> result = dataF25Service.getDataF25List(template);

        System.err.println(JSONArray.fromObject(result).toString());
    }
}
