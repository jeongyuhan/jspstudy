package quiz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Quiz01
 */
@WebServlet("/Quiz02") // 서블릿을 복사하여 붙여서 사용할 때 서블릿 이름을 바꿔도 Mapping값은 바뀌지 않으므로 여기를 바꿔주도록 해야한다.
public class Quiz02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Quiz02() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String cmd = request.getParameter("cmd");
		
		Calendar calendar = Calendar.getInstance(); // java를 사용하기 때문에 Calendar 사용가능
		String message = "";
		
		// 받아온 파라미터 cmd의 값에 따라 날짜를 출력할 것인지 시간을 출력할 것인지 다르기 때문에 switch문을 통해 구현해준다.
		switch(cmd) {
		case "date":
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH)+1;
			int date = calendar.get(Calendar.DATE);
			message = year + "년" + month + "월" + date + "일";
			break;
		case "time":
			int hour = calendar.get(Calendar.HOUR);
			int minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			message = hour + "시" + minute + "분" + second + "초";
			break;
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('" + message + "')");
		out.println("history.go(-1)");
		out.println("</script>");
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
