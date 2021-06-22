package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.PersonDAO;

class PersonTEST {

	// int size = 0;
	
	@BeforeEach // 실행순서 1
	void setUp() throws Exception {
		// size = PersonDAO.getInstance().selectPersonList().size();
	}

	@AfterEach // 실행순서 3
	void tearDown() throws Exception {
	}

	@Test // 실행순서 2
	void test() {
		// assertEquals(2, size, "등록된 사람은 2명이 아니다.");
		// fail("Not yet implemented");
		
		// 978521 주민번호 검색
		assertNotNull(PersonDAO.getInstance().selectPersonBySno("978521"), "978521주민번호는 없다.");
	}

}
