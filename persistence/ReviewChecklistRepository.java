package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.ReviewChecklistDTO;

public class ReviewChecklistRepository {
	private static ReviewChecklistRepository instance;
	private static DataSource ds;
	private ReviewChecklistRepository() {}
	
	public static ReviewChecklistRepository getInstance() {
		if(instance==null) {
			try {
				Context context = new InitialContext();
	            System.out.println("context:"+context);
	            Context envContext = (Context) context.lookup("java:/comp/env");
	            ds = (DataSource) envContext.lookup("jdbc/oracle");
	            System.out.println("ds:"+ds);
				return instance = new ReviewChecklistRepository();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;		
	}
	
	//검토체크리스트 읽어오기
	public static ArrayList<ReviewChecklistDTO> readReview()
	{
		ArrayList<ReviewChecklistDTO> reviewList=new ArrayList<ReviewChecklistDTO>();
		ReviewChecklistDTO dto=new ReviewChecklistDTO();
		
		Connection conn = null; 
		Statement stmt = null; 
	    ResultSet rs = null;  

	    try {
	    	String SQL = "SELECT * FROM REVIEW_CHECKLISTS";
	        conn = ds.getConnection();
	        stmt = conn.createStatement();

	        rs=stmt.executeQuery(SQL);
	            
	        while(rs.next())
	        {
	           	int num=rs.getInt(1);
	           	String content=rs.getString(2);
	           	dto=new ReviewChecklistDTO(num, content);
	            	
	           	reviewList.add(dto);
	        }
	    }catch (SQLException sqle) {
	        sqle.printStackTrace();	       
	    }finally{
	            // DB 연결을 종료한다.
	        try{
	            if ( rs != null ){rs.close();}   
	            if ( stmt  != null ){stmt .close();}   
	            if ( conn != null ){conn.close(); }
	        }catch(Exception e){
	            throw new RuntimeException(e.getMessage());
	        }
	    }
	    return reviewList;
	}
	
	//검토체크리스트 수정
	public void updateReview(ReviewChecklistDTO dto)
	{
		Connection conn = null; 
		PreparedStatement pstmt = null; 
	    ResultSet rs = null;  

	    try {
	    	String SQL = "UPDATE REVIEW_CHECKLISTS SET REVIEW_CONTENT=? WHERE REVIEW_NUM=?";
	        conn = ds.getConnection();
	        pstmt = conn.prepareStatement(SQL);
	        pstmt.setString(1, dto.getReviewContent());
	        pstmt.setInt(2, dto.getReviewNum());
	        
	        pstmt.executeUpdate();
	    }catch (SQLException sqle) {
	        sqle.printStackTrace();	       
	    }finally{
	            // DB 연결을 종료한다.
	        try{
	            if ( rs != null ){rs.close();}   
	            if ( pstmt  != null ){pstmt .close();}   
	            if ( conn != null ){conn.close(); }
	        }catch(Exception e){
	            throw new RuntimeException(e.getMessage());
	        }
	    }
	}
}
