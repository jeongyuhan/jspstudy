package ex04;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ex02
 */
@WebServlet("/Ex02")
public class Ex02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ex02() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// 1. <a>태그를 이용해서 넘어오면 100% get 방식이다.
		// 2. 함께 넘어오는 파라미터는 request가 처리한다.
		
		// request에서 파라미터 꺼내는 방법
		// 1. getParameter() 메소드를 이용한다.
		// 2. String getParameter(String parameter){ } 형식이다. 한 마디로 String을 반환한다.
		
		String name = request.getParameter("name");
		if(name != null && !name.isEmpty()) {
			System.out.println(name);			
		}
		
		String strHeight = request.getParameter("height");
		int height =0;
		if(strHeight != null && !strHeight.isEmpty()) {
			height = Integer.parseInt(strHeight);			
		}
		
		String strWeight = request.getParameter("weight");
		double weight = 0.0;
		if(strWeight != null && !strWeight.isEmpty()) {
			weight = Double.parseDouble(strWeight);
		}
		
		System.out.println("키 : " + height + "cm, 몸무게 : " + weight + "kg");
		// 파라미터 값이 넘어오지 않을 경우 null값이 넘어오게되고 null값을 parseInt등으로 변환하려고하면 Exception이 발생하기 때문에 if로 처리하는 구문을 추가해준다.
		// 또한 스코프의 문제가 생기기 때문에 height와 weight를 미리 선언해주고 초기화를 시켜둔다.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
