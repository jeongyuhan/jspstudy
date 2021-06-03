<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		.link {
			color: blue;
			font-size: 8px;
			text-decoration: none;
		}
		.link:hover {
			cursor: pointer;
		}
		.insert_reply {
			display: none;
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			// '답글'을 클릭하면 아래 insert_reply 클래스가 toggle된다.
			/*
				<tr>	
					<td>
						<a class="link">답글</a>
					</td>
				</tr>
				<tr class="insert_reply">
				</tr>
			*/	
			const links = $('.link'); 
			// links가 배열이므로 link.click()이 불가능하다. 배열은 each문(for문)을 통해 처리한다.
			// links배열처럼 타겟이 여러개일 경우 each(for)문을 돌려 처리한다음 parent()등으로 객체를 찾아간다.
			// $.each(배열, function(인덱스, 요소){})
			$.each(links, function(i, link){
				$(link).click(function(){ // $(link) == <a class="link">답글</a>
					// $(this) == $(link)
					$(this).parent().parent().next().toggleClass('insert_reply');
				})
			})
			const insert_reply = $('.insert_reply');
			
		})
	</script>
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
						<c:if test="${dto.state == 0}">
							<c:if test="${dto.depth == 1}">
								&nbsp;&nbsp;[re]
							</c:if>
							${dto.title}						
							<c:if test="${dto.depth == 0}">
								<a class="link">답글</a>						
							</c:if> 
							<!-- 
								-- loginDTO를 생성했을때 가능한 코드
								<c:if test="${loginDTO.id == dto.author}">
									<a href="">삭제</a>
								</c:if>
							-->
							<a href="/11_MYBATIS/delete.do?no=${dto.no}">삭제</a>
						</c:if>
						</td> 						
						<c:if test="${dto.state == -1}">
							삭제된 게시글입니다.
						</c:if>
						<td>${dto.author}</td>
						<td>${dto.lastmodified}</td>
						<td>${dto.hit}</td>						
					</tr>	
					<tr class="insert_reply">
						<form action="/11_MYBATIS/insertReply.do">
							<input type="hidden" name="groupno" value="${dto.groupno}">
							<td><input type="text" name="author" placeholder="작성자"></td>
							<td><input type="text" name="title" placeholder="제목"></td>
							<td><input type="text" name="content" placeholder="내용"></td>
							<td><button>작성</button></td>
							<td><input type="button" value="취소"></td>
						</form>
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