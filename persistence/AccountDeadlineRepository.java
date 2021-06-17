package persistence;

import domain.AccountDeadline;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.ArrayList;

public class AccountDeadlineRepository {
    private static AccountDeadlineRepository instance;
    private static DataSource ds;
    private AccountDeadlineRepository() { }
    public static AccountDeadlineRepository getInstance() {
        if(instance==null) {
            try {
                Context context = new InitialContext();
                ds = (DataSource) context.lookup("oracle.jdbc.driver.OracleDriver");
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

    public ArrayList<AccountDeadline> read() {
        return null;
    }
}
