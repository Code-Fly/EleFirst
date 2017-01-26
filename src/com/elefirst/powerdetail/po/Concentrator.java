package com.elefirst.powerdetail.po;

import java.util.List;

public class Concentrator {
	String concentratorId;
	List<String> pns;


	public List<String> getPns() {
		return pns;
	}

	public void setPns(List<String> pns) {
		this.pns = pns;
	}

	public String getConcentratorId() {
		return concentratorId;
	}

	public void setConcentratorId(String concentratorId) {
		this.concentratorId = concentratorId;
	}
}
