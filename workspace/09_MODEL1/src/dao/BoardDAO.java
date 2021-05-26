package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.util.DBConnector;
import dto.BoardDTO;
import dto.PageVO;

public class BoardDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private static BoardDAO dao = new BoardDAO();
	
	private BoardDAO() {
		con = DBConnector.getInstance().getConnection();
	}
	
	public static BoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	/* 1. 게시글 삽입 */
	public int insertBoard(BoardDTO dto) {
		int result = 0;
		try {
			sql = "INSERT INTO BOARD VALUES (BOARD_SEQ.NEXTVAL, ?, ?, ?, 0, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getAuthor());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, null);
		}
		return result;
	}
	
	/* 2. 전체 게시글 반환 */
	public List<BoardDTO> selectAll (PageVO pageVO){
		List<BoardDTO> list = new ArrayList<>();
		try {
			/***************************************************************************************************************
            SELECT B.RN, B.EMPLOYEE_ID, B.FIRST_NAME
			  FROM (SELECT ROWNUM AS RN, A.EMPLOYEE_ID, A.FIRST_NAME
			          FROM (SELECT EMPLOYEE_ID, FIRST_NAME
			                  FROM EMPLOYEES
			                 ORDER BY HIRE_DATE) A) B
			 WHERE B.RN BETWEEN 11 AND 20;
			 -- WHERE ROWNUM BETWEEN 11 AND 20; 처럼 ROWNUM 1이 포함되지 않으면 반환해주지 않는다.
			 -- 위 코드를 정상적으로 사용하려면 가상칼럼인 ROWNUM을 정규칼럼으로 바꿔주는 작업이 필요하다.
			 -- AS를 통해 별명을 주면 정규칼럼으로 바뀐다.
			 -- 그 후 실행순서에 맞춰 FROM절에서 ROWNUM을 바꿔준다. 
			 
			 -- EMPLOYEE_ID로 정렬을 하여 가져오기위해선 서브쿼리 내부에서 ORDER BY 작업을 해준다.
			 -- 단, ROWNUM에 번호를 붙여주기 전에 정렬을 해야 하므로 첫번째 서브쿼리 안에서 다시 두번째 서브쿼리를 넣어준다.
			 ******************************************************************************************************************/
			
			sql = "SELECT B.IDX, B.AUTHOR, B.TITLE, B.CONTENT, B.HIT, B.POSTDATE" +
				  "  FROM (SELECT ROWNUM AS RN, A.IDX, A.AUTHOR, A.TITLE, A.CONTENT, A.HIT, A.POSTDATE" +
				  "          FROM (SELECT IDX, AUTHOR, TITLE, CONTENT, HIT, POSTDATE" +
				  "                  FROM BOARD" +
				  "                 ORDER BY POSTDATE DESC) A) B" +
				  " WHERE B.RN BETWEEN ? AND ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pageVO.getBeginRecord());
			ps.setInt(2, pageVO.getEndRecord());
			rs = ps.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setIdx(rs.getLong(1));
				dto.setAuthor(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setHit(rs.getInt(5));
				dto.setPostdate(rs.getDate(6));
				list.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, rs);
		}
		return list;
	}
	
	/* 3. 조회수 증가 */
	public void updateHit(long idx) {
		try {
			sql = "UPDATE BOARD SET HIT = HIT + 1 WHERE IDX = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, idx);
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, null);
		}
	}
	
	/* 4. 게시글 반환 */
	public BoardDTO selectByIdx(long idx) {
		BoardDTO dto = null;
		try {
			sql = "SELECT AUTHOR, TITLE, CONTENT, HIT, POSTDATE FROM BOARD WHERE IDX = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, idx);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setIdx(idx);
				dto.setAuthor(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setHit(rs.getInt(4));
				dto.setPostdate(rs.getDate(5));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, rs);
		}
		return dto;
	}
	
	/* 5. 게시글 삭제 */
	public int deleteBoard(long idx) {
		int result = 0;
		try {
			sql = "DELETE FROM BOARD WHERE IDX = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, idx);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, null);
		}
		return result;
	}
	
	/* 6. 게시글 수정 */
	public int updateBoard(BoardDTO dto) {
		int result = 0;
		try {
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE IDX = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			ps.setLong(3, dto.getIdx());
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, null);
		}
		return result;
	}
	
	/* 7. 전체 게시글의 개수 반환 */
	public int getTotalRecord() {
		int totalRecord = 0;
		try {
			sql = "SELECT COUNT(IDX) FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				totalRecord = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, rs);
		}
		
		
		return totalRecord;
	}
	
	
	
}
