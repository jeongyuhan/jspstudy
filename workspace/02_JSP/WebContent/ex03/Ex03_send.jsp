<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function fn(f){
		// f는 document.forms.send_form과 같다.
		// 스크립트 코드를 통해서 f의 action을 지정한 뒤 submit을 할 수 있다.
		f.action = '/02_JSP/ex03/Ex03_receive.jsp'; // form은 input의 name이 파라미터이기 때문에 경로를 작성할 때 파라미터를 작성해주면 안된다.
		f.submit();
	}
</script>
</head>
<body>
	
	<form name="send_form">
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br>
		<input type="button" value="Ex03_receive.jsp로보내기" onclick="fn(this.form)">
	</form>
	
</body>
</html>