package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dto.FreebbsDTO;
import dto.MemberDTO;
import utill.DBManager;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private MemberDAO() {
		
	}
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public boolean insert(MemberDTO mDTO) {
		try {
			String sql = "insert into web_member values(?, ?, ?, ?, ?, ?)";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDTO.getName());
			pstmt.setString(2, mDTO.getResident());
			pstmt.setString(3, mDTO.getId());
			pstmt.setString(4, mDTO.getPwd());
			pstmt.setString(5, mDTO.getPhone());
			pstmt.setString(6, mDTO.getEmail());
			
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
	
	public boolean idChk(String id) {
		try {
			String sql = "select * from web_member where id=?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
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
	
	public MemberDTO login(String id, String pwd) {
		MemberDTO mDTO = null;
		try {
			String sql = "select * from web_member where id=?";
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				sql = "select * from web_member where id=? and pwd=?";
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, pwd);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					mDTO = new MemberDTO();
					mDTO.setName(rs.getString("name"));
					mDTO.setResident(rs.getString("resident"));
					mDTO.setId(rs.getString("id"));
					mDTO.setPwd(rs.getString("pwd"));
					mDTO.setPhone(rs.getString("phone"));
					mDTO.setEmail(rs.getString("email"));
					return mDTO;
				} else {
					mDTO = new MemberDTO();
					mDTO.setId(id);
					return mDTO;
				}
			} else {
				return mDTO;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return mDTO;
	}
	
	public MemberDTO selectOne(String id) {
		MemberDTO mDTO = null;
		try {
			String sql = "select * from web_member where id=?";
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mDTO = new MemberDTO();
				
				mDTO.setName(rs.getString("name"));
				mDTO.setResident(rs.getString("resident"));
				mDTO.setId(rs.getString("id"));
				mDTO.setPwd(rs.getString("pwd"));
				mDTO.setPhone(rs.getString("phone"));
				mDTO.setEmail(rs.getString("email"));
				
				return mDTO;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return mDTO;
	}
	
	public boolean update(MemberDTO mDTO) {
		try {
			String sql = "update web_member set pwd=?, phone=?, email=? where id=?";
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDTO.getPwd());
			pstmt.setString(2, mDTO.getPhone());
			pstmt.setString(3, mDTO.getEmail());
			pstmt.setString(4, mDTO.getId());
			
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
	
	public boolean delete(String id) {
		try {
			String sql = "delete web_member where id=?";
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
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
