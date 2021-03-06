package quiz;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Quiz03
 */
@WebServlet("/Quiz03")
public class Quiz03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Quiz03() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		
		// IP주소
		// 1. 기본은 request.getRemoteAddr()로 알아낼 수 있다.
		// 2. 거쳐서 온 IP는 X-Forwarded-For 요청 헤더(request header)에 있다.
		// ip 추가작업 = 화면 상단 Run 클릭 - Run Configurations... 클릭 - Tomcat v8.5 선택 - Arguments 클릭 - VM Arguments 에서 -Djava.net.preferIPv4Stack=true를 맨 밑줄에 작성하여 추가해준다.
		String ip = request.getHeader("X-Forwarded_For"); // 거쳐서 온 경우를 기본값으로 두고 거쳐서 오지 않은 경우를 if문으로 처리해준다. 
		if(ip == null) { // 거치지 않고 직접 왔다면
			ip = request.getRemoteAddr();
		}
		
		// 작성일자
		// 1) SimpleDateFormat() 을 이용한 작성일자 구현
		String date = new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(new Date());
		
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		//response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title>제목</title>");
		out.println("</head>");
		out.println("<<body>");
		out.println("<ul>");
		out.println("<li>제목 : " + title + "</li>");
		out.println("<li>작성자 : " + writer + "</li>");
		out.println("<li>내용 <pre>" + contents + "</pre></li>");
		out.println("<li>IP :" + ip + "</li>");
		out.println("<li>작성일자 :" + date + "</li>");
		out.println("</ul>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
