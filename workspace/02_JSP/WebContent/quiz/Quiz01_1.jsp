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
	// 2차원 배열로 사이트명과 주소를 묶어 저장한 뒤 사용하면 코드를 줄이고 편하게 사용할 수 있다.
		String[][] sites = {
				{"구글", "https://www.google.com"},
				{"네이버", "https://www.naver.com"},
				{"다음", "https://www.daum.net"},
				{"네이트", "https://www.nate.com"}
		};
	%>

	<form action="/02_JSP/quiz/Quiz01_2.jsp">
	사이트명:
		<select name="site">
			<% for(int i = 0; i < sites.length; i++) { %>
			<option value="<%=sites[i][1]%>"><%=sites[i][0]%></option> <!-- sites[i][0] : 사이트 이름 --><!-- sites[i][1] : 사이트 주소 -->
			<%-- option value="https://www.google.com">구글</option> 과 같은 코드이다. --%>
			<% } %>
		</select>
		<button>확인</button>
	</form>

</body>
</html>