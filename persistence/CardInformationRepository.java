package persistence;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.CardInformation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;

public class CardInformationRepository {
	private static CardInformationRepository instance;
	private static DataSource ds;
	private CardInformationRepository() {
		
	}
	public static CardInformationRepository getInstacne() {
		if(instance==null) {
			try {
				Context context = new InitialContext();
				Context envContext = (Context) context.lookup("java:/comp/env");
				ds = (DataSource) envContext.lookup("jdbc/oracle");
				return instance = new CardInformationRepository();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return instance;		
	}
	public void save(CardInformation information){
		Connection conn = null;
		PreparedStatement pstmt = null; 
		
		String sql = "INSERT INTO cardInformation(cardNumber, bank, accountNumber, password, employeeNo) values(?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, information.getCardNumber());
			pstmt.setString(2, information.getBank());
			pstmt.setInt(3, information.getAccountNumber());
			pstmt.setString(4, information.getPassword());
			pstmt.setInt(5, information.getEmployeeNo());
			
			int n = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	public void delete(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		
		String sql = "DELETE FROM cardinformation WHERE id=? ";
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, id);
			
			int n = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}	
	public ArrayList<CardInformation> findAll() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM cardInformation";
		ArrayList<CardInformation> informations = new ArrayList<CardInformation>();
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
				int employeeNo = rs.getInt("employeeNo");
				int cardNumber = rs.getInt("cardNumber");
				String bank = rs.getString("bank");
				int accountNumber = rs.getInt("accountNumber");
				String password = rs.getString("password");
				CardInformation posts = new CardInformation(id, employeeNo, cardNumber, bank, accountNumber, password);
				informations.add(posts);
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
		return informations;
	}
}
