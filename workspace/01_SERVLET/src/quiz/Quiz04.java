package quiz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
@WebServlet("/Quiz04")
public class Quiz04 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Quiz04() {
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
		String ip = request.getHeader("X-Forwarded_For"); // 거쳐서 온 경우를 기본값으로 두고 거쳐서 오지 않은 경우를 if문으로 처리해준다. 
		if(ip == null) { // 거치지 않고 직접 왔다면
			ip = request.getRemoteAddr();
		}
		
		// 작성일자
		// 1) SimpleDateFormat() 을 이용한 작성일자 구현
		String date = new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(new Date());
		
		// 생성할 파일명
		String filename = date.substring(0, 10) + "_" + writer + ".txt";
		
		// 파일 경로
		File file = new File("C:\\spring0303\\jspstudy", filename);

		// 파일 생성 스트림
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		
		// 파일 생성
		bw.write("작성일자 : " + date); bw.newLine();// 줄바꿈메소드 (\n 과 같은 역할)
		bw.write("작성IP : " + ip); bw.newLine();
		bw.write("작성자 : " + writer); bw.newLine();
		bw.write("제목 : " + title); bw.newLine();
		bw.write(contents); bw.newLine();
		
		if(bw != null) {
			bw.close();			
		}
		
		
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		//response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('" + filename + "파일이 생성되었습니다.')");
		out.println("history.back()");
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
