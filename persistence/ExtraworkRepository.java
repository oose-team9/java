package persistence;

import com.domain.Extrawork;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class ExtraworkRepository {
    private static ExtraworkRepository instance;
    private static DataSource dataSource;

    private ExtraworkRepository() {
    }

    public static ExtraworkRepository getInstance() {
        if (instance == null) {
            try {
                Context context = new InitialContext();
                dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OOSE_TEST");
                return instance = new ExtraworkRepository();
            } catch (NamingException ne) {
                ne.printStackTrace();
            }
        }
        return instance;
    }

    public boolean insertExtrawork(Extrawork extrawork) {
        Connection conn = null;
        PreparedStatement statement = null;
        boolean result = true;

        String sql = "INSERT INTO EXTRAWORKS(id,applicant,working_date,working_times) " +
                "values(extraworks_pk.nextval,?,?,?)";

        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, extrawork.getApplicant());
            statement.setTimestamp(2, extrawork.getWorkingDate());
            statement.setInt(3, extrawork.getWorkingTimes());

            int n = statement.executeUpdate();
        } catch (SQLException e) {
            result = false;
        } finally {
            try {
                statement.close();
                conn.close();
            } catch (SQLException e) {
                result = false;
            }
        }
        return result;
    }

    public ArrayList<Extrawork> selectAllExtraworks() {
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM EXTRAWORKS";
        ArrayList<Extrawork> extraworks = new ArrayList<Extrawork>();
        try {
            conn = dataSource.getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int applicant = rs.getInt("applicant");
                Timestamp workingDate = rs.getTimestamp("working_date");
                int workingTimes = rs.getInt("working_times");
                int approved = rs.getInt("approved");
                Timestamp approvedDate = rs.getTimestamp("approved_date");
                int approcedStatus = rs.getInt("approved_status");

                Extrawork extrawork = new Extrawork(id, applicant, workingDate, workingTimes, approved, approvedDate, approcedStatus);
                extraworks.add(extrawork);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return extraworks;
    }

    public Extrawork selectExtrawork(int id) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Extrawork extrawork = null;
        String sql = "SELECT * FROM EXTRAWORKS WHERE id = ?";

        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                int uid = rs.getInt("id");
                int applicant = rs.getInt("applicant");
                Timestamp workingDate = rs.getTimestamp("working_date");
                int workingTimes = rs.getInt("working_times");
                int approved = rs.getInt("approved");
                Timestamp approvedDate = rs.getTimestamp("approved_date");
                int approcedStatus = rs.getInt("approved_status");

                extrawork = new Extrawork(uid, applicant, workingDate, workingTimes, approved, approvedDate, approcedStatus);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return extrawork;
    }

    public boolean deleteExtrawork(int id) {
        Connection conn = null;
        PreparedStatement statement = null;
        boolean result = true;
        String sql = "DELETE FROM EXTRAWORKS WHERE id = ?";

        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            result = false;
        } finally {
            try {
                statement.close();
                conn.close();
            } catch (SQLException e) {
                result = false;
            }
        }
        return result;
    }

    public boolean updateExtrawork(Extrawork extrawork) {
        Connection conn = null;
        PreparedStatement statement = null;
        boolean result = true;
        String sql = "UPDATE EXTRAWORKS SET working_date=?,working_times=? WHERE id=?";

        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setTimestamp(1, extrawork.getWorkingDate());
            statement.setInt(2, extrawork.getWorkingTimes());
            statement.setInt(3, extrawork.getId());

            int n = statement.executeUpdate();
        } catch (SQLException e) {
            result = false;
        } finally {
            try {
                statement.close();
                conn.close();
            } catch (SQLException e) {
                result = false;
            }
        }
        return result;
    }

    public boolean checkDuplicate(Extrawork extrawork) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "SELECT id FROM EXTRAWORKS WHERE applicant = ? AND working_date = ?";

        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, extrawork.getApplicant());
            statement.setTimestamp(2, extrawork.getWorkingDate());
            rs = statement.executeQuery();

            if (rs.next() == false) return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
