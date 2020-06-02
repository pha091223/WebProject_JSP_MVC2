package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.CourseDTO;
import dto.FreebbsDTO;
import utill.DBManager;

public class CourseDAO {
	private static CourseDAO instance = new CourseDAO();
	
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private CourseDAO() {
		
	}
	
	public static CourseDAO getInstance() {
		return instance;
	}
	
	public int getCourseNo() {
		try {
			String sql = "select web_course_no.nextval from dual";
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
	
	public List<CourseDTO> allList(int startNum, int endNum){
		List<CourseDTO> cList = new ArrayList<>();
		try {
			String sql = "select t.* from "
					+ "(select sub.*, rownum as rn from "
					+ "(select * from web_course order by no) sub) t "
					+ "where rn between ? and ?";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CourseDTO cDTO = new CourseDTO();
				
				cDTO.setNo(rs.getInt("no"));
				cDTO.setName(rs.getString("name"));
				cDTO.setTeacher(rs.getString("teacher"));
				cDTO.setDay(rs.getString("day"));
				cDTO.setStime(rs.getString("stime"));
				cDTO.setEtime(rs.getString("etime"));
				cDTO.setWeek(rs.getString("week"));
				cDTO.setRegister(rs.getInt("register"));
				cDTO.setPeople(rs.getInt("people"));
				cDTO.setImg(rs.getString("img"));
				
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
	
	public List<CourseDTO> allList(){
		List<CourseDTO> cList = new ArrayList<>();
		try {
			String sql = "select * from web_course";
			
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				CourseDTO cDTO = new CourseDTO();
				
				cDTO.setNo(rs.getInt("no"));
				cDTO.setName(rs.getString("name"));
				cDTO.setTeacher(rs.getString("teacher"));
				cDTO.setDay(rs.getString("day"));
				cDTO.setStime(rs.getString("stime"));
				cDTO.setEtime(rs.getString("etime"));
				cDTO.setWeek(rs.getString("week"));
				cDTO.setRegister(rs.getInt("register"));
				cDTO.setPeople(rs.getInt("people"));
				cDTO.setImg(rs.getString("img"));
				
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
	
	public int allListCount() {
		try {
			String sql = "select count(*) from web_course";
			
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
	
	public CourseDTO selectOne(int no) {
		CourseDTO cDTO = null;
		try {
			String sql = "select * from web_course where no=?";
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cDTO = new CourseDTO();
				
				cDTO.setNo(rs.getInt("no"));
				cDTO.setName(rs.getString("name"));
				cDTO.setTeacher(rs.getString("teacher"));
				cDTO.setDay(rs.getString("day"));
				cDTO.setStime(rs.getString("stime"));
				cDTO.setEtime(rs.getString("etime"));
				cDTO.setWeek(rs.getString("week"));
				cDTO.setRegister(rs.getInt("register"));
				cDTO.setPeople(rs.getInt("people"));
				cDTO.setImg(rs.getString("img"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return cDTO;
	}
	
	public boolean insert(CourseDTO cDTO) {
		try {
			String sql = "insert into web_course values(?, ?, ?, ?, ?, ?, ?, 0, ?, ?)";
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cDTO.getNo());
			pstmt.setString(2, cDTO.getName());
			pstmt.setString(3, cDTO.getTeacher());
			pstmt.setString(4, cDTO.getDay());
			pstmt.setString(5, cDTO.getStime());
			pstmt.setString(6, cDTO.getEtime());
			pstmt.setString(7, cDTO.getWeek());
			pstmt.setInt(8, cDTO.getPeople());
			pstmt.setString(9, cDTO.getImg());
			
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
	
	public List<CourseDTO> searchList(String year, String month) {
		List<CourseDTO> cList = new ArrayList<>();
		try {
			String sql = "select * from web_course where day where day like ?_?___";
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, year);
			pstmt.setString(2, month);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CourseDTO cDTO = new CourseDTO();
				
				cDTO.setNo(rs.getInt("no"));
				cDTO.setName(rs.getString("name"));
				cDTO.setTeacher(rs.getString("teacher"));
				cDTO.setDay(rs.getString("day"));
				cDTO.setStime(rs.getString("stime"));
				cDTO.setEtime(rs.getString("etime"));
				cDTO.setWeek(rs.getString("week"));
				cDTO.setRegister(rs.getInt("register"));
				cDTO.setPeople(rs.getInt("people"));
				cDTO.setImg(rs.getString("img"));
				
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

}
