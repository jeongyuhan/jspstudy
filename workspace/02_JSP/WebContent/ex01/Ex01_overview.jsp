<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- HTML 주석 : 소스보기에서 보인다. -->    
<%-- JSP 주석 : 소스보기에서 안보인다. --%>    

<%--
	JSP
	
	1. Java Server Page
	
	2. HTML문서 내부에서 Java 코드를 사용할 수 있다.
	
	3. 서블릿 기반의 서버측 언어이다.
	
	4. 모든 JSP 페이지는 서블릿으로 변환된 뒤 실행된다.
		abc.jsp => abc_jsp.java => abc_jsp.class => run
		 (JSP)      (Servlet)   
		(C:\SPRING0303\jspstudy\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\02_JSP\org\apache\jsp\ex01) 에서 확인 가능   
--%>
<%--
	JSP 스크립트 요소
	
	1. <%@ 지시어 %> : 지시어(directive)
		page 지시어, include 지시어, taglib 지시어
		
	2. <%! 선언부 %> : 선언부(declaration)
		전역변수 선언, 메소드 정의
		
	3. <%= 표현식 %> : 표현식(expression)
		결과값 출력(변수, 메소드 호출, 연산)
		 
	4. <% 스크립트릿 %> : scriptlet
		Java 코드
--%>    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	
	<% // 여기는 자바 영역이다. %>
	<!-- JSP에서는 자바 코드와 html코드를 섞어서 사용할 수 있다. -->
	<% int a = 10; %>
	<% if (a > 0) { %>
		<h3><%=a%>은 양수입니다.</h3>
	<% } else { %>
		<h3><%=a%>은 음수입니다.</h3>	
	<% } %>
	
	
	<% for(int i = 0; i < 9; i++) { %>
		<%=i%><br> <!-- 웹 브라우저에 결과를 출력하려면 표현식으로 분리해야 하기 때문에 자바 코드를 모두 <% %>로 분리해준다. -->
	<% } %>	
		
		
		
	<!-- 메소드 정의는 선언부에서 한다. -->	
	<%!
		public int getTotal(int n) {
			int total = 0;
			for(int i = 0; i <= n; i++) {
				total += i;
			}
			return total;
		}
	%>	
	<h3>결과 : <%=getTotal(10)%></h3>
	<%!
		int square = 1; // 전역 변수이다.
		public void power(int n) {
			for(int i = 1; i <= n; i++){
				square *= i;
			}
		}
	%>	
	<%-- 결과값이 없기 때문에 <%= %>이 아닌 <% %> 으로 메소드를 호출해준다. --%>
	<% power(5); %> 	
	<h3>결과 : <%=square%></h3>
</body>
</html>