<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="com.wenr.model.*,com.wenr.dao.Gradedao" %>
<jsp:useBean id="Gradedao" class="com.wenr.dao.Gradedao" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生登录</title>
    
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
			background: url(images/student_selected.jpg);
			text-align:center;
			background-size:100% 370%;
			margin:0;
			padding:0;
			background-repeat:no-repeat;
		}
		table {
			border-collapse:collapse;
			margin:0px auto;
			text-align:center;
			font-size:1.2em;
		}
		h1 {
			text-align:center;
		}

		p {
			font-size:1.2em;
			text-align:center;
		}
	
	</style>

  </head>
  
  <body>
  	<h1>成绩单</h1>
  	<table border="1px" cellspacing="0px">
  		<tr>
  			<td>课程号</td>
  			<td>课程名</td>
  			<td>学生号</td>
  			<td>学生名</td>
  			<td>教师号</td>
  			<td>教师名</td>
  			<td>成绩</td>
  		</tr>
  		
  		<%
  			String[] color = {"yellow", "green"};
  			Student student = (Student)session.getAttribute("student");
  			ArrayList<Grade> list = Gradedao.get_student_Grade(student.getSid());
  			for (int i = 0; i < list.size(); i++) {
  				Grade grade = list.get(i);
  		%>
  			<tr bgcolor="<%=color[i%2] %>" >
				<td><%=grade.getCid() %></td>
				<td><%=grade.getCname() %></td>
				<td><%=grade.getSid() %></td>
				<td><%=grade.getSname() %></td>
				<td><%=grade.getTno() %></td>
				<td><%=grade.getTname() %></td>
				<td><%=grade.getScore() %></td>
  			</tr>
  			
  		<%
  			}
  		%>
  		
  	</table>
  	<p><a href="studentMain.jsp">[返回主界面]</a></p>
  	
  </body>
</html>