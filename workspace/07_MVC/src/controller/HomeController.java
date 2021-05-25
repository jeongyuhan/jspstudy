package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import model.ShapeCommand;

@WebServlet("*.do") // .do로 끝나는 모든 요청을 처리하는 Contoller 
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public HomeController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 기본 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 2. 요청 확인
		String[] arr = request.getRequestURI().split("/"); // 작성한 action의 값이 넘어온다. (/07_MVC/xxxxxx.do) 내가 필요한건 xxxxxx.do 이기 때문에
		String cmd = arr[arr.length - 1]; // xxxxx.do만 빼내오는 작업
		
		// 3. 요청에 따른 MODEL의 선택
		//	1) 클래스명 : ModelMapper
		//	2) 매개변수 : String cmd (어떤 요청인지를 전달)
		//	3) 반환타입 : Model의 인터페이스(Model을 반환)
		ShapeCommand command = ModelMapper.getInstance().getModel(cmd);
		// command는 RectangleCommand, TriangleCommand, CircleCommand 중 하나를 반환해온다.

		// Model의 기본
		// 1. 인터페이스를 하나 생성한다.
		// 2. 해당 인터페이스를 구현한다.
		//		-> 모든 Model의 타입은 인터페이스이다.
		
		// 4. Model 실행
		// 응답 View만 반환하는 경우
		//String path = command.execute(request, response);
		// ModelAndView mav = command.execute(request, response);
		ModelAndView mav = null;
		mav = command.execute(request, response);
		if(mav == null) {
			return;
		}
		
		
		// 5. 응답View로 이동
		// forward만 사용하는 경우
		// request.getRequestDispatcher(path).forward(request, response);
		
		// redirect와 forward를 모두 사용가능하게 하는 방법
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
