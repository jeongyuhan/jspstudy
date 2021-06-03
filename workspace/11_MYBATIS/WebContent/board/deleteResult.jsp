<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script>
		if('${param.result}' > 0) {
			alert('답글이 삭제되었습니다.');
			location.href = '/11_MYBATIS/selectList.do';
		} else {
			alert('답글 삭제에 실패했습니다.');
			history.back();
		}
	</script>
</head>
<body>
	
</body>
</html>