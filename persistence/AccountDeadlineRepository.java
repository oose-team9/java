package persistence;

import domain.AccountDeadline;
import domain.Accounts;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccountDeadlineRepository {
    private static AccountDeadlineRepository instance;
    private static DataSource ds;
    private AccountDeadlineRepository() { }
    public static AccountDeadlineRepository getInstance() {
        if(instance==null) {
            try {
                Context context = new InitialContext();
                ds = (DataSource) context.lookup("java:comp/env/jdbc/orcl");
                return instance = new AccountDeadlineRepository();
            } catch (NamingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void create(AccountDeadline deadline) {
    }

    public AccountDeadline read() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        AccountDeadline dl = new AccountDeadline();

        String sql = "SELECT * FROM account_deadline";

        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            dl.setId(rs.getInt(1));
            dl.setStartDay(rs.getDate(2));
            dl.setEndDay(rs.getDate(3));
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
        return dl;
    }
}
