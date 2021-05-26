<%@page import="dto.MemberDTO"%>
<%@page import="dao.BoardDAO"%>
<%@page import="dto.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		* {
			font-family: '돋움', sans-serif;
			font-size: 16px;
		}
	
		.container {
			width: 600px;
			margin: 100px auto;
		}
		
		
	</style>
</head>
<body>
	
	<%
		// 1. 파라미터 처리
		request.setCharacterEncoding("UTF-8");
	
		long idx = Long.parseLong(request.getParameter("idx"));
		
		// 2. DAO의 updateHit() 메소드 호출
		
		// 본인게시글은 조회수가 늘지 않도록 작업
		// 로그인된 사용자 : loginDTO
		String author1 = ((MemberDTO)session.getAttribute("loginDTO")).getId();

		// 게시글의 작성자 : dto
		BoardDTO dto = BoardDAO.getInstance().selectByIdx(idx);
		String author2 = dto.getAuthor();		
		
		if(!author1.equals(author2)) {
			BoardDAO.getInstance().updateHit(idx);
			dto.setHit(dto.getHit() + 1);
		}
		
		// 3. DAO의 selectByIdx() 메소드 호출
		// 본인게시물 확인시 조회수에 영향을 안주는 작업을 위해 위에서 사용하였기 때문에 selectById() 메소드는 위에서 호출되고있다.
		// BoardDTO dto = BoardDAO.getInstance().selectByIdx(idx);		

		// 4. session에 게시글 올리기
		session.setAttribute("boardDTO", dto);
	%>
	
	<div class="container">
		<h3>게시글 번호</h3>
		${boardDTO.idx}<br><br>
		<h3>제목</h3>
		${boardDTO.title}<br><br>
		<h3>작성자</h3>
		${boardDTO.author}<br><br>
		<h3>조회수</h3>
		${boardDTO.hit}<br><br>
		<h3>작성일</h3>
		${boardDTO.postdate}<br><br>		
		<h3>내용</h3>
		<pre>${boardDTO.content}</pre><br><br>
		
		<%-- 작성자만 수정/삭제하기를 할 수 있다. --%>
		<c:if test="${loginDTO.id eq boardDTO.author}">
			<input type="button" value="수정하기" onclick="location.href='updatePage.jsp'">
			<input type="button" value="삭제하기" onclick="fn_delete()">		
		</c:if>
		<input type="button" value="목록으로이동" onclick="location.href='boardList.jsp'">
	</div>
	
	<script>
		function fn_delete(){
			if(confirm('삭제하시겠습니까?')){
				location.href = 'deleteBoard.jsp'; // 어떤 게시물을 삭제할지는 session에 저장되어있기 때문에 문제 없는 코드가 된다.
			} 
		}
	</script>
	
</body>
</html>