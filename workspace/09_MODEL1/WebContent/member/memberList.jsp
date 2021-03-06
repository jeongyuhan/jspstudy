<%@page import="dao.MemberDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		.container {
			width: 600px;
			margin: 100px auto;
			text-align: center;
		}
		
		table {
			border-collapse: collapse;
			width: 100%;
		}
		
		td {
			border: 1px solid black;		
		}
	</style>
</head>
<body>
	
	<%
		List<MemberDTO> list = MemberDAO.getInstance().selectAll();
		pageContext.setAttribute("list", list);
	%>
	
	<div class="container">
		<table>
			<thead>
				<tr>
					<th>회원번호</th>
					<th>아이디</th>
					<th>비밀번호</th>
					<th>이름</th>
					<th>이메일</th>
					<th>가입일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${list}" >
					<tr>
						<td>${dto.no}</td>
						<td>${dto.id}</td>
						<td>${dto.pw}</td>
						<td>${dto.name}</td>
						<td>${dto.email}</td>
						<td>${dto.regdate}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6">
						<input type="button" value="되돌아가기" onclick="history.back()">
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	
</body>
</html>