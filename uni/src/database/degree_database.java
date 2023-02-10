package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class degree_database {
    private static String name = "root";
    private static String password = "";
    private static String add = "jdbc:mysql://localhost:3306/uni";
    public static Connection connect()  throws SQLException {
        Connection con = DriverManager.getConnection(add, name, password);
        return con;
    }
    public static void insert_degree(int id , int n1 , int n2 , int n3 , int n4 , int n5 , int n6){
        int sum  = n1 + n2 + n3 + n4 + n5 + n6;
        try (
            Connection con = connect();
            PreparedStatement p = con.prepareStatement("insert into degree (`id` , `s1`, `s2`, `s3`, `s4`, `s5`, `s6`,`sum`) values ("+id+","+n1+","+n2+","+n3+","+n4+","+n5+","+n6+","+sum+")");){
            p.execute();
        }catch (SQLException e){
            System.out.println(e.getSQLState());
        }

    }

}
