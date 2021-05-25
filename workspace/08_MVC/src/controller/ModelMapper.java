package controller;

import model.StudentCommand;
import model.StudentScoreCommand;

public class ModelMapper {
	
	private static ModelMapper mapper = new ModelMapper();
	private ModelMapper () {}
	public static ModelMapper getInstance() {
		if(mapper == null) {
			mapper = new ModelMapper();
		}
		return mapper;
	}
	
	public StudentCommand getModel(String cmd) {
		StudentCommand command = null;
		switch(cmd) {
		case "inquiry.do":
			command = new StudentScoreCommand();
			break;
		}
		return command;
	}
	
	
}
