package quiz;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Quiz05_1
 */
@WebServlet("/Quiz05_1")
public class Quiz05_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Quiz05_1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 리다이렉트는 기존 request의 정보를 전달하지 않는다. 
		// 따라서, 우리가 직접 다시 전달한다.
		
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String age = request.getParameter("age"); // 다시 파라미터로 붙여보낼것이기 때문에 parse할 필요가 없다.
		
		response.sendRedirect("/01_SERVLET/Quiz05_2?name=" + URLEncoder.encode(name, "utf-8") + "&age=" + age); // name의 경우 깨지기때문에 직접 인코딩을 해준다. 인코딩 : URLEncoder.encode("문자열", "UTF-8")
																												//												  디코딩 : URLDecoder.decode("문자열", "UTF-8")			
		// 새로운 파라미터를 생성하여 받아온 파라미터를 적용시켜준다.
		// 자동으로 안넘어갈 뿐 받아온 파라미터를 가지고 다시 새로운 파라미터 전달 코드를 생성해주면 된다.
		System.out.println(name + ", " + age);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
