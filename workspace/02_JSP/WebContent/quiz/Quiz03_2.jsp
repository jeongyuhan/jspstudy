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
	%>
	
	<form action="/02_JSP/quiz/Quiz03_3.jsp">
		<h3>2. 좋아하는 운동선수는 누구인가요?</h3>
		<input type="text" name="player">
		<!-- 화면에는 보이지 않지만 받아온 파라미터를 다시 input에 넣어 Quiz03_3으로 넘겨준다. -->
		<input type="hidden" name="name" value="<%=request.getParameter("name")%>">
		<input type="hidden" name="entertainer" value="<%=request.getParameter("entertainer")%>">
		<button>결과보기</button>
	</form>
</body>
</html>