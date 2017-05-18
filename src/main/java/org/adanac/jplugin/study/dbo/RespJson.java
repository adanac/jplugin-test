package org.adanac.jplugin.study.dbo;

import java.io.Serializable;

public class RespJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1952462140653769221L;

	private String resultCode = new String();

	private String msg = new String();

	private Object databody;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public Object getDatabody() {
		return databody;
	}

	public void setDatabody(Object databody) {
		this.databody = databody;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
