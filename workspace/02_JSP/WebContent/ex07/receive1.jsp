<%@page import="ex07.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
		request.setCharacterEncoding("UTF-8");
	
		String name = request.getParameter("name");
		name = name.isEmpty() ? "기본이름" : name;
		
		String strAge = request.getParameter("age");
		int age = strAge.isEmpty() ? 0 : Integer.parseInt(strAge);	
		
		// 파라미터를 받아서 빈(Bean)을 만든다.
		
		// 1. 생성자로 만든 빈
		Person p1 = new Person("브레드", 50);
		
		// 2. setter로 만든 빈(주로 사용)
		Person p2 = new Person(); // 디폴트 생성자 사용
		p2.setName("윌터");
		p2.setAge(50);
		
		// 3. 파라미터로 만든 빈
		Person p3 = new Person();
		p3.setName(name);
		p3.setAge(age);
	%>
	
	<h3>이름 : <%=p1.getName()%></h3>
	<h3>나이 : <%=p1.getAge()%></h3>
	
	<h3>이름 : <%=p2.getName()%></h3>
	<h3>나이 : <%=p2.getAge()%></h3>
	
	<h3>이름 : <%=p3.getName()%></h3>
	<h3>나이 : <%=p3.getAge()%></h3>
	
</body>
</html>