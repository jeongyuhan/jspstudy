<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:include page="../layout/header.jsp">
	<jsp:param value="게시판" name="title"/>
</jsp:include>
<link rel="stylesheet" href="../assets/css/layout.css">
<style>
	.board_list {
		width: 600px;
		margin: 0 auto;
	}
	
	table {
		border-collapse: collapse;
	}
	
	td {
		border: 1px solid gray;
		padding: 5px;
		text-align: center;
	}
	
	td:nth-of-type(1) {width: 100px;}
	td:nth-of-type(2) {width: 200px;}
	td:nth-of-type(3) {width: 100px;}
	td:nth-of-type(4) {width: 100px;}
	td:nth-of-type(5) {width: 100px;}
	
	
</style>
<div class="board_list">
	<%-- 로그인을 해야만 게시글을 작성할 수 있다. --%>
	<c:if test="${loginDTO != null}">
		<input type="button" value="게시글 작성하기" onclick="location.href = '/10_MODEL2/insertBoardPage.b'">
		<br><br>
	</c:if>
	
	<p>전체 : ${totalRecord}개 게시물</p>
	<table>
		<thead>
			<tr>
				<td>게시글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>조회수</td>
				<td>최종수정일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dto" items="${list}">
				<tr>
					<td>${dto.idx}</td>
					<td>${dto.title}</td>
					<td>${dto.author}</td>
					<td>${dto.hit}</td>
					<td>${dto.lastmodified}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<tfoot>
		<tr>
			<td colspan="5">
				
			</td>
		</tr>
	</tfoot>
</div>

<%@ include file="../layout/footer.jsp" %>
