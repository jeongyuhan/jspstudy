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
			selectMemberByNo();
			updateMember();
			init(); 
			insertMember();
			deleteMember();
		})
		
		// 함수
		// 1. 회원 목록 가져오기
		function selectMemberList(){
			$.ajax({
				url: '/13_AJAX/selectMemberList.do',
				type: 'get',
				dataType: 'json',
				success: function(result){
					/*
						result = {
							{"list":[
										{"no":5,"address":"부산","gender":"여","name":"에이미","id":"user5"},
										{"no":4,"address":"대구","gender":"남","name":"스미스","id":"user4"},
										{"no":3,"address":"대전","gender":"여","name":"제시카","id":"user3"},
										{"no":2,"address":"인천","gender":"여","name":"앨리스","id":"user2"},
										{"no":1,"address":"서울","gender":"남","name":"제임스","id":"user1"}
									],
							"isExist":true}

						}
					*/
					
					// 전체 회원 수 
					$('#totalRecord').text('전체회원 : ' + result.paging.totalRecord + '명');
					
					// 기존의 목록을 화면에서 제거
					$('#memberList').empty();
					
					if(result.isExist) { // 목록이 있다면,
						generateMemberList(result.list);
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
		
		// 1-1. 회원 목록을 받아서 테이블을 생성하는 함수
		function generateMemberList(list){
			$.each(list, function(i, member){ // $.each는 반복문이다.(for문과 같은)
				$('<tr>')
				.append($('<td>').text(member.no))
				.append($('<td>').text(member.id))
				.append($('<td>').text(member.name))
				.append($('<td>').text(member.gender))				
				.append($('<td>').text(member.address))
				.append($('<input type="hidden" name="no">').val(member.no))
				.append($('<td>').html('<input type="button" value="조회" id="view_btn"><input type="button" value="삭제" id="delete_btn">'))
				.appendTo('#memberList');
			})
		}
		
		// 2. 회원 정보 가져오기
		function selectMemberByNo(){
			/* ajax로 만든 동적 객체(#view_btn)에 이벤트 처리가 안됨 
			$('#view_btn').on('click', function(){
				alert('조회하시겠습니까?');				
			})
			*/
			
			$('body').on('click', '#view_btn', function(){
				// 회원번호 출력하기 (정보를 가져오고자 하는 no)
				// var no = $(this).parent().parent().find('input:hidden[name="no"]').val(); // $(this) == #view_btn
				var no = $(this).parents('tr').find('input:hidden[name="no"]').val();
				$.ajax({
					url: '/13_AJAX/selectMemberByNo.do',
					type: 'get',
					data: 'no=' + no,
					success: function(result){
						/*
						result = {
								{"no":5,
									"address":"부산",
									"gender":"여",
									"name":"에이미",
									"id":"user5",
									"isExist":true}
						}
						*/
						if(result.isExist) {
							$('.left input:text[name="id"]').val(result.id);
							// $('#id').val(result.id); 위아래코드 모두 id에 값을 넣어주는 방식이다.
							$('#name').val(result.name);
							$('.left input:radio[name="gender"][value="'+ result.gender + '"]').prop('checked', true); // attr == prop 주된 역할은 같다.
							$('#address').val(result.address);
							// 정보 수정에 사용하기 위한 no 데이터를 아래와같이 사용할 수 있도록 해준다.
							$('.left input:hidden[name="no"]').remove(); // 기존 hidden 제거한 뒤
							$('.left').append($('<input type="hidden" name="no">').val(result.no)); // 새로운 hidden 추가 
						} else {
							alert('해당 회원 정보를 확인할 수 없습니다.');
						}
					},
					error: function(xhr, status, error){
						console.log(status + " : " + error);
						alert('회원 정보 로드를 실패했습니다.');
					}
				})
			})
		}
		
		// 3. 회원 정보 수정
		function updateMember(){
			$('#update_btn').click(function(){
				// 서버로 JSON데이터 보내기
				var obj = {
						"no" : $('input:hidden[name="no"]').val(),
						"id" : $('#id').val(),
						"name" : $('#name').val(),
						"gender" : $('input:radio[name="gender"]:checked').val(),
						"address" : $('#address').val()
				};
				// console.log(JSON.stringify(obj));
				$.ajax({
					url: '/13_AJAX/updateMember.do',
					type: 'post',
					data: 'member=' + JSON.stringify(obj), // 파라미터로 보내기
					dataType: 'json',
					success: function(result){
						if(result.isSuccess){
							alert('회원 정보가 수정되었습니다.');
							selectMemberList(); // 수정된 정보로 회원 목록을 다시 만들기
						} else {
							alert('변경된 회원 정보가 없습니다.');
						}
					},
					error: function(xhr, status, error){
						console.log(status + " : " + error);
						alert('회원 정보 수정을 실패했습니다.');
					}
				})
			})
		}
		
		// 4. 초기화 
		function init(){
			$('#init_btn').click(function(){
				$('#id').val('');
				$('#name').val('');
				$('input:radio[name="gender"]').prop('checked', false);
				$('#address').val('');
			})
		}
		
		// 5. 회원 추가하기
		function insertMember(){
			$('#insert_btn').click(function(){
				var obj = {
						id : $('#id').val(),
						name : $('#name').val(),
						gender : $('input:radio[name="gender"]:checked').val(),
						address : $('#address').val()
				};
				// console.log(JSON.stringify(obj));		
				
				$.ajax({
					url: '/13_AJAX/insertMember.do',
					type: 'post',
					data: 'member=' + JSON.stringify(obj),
					dataType: 'json',
					success: function(result){
						if(result.isSuccess) {
							alert('신규 회원이 추가되었습니다.');
							selectMemberList();
						} else {
							alert('신규 회원 등록이 되지 않았습니다.');
						}
					},
					error: function(xhr, status, error){
						console.log(status + " : " + error);
						alert('회원 등록에 실패했습니다.');
					}	
				})
			})
			
		}
		
		// 6. 회원 삭제하기
		function deleteMember(){
			$('body').on('click', '#delete_btn', function(){ // ('이벤트', '이벤트대상', 'fn')
				/*
					<tr>
						<td>
							<input type="button" value="삭제" id="delete_btn">
						</td>
						<input type="hidden" name="no" value="5">
					</tr>	
				*/
				// 번호를 꺼내오는 부분은 충분히 이해하고 연습해야한다.
				var no = $(this).parent().parent().find('input:hidden[name="no"]').val();
				// alert(no);
				if(confirm(no + '번 회원을 삭제할까요?')) {
					$.ajax({
						url: '/13_AJAX/deleteMember.do',
						type: 'get',
						data: 'no=' + no,
						dataType: 'json',
						success: function(result){
							if (result.isSuccess) {
								alert('회원 정보가 삭제되었습니다.');
								selectMemberList();
							} else {
								alert('삭제된 회원이 없습니다.');
							}
						},
						error: function(xhr, status, error){
							console.log(status + " : " + error);
							alert('회원 삭제에 실패했습니다.');
						}	
					})
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
			<div id="totalRecord"></div>
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