<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="com.wenr.model.*,com.wenr.dao.*" %>
<jsp:useBean id="gradedao" class="com.wenr.dao.Gradedao" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body {
			background:url(images/student_selected_course.jpg);
			background-size:100% 210%;
			margin:0;
			padding:0;
			background-repeat:no-repeat;
		}
		h1,form {
			text-align:center;
		}
		table {
			border-collapse:collapse;
			margin:0px auto;
			text-align:center;
			font-size:1.2em;
		}
		p {
			font-size:1.2em;
			text-align:center;
		}
	</style>

  </head>
  
  <body>
  	<%
  		request.setCharacterEncoding("utf-8"); 
  		response.setContentType("text/html;charset=UTF-8");
  	%>
  
  		<h1>查看成绩</h1>
  		<p><a href="teacherMain.jsp">[返回主界面]</a></p>
  		<form action="servlet/TeacherServlet?action=searchGrade" method="post">
  			输入要查询的学号：<input type="text" name="sid" value="" />
  			<input type="submit" value="搜索">
  		</form>
  		
  		
  	  	<table border="1px" cellspacing="0px">
  		<tr>
  			<td>课程号</td>
  			<td>课程名</td>
  			<td>学号</td>
  			<td>学生姓名</td>
  			<td>成绩</td>
  		</tr>
  		
  		<%
  			int cutpage = 1;
  			final int cntPrePage = 7;
  			if (request.getParameter("cutpage") != null) {
  				cutpage = Integer.parseInt(request.getParameter("cutpage"));
  			}
  			String[] color = {"white","yellow"};
  			
  			ArrayList<Grade> list = new ArrayList<Grade>();//(ArrayList<Grade>)request.getAttribute("courseList");
  			Object obj = request.getAttribute("gradeList");
  			//检查是不是ArrayList
  			if(obj instanceof ArrayList<?>)
  			{
  				//取出ArrayList
  				ArrayList< ? > al = (ArrayList< ? >) obj;
  				if (al.size() > 0) {
  				    // 一个个转换过来.
  				    for (int i = 0; i < al.size(); i++) {
  				      // 还得判断是不是String
  				      Object o = al.get(i);
  				      if(o instanceof Grade )
  				      {
  				    	  list.add((Grade)o);//timeSpent是前面自己定义的ArrayList
  				      }
  				    }
  			}
  			}
  			
  			int count = list.size();
  			int prepage = cutpage - 1;
  			int nxtpage = cutpage + 1;
  			if (prepage <= 0) prepage = 1;
  			if (cutpage * cntPrePage >= count) nxtpage = cutpage;
  			
  			if (list != null && list.size() > 0) {
  				for (int i = (cutpage-1)*cntPrePage; i < cutpage*cntPrePage && i < count; i++) {
	  				Grade grade = list.get(i);
	  	%>
		<tr bgcolor="<%=color[i%2] %>" >
			<td><%=grade.getCid() %></td>
			<td><%=grade.getCname() %></td>
			<td><%=grade.getSid() %></td>
			<td><%=grade.getSname() %></td>
			<td><%=grade.getScore() %></td>
		</tr>
	  			
	  	<%
	  			}
	  		}
  		%>
  		</table>
  		
  </body>
</html>
