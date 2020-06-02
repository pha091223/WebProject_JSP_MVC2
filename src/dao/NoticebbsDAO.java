package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.NoticebbsDTO;
import utill.DBManager;

public class NoticebbsDAO {
	private static NoticebbsDAO instance = new NoticebbsDAO();
	
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private NoticebbsDAO() {
		
	}
	
	public static NoticebbsDAO getInstance() {
		return instance;
	}
	
	public List<NoticebbsDTO> homeList() {
		List<NoticebbsDTO> nbList = new ArrayList<>();
		try {
			String sql = "select t.* from "
					+ "(select sub.*, rownum as rn from "
					+ "(select * from web_noticeboard where seq=0 order by grp desc) sub) t "
					+ "where rn between ? and ?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 7);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticebbsDTO nbDTO = new NoticebbsDTO();
				
				nbDTO.setNo(rs.getInt("no"));
				nbDTO.setTitle(rs.getString("title"));
				nbDTO.setId(rs.getString("id"));
				nbDTO.setName(rs.getString("name"));
				nbDTO.setContent(rs.getString("content"));
				nbDTO.setPwd(rs.getString("pwd"));
				
				String temp = rs.getString("day");
				String day = temp.substring(0, 11);
				
				nbDTO.setDay(day);
				nbDTO.setFname(rs.getString("fname"));
				nbDTO.setReadcount(rs.getInt("readcount"));
				nbDTO.setGrp(rs.getInt("grp"));
				nbDTO.setSeq(rs.getInt("seq"));
				nbDTO.setLvl(rs.getInt("lvl"));
				
				nbList.add(nbDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return nbList;
	}
	
	public int getWriteNo() {
		try {
			String sql = "select web_noticeboard_no.nextval from dual";
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
			String sql = "select max(grp) from web_noticeboard";
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
			String sql = "select max(seq) from web_noticeboard where grp=?";
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
			String sql = "select * from web_noticeboard where grp=? and seq=? and lvl>?";
			
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
			String sql = " update web_noticeboard set seq=seq+1 where grp=? and seq>?";
			
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
	
	public List<NoticebbsDTO> allList(int startNum, int endNum){
		List<NoticebbsDTO> nbList = new ArrayList<>();
		try {
			String sql = "select t.* from "
					+ "(select sub.*, rownum as rn from "
					+ "(select * from web_noticeboard order by grp desc, seq asc) sub) t "
					+ "where rn between ? and ?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticebbsDTO nbDTO = new NoticebbsDTO();
				
				nbDTO.setNo(rs.getInt("no"));
				nbDTO.setTitle(rs.getString("title"));
				nbDTO.setId(rs.getString("id"));
				nbDTO.setName(rs.getString("name"));
				nbDTO.setContent(rs.getString("content"));
				nbDTO.setPwd(rs.getString("pwd"));
				
				String temp = rs.getString("day");
				String day = temp.substring(0, 11);
				
				nbDTO.setDay(day);
				nbDTO.setFname(rs.getString("fname"));
				nbDTO.setReadcount(rs.getInt("readcount"));
				nbDTO.setGrp(rs.getInt("grp"));
				nbDTO.setSeq(rs.getInt("seq"));
				nbDTO.setLvl(rs.getInt("lvl"));
				
				nbList.add(nbDTO);
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
			String sql = "select count(*) from web_noticeboard";
			
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
	
	public boolean insert(NoticebbsDTO nbDTO) {
		try {
			String sql = "insert into web_noticeboard(no, title, id, name, content, pwd, fname, day)"
					+ " values(?, ?, ?, ?, ?, ?, ?, sysdate)";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, nbDTO.getNo());
			pstmt.setString(2, nbDTO.getTitle());
			pstmt.setString(3, nbDTO.getId());
			pstmt.setString(4, nbDTO.getName());
			pstmt.setString(5, nbDTO.getContent());
			pstmt.setString(6, nbDTO.getPwd());
			pstmt.setString(7, nbDTO.getFname());
			
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
	
	public boolean insert_reply(NoticebbsDTO nbDTO) {
		try {
			String sql = "insert into web_noticeboard(no, title, id, name, content, pwd, fname, day, grp, seq, lvl)"
					+ " values(?, ?, ?, ?, ?, ?, ?, sysdate, ?, ?, ?)";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, nbDTO.getNo());
			pstmt.setString(2, nbDTO.getTitle());
			pstmt.setString(3, nbDTO.getId());
			pstmt.setString(4, nbDTO.getName());
			pstmt.setString(5, nbDTO.getContent());
			pstmt.setString(6, nbDTO.getPwd());
			pstmt.setString(7, nbDTO.getFname());
			pstmt.setInt(8, nbDTO.getGrp());
			pstmt.setInt(9, nbDTO.getSeq());
			pstmt.setInt(10, nbDTO.getLvl());
			
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
	
	public NoticebbsDTO selectOne(int no) {
		NoticebbsDTO nbDTO = null;
		try {
			String sql = "select * from web_noticeboard where no=?";
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				nbDTO = new NoticebbsDTO();
				
				nbDTO.setNo(rs.getInt("no"));
				nbDTO.setTitle(rs.getString("title"));
				nbDTO.setId(rs.getString("id"));
				nbDTO.setName(rs.getString("name"));
				nbDTO.setContent(rs.getString("content"));
				nbDTO.setPwd(rs.getString("pwd"));
				nbDTO.setDay(rs.getString("day"));
				nbDTO.setFname(rs.getString("fname"));
				nbDTO.setReadcount(rs.getInt("readcount"));
				nbDTO.setGrp(rs.getInt("grp"));
				nbDTO.setSeq(rs.getInt("seq"));
				nbDTO.setLvl(rs.getInt("lvl"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return nbDTO;
	}
	
	public void addReadCount(int no) {
		try {
			String sql = "update web_noticeboard set readcount=readcount+1 where no=?";
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
			String sql = "delete from web_noticeboard where no=?";
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
			String sql = "update web_noticeboard set fname=null where no=?";
			
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
	
	public boolean update(NoticebbsDTO nbDTO) {
		try {
			String sql = "update web_noticeboard set title=?, content=?, fname=?, pwd=? where no=?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nbDTO.getTitle());
			pstmt.setString(2, nbDTO.getContent());
			pstmt.setString(3, nbDTO.getFname());
			pstmt.setString(4, nbDTO.getPwd());
			pstmt.setInt(5, nbDTO.getNo());
			
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
