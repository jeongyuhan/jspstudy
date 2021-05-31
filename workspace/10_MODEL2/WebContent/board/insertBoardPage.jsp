<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:include page="../layout/header.jsp">
	<jsp:param value="게시글 작성하기" name="title"/>
</jsp:include>
<link rel="stylesheet" href="../assets/css/layout.css">
<style>
	.insert_form {
		width: 600px;
		margin: 0 auto;
	}
	
	
</style>
<script>
	$(document).ready(function(){
		const f = $('#f');
		const title = $('#title');
		const insert_btn = $('#insert_btn');
		insert_btn.click(function(){
			if(title.val() == '') {
				alert('제목은 필수입니다.');
				title.focus();
				return;
			}
			f.attr('action', '/10_MODEL2/insertBoard.b');
			f.submit();
		})
		
		const list_btn = $('#list_btn');
		list_btn.click(function(){
			location.href = '/10_MODEL2/selectListBoardPage.b';
		})
	})
</script>
<div class="insert_form">
	<form id="f" method="post" enctype="multipart/form-data">
		<p class="title">작성자</p>
		<input type="text" id="author" name="author" value="${loginDTO.id}" readonly><br>
		<p class="title">제목</p>
		<input type="text" id="title" name="title" autofocus><br>
		<p class="title">첨부하기</p>
		<input type="file" id="filename" name="filename"><br><br>
		<p class="content">내용</p>
		<textarea id="content" name="content"></textarea><br><br>
		<input type="hidden" name="ip" value="<%=request.getRemoteAddr()%>">
		<div class="btns">
			<input type="button" value="작성하기" id="insert_btn">
			<input type="reset" value="입력초기화" id="reset_btn">
			<input type="button" value="목록보기" id="list_btn">
		</div>
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>