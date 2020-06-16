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
import java.util.regex.Pattern;

public class AdminServlet extends HttpServlet {
	/*判断非负数*/
	public static boolean isInt(String str){
		    Pattern pattern = Pattern.compile("^[+]?[0-9]+");
		    return pattern.matcher(str).matches();  
		}
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
			if(isInt(request.getParameter("cid")))
			{
				courseDao.deleteCourseById(Integer.parseInt(request.getParameter("cid")));
			}
	  		out.print("<script>alert('删除成功'); window.location='../adminSearchCourse.jsp' </script>");
	  		
		} else if ("update".equals(action)) {
			// 更新课程
			Course course = new Course();
			if(isInt(request.getParameter("cid"))&&isInt(request.getParameter("credit")))
			{
				course.setCid(Integer.parseInt(request.getParameter("cid")));
				course.setCname(request.getParameter("cname"));
				course.setCredit(Integer.parseInt(request.getParameter("credit")));
				courseDao.updateCourse(course);
			}
	  		response.sendRedirect("../adminSearchCourse.jsp");
		} else if ("addStudent".equals(action)) {
			Student student = new Student();
			StudentDao studentDao = new StudentDao();
			int rs = 0;
			if(isInt(request.getParameter("sid")))
			{
			  	student.setSid(Integer.parseInt(request.getParameter("sid")));
			  	student.setSname(request.getParameter("sname"));
			  	student.setSsex(request.getParameter("ssex"));
			  	student.setSpwd(request.getParameter("spwd"));
				rs = studentDao.addStudent(student);;
				if(rs != 0)
				{
					out.print("<script>alert('添加成功'); window.location='../adminAddStudent.jsp' </script>");
				}
				else
					out.print("<script>alert('添加失败，请检查'); window.location='../adminAddStudent.jsp' </script>");	
			}
			else
			{
				out.print("<script>alert('学号错误，请检查'); window.location='../adminAddStudent.jsp' </script>");
			}
		} else if ("addCourse".equals(action)) {
			/* jz */
			Course course = new Course();
			int rs = 0;
			if(isInt(request.getParameter("cid"))&&isInt(request.getParameter("tno"))&&isInt(request.getParameter("credit"))&&isInt(request.getParameter("chour")))
			{
				course.setCid(Integer.parseInt(request.getParameter("cid")));
				course.setCname(request.getParameter("cname"));
				course.setTno(Integer.parseInt(request.getParameter("tno")));
				course.setCredit(Integer.parseInt(request.getParameter("credit")));
				course.setChour(Integer.parseInt(request.getParameter("chour")));
				
				rs = courseDao.addCourse(course);
				if(rs != 0)
				{
					out.print("<script>alert('添加成功'); window.location='../adminAddCourse.jsp' </script>");
				}
				else
					out.print("<script>alert('添加失败，请检查'); window.location='../adminAddCourse.jsp' </script>");
				
			}
			else {
				out.print("<script>alert('添加失败，请检查'); window.location='../adminAddCourse.jsp' </script>");
			}
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
				if(isInt(s))
				{
					sid = Integer.parseInt(s);
					list = dao.getGradeBySid(sid);
				}

			}
			if(!list.isEmpty())
			{
				request.setAttribute("gradeList", list);
				request.setAttribute("course", sid);
				// 这里本来想用response.sendRedirect(location); 蓝儿发现并不可以传递值
				// 请求转发就是到另一个页面去处理  所以这里就是请求转发比较合适（我猜……
				request.getRequestDispatcher("../adminSearchGrade.jsp").forward(request, response);
			}
			else
			{
				out.print("<script>alert('该学号查不到成绩'); window.location='../adminSearchGrade.jsp' </script>");
			}

		}
			/* end */
		
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
