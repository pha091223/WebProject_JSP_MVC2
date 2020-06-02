package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.EventbbsDTO;
import utill.DBManager;

public class EventbbsDAO {
	private static EventbbsDAO instance = new EventbbsDAO();
	
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private EventbbsDAO() {
		
	}
	
	public static EventbbsDAO getInstance() {
		return instance;
	}
	
	public List<EventbbsDTO> homeList() {
		List<EventbbsDTO> ebList = new ArrayList<>();
		try {
			String sql = "select t.* from "
					+ "(select sub.*, rownum as rn from "
					+ "(select * from web_eventboard where seq=0 order by grp desc) sub) t "
					+ "where rn between ? and ?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 7);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EventbbsDTO ebDTO = new EventbbsDTO();
				
				ebDTO.setNo(rs.getInt("no"));
				ebDTO.setTitle(rs.getString("title"));
				ebDTO.setId(rs.getString("id"));
				ebDTO.setName(rs.getString("name"));
				ebDTO.setContent(rs.getString("content"));
				ebDTO.setPwd(rs.getString("pwd"));
				
				String temp = rs.getString("day");
				String day = temp.substring(0, 11);
				
				ebDTO.setDay(day);
				ebDTO.setFname(rs.getString("fname"));
				ebDTO.setReadcount(rs.getInt("readcount"));
				ebDTO.setGrp(rs.getInt("grp"));
				ebDTO.setSeq(rs.getInt("seq"));
				ebDTO.setLvl(rs.getInt("lvl"));
				
				ebList.add(ebDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return ebList;
	}
	
	public int getWriteNo() {
		try {
			String sql = "select web_eventboard_no.nextval from dual";
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
			String sql = "select max(grp) from web_eventboard";
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
			String sql = "select max(seq) from web_eventboard where grp=?";
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
			String sql = "select * from web_eventboard where grp=? and seq=? and lvl>?";
			
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
			String sql = " update web_eventboard set seq=seq+1 where grp=? and seq>?";
			
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
	
	public List<EventbbsDTO> allList(int startNum, int endNum){
		List<EventbbsDTO> ebList = new ArrayList<>();
		try {
			String sql = "select t.* from "
					+ "(select sub.*, rownum as rn from "
					+ "(select * from web_eventboard order by grp desc, seq asc) sub) t "
					+ "where rn between ? and ?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EventbbsDTO ebDTO = new EventbbsDTO();
				
				ebDTO.setNo(rs.getInt("no"));
				ebDTO.setTitle(rs.getString("title"));
				ebDTO.setId(rs.getString("id"));
				ebDTO.setName(rs.getString("name"));
				ebDTO.setContent(rs.getString("content"));
				ebDTO.setPwd(rs.getString("pwd"));
				
				String temp = rs.getString("day");
				String day = temp.substring(0, 11);
				
				ebDTO.setDay(day);
				ebDTO.setFname(rs.getString("fname"));
				ebDTO.setReadcount(rs.getInt("readcount"));
				ebDTO.setGrp(rs.getInt("grp"));
				ebDTO.setSeq(rs.getInt("seq"));
				ebDTO.setLvl(rs.getInt("lvl"));
				
				ebList.add(ebDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return ebList;
	}
	
	public int allListCount() {
		try {
			String sql = "select count(*) from web_eventboard";
			
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
	
	public boolean insert(EventbbsDTO ebDTO) {
		try {
			String sql = "insert into web_eventboard(no, title, id, name, content, pwd, fname, day, grp)"
					+ " values(?, ?, ?, ?, ?, ?, ?, sysdate, ?)";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ebDTO.getNo());
			pstmt.setString(2, ebDTO.getTitle());
			pstmt.setString(3, ebDTO.getId());
			pstmt.setString(4, ebDTO.getName());
			pstmt.setString(5, ebDTO.getContent());
			pstmt.setString(6, ebDTO.getPwd());
			pstmt.setString(7, ebDTO.getFname());
			pstmt.setInt(8, ebDTO.getGrp());
			
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
	
	public boolean insert_reply(EventbbsDTO ebDTO) {
		try {
			String sql = "insert into web_eventboard(no, title, id, name, content, pwd, fname, day, grp, seq, lvl)"
					+ " values(?, ?, ?, ?, ?, ?, ?, sysdate, ?, ?, ?)";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ebDTO.getNo());
			pstmt.setString(2, ebDTO.getTitle());
			pstmt.setString(3, ebDTO.getId());
			pstmt.setString(4, ebDTO.getName());
			pstmt.setString(5, ebDTO.getContent());
			pstmt.setString(6, ebDTO.getPwd());
			pstmt.setString(7, ebDTO.getFname());
			pstmt.setInt(8, ebDTO.getGrp());
			pstmt.setInt(9, ebDTO.getSeq());
			pstmt.setInt(10, ebDTO.getLvl());
			
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
	
	public EventbbsDTO selectOne(int no) {
		EventbbsDTO ebDTO = null;
		try {
			String sql = "select * from web_eventboard where no=?";
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ebDTO = new EventbbsDTO();
				
				ebDTO.setNo(rs.getInt("no"));
				ebDTO.setTitle(rs.getString("title"));
				ebDTO.setId(rs.getString("id"));
				ebDTO.setName(rs.getString("name"));
				ebDTO.setContent(rs.getString("content"));
				ebDTO.setPwd(rs.getString("pwd"));
				ebDTO.setDay(rs.getString("day"));
				ebDTO.setFname(rs.getString("fname"));
				ebDTO.setReadcount(rs.getInt("readcount"));
				ebDTO.setGrp(rs.getInt("grp"));
				ebDTO.setSeq(rs.getInt("seq"));
				ebDTO.setLvl(rs.getInt("lvl"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return ebDTO;
	}
	
	public void addReadCount(int no) {
		try {
			String sql = "update web_eventboard set readcount=readcount+1 where no=?";
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
			String sql = "delete from web_eventboard where no=?";
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
			String sql = "update web_eventboard set fname=null where no=?";
			
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
	
	public boolean update(EventbbsDTO ebDTO) {
		try {
			String sql = "update web_eventboard set title=?, content=?, fname=?, pwd=? where no=?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ebDTO.getTitle());
			pstmt.setString(2, ebDTO.getContent());
			pstmt.setString(3, ebDTO.getFname());
			pstmt.setString(4, ebDTO.getPwd());
			pstmt.setInt(5, ebDTO.getNo());
			
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
