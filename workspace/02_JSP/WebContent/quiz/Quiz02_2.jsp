<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 예외가 발생하면 이동할 Quiz02_error.jsp 페이지를 등록 --%>    
<%@ page errorPage="Quiz02_error.jsp" %>


<%-- 선생님 풀이 --%>
<% 	
	request.setCharacterEncoding("UTF-8");

	String strAge = request.getParameter("age");
	int age = strAge.isEmpty() ? 0 : Integer.parseInt(strAge);	
	
	if(age < 20) {
		// forward (request 그대로 전달한다.)
		request.getRequestDispatcher("Quiz02_kid.jsp").forward(request, response);
	} else {
		request.getRequestDispatcher("Quiz02_adult.jsp").forward(request, response);
		
	}
%>

  
<%--  풀이 --%>
<%
	//request.setCharacterEncoding("UTF-8");

	//String strAge = request.getParameter("age");
	//int age = strAge.isEmpty() ? 0 : Integer.parseInt(strAge);	
	
	//if(age <= 19) {
	//	out.println("당신이 나이는 " + age + "살 이므로 주류 구매가 불가능합니다.");
	//} else {
	//	out.println("당신이 나이는 " + age + "살 이므로 주류 구매가 가능합니다.");
	//}
%>
	<!-- 
	<br>
	<a href="/02_JSP/quiz/Quiz02_1.jsp">처음으로 이동</a>
 	-->
	