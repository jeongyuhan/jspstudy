package command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.PersonDAO;
import dto.Person;

@WebServlet("/insertPerson.do")
public class InsertPersonCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertPersonCommand() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			
			String sno = request.getParameter("sno");
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age")); // NumberFormatException 발생
			if(age < 0 || age > 100) { // 유효 범위를 벗어난 나이가 입력된 경우 
				// 예외의 강제 발생
				throw new RuntimeException(); // RuntimeException 발생
			}
			String birthday = request.getParameter("birthday");
			
			Person p = new Person();
			p.setSno(sno);
			p.setName(name);
			p.setAge(age);
			p.setBirthday(birthday);
			
			int result = PersonDAO.getInstance().insertPerson(p);
			
			JSONObject obj = new JSONObject();
			obj.put("result", result);
			response.getWriter().println(obj);
			
		} catch(NumberFormatException e) {
			// 나이 : 정수 이외의 값이 입력된 경우
			response.setStatus(3001); // 에러 코드 값을 작성, xhr.status를 통해서 확인
			response.getWriter().println("나이는 정수만 입력 가능합니다."); 
		} catch(RuntimeException e) {
			// 나이 : 유효 범위 이외의 값이 입력된 경우
			response.setStatus(3002); // 에러 코드 값을 작성, xhr.status를 통해서 확인
			response.getWriter().println("나이는 0~100 사이만 입력 가능합니다."); 	
		} catch(SQLException e) {
			// 이름, 생일 : 칼럼의 크기보다 길이가 긴 값이 입력됨
			response.setStatus(3003);
			response.getWriter().println("입력 데이터를 확인하세요.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
