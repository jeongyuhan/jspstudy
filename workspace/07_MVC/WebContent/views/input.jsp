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
			const f = $('#f');
			const btn1 = $('#btn1');
			const btn2 = $('#btn2');
			const btn3 = $('#btn3');
			btn1.on('click', function(){
				f.attr('action', '/07_MVC/rectangle.do');
				f.submit();
			})
			
			btn2.on('click', function(){
				f.attr('action', '/07_MVC/triangle.do');
				f.submit();
			})
			
			btn3.on('click', function(){
				f.attr('action', '/07_MVC/circle.do');
				f.submit();
			})

			// location.href 를 사용하면 안된다. 왜냐하면 location.href는 이동만 할 뿐 input에 입력한 파라미터를 넘겨주지는 않기 때문이다.
			// 따라서, 이동만하는 location.href 가 아닌 action을 통해 이동하고, submit을 통해 데이터를 넘겨주는 역할을 모두 실행시켜주어야한다.
			// 요청페이지에서 보낼 데이터가 없을 때는 location.href를 사용해도 무관하다.(06_MVC에서는 보낼 데이터가 없기 때문에 location.href를 사용했다.)
		})
	</script>
</head>
<body>
	
	<form id="f">
		<h3>도형의 면적 구하기</h3>
		<input type="text" name="width" placeholder="너비 입력"><br>
		<input type="text" name="height" placeholder="높이 입력"><br>
		<input type="text" name="radius" placeholder="반지름 입력"><br>
		<input type="button" value="사각형 면적" id="btn1">
		<input type="button" value="삼각형 면적" id="btn2">
		<input type="button" value="원 면적" id="btn3">
	</form>
	
</body>
</html>