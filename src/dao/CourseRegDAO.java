package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dto.CourseRegDTO;
import utill.DBManager;

public class CourseRegDAO {
	private static CourseRegDAO instance = new CourseRegDAO();
	
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private CourseRegDAO() {
		
	}
	
	public static CourseRegDAO getInstance() {
		return instance;
	}
	
	public boolean insert(CourseRegDTO crDTO) {
		try {
			String sql = "insert into web_course_reg values(?, ?)";
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, crDTO.getCno());
			pstmt.setString(2, crDTO.getId());
			
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
