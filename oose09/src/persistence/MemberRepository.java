package persistence;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.Member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Connection;

public class MemberRepository {
	private static MemberRepository instance;
	private static DataSource ds;
	private MemberRepository() {
		
	}
	public static MemberRepository getInstacne() {
		if(instance==null) {
			try {
				Context context = new InitialContext();
				ds = (DataSource) context.lookup("java:comp/env/jdbc/MySQL");
				return instance = new MemberRepository();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;		
	}
	public void save(Member member){
		Connection conn = null;
		PreparedStatement pstmt = null; 
		
		String sql = "INSERT INTO MEMBER(age,name) values(?,?)";
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, member.getAge());
			pstmt.setString(2, member.getName());
			
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
	public Member findById(Long id){
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		Member member = new Member();
		String sql = "SELECT * FROM MEMBER WHERE ID=?";
		try {
			conn = ds.getConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {			
			pstmt = conn.prepareStatement(sql);			
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();	
			rs.next();
			member.setId(rs.getLong(1));
			member.setAge(rs.getInt(2));
			member.setName(rs.getString(3));                
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
		return member;
	}
	public Member findByName(String name){
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		Member member = new Member();
		String sql = "SELECT * FROM MEMBER WHERE NAME=?";
		try {
			conn = ds.getConnection();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {			
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();	
			if (rs.next() == false) {
				return null;
			}
			rs.next();
			member.setId(rs.getLong(1));
			member.setAge(rs.getInt(2));
			member.setName(rs.getString(3));                
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
		return member;
	}
	public ArrayList<Member> findAll() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER";
		ArrayList<Member> members = new ArrayList<Member>();
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Long id = rs.getLong("id");
				int age = rs.getInt("age");
				String name = rs.getString("name");				
				Member member = new Member(id,name,age);
				members.add(member);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
		return members;
	}
}
