package com.AttendanceManager.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.AttendanceManager.model.datewiseatt;
import com.AttendanceManager.model.student;
import com.AttendanceManager.model.studentattendance;
import com.AttendanceManager.model.timetable;
import com.AttendanceManager.model.user;
import com.AttendanceManager.service.WebAppService;

@Controller
public class AppController {

	@Autowired
	WebAppService s;
	
	@RequestMapping(value="/")
	public String Home()
	{
		return "index";
	}
	@RequestMapping(value="login")
	public String login(user m,HttpServletRequest req,HttpSession session)
	{
		String us=req.getParameter("user");
		System.out.println(us);
		if(us.equals("teacher"))
		{
			int x=s.login(m);
			if(x!=0)
			{
				session.setAttribute("uid", "teacher");
				session.setAttribute("tid", x);
				List<timetable> l=s.allData(x);
				req.setAttribute("list", l);
				Date d=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("EEEE");
				String day=sdf.format(d);
				day=day.toLowerCase();
				//System.out.println(day);
				req.setAttribute("day", day);
				req.setAttribute("time",d.getHours()-12 );
				return "timetable";
			}
		}
		else
		{
			m.setUser(us);
			m.setUid(req.getParameter("uid"));
			int x=s.login(m);
			if(x!=0)
			{
				session.setAttribute("uid", "student");
				session.setAttribute("sid", x);
				List<studentattendance> l=s.getStudentAttendance(x);
				req.setAttribute("list", l);
				req.setAttribute("sid", studentattendance.getSid());
				session.setAttribute("sname", studentattendance.getSname());
				
				return "student";
			}
		}
		return "index";
		}
	
	
	@RequestMapping(value="updateattendance")
	public String updateAttendance(HttpServletRequest req,HttpSession session)
	{
		if(session.getAttribute("uid")=="teacher")
		{
			List<student> l=(List<student>)session.getAttribute("list");
			
			for(student s:l)
			{
				s.setAttendance(req.getParameter(s.getSid()));
			}
			s.updateAttendance(l);
			Integer x=(Integer)(session.getAttribute("tid"));
			List<timetable> l2=s.allData(x);
			req.setAttribute("list", l2);
			Date d=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("EEEE");
			String day=sdf.format(d);
			day=day.toLowerCase();
			//System.out.println(day);
			req.setAttribute("day", day);
			req.setAttribute("time",d.getHours()-12 );
			req.setAttribute("msg", "attendance of  batch saved successfully");
			return "timetable";
		}
		return "index";
	}
	
	
	@RequestMapping(value="getattdate")
	public String shubham(HttpServletRequest req,HttpSession session,datewiseatt d)
	{
		if(session.getAttribute("uid")=="student")
		{
			datewiseatt.setCid(Integer.parseInt(req.getParameter("courseid")));
			datewiseatt.setSid((Integer)session.getAttribute("sid"));
			List <datewiseatt> l=s.getDateWiseAttendance();
			req.setAttribute("list", l);
			return "datewise";
		}
		return "index";
	}
	
	@RequestMapping(value="view_timetable")
	public String viewStudentTimetable(HttpServletRequest req,HttpSession session)
	{
		if(session.getAttribute("uid")=="student")
		{
			int sid=(Integer)session.getAttribute("sid");
			List<timetable> l=s.getStudentTimetable(sid);
			req.setAttribute("list", l);
		}
		return "timetable";
	}
	
	@RequestMapping(value="courses")
	public String getCourseData(HttpServletRequest req,HttpSession session)
	{
		System.out.println(req.getParameter("coursename"));
		if(session.getAttribute("uid")=="teacher")
		{
			
			int y=s.check(req.getParameter("coursename"));
			if(y==0)
			{
				List<student> l=s.studentData(req.getParameter("coursename"));
				session.setAttribute("list", l);
				//req.setAttribute("list", l);
				return "python";
			}
			req.setAttribute("msg", "Attendance already done");
			Integer x=(Integer)(session.getAttribute("tid"));
			List<timetable> l2=s.allData(x);
			req.setAttribute("list", l2);
			Date d=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("EEEE");
			String day=sdf.format(d);
			day=day.toLowerCase();
			//System.out.println(day);
			req.setAttribute("day", day);
			req.setAttribute("day", day);
			req.setAttribute("time",d.getHours()-12 );
			return "timetable";
		
		}
		return "index";
	}
	
}
