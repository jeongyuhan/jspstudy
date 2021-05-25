package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public class RectangleCommand implements ShapeCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		int width = 0;
		int height = 0;
		
		try {
			width = Integer.parseInt(request.getParameter("width"));
			height = Integer.parseInt(request.getParameter("height"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// 결과 값을 request에 저장
		request.setAttribute("area", width * height);
		
		// 응답 View만 반환
		// return "views/output.jsp";
		
		// 응답 View와 이동경로 반환
		return new ModelAndView("views/output.jsp", false);	
		
	}

}
