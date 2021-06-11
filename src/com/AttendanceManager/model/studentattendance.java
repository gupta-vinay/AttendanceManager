package com.AttendanceManager.model;

public class studentattendance {
	
	private static String sid;
	private static String sname;
	private String cid;
	private String cname;
	private String total_classes;
	private String present;
	
	public static String getSid() {
		return sid;
	}
	public static void setSid(String sid) {
		studentattendance.sid = sid;
	}
	public static String getSname() {
		return sname;
	}
	public static void setSname(String sname) {
		studentattendance.sname = sname;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getTotal_classes() {
		return total_classes;
	}
	public void setTotal_classes(String total_classes) {
		this.total_classes = total_classes;
	}
	public String getPresent() {
		return present;
	}
	public void setPresent(String present) {
		this.present = present;
	}

}
