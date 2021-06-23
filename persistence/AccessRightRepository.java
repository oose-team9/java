package persistence;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.AccessRight;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;

public class AccessRightRepository {
	private static AccessRightRepository instance;
	private static DataSource ds;
	private AccessRightRepository() {
		
	}
	public static AccessRightRepository getInstacne() {
		if(instance==null) {
			try {
				Context context = new InitialContext();
				Context envContext = (Context) context.lookup("java:/comp/env");
				ds = (DataSource) envContext.lookup("jdbc/oracle");
				return instance = new AccessRightRepository();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	public void save(AccessRight accessRight) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null; 
		
		String sql = "INSERT INTO accessRight(cardNumber, authorizationDate, employeeNo) values(?, SYSDATE, ?)";
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, accessRight.getCardNumber());
			pstmt.setInt(2, accessRight.getEmployeeNo());
			
			int n = pstmt.executeUpdate();
		}catch(SQLException e) {
			throw new Exception(e);
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	public ArrayList<AccessRight> findAll() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM accessRight";
		ArrayList<AccessRight> accessRights = new ArrayList<AccessRight>();
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				int cardNumber = rs.getInt("cardNumber");
				Date authorizationDate = rs.getDate("authorizationDate");
				int employeeNo = rs.getInt("employeeNo");
				AccessRight accessRight = new AccessRight(id, employeeNo, cardNumber, authorizationDate);
				accessRights.add(accessRight);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return accessRights;
	}
}
