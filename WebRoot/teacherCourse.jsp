<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="com.wenr.model.*,com.wenr.dao.TeacherDao" %>
<jsp:useBean id="TeacherDao" class="com.wenr.dao.TeacherDao" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>教师登录</title>
    
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
		}
		table {
			border-collapse:collapse;
			margin:0px auto;
			text-align:center;
			font-size:1.2em;
		}
		h1 {
			margin-top:100px;
			text-align:center;
		}

		p {
			font-size:1.2em;
			text-align:center;
		}
	
	</style>

  </head>
  
  <body>
    	
  	<h1>课程信息</h1>
  
  	<table border="1px" cellspacing="0px">
  		<tr>
  			<td>课程号</td>
  			<td>课程名</td>
  			<td>学分</td>
  			<td>学时</td>
  		</tr>
  		
  		<%
  			String[] color = {"yellow", "green"};
  			Teacher teacher = (Teacher)session.getAttribute("teacher");
  			ArrayList<Course> list = TeacherDao.get_teacher_Course(teacher);
  			for (int i = 0; i < list.size(); i++) {
  				Course course = list.get(i);
  		%>
  			<tr bgcolor="<%=color[i%2] %>" >
				<td><%=course.getCid() %></td>
				<td><%=course.getCname() %></td>
				<td><%=course.getCredit() %></td>
				<td><%=course.getChour() %></td>
  			</tr>
  			
  		<%
  			}
  		%>
  		
  	</table>
  	<p><a href="teacherMain.jsp">[返回主界面]</a></p>
  	
  </body>
</html>