package com.wenr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wenr.model.Course;
import com.wenr.model.Grade;
import com.wenr.util.DBUtil;

public class Gradedao {
	
	//所有课程成绩
	public ArrayList<Grade> getAllGrade() {
		ArrayList<Grade> list = new ArrayList<Grade>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from grade WHERE score is not NULL";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				Grade grade = new Grade();
				grade.setCid(rs.getInt(1));
				grade.setCname(rs.getString(2));
				grade.setSid(rs.getInt(3));
				grade.setSname(rs.getString(4));
				grade.setTno(rs.getInt(5));
				grade.setTname(rs.getString(6));
				grade.setScore(rs.getDouble(7));
				list.add(grade);
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
	
	// 通过学号查询    模糊查询
	public ArrayList<Grade> getGradeBySid(int sid) {
			
		ArrayList<Grade> list = new ArrayList<Grade>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from grade where sid like ? and score is not NULL";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+sid+"%");
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
					Grade grade = new Grade();
					/* jz */
					grade.setCid(rs.getInt(1));
					grade.setCname(rs.getString(2));
					grade.setSid(rs.getInt(3));
					grade.setSname(rs.getString(4));
					grade.setTno(rs.getInt(5));
					grade.setTname(rs.getString(6));
					grade.setScore(rs.getDouble(7));
					list.add(grade);
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
	//查询学生成绩
	public ArrayList<Grade> get_student_Grade(int sid) {
		ArrayList<Grade> list = new ArrayList<Grade>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from grade where sid=? and score is not NULL";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				Grade grade = new Grade();
				grade.setCid(rs.getInt(1));
				grade.setCname(rs.getString(2));
				grade.setSid(rs.getInt(3));
				grade.setSname(rs.getString(4));
				grade.setTno(rs.getInt(5));
				grade.setTname(rs.getString(6));
				grade.setScore(rs.getDouble(7));
				list.add(grade);
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
	public ArrayList<Grade> show_all_score(int t_no) {
		ArrayList<Grade> list = new ArrayList<Grade>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from grade where t_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t_no);
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				Grade grade = new Grade();
				grade.setCid(rs.getInt(1));
				grade.setCname(rs.getString(2));
				grade.setSid(rs.getInt(3));
				grade.setSname(rs.getString(4));
				grade.setTno(rs.getInt(5));
				grade.setTname(rs.getString(6));
				grade.setScore(rs.getDouble(7));
				list.add(grade);
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
	
	public void teacher_add_score(int sid,int cid,double score) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			/* jz */
			conn = DBUtil.getConnection();
			String sql = "update score set score=? where sid=? and cid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, score);
			pstmt.setInt(2, sid);
			pstmt.setInt(3, cid);
			/* end */
			pstmt.executeUpdate();
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
		}
	}

	
	
	
}
