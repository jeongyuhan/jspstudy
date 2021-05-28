package command.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class JoinCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		// join.jsp에서 보낸 id, pw, name, email
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		// DB로 보낼 DTO 생성
		MemberDTO dto = new MemberDTO(id, pw, name, email);
		
		// DAO의 join() 메소드 호출
		int result = MemberDAO.getInstance().join(dto);
		
		try {
			// 응답을 만들 Printwriter
			PrintWriter out = response.getWriter();
			if(result > 0) {
				out.println("<script>");
				out.println("alert('가입되었습니다.')");
				out.println("location.href='/10_MODEL2/loginPage.m'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('가입에 실패하였습니다..')");
				out.println("location.href='/10_MODEL2/joinPage.m'");
				out.println("</script>");				
			}
			out.flush();
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		} 
		
		return null; // 성공/실패 여부에 따라 location을 통해 이동하기 때문에 ModelAndView에 null을 return해준다.
		             // null을 반환할 경우의 처리는 ModelAndView에 처리하는 메소드를 만들어두었다. 만약 만들지 않았다 하더라도 문제없이 진행된다.
	}

}
