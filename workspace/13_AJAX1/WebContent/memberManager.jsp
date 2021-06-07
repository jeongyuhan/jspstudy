<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		// 페이지 로드 이벤트
		$(document).ready(function(){
			
			selectMemberList();
			
		})
		
		// 함수
		function selectMemberList(){
			$.ajax({
				url: 'selectMemberList.do',
				type: 'get',
				dataType: 'json',
				success: function(result){
					/*
						result = {
							'isExist' : true(or false),	
						}
					*/
					if(result.isExist) { // 목록이 있다면,
						generateMemberList(result);
					} else { // 목록이 없다면
						// tr, td 태그를 생성하여 memberList 아래에 넣겠다는 의미 
						$('<tr>')
						.append( $('<td colspan="6">').text('회원 목록이 없습니다.') )
						.appendTo('#memberList');
					}
				},
				error: function(xhr, status, error){
					console.log(status + " : " + error);
					alert('회원 목록 로드를 실패했습니다.');
				}
			})
		}
		
	</script>
	<style>
		* {
			box-sizing: border-box;
		}
		.container {
			display: flex;
		}
		.left {
			width: 500px;
		}
		.right {
			width: 1000px;
		}
		table {
			border-collapse: collapse;
		}
		td {
			padding: 5px 10px;
			text-align: center;
			border-top: 1px solid gray;
			border-bottom: 1px solid gray;
		}
	</style>
</head>
<body>

	<div class="container">
		<%-- 회원삽입/수정/보기 --%>
		<div class="left">
			<h3>삽입/수정/보기</h3>
			아이디<br>
			<input type="text" name="id" id="id"><br><br>
			이름<br>
			<input type="text" name="name" id="name"><br><br>
			성별<br>
			<label><input type="radio" name="gender" value="남">남</label>
			<label><input type="radio" name="gender" value="여">여</label><br><br>
			주소<br>
			<select name="address" id="address">
				<option value="">지역 선택</option>
				<option value="서울">서울</option>
				<option value="인천">인천</option>
				<option value="대전">대전</option>
				<option value="대구">대구</option>
				<option value="광주">광주</option>
				<option value="울산">울산</option>
				<option value="부산">부산</option>
			</select><br><br>
			<input type="button" value="회원등록" id="insert_btn">
			<input type="button" value="정보수정" id="update_btn">
			<input type="button" value="초기화" id="init_btn">			
		</div>
		<%-- 회원목록/삭제 --%>
		<div class="right">
			<h3>회원목록/삭제</h3>
			<table>
				<thead>
					<tr>
						<td>회원번호</td>
						<td>아이디</td>
						<td>이름</td>
						<td>성별</td>
						<td>주소</td>
						<td>버튼</td>
					</tr>
				</thead>
				<tbody id="memberList"></tbody>
			</table>
		</div>
	</div>

</body>
</html>