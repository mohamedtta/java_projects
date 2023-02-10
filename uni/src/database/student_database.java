package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import Main.student;

import javax.swing.table.DefaultTableModel;

public class student_database {
    private static String name = "root";
    private static String password = "";
    private static String add = "jdbc:mysql://localhost:3306/uni";
    public static Connection connect()  throws SQLException {
        Connection con = DriverManager.getConnection(add, name, password);
        return con;
    }
    public static void insert_student(String fname , String lname , String address , String dep , String uesername , String password ){
        try (
            Connection con = connect();
            PreparedStatement p = con.prepareStatement("insert into student (`fname`, `lname`, `adddress`, `dep` , `username` , `password`) values ('"+fname+"','"+lname+"','"+address+"','"+dep+"','"+uesername+"','"+password+"'"+")");) {
            p.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public static ArrayList<student> getSudent(String dep){
        ArrayList<student> list = new ArrayList<>();
        try (
            Connection con = connect();
            PreparedStatement p = con.prepareStatement("select * from student where dep = '"+dep+"'");){
            ResultSet r = p.executeQuery();
            while (r.next()){
                list.add(new student(r.getInt("id") , r.getString("fname") , r.getString("lname") , r.getString("adddress") , r.getString("dep") , r.getString("username") , r.getString("password")));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }
    public static String[] getSudentuser(String user){
        String[] arr = new String[5];
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("select * from student where username = '"+user+"'");){
            ResultSet r = p.executeQuery();
            if (r.next()){
                arr = new String[]{ r.getString("fname") , r.getString("lname") , r.getString("adddress") , r.getString("dep") , r.getString("username") };
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arr;
    }

    public static ArrayList<student> getSudentANDdegree(String dep){
        ArrayList<student> list = new ArrayList<>();
        try (
                Connection con = connect();
                PreparedStatement p = con.prepareStatement("select * from student,degree where student.id = degree.id AND dep = '"+dep+"'");){
            ResultSet r = p.executeQuery();
            while (r.next()){
                list.add(new student(r.getInt("id") , r.getString("fname") , r.getString("lname") , r.getString("sum")+"" , r.getString("dep"), r.getString("username") , r.getString("password")));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }
    public static DefaultTableModel getupdate(String dep){
        String header [] = {"Fname" , "Lname" , "department" , "sum"};
        DefaultTableModel model = new DefaultTableModel(header,0);
        try (
            Connection con = connect();
           PreparedStatement p =  con.prepareStatement("SELECT `fname`, `lname`, `dep` , `sum`  FROM student, degree WHERE student.id = degree.id AND dep = '"+dep+"'");){
            ResultSet r = p.executeQuery();
            while(r.next()){
                Vector<String> vec = new Vector<>();
                vec.add(r.getString("fname"));
                vec.add(r.getString("lname"));
                vec.add(r.getString("dep"));
                vec.add(r.getInt("sum")+"");
                model.addRow(vec);
            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return model;
    }
    public static void update(int sum , int id){
        try (
            Connection con = connect();
            PreparedStatement p = con.prepareStatement("update degree set sum = "+sum+" where id = "+id)){

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static String[] degree_stu(String username){
        String arr[] = new String[7];
        try (
            Connection con = connect();
            PreparedStatement p = con.prepareStatement("select `s1`,`s2`,`s3`,`s4`,`s5`,`s6`,`sum` from student,degree where student.id = degree.id AND username='"+username+"'");){
            ResultSet r = p.executeQuery();
            if(r.next()){
                 arr = new String[]{r.getInt("s1")+"",r.getInt("s2")+"",r.getInt("s3")+"",r.getInt("s4")+"",r.getInt("s5")+"",r.getInt("s6")+"",r.getInt("sum")+"",};
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arr;
    }
    public static int check(String user , String pass){
        ArrayList<student> list = new ArrayList<>();
        try (
            Connection con = connect();
            PreparedStatement p = con.prepareStatement("select `username` , `password` from student");){
            ResultSet r = p.executeQuery();
            while (r.next()){
                list.add(new student(r.getString("username") , r.getString("password")));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        int x = 0;
        for(int i=0 ; i< list.size() ; i++){
            if(list.get(i).getUsername().equals(user)){
                if (list.get(i).getPassword().equals(pass)) {
                    x = 2;
                    break;
                }
                else {
                    x = 1;
                    break;
                }
            }else x =0;
        }
        return x;
    }
}
