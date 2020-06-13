package com.wenr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wenr.model.Course;
import com.wenr.model.Student;
import com.wenr.model.Grade;
import com.wenr.model.Teacher;
import com.wenr.util.DBUtil;

public class TeacherDao {
	public boolean isValid(Teacher teacher) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from teacher where t_no=? and t_pwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, teacher.getTid());
			pstmt.setString(2, teacher.getTpwd());
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				teacher.setTname(rs.getString("t_name"));
				return true;
			} else {
				return false;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}
	
	//及格人数
	public int getjige(Grade grade) {
	
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int a = 999;
	
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) "
					+ "from grade "
					+ "where grade.cid=? and grade.t_no=? and grade.score>=60";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, grade.getCid());
			pstmt.setInt(2,grade.getTno());
			
			rs = pstmt.executeQuery();
			if(rs != null && rs.next())
			{
				a = rs.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
		return a;
	}
	public ArrayList<Course> get_teacher_Course(Teacher teacher) {
		
		ArrayList<Course> list = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select course.cid,course.cname,course.credit,course.chour from course where t_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, teacher.getTid());
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				Course course = new Course();
				course.setCid(rs.getInt(1));
				course.setCname(rs.getString(2));
				course.setCredit(rs.getInt(3));
				course.setChour(rs.getInt(4));
				list.add(course);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}

	
	
}
