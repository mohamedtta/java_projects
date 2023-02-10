import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class GUI extends JFrame {
    JButton insertItem = new JButton("insert new item");
    JButton sell_item = new JButton("Buy an item");
    JButton Add_Item = new JButton("Add an item");
    JButton Refresh = new JButton("Refresh");
    JButton del = new JButton("Delete and item");
    JButton Search = new JButton("search for an item");
    JButton history = new JButton("show your history");
    DB db = new DB();
    Connection con;
    public void Mssg(String mssg){
        JOptionPane.showMessageDialog(null,mssg);
    }
    public void  window(String title , JPanel p1){
        JFrame f = new JFrame();
        f.setSize(500,300);
        f.setVisible(true);
        f.setLocation(500,200);
        f.add(p1);
    }

    public GUI() {
        try {
            con = db.Connect();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
    public GUI(String title){
        setSize(800,800);
        setLocation(400,100);
        setTitle(title);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public DefaultTableModel gettable(){
        String[] colname = {"id","item_name","price", "count_item" , "total_price"};
        DefaultTableModel table = new DefaultTableModel(colname,0);
        String query = "select * from items";
        try {
            DB.smt = con.prepareStatement(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            ResultSet r = DB.smt.executeQuery(query);
            while (r.next()) {
                Vector<String> content = new Vector<String>();
                content.add(String.valueOf(r.getInt("id")));
                content.add(r.getString("item_name"));
                content.add(String.valueOf(r.getInt("price")));
                content.add(String.valueOf(r.getInt("count_item")));
                content.add(String.valueOf(r.getInt("total_price")));
                table.addRow(content);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return table;
    }
    public int getcounter(String columnName){
        String query = "select count_item from items where item_name = '"+columnName+"'";
        int count =0;
        try {
            DB.smt = con.prepareStatement(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            ResultSet r = DB.smt.executeQuery(query);
            if(r.next()){
                count = r.getInt("count_item");
            }else {
                Mssg("no such an item");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return count;
    }
    public void setcounter(int number,String name){
        String query = "update `items` set count_item = '"+number+"' where item_name = '"+name+"'";
        try {
            DB.smt = con.prepareStatement(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
             DB.smt.execute(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public int getPrice(String name){
        int price = 0;
        String query = "select price from items where item_name = '"+name+"'";
        try {
            DB.smt = con.prepareStatement(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            ResultSet r = DB.smt.executeQuery(query);
            if(r.next()) price = r.getInt("price");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return price;
    }
    public void setTotal(int total , String name){
        String query = "update items set total_price = '"+total+"'  where item_name =  '"+name+"'";
        try {
            DB.smt = con.prepareStatement(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            DB.smt.execute(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public int getTotal(String name){
        String query = "select * from items where item_name = '"+name+"'";
        int tot =0;
        try {
            DB.smt = con.prepareStatement(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            ResultSet r = DB.smt.executeQuery(query);
            if (r.next())  tot = r.getInt("total_price");
            else Mssg("item not found");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return tot;
    }

    Vector<String> getItemName(){
        Vector<String> ItemName = new Vector<String>();
        String query = "select item_name from items";
        try {
            DB.smt = con.prepareStatement(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            ResultSet r = DB.smt.executeQuery(query);
            while(r.next()){
                ItemName.add(r.getString("item_name"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return ItemName;
    }

    public DefaultTableModel showhistory(String dB){
        String[] colname = {"item_name", "count" , "price","total_price","Date","time"};
        DefaultTableModel table = new DefaultTableModel(colname,0);
        String query = "select * from "+dB;
        try {
            DB.smt = con.prepareStatement(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            ResultSet r = DB.smt.executeQuery(query);
            while (r.next()) {
                Vector<String> content = new Vector<String>();
                content.add(r.getString("item_name"));
                content.add(String.valueOf(r.getInt("count")));
                content.add(String.valueOf(r.getInt("price")));
                content.add(String.valueOf(r.getInt("total_price")));
                content.add(String.valueOf(r.getDate("Date")));
                content.add(String.valueOf(r.getTime("time")));
                table.addRow(content);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return table;
    }
    public boolean item_found(String table , String item){
        String query = "select * from "+table+" where item_name = '"+item+"'";
        boolean what = false;
        try {
            DB.smt = con.prepareStatement(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            ResultSet r = DB.smt.executeQuery(query);
            what = r.next();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return what;
    }
    public ArrayList getnames(){
        ArrayList<String> arr = new ArrayList<>();
        String query = "select item_name from items";
        try {
            DB.smt = con.prepareStatement(query);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        try {
            ResultSet r  = DB.smt.executeQuery(query);
            while(r.next()){
               String n = r.getString("item_name");
               arr.add(n);
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return arr;
    }
}
