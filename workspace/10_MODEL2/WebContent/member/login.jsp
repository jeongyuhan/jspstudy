<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../assets/css/layout.css">

<% request.setCharacterEncoding("UTF-8"); %>
<jsp:include page="../layout/header.jsp">
	<jsp:param value="로그인" name="title"/>
</jsp:include>

<style>
	.login_form {
		width: 300px;
		margin: 50px auto;
	}
	
	#f input {
		padding: 5px;
		width: 100%;
		height: 50px;
	}
	
	#f button {
		width: 100%;
		height: 50px;
		line-height: 50px;
		background-color: yellow;
		border: none;
		font-size: 18px;		
	}
	
	#f button:hover {
		cursor: pointer;
	}
	
	.message {
		font-size: 12px;
		color: crimson;
	}
</style>

<script>
	$(document).ready(function(){
		const f = $('#f');
		const id = $('#id');
		const pw = $('#pw');
		const id_message = $('#id_message');
		const pw_message = $('#pw_message');
		
		f.submit(function(event){
			if(id.val() == ''){
				id_message.text('아이디를 입력하세요');
				id.focus();
				event.preventDefault();		
				return false;
			}
			else if(pw.val() == ''){
				id_message.text(''); // id_message가 입력된 뒤 작업이기 때문에 기존에 출력된 id_message를 지워주는 작업을 해준다.
				pw_message.text('비밀번호를 입력하세요');
				pw.focus();
				event.preventDefault();
				return false;
			}
		})
				
	})
</script>

<div class="login_form">
	<form id="f" method="post" action="/10_MODEL2/login.m">
		<input type="text" name="id" id="id" placeholder="ID"><br>
		<span class="message" id="id_message"></span><br>
		<input type="password" name="pw" id="pw" placeholder="PW"><br>
		<span class="message" id="pw_message"></span><br>
		<button>로그인</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>