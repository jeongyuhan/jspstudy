<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	
	<a href="/11_MYBATIS/insertPage.do">새글작성</a>
	<br><br><br>
	<form action="/11_MYBATIS/findList.do"> 
		<select name="column">
			<option value="TITLE">내용</option>
			<option value="AUTHOR">작성자</option>
			<option value="BOTH">내용 + 작성자</option>
		</select>
		<input type="text" name="query"><button>검색</button>
	</form>
	전체 게시글 : ${totalRecord}<br>
	<table border="1">
		<thead>
			<tr>
				<th>순번</th>
				<th>제목</th>
				<th>작성자</th>
				<th>최종수정일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="5">작성된 게시물이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach var="dto" items="${list}" varStatus="k">
					<tr>
						<td>${seq - k.index}</td>
						<td>
						<c:if test="${dto.depth == 1}">
							&nbsp;&nbsp;[re]
						</c:if>
						${dto.title}						
						<c:if test="${dto.depth == 0}">
							<font size="1"><a href="/11_MYBATIS/insertReplyPage.do?groupno=${dto.no}">답글</a></font>						
							<%-- 게시물의 no를 넘겨주어 inserReply.jsp에서 어떤 게시물의 답글인 알아내는데 사용할 수 있도록한다.  --%>
						</c:if> 
						</td> 
						<td>${dto.author}</td>
						<td>${dto.lastmodified}</td>
						<td>${dto.hit}</td>						
					</tr>				
				</c:forEach>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">${paging}</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>