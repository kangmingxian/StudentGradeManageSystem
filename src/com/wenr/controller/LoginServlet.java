package com.wenr.controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wenr.dao.StudentDao;
import com.wenr.model.Student;
import com.wenr.dao.TeacherDao;
import com.wenr.model.Teacher;
public class LoginServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public LoginServlet() {
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
		
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		String path = request.getContextPath();
		String action = request.getParameter("action");

		if ("login".equals(action)) {
			String inumber = request.getParameter("inumber");
			String password = request.getParameter("password");
			String identity = request.getParameter("identity");
			String[] isUserCookies = request.getParameterValues("isUseCookie");
			
			if ("student".equals(identity)) {
				// �����¼����ѧ��
				StudentDao studentDao = new StudentDao();
				Student student = new Student();
				try {
					student.setSid(Integer.parseInt(inumber));
				} catch (Exception ex) {
					response.sendRedirect(path + "/failure.jsp");
					return ;
				}
				
				student.setSpwd(password);
				if (studentDao.isValid(student)) {
					// �˺�����Ϸ�
					session.setAttribute("student", student);
					if (isUserCookies != null && isUserCookies.length > 0) {
						saveCookie(inumber, password, response);
					} else {
						notSaveCookie(inumber, password, request, response);
					}		  		
					response.sendRedirect(path + "/studentMain.jsp");
				} else {
					// ���Ϸ�
					response.sendRedirect(path + "/failure.jsp");
				}
				
			} else if ("admin".equals(identity)) {
				// �����¼���ǹ���Ա
				if ("001".equals(inumber) && "001".equals(password)) {
					// �˺�����Ϸ�
					session.setAttribute("admin", "����Ա");
		  			if (isUserCookies != null && isUserCookies.length > 0) {
						saveCookie(inumber, password, response);
					} else {
						notSaveCookie(inumber, password, request, response);
					}
		  			response.sendRedirect(path + "/adminMain.jsp");
				} else {
					// ���Ϸ�
					response.sendRedirect(path + "/failure.jsp");
				}
			} else if ("teacher".equals(identity)) {
				
					// �����¼������ʦ
					TeacherDao teacherDao = new TeacherDao();
					Teacher teacher = new Teacher();
					try {
						teacher.setTid(Integer.parseInt(inumber));
					} catch (Exception ex) {
						response.sendRedirect(path + "/failure.jsp");
						return ;
					}
					
					teacher.setTpwd(password);
					if (teacherDao.isValid(teacher)) {
						// �˺�����Ϸ�
						session.setAttribute("teacher", teacher);
						if (isUserCookies != null && isUserCookies.length > 0) {
							saveCookie(inumber, password, response);
						} else {
							notSaveCookie(inumber, password, request, response);
						}		  		
						response.sendRedirect(path + "/teacherMain.jsp");
					} else {
						// ���Ϸ�
						response.sendRedirect(path + "/failure.jsp");
					}
					
			}
			
		} else if ("logout".equals(action)) {
			// �˳���¼
			session.invalidate();
			response.sendRedirect(path + "/index.jsp");
		}
		
	} 
	
	// ��ס�˺�����
	public void saveCookie(String inumber, String password, HttpServletResponse response) {
		Cookie inumberCookie = new Cookie("inumber", inumber);
		Cookie passwordCookie = new Cookie("password", password);
		// ����Cookie�洢·��  ����index��ȡ��������
		inumberCookie.setPath("/");
		passwordCookie.setPath("/");
		inumberCookie.setMaxAge(864000);	// 10 days
		passwordCookie.setMaxAge(864000);
		response.addCookie(inumberCookie);
		response.addCookie(passwordCookie);
	}
	
	// ����ס�˺�����
	public void notSaveCookie(String inumber, String password, 
			HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies  = request.getCookies();
		for (Cookie cookie: cookies) {
			if (cookie.getName().equals("inumber") || cookie.getName().equals("password")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
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
