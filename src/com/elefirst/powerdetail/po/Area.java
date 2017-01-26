package com.elefirst.powerdetail.po;

import java.util.List;

public class Area {
	String areaId;
	String type;
	List<Concentrator> concentrators;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public List<Concentrator> getConcentrators() {
		return concentrators;
	}
	public void setConcentrators(List<Concentrator> concentrators) {
		this.concentrators = concentrators;
	}
	
}
