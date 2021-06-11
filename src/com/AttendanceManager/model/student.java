package com.AttendanceManager.model;


public class student {
	
	private static int cid;
	private String sid;
	private String sname;
	private String attendance;
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public static int getCid() {
		return cid;
	}
	public static void setCid(int cid) {
		student.cid = cid;
	}
	  
}
