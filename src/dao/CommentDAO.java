package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.CommentDTO;
import dto.FreebbsDTO;
import utill.DBManager;

public class CommentDAO {
	private static CommentDAO instance = new CommentDAO();
	
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private CommentDAO() {
		
	}
	
	public static CommentDAO getInstance() {
		return instance;
	}
	
	public int getWriteNo() {
		try {
			String sql = "select web_comment_no.nextval from dual";
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("nextval");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return 0;
	}
	
	public List<CommentDTO> allList(int startNum, int endNum, int no, String type){
		List<CommentDTO> cList = new ArrayList<>();
		try {
			String sql = "select * from web_comment where no=? and type=?";
			
//			String sql = "select t.* from "
//					+ "(select sub.*, rownum as rn from "
//					+ "(select * from web_comment order by cno) sub) t "
//					+ "where rn between ? and ?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, type);
			
//			pstmt.setInt(1, startNum);
//			pstmt.setInt(2, endNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentDTO cDTO = new CommentDTO();
				
				cDTO.setCno(rs.getInt("cno"));
				cDTO.setNo(rs.getInt("no"));
				cDTO.setId(rs.getString("id"));
				cDTO.setName(rs.getString("name"));
				cDTO.setComments(rs.getString("comments"));
				cDTO.setDay(rs.getString("day"));
				cDTO.setType(rs.getString("type"));
				cDTO.setGrp(rs.getInt("grp"));
				cDTO.setSeq(rs.getInt("seq"));
				cDTO.setLvl(rs.getInt("lvl"));
				
				cList.add(cDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return cList;
	}
	
	public int allListCount(int no) {
		try {
			String sql = "select count(*) from web_comment where no=?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return 0;
	}
	
	public boolean insert(CommentDTO cDTO) {
		try {
			String sql = "insert into web_comment(cno, no, id, name, comments, day)"
					+ " values(?, ?, ?, ?, ?, sysdate)";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cDTO.getCno());
			pstmt.setInt(2, cDTO.getNo());
			pstmt.setString(3, cDTO.getId());
			pstmt.setString(4, cDTO.getName());
			pstmt.setString(5, cDTO.getComments());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return false;
	}

}
