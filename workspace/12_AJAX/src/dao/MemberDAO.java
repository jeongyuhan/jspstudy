package dao;

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
	
	/* 1. 로그인 */
	public Member login(Member member) {
		SqlSession ss = factory.openSession();
		Member loginUser = ss.selectOne("dao.member.login", member);
		ss.close();
		return loginUser;
	}
	
	
	/* 2. 아이디 체크 */
	public boolean idCheck(String id) {
		SqlSession ss = factory.openSession();
		Member member = ss.selectOne("dao.member.idCheck", id);
		ss.close();
		return member == null; // 일치하는 id를 가진 member가 없다 == 사용가능한 id
	}
	
	/* 3. 회원가입 */
	public int join(Member member) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("dao.member.join", member);
		
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	/* 4. 비밀번호 수정 */
	public int updatePw(Member member) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("dao.member.updatePw", member);
		
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	/* 5. 회원 정보 수정 */
	public int updateMember(Member member) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("dao.member.updateMember", member);
		
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	/* 6. 회원 탈퇴 */
	public int deleteMember(long no) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete("dao.member.deleteMember", no);
		
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
}
