import javax.swing.*;

import database.student_database;

import java.awt.*;

public class information extends JPanel {
    JTable table;
    JScrollPane scroll;
    JLabel fname;
    JLabel Fname;
    JLabel lname;
    JLabel Lname;
    JLabel address;
    JLabel Address;
    JLabel dep;
    JLabel Dep;
    JLabel user;
    JLabel User;

    public information(String username){
        setLayout(null);
        String data[] = student_database.getSudentuser(username);
        //--------------------------firstname--------------------------------------------
        Fname = new JLabel("firstname:");
        Fname.setBounds(0,10,200,30);
        Fname.setFont(new Font("Arial" , Font.BOLD , 25));
        Fname.setForeground(Color.RED);
        add(Fname);
        fname = new JLabel(data[0]);
        fname.setBounds(160,10,200,30);
        fname.setFont(new Font("Arial" , Font.PLAIN , 25));
        add(fname);
        //--------------------------lastname--------------------------------------------
        Lname = new JLabel("lastname:");
        Lname.setBounds(0,60,200,30);
        Lname.setFont(new Font("Arial" , Font.BOLD , 25));
        Lname.setForeground(Color.RED);
        add(Lname);
        lname = new JLabel(data[1]);
        lname.setBounds(160,60,200,30);
        lname.setFont(new Font("Arial" , Font.PLAIN , 25));
        add(lname);
        //--------------------------Address--------------------------------------------
        Address = new JLabel("address:");
        Address.setBounds(0,110,200,30);
        Address.setFont(new Font("Arial" , Font.BOLD , 25));
        Address.setForeground(Color.RED);
        add(Address);
        address = new JLabel(data[2]);
        address.setBounds(135,110,200,30);
        address.setFont(new Font("Arial" , Font.PLAIN , 25));
        add(address);
        //--------------------------department--------------------------------------------
        Dep = new JLabel("department:");
        Dep.setBounds(0,160,200,30);
        Dep.setFont(new Font("Arial" , Font.BOLD , 25));
        Dep.setForeground(Color.RED);
        add(Dep);
        dep = new JLabel(data[3]);
        dep.setBounds(190,160,200,30);
        dep.setFont(new Font("Arial" , Font.PLAIN , 25));
        add(dep);
        //--------------------------username--------------------------------------------
        User = new JLabel("username:");
        User.setBounds(0,210,200,30);
        User.setFont(new Font("Arial" , Font.BOLD , 25));
        User.setForeground(Color.RED);
        add(User);
        user = new JLabel(data[4]);
        user.setBounds(160,210,200,30);
        user.setFont(new Font("Arial" , Font.PLAIN , 25));
        add(user);


    }
}
