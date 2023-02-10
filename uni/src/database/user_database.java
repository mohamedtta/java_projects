package database;

import java.sql.*;
import java.util.ArrayList;
import Main.user_info;

public class user_database {
    private static String name = "root";
    private static String password = "";
    private static String add = "jdbc:mysql://localhost:3306/uni";
    public static Connection connect()  throws  SQLException{
        Connection con = DriverManager.getConnection(add, name, password);
        return con;
    }
    public static void insert(String user , String pass , String dep){
        try (
            Connection con = connect();
            PreparedStatement p = con.prepareStatement( "insert into user values ( '"+user+"','"+pass+"','"+dep+"')");){
            p.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<user_info> get_users(){
        ArrayList <user_info> list = new ArrayList<>();
        try (
          Connection con = connect();
          PreparedStatement s = con.prepareStatement("select * from user");){
          ResultSet r = s.executeQuery();
          while (r.next()){
              list.add(new user_info(r.getString("user_name") , r.getString("password") , r.getString("dep")));
          }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }
    public static int check (String user, String pass){
        ArrayList<user_info> list  = get_users();
        int x=0;
        for (int i=0 ; i< list.size() ; i++){
            if(list.get(i).getuser().equals(user)){
                if (list.get(i).getPass().equals(pass)){
                    x=2;
                    break;
                }else {
                    x=1;
                    break;
                }
            }else {
                x=0;
            }
        }
        return x;
    }
    public static String get_dep(String uu){
        String dep_name = null;
        try (
            Connection con = connect();
            PreparedStatement p = con.prepareStatement("SELECT  `dep` FROM `user` WHERE user_name = '"+uu+"'" );){
            ResultSet r = p.executeQuery();
            if(r.next()){
                dep_name = r.getString("dep");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return dep_name;
    }

    }

