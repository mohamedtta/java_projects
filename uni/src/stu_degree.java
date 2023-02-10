import javax.swing.*;
import database.student_database;

import java.awt.event.ActionEvent;

public class stu_degree extends JFrame {
    JTabbedPane tab;
    String user;
    grades g;
    information info;
    JButton logout;
    public stu_degree(String user){
        this.user = user;
        tab = new JTabbedPane();
        g = new grades(user);
        tab.addTab("grade" , g);
        info = new information(user);
        tab.addTab("Info" , info);
        tab.setBounds(0,30,500,350);
        add(tab);
        logout = new JButton("logout");
        logout.setBounds(0,0,100,20);
        add(logout);
        logout.addActionListener((ActionEvent e)->{
            dispose();
            new stu_screen();
        });


        //-----------------------------------Main form
        setLayout(null);
        setTitle("university");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500,400);
    }
}
