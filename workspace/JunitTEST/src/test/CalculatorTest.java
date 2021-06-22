package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Calculator;

class CalculatorTest {

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Before");
	}

	@AfterEach
	void tearDown() throws Exception {		
		System.out.println("After");
	}

	@Test // 이 메소드는 테스트 메소드이다.
	@DisplayName("1 + 1 = 2")
	void test() { // 메소드명이 test일 필요는 없다. but, test메소드에는 @Test 애너테이션이 들어가 줘야 한다.
		Calculator calculator = new Calculator();
		// assertNull(calculator); // calculator가 Null인지 체크하는 코드(Null이면 통과, Null이 아니면 실패)
		// assertNotNull(calculator); // calculator가 Null이 아닌지 체크하는 코드(Null이 아니면 통과, Null이면 실패)
		assertEquals(3, calculator.add(1, 1), "1 + 1 = 2 이어야 한다.");	// assertEquals(expected, actual); expected(=기대한 값), actual(=실제 값)
		
		System.out.println("test");
		// fail("Not yet implemented"); // 실패의 경우 메시지를 만들어주는 코드이다.
		/*
			fail코드 사용의 예
		
			if(DAO.getInstance().insert(person) == 0) {
				fail("삽입 실패");
			}
		*/
		/*
			assertEquals코드 사용의 예
		
			assertEquals(1, DAO.getInstance().insert(person), "삽입 실패");
		*/
		
	}

}
