package com.elefirst.base.entity;

import java.util.List;

public class DataGrid {

	/**
	 * 总记录数
	 */
	private long total;

	/**
	 * 数据
	 */
	private List rows;
	
	/**
	 * 返回消息体
	 */
	private GeneralMessage gm;

	public GeneralMessage getGm() {
		return gm;
	}

	public void setGm(GeneralMessage gm) {
		this.gm = gm;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
