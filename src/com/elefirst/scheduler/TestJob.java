package com.elefirst.scheduler;

import com.elefirst.power.po.DataF25FrozenMinute;
import com.elefirst.power.service.iface.IDataF25FrozenMinuteService;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by barrie on 2017/3/13.
 */
@Component
public class TestJob {
    @Autowired
    private IDataF25FrozenMinuteService dataF25FrozenMinuteService;

    //    @Scheduled(cron = "0/5 * * * * ?")
    public void job1() {
        DataF25FrozenMinute template = new DataF25FrozenMinute();
        template.setAreaId("1");
        template.setConcentratorId("417");
        template.setPn("1");
        template.setPage(1);
        template.setRows(10);

        List<DataF25FrozenMinute> result = dataF25FrozenMinuteService.getDataF25FrozenMinuteList(template);

        System.err.println(JSONArray.fromObject(result).toString());
    }
    
    public static void main(String[] args) {  
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");  
    }  
}
