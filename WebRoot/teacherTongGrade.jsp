<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="com.wenr.model.*,com.wenr.util.DBUtil,com.wenr.dao.*,java.sql.Connection,java.util.ArrayList,java.sql.ResultSet,java.sql.PreparedStatement" %>
<jsp:useBean id="teacherdao" class="com.wenr.dao.TeacherDao" />
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
  
  		<h1>成绩统计</h1>  		
  	  	<table border="1px" cellspacing="0px">
  		<tr>s
  			<td>课程号</td>
  			<td>课程名</td>
  			<td>平均分</td>
  			<td>及格人数</td>
  			<td>总人数</td>  			
  		</tr>

  		<%
  			String[] color = {"yellow", "green"};
	  		Teacher teacher = (Teacher)session.getAttribute("teacher");
			Grade grade = new Grade();
			double avg;
			int all;
			int a;
			int i =11;
			Connection conn = null;
			PreparedStatement pstmt = null;
  			ResultSet rs = null;
  			conn = DBUtil.getConnection();
  			String sql = "SELECT avg.cid,avg.cname,avg.t_no,avg.avg,avg.`all` FROM avg WHERE avg.t_no =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,i);
			
			rs = pstmt.executeQuery();
			while(rs != null && rs.next())
			{
				grade.setCid(rs.getInt(1));
				grade.setCname(rs.getString(2));
				grade.setTno(rs.getInt(3));
				avg = rs.getDouble(4);
				all = rs.getInt(5);
				a = teacherdao.getjige(grade);
				%>
				<tr bgcolor="<%=color[i%2] %>" >
					<td><%=grade.getCid() %></td>
					<td><%=grade.getCname() %></td>
					<td><%=avg%></td>
					<td><%=a%></td>
					<td><%=all %></td>
				</tr>
			  			
			  	<%
			  	i++;
			}
			
  		%>
  		</table>
  		
  		<p><a href="teacherMain.jsp">[返回主界面]</a></p>
  </body>
</html>
