package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Member;
import mybatis.config.DBService;

public class MemberDAO {
	
	private SqlSessionFactory factory;
	private static MemberDAO instance; // 아래에서 없을 경우 만들어주기 때문에 여기서 new MemberDAO()를 생략해도된다.
	
	private MemberDAO() {
		factory = DBService.getInstance().getFactory();
	}
	
	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	
	/* 회원 목록 조회 */
	public List<Member> selectMemberList(){
		SqlSession ss = factory.openSession();
		List<Member> list = ss.selectList("dao.member.selectMemberList");
		ss.close();
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
}
