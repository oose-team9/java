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

import domain.ExaminationChecklistDTO;
import domain.ReviewChecklistDTO;

//검수체크리스트
public class ExaminationChecklistRepository {
	private static ExaminationChecklistRepository instance;
	private static DataSource ds;
	private ExaminationChecklistRepository() {}
	
	public static ExaminationChecklistRepository getInstance() {
		if(instance==null) {
			try {
				Context context = new InitialContext();
	            System.out.println("context:"+context);
	            Context envContext = (Context) context.lookup("java:/comp/env");
	            ds = (DataSource) envContext.lookup("jdbc/oracle");
	            System.out.println("ds:"+ds);

				return instance = new ExaminationChecklistRepository();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;		
	}
	
	//검수체크리스트 읽어오기
	public static ArrayList<ExaminationChecklistDTO> readExamination()
	{
		ArrayList<ExaminationChecklistDTO> examinationList=new ArrayList<ExaminationChecklistDTO>();
		ExaminationChecklistDTO dto=new ExaminationChecklistDTO();
		
		Connection conn = null; 
		Statement stmt = null; 
        ResultSet rs = null;  

        try {
            String SQL = "SELECT * FROM EXAMINATION_CHECKLISTS";
            conn = ds.getConnection();
            stmt = conn.createStatement();

            rs=stmt.executeQuery(SQL);
            
            while(rs.next())
            {
            	int num=rs.getInt(1);
            	String content=rs.getString(2);
            	dto=new ExaminationChecklistDTO(num, content);
            	
            	examinationList.add(dto);
            }
        } catch (SQLException sqle) {
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
        
        return examinationList;
	}
	
	//검수체크리스트 수정
	public void updateExamination(ExaminationChecklistDTO dto)
	{
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rs = null;  

		try {
		    String SQL = "UPDATE EXAMINATION_CHECKLISTS SET EXAMINATION_CONTENT=? WHERE EXAMINATION_NUM=?";
		    conn = ds.getConnection();
		    pstmt = conn.prepareStatement(SQL);
		    pstmt.setString(1, dto.getExaminationContent());
		    pstmt.setInt(2, dto.getExaminationNum());
		        
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
