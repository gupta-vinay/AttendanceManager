package com.AttendanceManager.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.AttendanceManager.model.datewiseatt;
import com.AttendanceManager.model.student;
import com.AttendanceManager.model.studentattendance;
import com.AttendanceManager.model.timetable;
import com.AttendanceManager.model.user;

@Repository
public class WebAppDaoImpl implements WebAppDao{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int login(user m) {
		
			// TODO Auto-generated method stub
			final String procedureCall="{call attendance_manager(?,?,?,?,?)}";
			Connection con=null;
			System.out.println(m.getUser());
			if(m.getUser().equals("teacher"))
			{
				System.out.println("hello");
				try {
					con=jdbcTemplate.getDataSource().getConnection();
					CallableStatement cs=con.prepareCall(procedureCall);
					cs.setString(1, "login");
					cs.setString(2, m.getEmail());
					cs.setString(3, m.getPass());
					cs.setString(4, null);
					cs.setInt(5, 0);
					boolean b=cs.execute();
					if(b==true)
					{
						ResultSet rs=cs.getResultSet();
						if(rs.next())
						{
							System.out.println(rs.getString("password"));
							if(rs.getString("password").equals(m.getPass()))
							{
								return (rs.getInt("uid"));
							}
							else {
								return 0;
							}
						}
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else {
				try {
					con=jdbcTemplate.getDataSource().getConnection();
					CallableStatement cs=con.prepareCall(procedureCall);
					cs.setString(1, "loginstudent");
					cs.setString(2, m.getEmail());
					cs.setString(3, m.getPass());
					cs.setString(4, null);
					cs.setInt(5, Integer.parseInt(m.getUid()));
					ResultSet rs=cs.executeQuery();
					if(rs.next())
					{
						//System.out.println(rs.getString("password"));
						if(rs.getString("password").equals(m.getPass()))
						{
							return (rs.getInt("sid"));
						}
						else {
							return 0;
						}
					}
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			return 0;
		}

	@Override
	public List<timetable> allData(int x) {
		// TODO Auto-generated method stub
		
		final String procedureCall="{call attendance_manager(?,?,?,?,?)}";
		Connection con=null;
		try {
			con=jdbcTemplate.getDataSource().getConnection();
			CallableStatement cs=con.prepareCall(procedureCall);
			cs.setString(1, "timetable");
			cs.setString(2, null);
			cs.setString(3, null);
			cs.setString(4, null);
			cs.setInt(5, x);
			ResultSet rs=cs.executeQuery();
			List<timetable> l=new ArrayList<timetable>();
			while(rs.next())
			{
				//System.out.println(rs.getString("day"));
				//System.out.println(rs.getString("4-5"));
				timetable t=new timetable();
				t.setDay(rs.getString("day"));
				t.setFour(rs.getString("4-5"));
				t.setFive(rs.getString("5-6"));
				t.setSix(rs.getString("6-7"));
				t.setSeven(rs.getString("7-8"));
				l.add(t);
			}
			return l;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<student> studentData(String str) {
		// TODO Auto-generated method stub
		final String procedureCall="{call attendance_manager(?,?,?,?,?)}";
		Connection con=null;
		try {
			con=jdbcTemplate.getDataSource().getConnection();
			CallableStatement cs=con.prepareCall(procedureCall);
			cs.setString(1, "getcid");
			cs.setString(2, str);
			cs.setString(3, null);
			cs.setString(4, null);
			cs.setInt(5, 0);
			ResultSet rs=cs.executeQuery();
			rs.next();
			int cid=rs.getInt("cid");
			List<student> l=new ArrayList<student>();
			try {
				con=jdbcTemplate.getDataSource().getConnection();
				CallableStatement cs2=con.prepareCall(procedureCall);
				cs2.setString(1, "getstudents");
				cs2.setString(2, null);
				cs2.setString(3, null);
				cs2.setString(4, null);
				cs2.setInt(5, cid);
				rs=cs2.executeQuery();
				student.setCid(cid);
				while(rs.next())
				{
					//System.out.println(rs.getString("day"));
					//System.out.println(rs.getString("4-5"));
					student s=new student();
					s.setSid(Integer.toString(rs.getInt("sid")));
					s.setSname(rs.getString("sname"));
					l.add(s);
				}
				return l;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void updateAttendance(List<student> l) {
		// TODO Auto-generated method stub
		final String procedureCall="{call attendance_manager2(?,?,?,?,?,?)}";
		Connection con=null;
		Date d=new Date();
		long ms=d.getTime();
		java.sql.Date d2=new java.sql.Date(ms);
		
			try {
				con=jdbcTemplate.getDataSource().getConnection();
				CallableStatement cs=con.prepareCall(procedureCall);
				for(student s:l)
				{
					cs.setString(1, "update_attendance");
					cs.setInt(2, Integer.parseInt(s.getSid()));
					cs.setString(3, s.getSname());
					cs.setDate(4,d2 );
					cs.setInt(5, Integer.parseInt(s.getAttendance()));
					cs.setInt(6, s.getCid());
					cs.addBatch();
					//cs.executeUpdate();
				}
				cs.executeBatch();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}

	@Override
	public int check(String str) {
		final String procedureCall="{call attendance_manager(?,?,?,?,?)}";
		Connection con=null;
		try {
			con=jdbcTemplate.getDataSource().getConnection();
			CallableStatement cs=con.prepareCall(procedureCall);
			cs.setString(1, "getcid");
			cs.setString(2, str);
			cs.setString(3, null);
			cs.setString(4, null);
			cs.setInt(5, 0);
			ResultSet rs=cs.executeQuery();
			rs.next();
			int cid=rs.getInt("cid");
			System.out.println(cid);
			
			final String procedureCall2="{call attendance_manager2(?,?,?,?,?,?)}";
			Date d=new Date();
			long ms=d.getTime();
			java.sql.Date d2=new java.sql.Date(ms);
			System.out.println(d2);
			try {
				con=jdbcTemplate.getDataSource().getConnection();
				CallableStatement cs2=con.prepareCall(procedureCall2);
				cs2.setString(1, "check_attendance");
				cs2.setInt(2, 0);
				cs2.setString(3, null);
				cs2.setDate(4, d2);
				cs2.setInt(5, 0);
				cs2.setInt(6, cid);
				ResultSet rs2=cs2.executeQuery();
				if(rs2.next())
				{
					System.out.println("11");
					return 1;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public List<studentattendance> getStudentAttendance(int x) {
		// TODO Auto-generated method stub
		final String procedureCall="{call attendance_manager2(?,?,?,?,?,?)}";
		Connection con=null;
		
		try {
			con=jdbcTemplate.getDataSource().getConnection();
			CallableStatement cs=con.prepareCall(procedureCall);
			cs.setString(1, "get_cid_of_sid");
			cs.setInt(2, x);
			cs.setString(3, null);
			cs.setDate(4, null );
			cs.setInt(5, 0);
			cs.setInt(6, 0);
			ResultSet rs=cs.executeQuery();
			studentattendance.setSid(Integer.toString(x));
			List<studentattendance> l=new ArrayList<studentattendance>();
			while(rs.next())
			{
				studentattendance sa=new studentattendance();
				sa.setCid(Integer.toString(rs.getInt("cid")));
				sa.setCname(rs.getString("cname"));
				try {
					con=jdbcTemplate.getDataSource().getConnection();
					cs=con.prepareCall(procedureCall);
					cs.setString(1, "get_att_count");
					cs.setInt(2, x);
					cs.setString(3, null);
					cs.setDate(4, null);
					cs.setInt(5, 0);
					cs.setInt(6, rs.getInt("cid"));
					ResultSet rs2=cs.executeQuery();
					rs2.next();
					studentattendance.setSname(rs2.getString("sname"));
					sa.setTotal_classes(Integer.toString(rs2.getInt("total_classes")));
					sa.setPresent(Integer.toString(rs2.getInt("present")));
					l.add(sa);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
					return l;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		return null;
	}

	@Override
	public List<datewiseatt> getDateWiseAttendance() {
		// TODO Auto-generated method stub
		
		final String procedureCall="{call attendance_manager2(?,?,?,?,?,?)}";
		Connection con=null;
		
				try {
					con=jdbcTemplate.getDataSource().getConnection();
					CallableStatement cs=con.prepareCall(procedureCall);
					cs.setString(1, "get_sattendance");
					cs.setInt(2, datewiseatt.getSid());
					cs.setString(3, null);
					cs.setDate(4, null);
					cs.setInt(5, 0);
					cs.setInt(6, datewiseatt.getCid());
					ResultSet rs=cs.executeQuery();
					List<datewiseatt> l=new ArrayList<datewiseatt>();
					while(rs.next())
					{
						datewiseatt dt=new datewiseatt();
						
						if(rs.getInt("attendance")==1)
							dt.setPresent("Present");
						else
							dt.setPresent("Absent");
						java.sql.Date d=rs.getDate("adate");
						long ms=d.getTime();
						Date ud=new Date(ms);
						dt.setD(ud);
						l.add(dt);
					}
					
					return l;
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return null;
	}

	@Override
	public List<timetable> getStudentTimetable(int sid) {
		// TODO Auto-generated method stub
		final String procedureCall="{call attendance_manager2(?,?,?,?,?,?)}";
		final String procedureCall2="{call attendance_manager(?,?,?,?,?)}";
		Connection con=null;
		
		try {
			con=jdbcTemplate.getDataSource().getConnection();
			CallableStatement cs=con.prepareCall(procedureCall);
			cs.setString(1, "get_cid_of_sid");
			cs.setInt(2, sid);
			cs.setString(3, null);
			cs.setDate(4, null );
			cs.setInt(5, 0);
			cs.setInt(6, 0);
			ResultSet rs=cs.executeQuery();
			ArrayList<String> courses=new ArrayList<String>();
			while(rs.next())
			{
				courses.add(rs.getString("cname"));
			}
			try {
				con=jdbcTemplate.getDataSource().getConnection();
				CallableStatement cs2=con.prepareCall(procedureCall2);
				cs2.setString(1, "student_timetable");
				cs2.setString(2, null);
				cs2.setString(3, null);
				cs2.setString(4, null);
				cs2.setInt(5, 0);
				rs=cs2.executeQuery();
				List<timetable> l=new ArrayList<timetable>();
				while(rs.next())
				{
					//System.out.println(rs.getString("day"));
					//System.out.println(rs.getString("4-5"));
					timetable t=new timetable();
					t.setDay(rs.getString("day"));
					for(String s:courses)
					{
						if(s.equals(rs.getString("4-5")))
							t.setFour(rs.getString("4-5"));
						
						if(s.equals(rs.getString("5-6")))
							t.setFive(rs.getString("5-6"));
						
						if(s.equals(rs.getString("6-7")))
							t.setSix(rs.getString("6-7"));
						
						if(s.equals(rs.getString("7-8")))
							t.setSeven(rs.getString("7-8"));
					}
					
					l.add(t);
				}
				return l;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		return null;
	}

	}


