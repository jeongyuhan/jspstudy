<%@page import="dao.MemberDAO"%>
<%@page import="dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. 파라미터 처리	
	request.setCharacterEncoding("UTF-8");

	String pw = request.getParameter("pw");
	
	// 2. DB로 보낼 DTO 생성
	MemberDTO dto = new MemberDTO();
	dto.setPw(pw); // 변경할 비밀번호 전달
	dto.setNo(((MemberDTO)session.getAttribute("loginDTO")).getNo()); // 어떤회원의 비밀번호를 변경할 것인지를 알려줄 pk인 no를 보내준다.
	
	// 3. DAO의 updatePW() 메소드 호출
	int result = MemberDAO.getInstance().updatePw(dto);
	
	// 4. 결과
	if(result > 0) { // if(result == 1) 과 같은 코드
		// 성공했을 때 session의 loginDTO 비밀번호가 갱신되어야 하기 때문에
		MemberDTO loginDTO = (MemberDTO)session.getAttribute("loginDTO");
		loginDTO.setPw(pw);
		out.println("<script>");
		out.println("alert('비밀번호가 수정되었습니다.')");
		out.println("location.href = 'myPage.jsp'");
		out.println("</script>");
	} else {
		out.println("<script>");
		out.println("alert('비밀번호가 수정되지 않았습니다.')");
		out.println("history.back()");
		out.println("</script>");		
	}
	
%>