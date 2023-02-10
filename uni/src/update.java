import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import database.student_database;
import Main.student;

public class update extends JPanel {
    int id;
    JTable table;
    JScrollPane scroll;
    JLabel sum;
    JTextField Sum;
    JButton update;
    public update(String dep){
        //----------------------------table--------------------------------------------------------------------
        ArrayList<student> list = student_database.getSudent(dep);
        DefaultTableModel model  = student_database.getupdate(dep);
        table = new JTable(model);
        scroll = new JScrollPane(table);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               int  x = table.getSelectedRow();
               id = list.get(x).getId();
                System.out.println(id);
            }
        });
        scroll.setBounds(0,0,500,250);
        add(scroll);
        //--------------------------labels and text feild------------------------------------
        sum = new JLabel("Sum");
        Sum = new JTextField();
        sum.setBounds(10, 250,50,50);
        Sum.setBounds(45,269,100,20);
        add(Sum);
        add(sum);

        //----------------------------button------------------------------------------------
        update = new JButton("update" );
        update.setBounds(50,295,100,20);
        add(update);
        update.addActionListener((ActionEvent e)->{
            student_database.update(Integer.parseInt(Sum.getText()) , id);
        });
        setLayout(null);
    }
}
