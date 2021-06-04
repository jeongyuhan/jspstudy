package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import common.ModelAndView;
import dao.MemberDAO;
import dto.Member;

public class UpdateMemberCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long no = Long.parseLong(request.getParameter("no"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		Member member = new Member();
		member.setNo(no);
		member.setName(name);
		member.setEmail(email);
		member.setPhone(phone);
		
		int result = MemberDAO.getInstance().updateMember(member);
		
		if(result > 0) {
			HttpSession session = request.getSession();
			session.getAttribute("loginUser");
			Member loginUser = (Member)session.getAttribute("loginUser");
			loginUser.setName(name);
			loginUser.setEmail(email);
			loginUser.setPhone(phone);
		}
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(obj);
		out.close();
		
		return null;
	}

}
