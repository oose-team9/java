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
    private AccountsRepository() { }
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
    }

    public ArrayList<Accounts> read() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Accounts> accs = new ArrayList<Accounts>();

        String sql = "SELECT e.DEPARTMENT_NAME, e.EMP_NO, e.NAME, a.BANK_NAME, a.ACC_NUM " +
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
                Accounts post = new Accounts(dname, empno, name, bname, accNum);
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

    public void delete(int i) {
    }
}
