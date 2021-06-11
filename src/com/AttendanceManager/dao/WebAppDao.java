package com.AttendanceManager.dao;

import java.util.List;

import com.AttendanceManager.model.datewiseatt;
import com.AttendanceManager.model.student;
import com.AttendanceManager.model.studentattendance;
import com.AttendanceManager.model.timetable;
import com.AttendanceManager.model.user;

public interface WebAppDao {

	int login(user m);

	List<timetable> allData(int x);

	List<student> studentData(String str);

	void updateAttendance(List<student> l);

	int check(String str);

	List<studentattendance> getStudentAttendance(int x);

	List<datewiseatt> getDateWiseAttendance();

	List<timetable> getStudentTimetable(int sid);

}
