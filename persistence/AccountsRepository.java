package persistence;

import domain.Accounts;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
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
        return null;
    }

    public void delete(int i) {
    }
}
