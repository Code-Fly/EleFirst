package com.elefirst.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.elefirst.powerdetail.po.Area;
import com.elefirst.powerdetail.po.Concentrator;

/**
 * jinlu 测试
 * @author Administrator
 *
 */
public class Test01 {
    public static void main(String[] args) {
    	Concentrator c1 = new Concentrator();
    	c1.setConcentratorId("417");
    	List<String> pns1 = new ArrayList<String>();
    	pns1.add("1");
    	pns1.add("2");
    	pns1.add("3");
    	c1.setPns(pns1);
    	
    	Concentrator c2 = new Concentrator();
    	c2.setConcentratorId("418");
    	List<String> pns2 = new ArrayList<String>();
    	pns2.add("1");
    	pns2.add("2");
    	pns2.add("3");
    	c2.setPns(pns2);
    	
    	List<Concentrator> conns = new ArrayList<Concentrator>();
    	conns.add(c1);
    	conns.add(c2);
    	
    	Area f = new Area();
    	f.setAreaId("1");
    	f.setType("physical");
    	f.setConcentrators(conns);
    	//f.setConcentratorId(new String[]{"417","418","419","420"});
    	//f.setPn(new String[]{"1","2","3","4"});
    	String jsonString = JSON.toJSONString(f);
    	System.out.println(jsonString);  
    	
    	String powerDetailJason = "{\"areaId\":\"1\",\"concentrators\":[{\"concentratorId\":\"417\",\"pns\":[\"1\",\"2\",\"3\"]},{\"concentratorId\":\"418\",\"pns\":[\"1\",\"2\",\"3\"]}],\"type\":\"physical\"}";

	}
//	student.setHobby(new String[]{"篮球","上网","跑步","游戏"});  
//	/** 
//	 * java对象转换成json对象，并获取json对象属性 
//	 */  
//	JSONObject jsonStu = JSONObject.fromObject(student);  
//	System.out.println(jsonStu.toString());  
//	System.out.println(jsonStu.getJSONArray("hobby"));
}
