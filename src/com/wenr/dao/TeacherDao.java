package com.wenr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wenr.model.Course;
import com.wenr.model.Teacher;
import com.wenr.util.DBUtil;

public class TeacherDao {
	public boolean isValid(Teacher teacher) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from teacher where t_no=? and pwd=?";
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
	
}
