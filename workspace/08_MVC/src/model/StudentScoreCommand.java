package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public class StudentScoreCommand implements StudentCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		int kor = 0;
		int eng = 0;
		int mat = 0;
		
		try {
			kor = Integer.parseInt(request.getParameter("kor"));
			eng = Integer.parseInt(request.getParameter("eng"));
			mat = Integer.parseInt(request.getParameter("mat"));
			double average = (kor + eng + mat) / 3.0;
			char grade = '0';
			if(average >= 90) {
				grade = 'A';
			} else if(average >= 80) {
				grade = 'B';
			} else if(average >= 70) {
				grade = 'C';
			} else if(average >= 60) {
				grade = 'D';
			} else {
				grade = 'F';
			}
			request.setAttribute("average", average);
			request.setAttribute("grade", grade);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("views/output.jsp", false);
	}

}
