import javax.swing.*;
import java.awt.*;
import Main.student;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import database.student_database;
import database.degree_database;

public class degree extends JPanel {
    JTable table;
    String dep;
    int x , id ;
    JLabel s1 , s2,s3,s4,s5,s6;
    JTextField st1,st2,st3,st4,st5,st6;
    JButton deg;
    JScrollPane scroll;
    String data [][];
    String header[] = {"id","first name","last name"};
    public degree(String dep){
        this.dep = dep;
        setLayout(null);
        show_degree();
    }
    ArrayList<student> list ;

    public void show_degree(){
        list =  student_database.getSudent(dep);
        //-------------------------table and its events-----------------------------------------------------
        data = new String[list.size()][3];
        for(int i=0 ; i<list.size() ; i++){
            data [i] [0] =""+ list.get(i).getId();
            data [i] [1] =list.get(i).getFirstname();
            data [i] [2] = list.get(i).getLastname();
        }
        table = new JTable(data , header);
        scroll = new JScrollPane(table);
        scroll.setBounds(0,0,250,300);
        add(scroll);
        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                x = table.getSelectedRow() ;
                 id = list.get(x).getId();
                System.out.println(id);
            }
        });
        //--------------------------------labels-------------------------------------------------------
        s1 = new JLabel("cloud");
        s2 = new JLabel("minning");
        s3 = new JLabel("It project");
        s4 = new JLabel("Prog 3");
        s5 = new JLabel("Accounting");
        s6 = new JLabel("OS 1");
        s1.setBounds(260,20,180,25);
        s2.setBounds(260,50,180,25);
        s3.setBounds(260,80,180,25);
        s4.setBounds(260,110,180,25);
        s5.setBounds(260,140,180,25);
        s6.setBounds(260,170,180,25);
        add(s1);add(s2);add(s3);add(s4);add(s5);add(s6);
        //-------------------------------------Text fields-------------------------------------------------
        st1 = new JTextField();
        st2 = new JTextField();
        st3 = new JTextField();
        st4 = new JTextField();
        st5 = new JTextField();
        st6 = new JTextField();
        st1.setBounds(350,20,50,25);
        st2.setBounds(350,50,50,25);
        st3.setBounds(350,80,50,25);
        st4.setBounds(350,110,50,25);
        st5.setBounds(350,140,50,25);
        st6.setBounds(350,170,50,25);
        add(st1);add(st2);add(st3);add(st4);add(st5);add(st6);
        //-----------------------------------------Button-------------------------------------------------------
        deg = new JButton("add degree");
        deg.setBounds(300,220,150,20);
        deg.setBackground(Color.BLACK);
        deg.setForeground(Color.WHITE);
        add(deg);
        deg.addActionListener((ActionEvent e)->{
            degree_database.insert_degree(id , Integer.parseInt(st1.getText()),Integer.parseInt(st2.getText()) , Integer.parseInt(st3.getText()) , Integer.parseInt(st4.getText()) ,Integer.parseInt(st5.getText()), Integer.parseInt(st6.getText()) );
            st1.setText("");st2.setText("");st3.setText("");st4.setText("");st5.setText("");st6.setText("");
        });






    }

}
