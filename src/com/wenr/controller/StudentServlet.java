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
import com.wenr.model.Course;
import com.wenr.dao.StudentDao;
import com.wenr.model.Student;
import java.util.regex.Pattern;
public class StudentServlet extends HttpServlet {
	/*�жϷǸ���*/
	public static boolean isint(String str){
	    Pattern pattern = Pattern.compile("^[+]?[0-9]+");
	    return pattern.matcher(str).matches();  
	}
	/*�ж�0-100���С��*/
	public static boolean isdouble(String str){
	    Pattern pattern = Pattern.compile("^[+]?[0-9]+[.][0-9]+");
	    return (pattern.matcher(str).matches()||isint(str))&&(Double.parseDouble(str)>=0&&Double.parseDouble(str)<=100);  
	}
	/**
		 * Constructor of the object.
		 */
	public StudentServlet() {
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
		doPost(request, response);
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
		
		// �ײ��������Ҫд��ǰ��   ���򲻹��ã���= =   ��������ʵ��̫�����ˡ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String action = request.getParameter("action");
		String path = request.getContextPath();		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		
		if ("delete".equals(action)) {
			// ɾ��ѧ����ĳһ�ſγ�  ������ɼ��򲻿���ɾ��  �������ɾ��
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>ѧ����¼</TITLE></HEAD>");
			out.println("  <BODY>");
			StudentDao studentDao = new StudentDao();
			Student student = (Student)session.getAttribute("student");
	    	int cid = Integer.parseInt(request.getParameter("cid"));
	    	if (studentDao.deleteCourse(student, cid)) {
	    		// ɾ���ɹ�
	    		out.println("<h1>ɾ���ɹ���</h1>");
	    	} else {
	    		// ɾ��ʧ��
	    		out.println("<h1>ɾ��ʧ�ܣ��ѳ��ɼ��γ̲�����ɾ����</h1>");
	    	}
	    	out.println("<a href=\"" + path +  "/studentSelected.jsp\">����</a>");
	    	out.println("  </BODY>");
			out.println("</HTML>"); 
		}
		
		else if ("lookup".equals(action)) {
			String courseName = "";
			if (request.getParameter("course") != null) {
				courseName = request.getParameter("course");
			}
			courseName = courseName.trim();//ȥ����λ�ո�
			ArrayList<Course> list = new ArrayList<Course>();
			CourseDao dao = new CourseDao();
			if ("".equals(courseName)) {
				list = dao.getAllCourse();
			} else {
				list = dao.getCourseByName(courseName);
			}
			request.setAttribute("courseList", list);
			request.setAttribute("course", courseName);
			// ���ﱾ������response.sendRedirect(location); �������ֲ������Դ���ֵ
			// ����ת�����ǵ���һ��ҳ��ȥ����  ���������������ת���ȽϺ��ʣ��Ҳ¡���
			request.getRequestDispatcher("../studentSearchCourse.jsp").forward(request, response);
		}
		
		else if ("select".equals(action)) {
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>ѧ����¼</TITLE></HEAD>");
			out.println("  <BODY>");    	
			int cid = Integer.parseInt(request.getParameter("cid"));
	  		Student student = (Student)session.getAttribute("student");
	  		StudentDao studentDao = new StudentDao();
	  		if (studentDao.addCourse(student, cid)) {
	  			// �����ͬѧû��ѡ�����ſ� ѡ�γɹ�
	  			out.println("<h1>ѡ�γɹ���</h1>");
	  		} else {
	  			// ѡ��ʧ��
	  			out.println("<h1>ѡ��ʧ�ܣ������ظ�ѡ��</h1>");
	  		}
	  		// ��������ҳ  ѡ��֮����Ȼ�ص���ҳ 
	  		String param = "";
	  		if (request.getParameter("cutpage") != null) param = "&cutpage=" + request.getParameter("cutpage");
	  		out.println("<a href=\"" + path +  "/servlet/StudentServlet?action=lookup" + param + "\">����</a>");
	  		out.println("  </BODY>");
			out.println("</HTML>"); 
		}
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
