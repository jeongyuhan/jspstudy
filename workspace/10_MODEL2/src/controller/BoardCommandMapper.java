package controller;

import command.board.BoardCommand;
import command.board.InsertBoardCommand;
import command.board.SelectListBoardCommand;
import insertBoardPage.insertBoardPageCommand;

public class BoardCommandMapper {
	
	// singleton 작업
	private static BoardCommandMapper instance = new BoardCommandMapper();
	private BoardCommandMapper() {}
	public static BoardCommandMapper getInstance() {
		if(instance == null) {
			instance = new BoardCommandMapper();
		}
		return instance;
	}
	
	public BoardCommand getCommand(String cmd) {
		BoardCommand command = null;
		switch(cmd) {
		case "selectListBoardPage.b":
			command = new SelectListBoardCommand();
			break;
		case "insertBoardPage.b":
			command = new insertBoardPageCommand();
			break;
		case "insertBoard.b":
			command = new InsertBoardCommand();
			break;
			
		}
		return command;
	}
	
}
