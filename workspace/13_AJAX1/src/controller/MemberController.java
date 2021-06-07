package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.MemberCommand;
import command.SelectMemberListCommand;

@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String[] arr = request.getRequestURI().split("/");
		String cmd = arr[arr.length - 1];
		
		MemberCommand command = null;		
		switch (cmd) {
		case "selectMemberList.do":
			command = new SelectMemberListCommand(); 
			break;
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		ModelAndView mav = null;
		try {
			if (command != null) {
				mav = command.execute(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// ajax로 처리되는 command는 아래 코드가 동작하지 않는다.
		if (mav != null) {
			if (mav.isRedirect()) {
				response.sendRedirect(mav.getView());
			} else {
				request.getRequestDispatcher(mav.getView()).forward(request, response);
			}
		}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}