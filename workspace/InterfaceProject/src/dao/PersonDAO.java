package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import dto.Person;

public class PersonDAO {
	
	private static PersonDAO instance;
	
	private PersonDAO() {
		
	}
	
	public static PersonDAO getInstance() {
		if(instance == null) {
			instance = new PersonDAO();
		}
		return instance;
	}
	
	private Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // jdbc 이름
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost", "spring", "1111");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	private void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(con != null) con.close();
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		} catch(Exception e) {
			 e.printStackTrace();
		}
	}
	
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// 1. list 반환
	public List<Person> selectPersonList() {
		List<Person> list = new ArrayList<Person>();
		try {
			con = getConnection();
			sql = "SELECT SNO, NAME, AGE, BIRTHDAY, REGDATE FROM PERSON";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Person p = new Person();
				p.setSno(rs.getString(1));
				p.setName(rs.getString(2));
				p.setAge(rs.getInt(3));
				p.setBirthday(rs.getString(4));
				p.setRegdate(rs.getDate(5));
				list.add(p);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list;
	}
	
	// 2. 추가
	public int insertPerson(Person person) throws SQLIntegrityConstraintViolationException, SQLException {
		int result = 0;
		con = getConnection();
		sql = "INSERT INTO PERSON VALUES (?, ?, ?, ?, SYSDATE)";
		ps = con.prepareStatement(sql);
		ps.setString(1, person.getSno());
		ps.setString(2, person.getName());
		ps.setInt(3, person.getAge());
		ps.setString(4, person.getBirthday());
		result = ps.executeUpdate();
		
		close(con, ps, null);
		
		return result;
	}
	
	// 3. 삭제
	public int deletePerson(String sno) {
		int result = 0;
		try {
			con = getConnection();
			sql = "DELETE FROM PERSON WHERE SNO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, sno);
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	// 4. 한명의 정보를 가져오는 메소드를 통해 JUnit으로 테스트하기위한 메소드
	public Person selectPersonBySno(String sno) {
		Person person = null;
		try {
			con = getConnection();
			sql = "SELECT SNO, NAME, AGE, BIRTHDAY, REGDATE FROM PERSON WHERE SNO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, sno);
			rs = ps.executeQuery();
			if (rs.next()) {
				person = new Person();
				person.setSno(rs.getString(1));
				person.setName(rs.getString(2));
				person.setAge(rs.getInt(3));
				person.setBirthday(rs.getString(4));
				person.setRegdate(rs.getDate(5));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return person;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
