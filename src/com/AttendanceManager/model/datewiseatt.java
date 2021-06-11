package com.AttendanceManager.model;

import java.util.Date;

public class datewiseatt {
	private static int cid;
	private static int sid;
	private Date d;
	private String present;
	public static int getCid() {
		return cid;
	}
	public static void setCid(int cid) {
		datewiseatt.cid = cid;
	}
	public static int getSid() {
		return sid;
	}
	public static void setSid(int sid) {
		datewiseatt.sid = sid;
	}
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	public String getPresent() {
		return present;
	}
	public void setPresent(String present) {
		this.present = present;
	}
	

}
