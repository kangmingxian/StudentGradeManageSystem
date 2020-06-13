package com.wenr.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wenr.dao.CourseDao;
import com.wenr.dao.StudentDao;
import com.wenr.dao.Gradedao;
import com.wenr.model.Course;
import com.wenr.model.Grade;
import com.wenr.model.Student;

public class AdminServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public AdminServlet() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String action = request.getParameter("action");
		String path = request.getContextPath();		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		CourseDao courseDao = new CourseDao();
		
		if ("delete".equals(action)) {
			// 删除课程
			courseDao.deleteCourseById(Integer.parseInt(request.getParameter("cid")));
	  		response.sendRedirect("../adminSearchCourse.jsp");
		} else if ("update".equals(action)) {
			// 更新课程
			Course course = new Course();
			course.setCid(Integer.parseInt(request.getParameter("cid")));
			course.setCname(request.getParameter("cname"));
			course.setCredit(Integer.parseInt(request.getParameter("credit")));
			courseDao.updateCourse(course);
	  		response.sendRedirect("../adminSearchCourse.jsp");
		} else if ("addStudent".equals(action)) {
			Student student = new Student();
			StudentDao studentDao = new StudentDao();
		  	student.setSid(Integer.parseInt(request.getParameter("sid")));
		  	student.setSname(request.getParameter("sname"));
		  	student.setSsex(request.getParameter("ssex"));
		  	student.setSpwd(request.getParameter("spwd"));
			studentDao.addStudent(student);
			response.sendRedirect("../adminAddStudent.jsp");
		} else if ("addCourse".equals(action)) {
			/* jz */
			Course course = new Course();
			course.setCid(Integer.parseInt(request.getParameter("cid")));
			course.setCname(request.getParameter("cname"));
			course.setTno(Integer.parseInt(request.getParameter("tno")));
			course.setCredit(Integer.parseInt(request.getParameter("credit")));
			course.setChour(Integer.parseInt(request.getParameter("chour")));
			courseDao.addCourse(course);
			response.sendRedirect("../adminAddCourse.jsp");
			/* end */
		}
		/* jz */
		else if ("searchGrade".equals(action)) {
			String s = "";
			int sid = 0;
			if (request.getParameter("sid") != null) {
				s = request.getParameter("sid");
			}
			
			ArrayList<Grade> list = new ArrayList<Grade>();
			Gradedao dao = new Gradedao();
			if ("".equals(s)) {
				list = dao.getAllGrade();
			} else {
				sid = Integer.parseInt(s);
				list = dao.getGradeBySid(sid);
			}
			request.setAttribute("gradeList", list);
			request.setAttribute("course", sid);
			// 这里本来想用response.sendRedirect(location); 蓝儿发现并不可以传递值
			// 请求转发就是到另一个页面去处理  所以这里就是请求转发比较合适（我猜……
			request.getRequestDispatcher("../adminSearchGrade.jsp").forward(request, response);
		}/* end */
		
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
