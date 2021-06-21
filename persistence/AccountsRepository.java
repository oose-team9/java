package persistence;

import domain.Accounts;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class AccountsRepository {
    private static AccountsRepository instance;
    private static DataSource ds;
    public AccountsRepository() { }
    public static AccountsRepository getInstance() {
        if(instance==null) {
            try {
                Context context = new InitialContext();
                ds = (DataSource) context.lookup("java:comp/env/jdbc/orcl");
                return instance = new AccountsRepository();
            } catch (NamingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void create(Accounts account) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "INSERT INTO ACCOUNTS(EMP_NO, BANK_NAME, ACC_NUM) values(?,?,?)";
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, account.getEmpNo());
            pstmt.setString(2, account.getBankName());
            pstmt.setString(3, account.getAccNum());
            int n = pstmt.executeUpdate();
            System.out.println("EXECUTE: " + account.toString());
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

    public ArrayList<Accounts> read() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Accounts> accs = new ArrayList<Accounts>();

        String sql = "SELECT e.DEPARTMENT_NAME, e.EMP_NO, e.NAME, a.BANK_NAME, a.ACC_NUM, a.ID " +
                "FROM accounts a JOIN employees e on a.emp_no = e.emp_no";

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
                String dname = rs.getString(1);
                int empno = rs.getInt(2);
                String name = rs.getString(3);
                String bname = rs.getString(4);
                String accNum = rs.getString(5);
                int aid = rs.getInt(6);
                Accounts post = new Accounts(dname, empno, name, bname, accNum, aid);
                accs.add(post);
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
        return accs;
    }

    public int delete(String[] ids) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        int[] cnt = null;

        String sql = "DELETE FROM ACCOUNTS WHERE ID = ?";
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < ids.length; i++) {
                pstmt.setString(1, ids[i]);
                pstmt.addBatch();
            }
            cnt = pstmt.executeBatch();

            for (int i = 0; i < cnt.length; i++) {
                if(cnt[i] == -2)
                    result++;
            }

            if(ids.length == result)
                conn.commit();
            else
                conn.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            try {
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
