package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.QnaDTO;
import dto.QnaDTO;
import utill.DBManager;

public class QnaDAO {
	private static QnaDAO instance = new QnaDAO();
	
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private QnaDAO() {
		
	}
	
	public static QnaDAO getInstance() {
		return instance;
	}
	
	public int getWriteNo() {
		try {
			String sql = "select web_qna_no.nextval from dual";
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
	
	public int getGrpNo() {
		try {
			String sql = "select max(grp) from web_qna";
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int grp = rs.getInt("max(grp)");
				
				if(grp>0) {
					return grp;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return 0;
	}
	
	public int getSeqNo(int grp) {
		try {
			String sql = "select max(seq) from web_qna where grp=?";
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, grp);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int seq = rs.getInt("max(seq)");
				
				if(seq>0) {
					return seq;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return 0;
	}
	
	public boolean depthLevelGrp(int grp, int seq, int lvl) {
		try {
			String sql = "select * from web_qna where grp=? and seq=? and lvl>?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, grp);
			pstmt.setInt(2, seq);
			pstmt.setInt(3, lvl);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return false;
	}
	
	public void addSeqCount(int grp, int seq) {
		try {
			String sql = " update web_qna set seq=seq+1 where grp=? and seq>?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, grp);
			pstmt.setInt(2, seq);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public List<QnaDTO> allList(int startNum, int endNum){
		List<QnaDTO> nbList = new ArrayList<>();
		try {
			String sql = "select t.* from "
					+ "(select sub.*, rownum as rn from "
					+ "(select * from web_qna order by grp desc, seq asc) sub) t "
					+ "where rn between ? and ?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QnaDTO qDTO = new QnaDTO();
				
				qDTO.setNo(rs.getInt("no"));
				qDTO.setTitle(rs.getString("title"));
				qDTO.setId(rs.getString("id"));
				qDTO.setName(rs.getString("name"));
				qDTO.setContent(rs.getString("content"));
				qDTO.setPwd(rs.getString("pwd"));
				
				String temp = rs.getString("day");
				String day = temp.substring(0, 11);
				
				qDTO.setDay(day);
				qDTO.setFname(rs.getString("fname"));
				qDTO.setReadcount(rs.getInt("readcount"));
				qDTO.setGrp(rs.getInt("grp"));
				qDTO.setSeq(rs.getInt("seq"));
				qDTO.setLvl(rs.getInt("lvl"));
				
				nbList.add(qDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return nbList;
	}
	
	public int allListCount() {
		try {
			String sql = "select count(*) from web_qna";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
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
	
	public boolean insert(QnaDTO qDTO) {
		try {
			String sql = "insert into web_qna(no, title, id, name, content, pwd, fname, day, grp)"
					+ " values(?, ?, ?, ?, ?, ?, ?, sysdate, ?)";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, qDTO.getNo());
			pstmt.setString(2, qDTO.getTitle());
			pstmt.setString(3, qDTO.getId());
			pstmt.setString(4, qDTO.getName());
			pstmt.setString(5, qDTO.getContent());
			pstmt.setString(6, qDTO.getPwd());
			pstmt.setString(7, qDTO.getFname());
			pstmt.setInt(8, qDTO.getGrp());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return false;
	}
	
	public boolean insert_reply(QnaDTO qDTO) {
		try {
			String sql = "insert into web_qna(no, title, id, name, content, pwd, fname, day, grp, seq, lvl)"
					+ " values(?, ?, ?, ?, ?, ?, ?, sysdate, ?, ?, ?)";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, qDTO.getNo());
			pstmt.setString(2, qDTO.getTitle());
			pstmt.setString(3, qDTO.getId());
			pstmt.setString(4, qDTO.getName());
			pstmt.setString(5, qDTO.getContent());
			pstmt.setString(6, qDTO.getPwd());
			pstmt.setString(7, qDTO.getFname());
			pstmt.setInt(8, qDTO.getGrp());
			pstmt.setInt(9, qDTO.getSeq());
			pstmt.setInt(10, qDTO.getLvl());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return false;
	}
	
	public QnaDTO selectOne(int no) {
		QnaDTO qDTO = null;
		try {
			String sql = "select * from web_qna where no=?";
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qDTO = new QnaDTO();
				
				qDTO.setNo(rs.getInt("no"));
				qDTO.setTitle(rs.getString("title"));
				qDTO.setId(rs.getString("id"));
				qDTO.setName(rs.getString("name"));
				qDTO.setContent(rs.getString("content"));
				qDTO.setPwd(rs.getString("pwd"));
				qDTO.setDay(rs.getString("day"));
				qDTO.setFname(rs.getString("fname"));
				qDTO.setReadcount(rs.getInt("readcount"));
				qDTO.setGrp(rs.getInt("grp"));
				qDTO.setSeq(rs.getInt("seq"));
				qDTO.setLvl(rs.getInt("lvl"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return qDTO;
	}
	
	public void addReadCount(int no) {
		try {
			String sql = "update web_qna set readcount=readcount+1 where no=?";
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("조회수 +1 성공.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public boolean delete(int no) {
		try {
			String sql = "delete from web_qna where no=?";
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
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
	
	public boolean update(int no) {
		try {
			String sql = "update web_qna set fname=null where no=?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
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
	
	public boolean update(QnaDTO qDTO) {
		try {
			String sql = "update web_qna set title=?, content=?, fname=?, pwd=? where no=?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qDTO.getTitle());
			pstmt.setString(2, qDTO.getContent());
			pstmt.setString(3, qDTO.getFname());
			pstmt.setString(4, qDTO.getPwd());
			pstmt.setInt(5, qDTO.getNo());
			
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
