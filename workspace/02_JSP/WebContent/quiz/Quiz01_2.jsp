<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	request.setCharacterEncoding("UTF-8");

	String site = request.getParameter("site");
%>

<%-- 풀이1. 자바스크립트로 이동하기 --%>
<script>
	<%-- location.href = '<%=site%>'; --%>
</script>

<!-- 풀이2. 리다이렉트로 이동하기 -->
<%
	// site에는 전체 URL이 포함되어 있다.
	// redirect : 클라이언트에게 전체 URL을 응답하면 클라이언트가 다시 이동한다.
	response.sendRedirect(site);

	// 참고.
	// forward는 서버 내부 경로로 이동하기 때문에 전체 URL을 처리할 수 없다.
	// request.getRequestDispatcher(site).forward(request, response);
%>


<%--
	문제에 대한 정리
	<select>에 name을 주고 <option>에 있는 value로 경로를 파라미터 형식으로 받아온 뒤 처리한다.
	즉, 파라미터를 받아오는 매개체는 주소가 저장된 각각의 <option>이 아닌
	모든 <option>을 포함한 <select>를 통해 받아온다.	
--%>