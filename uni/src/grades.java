import database.student_database;

import javax.swing.*;

public class grades extends JPanel {
    JTable table;
    JScrollPane scroll;

    public grades(String user){
        String header[] = {"s1","s2","s3","s4","s5","s6","sum"};
        String data[][]= {student_database.degree_stu(user)};

        table = new JTable(data,header);
        scroll = new JScrollPane(table);
        add(scroll);


    }

}
