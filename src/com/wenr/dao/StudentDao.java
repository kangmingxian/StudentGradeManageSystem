package com.wenr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wenr.model.Course;
import com.wenr.model.Student;
import com.wenr.util.DBUtil;

public class StudentDao {
	
	public boolean isValid(Student student) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		
		try {
			/* jz */
			conn = DBUtil.getConnection();
			String sql = "select * from student where sid=? and spwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, student.getSid());
			pstmt.setString(2, student.getSpwd());
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				student.setSname(rs.getString("sname"));
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
		/* end */
		return false;
	}
	
public ArrayList<Course> getSelectedCourse(Student student) {
		
		ArrayList<Course> list = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select course.cid,course.cname,course.t_no,course.credit,course.chour,score.score from course,score,student where student.sid=score.sid and student.sid=? and course.cid=score.cid;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, student.getSid());
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				Course course = new Course();
				course.setCid(rs.getInt(1));
				course.setCname(rs.getString(2));
				course.setTno(rs.getInt(3));
				course.setCredit(rs.getInt(4));
				course.setChour(rs.getInt(5));
				course.setScore(rs.getInt(6));
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
	
	public ArrayList<Student> getAllStudent() {
		
		ArrayList<Student> list = new ArrayList<Student>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from student";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				Student student = new Student();
				/* jz */
				student.setSid(rs.getInt(1));
				student.setSname(rs.getString(2));
				student.setSsex(rs.getString(3));
				student.setSpwd(rs.getString(4));
				/* end */
				list.add(student);
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
	

public boolean deleteCourse(Student student, int cid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select score from score where sid=? and cid=? and score is not NULL;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, student.getSid());
			pstmt.setInt(2, cid);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				// 存在成绩 不可以删除
				return false;
			} else {
				// 成绩不存在，可以删除
				if (pstmt != null) pstmt.close();
				sql = "delete from score where sid=? and cid=?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, student.getSid());
				pstmt.setInt(2, cid);
				pstmt.executeUpdate();
				return true;
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
	
public boolean addCourse(Student student, int cid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from score where sid=? and cid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, student.getSid());
			pstmt.setInt(2, cid);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				// 该同学已经选过该门课 不能重复选择
				return false;
			} else {
				// 没选过 可以选择
				sql = "insert into score(sid, cid) values(?, ?)";
				if (pstmt != null) pstmt.close();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, student.getSid());
				pstmt.setInt(2, cid);
				pstmt.executeUpdate();
				return true;
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
	
	public int addStudent(Student student) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			/* jz */
			conn = DBUtil.getConnection();
			String sql = "insert into student(sid, sname, ssex, spwd) values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, student.getSid());
			pstmt.setString(2, student.getSname());
			pstmt.setString(3, student.getSsex());
			pstmt.setString(4, student.getSpwd());
			/* end */
			rs=pstmt.executeUpdate();
			return rs;
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
		return rs;
	}
	
	public static void main(String[] args) {
		StudentDao dao = new StudentDao();
		Student student = new Student();
		student.setSid(100000000);
		student.setSpwd("1");
		dao.isValid(student);
		System.out.println(student.getSname());
		
		ArrayList<Course> list = dao.getSelectedCourse(student);
		for (Course course: list) {
			System.out.println(course.getCname() + "," + course.getCredit() + "," + course.getScore());
		}
		
		dao.deleteCourse(student, 100000003);
		
	}
	
	public ArrayList<Student> searchGrede() {
		
		ArrayList<Student> list = new ArrayList<Student>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from student";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				Student student = new Student();
				/* jz */
				student.setSid(rs.getInt(1));
				student.setSname(rs.getString(2));
				student.setSsex(rs.getString(3));
				student.setSpwd(rs.getString(4));
				/* end */
				list.add(student);
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
