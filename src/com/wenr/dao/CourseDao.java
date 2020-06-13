//jz
package com.wenr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wenr.model.Course;
import com.wenr.util.DBUtil;

public class CourseDao {
	
	public ArrayList<Course> getAllCourse() {
		
		ArrayList<Course> list = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from course";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				Course course = new Course();
				/* jz */
				course.setCid(rs.getInt(1));
				course.setCname(rs.getString(2));
				course.setTno(rs.getInt(3));
				course.setCredit(rs.getInt(4));
				course.setChour(rs.getInt(5));
				/* end */
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
	
	// ͨ���γ�����ѯ    ģ����ѯ
	public ArrayList<Course> getCourseByName(String cname) {
		
		ArrayList<Course> list = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from course where cname like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+cname+"%");
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				Course course = new Course();
				/* jz */
				course.setCid(rs.getInt(1));
				course.setCname(rs.getString(2));
				course.setTno(rs.getInt(3));
				course.setCredit(rs.getInt(4));
				course.setChour(rs.getInt(5));
				/* end */
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
	
	public Course getCourseById(int cid) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from course where cid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				Course course = new Course();
				/* jz */
				course.setCid(rs.getInt(1));
				course.setCname(rs.getString(2));
				course.setTno(rs.getInt(3));
				course.setCredit(rs.getInt(4));
				course.setChour(rs.getInt(5));
				/* end */
				return course;
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
		
		return null;
	}
	
	public void addCourse(Course course) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			/* jz */
			conn = DBUtil.getConnection();
			String sql = "insert into course(cid, cname, t_no, credit, chour) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, course.getCid());
			pstmt.setString(2, course.getCname());
			pstmt.setInt(3, course.getTno());
			pstmt.setInt(4, course.getCredit());
			pstmt.setInt(5, course.getChour());
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
	
	public void updateCourse(Course course) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			/* jz */
			conn = DBUtil.getConnection();
			String sql = "update course set cname=?,t_no=?,credit=?,chour=? where cid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, course.getCname());
			pstmt.setInt(2, course.getTno());
			pstmt.setInt(3, course.getCredit());
			pstmt.setInt(4, course.getChour());
			pstmt.setInt(5, course.getCid());
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
	
	public void deleteCourseById(int cid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from course where cid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
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
