package ex02;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class YourServlet
 * 
 * 1. 서블릿을 실행하는 방법
 * 		1) 톰캣을 실행한다.
 * 		2) 톰캣의 컨테이너에 프로젝트를 담는다.(실행할 때(F11) next를 눌러 프로젝트를 컨테이너에 담는 작업)
 * 		3) 주소창에 URL을 입력해도 실행할 수 있다. 
 * 			- URL => 호스트명:포트번호/ContextPath/URLMapping
 * 			- ContextPath는 해당 프로젝트로 생성된 웹 페이지들의 기본 주소로 사용된다. 기본값은 "프로젝트명"이다.
 * 			- URLMapping은 어떤 페이지의 주소로 사용된다. 기본값은 "서블릿명"이다.
 * 	
 * 2. ContextPath 수정 방법 2가지
 * 		1) 프로젝트 생성 단계에서 Context root를 변경한다.
 * 		2) 생성된 프로젝트의 Properties(속성)에서 Context root를 변경한다.
 * 		   : 프로젝트 우클릭 - Properties - Web Project Settings 
 * 
 * 3. URLMapping 수정 방법 3가지
 * 		1) 서블릿 생성 단계에서 URL Mapping을 변경한다.
 * 		2) 완성된 서블릿의 @WebServlet() 애너테이션 값을 변경한다.
 * 		3) web.xml을 열고 <servlet> 태그와 <servlet-mapping> 태그를 추가해준다.
 */
@WebServlet("/galaxy") // 여기가 URL mapping이다. 여기서 URL mapping값을 바꿔줘도 된다.
public class YourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YourServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
