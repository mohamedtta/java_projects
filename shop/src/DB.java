import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    private final String  username ="root";
    private final String  pass     ="";
    private final String  add      ="jdbc:mysql://localhost:3306/shop";
    public static Statement smt;

    public Connection Connect() throws SQLException {
        Connection con = DriverManager.getConnection(add,username,pass);
        return con;
    }
}
