package com.wenr.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wenr.dao.Gradedao;
import com.wenr.model.Grade;
import java.util.regex.Pattern;
/**
 * Servlet implementation class TeacherServlet
 */
@WebServlet(  
displayName = "This is teacher Action", //����  
name = "TeacherServlet", //servlet����  
urlPatterns = { "/servlet/TeacherServlet" },//url  
loadOnStartup = 1 //������  
//initParams = { @WebInitParam(name = "username", value = "����") }//��ʼ������  
)


public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
     * @see HttpServlet#HttpServlet()
     */
    public TeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<h1>ɾ���ɹ���</h1>");
		doPost(request,response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String action = request.getParameter("action");
		String path = request.getContextPath();		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Gradedao grade = new Gradedao();
		
		if ("searchGrade".equals(action)) {
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
			// ���ﱾ������response.sendRedirect(location); �������ֲ������Դ���ֵ
			// ����ת�����ǵ���һ��ҳ��ȥ����  ���������������ת���ȽϺ��ʣ��Ҳ¡���
			request.getRequestDispatcher("../teacherSearchGrade.jsp").forward(request, response);
		}
		else if ("add_score".equals(action)) {
			// ��ӳɼ�
			if(!request.getParameter("score1").isEmpty()&&isint(request.getParameter("sid"))&&isint(request.getParameter("cid"))&&isdouble(request.getParameter("score1")))
			{
				grade.teacher_add_score(Integer.parseInt(request.getParameter("sid")), Integer.parseInt(request.getParameter("cid")), Double.parseDouble(request.getParameter("score1")));
			}
	  		response.sendRedirect("../teacherAddScore.jsp");
			}
		
		
		
		
		
	}

}
