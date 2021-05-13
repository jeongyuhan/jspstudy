<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<%-- <script>
	onload = function(){
		const f = document.getElementById('agree');
		f.addEventListener('check', function(){
			if(f.value == '동의') {
				<% 
				request.setCharacterEncoding("utf-8");
				
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String name = request.getParameter("name");
			 	
				String filename = id + ".txt";
				String realPath = application.getRealPath("quiz/" + filename);
				
				File file = new File(realPath);
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write("가입 아이디 : " + id);
				bw.write("가입 비밀번호 : " + pw);
				bw.write("가입 성명 : " + name);
				if(bw != null) {
					bw.close();
				}
			%>
				location.href = '/02_JSP/quiz/Quiz05_3.jsp';
			} else {
				location.href = '/02_JSP/quiz/Quiz05_4.jsp';
			}
		})
	}
</script> --%>
</head>
<body>

	<h3>이용 약관</h3>
	------------------------------------------------------<br>
	1. 회원 정보는 웹 사이트 운영을 위해서만 사용됩니다.<br>
	2. 원치 않으면 정보 제공을 하지 않을 수 있습니다.<br>
	3. 다만, 이 경우 정상적인 웹 사이트 이용이 제한됩니다.<br>
	------------------------------------------------------<br><br>
	<form action="/02_JSP/quiz/Quiz05_3.jsp" method="post">
		위 약관에 동의하십니까?<br><br>
		<input type="radio" id="agreement" value="yes">동의 함
		<input type="radio" id="agreement" value="no">동의 안 함 <br><br>
		<%
			request.setCharacterEncoding("UTF-8");		
		%>
		<input type="hidden" name="id" value="<%=request.getParameter("id")%>">
		<input type="hidden" name="pw" value="<%=request.getParameter("pw")%>">
		<input type="hidden" name="name" value="<%=request.getParameter("name")%>">
		<input type="submit" value="회원가입">
	</form> 
</body>
</html>