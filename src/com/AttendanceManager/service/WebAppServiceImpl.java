package com.AttendanceManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AttendanceManager.dao.WebAppDao;
import com.AttendanceManager.model.datewiseatt;
import com.AttendanceManager.model.student;
import com.AttendanceManager.model.studentattendance;
import com.AttendanceManager.model.timetable;
import com.AttendanceManager.model.user;

@Service
public class WebAppServiceImpl implements WebAppService {
	
	@Autowired 
	WebAppDao d;

	@Override
	public int login(user m) {
		// TODO Auto-generated method stub
		return d.login(m);
	}

	@Override
	public List<timetable> allData(int x) {
		// TODO Auto-generated method stub
		return d.allData(x);
	}

	@Override
	public List<student> studentData(String str) {
		// TODO Auto-generated method stub
		return d.studentData(str);
	}

	@Override
	public void updateAttendance(List<student> l) {
		// TODO Auto-generated method stub
		d.updateAttendance(l);
	}

	@Override
	public int check(String str) {
		// TODO Auto-generated method stub
		return d.check(str);
	}

	@Override
	public List<studentattendance> getStudentAttendance(int x) {
		// TODO Auto-generated method stub
		return d.getStudentAttendance(x);
	}

	@Override
	public List<datewiseatt> getDateWiseAttendance() {
		// TODO Auto-generated method stub
		return d.getDateWiseAttendance();
	}

	@Override
	public List<timetable> getStudentTimetable(int sid) {
		// TODO Auto-generated method stub
		return d.getStudentTimetable(sid);
	}
	

}
