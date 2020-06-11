<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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
			* {
/* 			border:1px solid #F00; */
		}
		body {
			background:#AAFFEE url(images/admin_main.jpg);
			text-align:center;
			background-size:100% 210%;
			margin:0;
			padding:0;
			background-repeat:no-repeat;
			
		}
		#wel {
			margin: 100px 400px 0 400px;
			border:1px solid #000;
		}
		a {
			font-size:1.5em;
			background: #DDDDDD;
		}
		a:hover {background: #00BBFF;}
	</style> 

  </head>
  
  <body>
  	
  	<div id="wel">
  		<h1>欢迎登录，${admin}</h1>
  		<p><a href="adminSearchCourse.jsp"><font color=red >课程信息</font></a></p>
  		<p><a href="adminSearchStudent.jsp"><font color=red >学生信息</font></a></p>
  		<p><a href="adminAddCourse.jsp"><font color=red >添加课程</font></a></p>
  		<p><a href="adminAddStudent.jsp"><font color=red >添加学生</font></a></p>
		<p><a><font color=red >成绩查询</font></a></p>
  		<p><a href="servlet/LoginServlet?action=logout"><font color=red >退出登录</font></a></p>  		
  	</div>
  </body>
</html>
