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

import domain.ContractbookDTO;

public class ContractbookRepository {
	private static ContractbookRepository instance;
	private static DataSource ds;
	private ContractbookRepository() {}
	
	public static ContractbookRepository getInstance() {
		if(instance==null) {
			try {
//				Hashtable table = new Hashtable();
//				
//				//변경
//				table.put("java.naming.factory.initial", "org.apache.naming.java.javaURLContextFactory");
//				
//				//변경
//				ds = (DataSource)new InitialContext(table).lookup("java:comp/env/jdbc/MySQL");
//				return instance = new ContractbookRepository();

	            Context context = new InitialContext();
	            System.out.println("context:"+context);
	            Context envContext = (Context) context.lookup("java:/comp/env");
//	            ds = (DataSource) context.lookup("java:comp/env/jdbc/MySQL");
	            ds = (DataSource) envContext.lookup("jdbc/oracle");
	            System.out.println("ds:"+ds);
	            return instance = new ContractbookRepository();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;		
	}
	
	//계약대장 등록
	public static void createContractbook(ContractbookDTO dto)
	{
		Connection conn = null; 
        PreparedStatement pstm = null; 
        ResultSet rs = null;  

        try {
            String SQL = "INSERT INTO CONTRACTBOOKS VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            conn = ds.getConnection();
            pstm = conn.prepareStatement(SQL);
            pstm.setInt(1, dto.getContractbookNum());
            pstm.setInt(2, dto.getContractNum());
            pstm.setString(3,dto.getContractName());
            pstm.setString(4, dto.getManager());
            pstm.setString(5, dto.getContractDate());
            pstm.setInt(6,  dto.getPrice());
            pstm.setString(7,  dto.getPeriod());
            pstm.setString(8,  dto.getContractingParties());
            pstm.executeUpdate();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();	       
        }finally{
            // DB 연결을 종료한다.
            try{
                if ( rs != null ){rs.close();}   
                if ( pstm  != null ){pstm .close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
          }
        }
	}
	
	//계약대장 조회
	public static ContractbookDTO readContractbook(int contractbookNum)
	{
		ContractbookDTO dto=new ContractbookDTO();
		
		Connection conn = null; 
		PreparedStatement pstmt = null; 
        ResultSet rs = null;  

        try {
            String SQL = "SELECT * FROM CONTRACTBOOKS WHERE CONTRACTBOOK_NUM="+contractbookNum;
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs=pstmt.executeQuery(SQL);
            
            while(rs.next())
            {
            	int bookNum=rs.getInt(1);
            	int contractNum=rs.getInt(2);
            	String contractName=rs.getString(3);
            	String manager=rs.getString(4);
            	String contractDate=rs.getString(5);
            	int price=rs.getInt(6);
            	String period=rs.getString(7);
            	String parties=rs.getString(8);
            	
            	dto=new ContractbookDTO(bookNum, contractNum, contractName, manager, contractDate, price, period, parties);
            }
        } catch (SQLException sqle) {
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
        
        return dto;
	}

	public ArrayList<ContractbookDTO> readContractbooks() {
		ContractbookDTO dto=new ContractbookDTO();
		ArrayList<ContractbookDTO> list=new ArrayList<ContractbookDTO>();
		
		Connection conn = null; 
		PreparedStatement pstmt = null; 
        ResultSet rs = null;  

        try {
            String SQL = "SELECT * FROM CONTRACTBOOKS";
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs=pstmt.executeQuery(SQL);
            
            while(rs.next())
            {
            	int bookNum=rs.getInt(1);
            	int contractNum=rs.getInt(2);
            	String contractName=rs.getString(3);
            	String manager=rs.getString(4);
            	String contractDate=rs.getString(5);
            	int price=rs.getInt(6);
            	String period=rs.getString(7);
            	String parties=rs.getString(8);
            	
            	dto=new ContractbookDTO(bookNum, contractNum, contractName, manager, contractDate, price, period, parties);
            	list.add(dto);
            }
        } catch (SQLException sqle) {
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
        
        return list;
	}
}
