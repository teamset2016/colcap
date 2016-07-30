package com.teamset.colcap.dto;

import java.io.Serializable;

public class Code implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -771898225362661987L;

	private String code;

	private String desc;

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
