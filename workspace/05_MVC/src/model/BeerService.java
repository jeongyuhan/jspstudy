package model;

import javax.servlet.http.HttpServletRequest;

public class BeerService {
	
	public String execute(HttpServletRequest request) {
		
		int age = 0;
		try {
			age = Integer.parseInt(request.getParameter("age"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("result", age < 20 ? "19세 미만에게는 판매 불가합니다." : "여기있습니다!");
		
		return "views/output.jsp";
	}
	
}
