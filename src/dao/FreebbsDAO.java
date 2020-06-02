package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.FreebbsDTO;
import utill.DBManager;

public class FreebbsDAO {
	private static FreebbsDAO instance = new FreebbsDAO();
	
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private FreebbsDAO() {
		
	}
	
	public static FreebbsDAO getInstance() {
		return instance;
	}
	
	public int getWriteNo() {
		try {
			String sql = "select web_freeboard_no.nextval from dual";
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
			String sql = "select max(grp) from web_freeboard";
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
			String sql = "select max(seq) from web_freeboard where grp=?";
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
			String sql = "select * from web_freeboard where grp=? and seq=? and lvl>?";
			
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
			String sql = " update web_freeboard set seq=seq+1 where grp=? and seq>?";
			
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
	
	public List<FreebbsDTO> allList(int startNum, int endNum){
		List<FreebbsDTO> fbList = new ArrayList<>();
		try {
			String sql = "select t.* from "
					+ "(select sub.*, rownum as rn from "
					+ "(select * from web_freeboard order by grp desc, seq asc) sub) t "
					+ "where rn between ? and ?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FreebbsDTO fbDTO = new FreebbsDTO();
				
				fbDTO.setNo(rs.getInt("no"));
				fbDTO.setTitle(rs.getString("title"));
				fbDTO.setId(rs.getString("id"));
				fbDTO.setName(rs.getString("name"));
				fbDTO.setContent(rs.getString("content"));
				fbDTO.setPwd(rs.getString("pwd"));
				
				String temp = rs.getString("day");
				String day = temp.substring(0, 11);
				
				fbDTO.setDay(day);
				fbDTO.setFname(rs.getString("fname"));
				fbDTO.setReadcount(rs.getInt("readcount"));
				fbDTO.setGrp(rs.getInt("grp"));
				fbDTO.setSeq(rs.getInt("seq"));
				fbDTO.setLvl(rs.getInt("lvl"));
				
				fbList.add(fbDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return fbList;
	}
	
	public int allListCount() {
		try {
			String sql = "select count(*) from web_freeboard";
			
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
	
	public boolean insert(FreebbsDTO fbDTO) {
		try {
			String sql = "insert into web_freeboard(no, title, id, name, content, pwd, fname, day, grp)"
					+ " values(?, ?, ?, ?, ?, ?, ?, sysdate, ?)";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, fbDTO.getNo());
			pstmt.setString(2, fbDTO.getTitle());
			pstmt.setString(3, fbDTO.getId());
			pstmt.setString(4, fbDTO.getName());
			pstmt.setString(5, fbDTO.getContent());
			pstmt.setString(6, fbDTO.getPwd());
			pstmt.setString(7, fbDTO.getFname());
			pstmt.setInt(8, fbDTO.getGrp());
			
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
	
	public boolean insert_reply(FreebbsDTO fbDTO) {
		try {
			String sql = "insert into web_freeboard(no, title, id, name, content, pwd, fname, day, grp, seq, lvl)"
					+ " values(?, ?, ?, ?, ?, ?, ?, sysdate, ?, ?, ?)";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, fbDTO.getNo());
			pstmt.setString(2, fbDTO.getTitle());
			pstmt.setString(3, fbDTO.getId());
			pstmt.setString(4, fbDTO.getName());
			pstmt.setString(5, fbDTO.getContent());
			pstmt.setString(6, fbDTO.getPwd());
			pstmt.setString(7, fbDTO.getFname());
			pstmt.setInt(8, fbDTO.getGrp());
			pstmt.setInt(9, fbDTO.getSeq());
			pstmt.setInt(10, fbDTO.getLvl());
			
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
	
	public FreebbsDTO selectOne(int no) {
		FreebbsDTO fbDTO = null;
		try {
			String sql = "select * from web_freeboard where no=?";
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				fbDTO = new FreebbsDTO();
				
				fbDTO.setNo(rs.getInt("no"));
				fbDTO.setTitle(rs.getString("title"));
				fbDTO.setId(rs.getString("id"));
				fbDTO.setName(rs.getString("name"));
				fbDTO.setContent(rs.getString("content"));
				fbDTO.setPwd(rs.getString("pwd"));
				fbDTO.setDay(rs.getString("day"));
				fbDTO.setFname(rs.getString("fname"));
				fbDTO.setReadcount(rs.getInt("readcount"));
				fbDTO.setGrp(rs.getInt("grp"));
				fbDTO.setSeq(rs.getInt("seq"));
				fbDTO.setLvl(rs.getInt("lvl"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return fbDTO;
	}
	
	public void addReadCount(int no) {
		try {
			String sql = "update web_freeboard set readcount=readcount+1 where no=?";
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
			String sql = "delete from web_freeboard where no=?";
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
			String sql = "update web_freeboard set fname=null where no=?";
			
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
	
	public boolean update(FreebbsDTO fbDTO) {
		try {
			String sql = "update web_freeboard set title=?, content=?, fname=?, pwd=? where no=?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fbDTO.getTitle());
			pstmt.setString(2, fbDTO.getContent());
			pstmt.setString(3, fbDTO.getFname());
			pstmt.setString(4, fbDTO.getPwd());
			pstmt.setInt(5, fbDTO.getNo());
			
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
