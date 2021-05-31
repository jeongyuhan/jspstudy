package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.board.BoardCommand;
import common.ModelAndView;

@WebServlet("*.b")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request, response 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 요청 분리 작업
		String[] arr = request.getRequestURI().split("/");
		String cmd = arr[arr.length - 1]; 
		/* 
		 * 요청 분리 작업(다른방법)
		 * String requestURI = request.getRequestURI(); 	// /10_MODEL2/insertBoard.b
		 * String contextPath = request.getContextPath(); 	// /10_MODEL2
		 * requestURI.substring(contextPath.length() + 1);  // insertBoard.b
		*/
		
		
		// command들을 호출 할 수 있도록 BoardCommand를 생성
		BoardCommand command = BoardCommandMapper.getInstance().getCommand(cmd);
		ModelAndView mav = null;
		if(command != null) {
			mav = command.execute(request, response);
		}
		if(mav == null) {
			return;
		}
		if(mav.isRedirect()) {
			response.sendRedirect(mav.getView());
		} else {
			request.getRequestDispatcher(mav.getView()).forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
