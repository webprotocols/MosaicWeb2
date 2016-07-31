<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.html</title>
</head>
<body>
<h1>설정확인¸</h1>
<ol>
	<li><a href="Hello.html">HTML 설정확인¸</a></li>
	<li><a href="Hello.jsp">JSP 설정확인¸</a></li>
	<li><a href="HelloServlet">Servlet 설정확인¸</a></li>
	<li><a href="dept.jsp">Mybatis 설정확인¸</a></li>
	
</ol>

<a href="/admin/admin.jsp">어드민 페이지로 이동</a><br>

<%
	for (int i=0; i<10; i++) {
		log("i = " + i);
	}

%>
</body>
</html>