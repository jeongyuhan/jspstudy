package insertBoardPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.board.BoardCommand;
import common.ModelAndView;

public class insertBoardPageCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		return new ModelAndView("/10_MODEL2/board/insertBoardPage.jsp", true);
	}

}
