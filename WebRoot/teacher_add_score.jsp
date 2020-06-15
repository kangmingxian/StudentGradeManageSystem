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
    	
  	<h1>成绩录入</h1>
  
  	<table border="1px" cellspacing="0px">
  		<tr>
  			<td>课程号</td>
  			<td>课程名</td>
  			<td>学生号</td>
  			<td>学生名</td>
  			<td>现在的成绩</td>
  			<td>更改的成绩</td>
  			<td>操作</td>
  		</tr>
  		
  		<%
  			String[] color = {"yellow", "green"};
  			Teacher teacher = (Teacher)session.getAttribute("teacher");
  			ArrayList<Grade> list = Gradedao.show_all_score((teacher.getTid()));
  			for (int i = 0; i < list.size(); i++) {
  				Grade t= list.get(i);
  		%>
  		<form action="servlet/StudentServlet?action=add_score&cid=<%=t.getCid()%>&sid=<%=t.getSid() %>" method="post">
  			<tr bgcolor="<%=color[i%2] %>" >
				<td><%=t.getCid() %></td>
				<td><%=t.getCname() %></td>
				<td><%=t.getSid() %></td>
				<td><%=t.getSname() %></td>
				<td><%=t.getScore() %></td>
	
	
				<td><input  id="x" name="score1" /></td>

				<td><input id="sub" type="submit" value="确认" > </td>
				
  			</tr>
  		</form>
  		<%
  			}
  		%>
  		
  	</table>
  	<p><a href="teacherMain.jsp">[返回主界面]</a></p>
  	
  </body>
</html>