import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.ArrayList;
import Main.student;
import com.mysql.cj.protocol.Message;
import database.student_database;
public class STD_DEG extends JPanel {
    JTable table;
    String dep;
    JScrollPane scroll;
    String data [][];
    String[] header = {"id" , "fname" , "lname" , "degree" , "dep"};
    ArrayList<student> list;
    JButton print;
    public STD_DEG(String dep){
        this.dep = dep;
        setLayout(null);
        show_student_with_degree();
    }
    public void show_student_with_degree(){
        list = student_database.getSudentANDdegree(dep);
        data = new String[list.size()][5];
        for(int i=0 ; i<list.size();i++){
            data[i][0]=list.get(i).getId()+"";
            data[i][1]=list.get(i).getFirstname()+"";
            data[i][2]=list.get(i).getLastname()+"";
            data[i][3]=list.get(i).getAddress()+"";
            data[i][4]=list.get(i).getDepartment()+"";
        }
        table = new JTable(data , header);
        scroll = new JScrollPane(table);
        scroll.setBounds(0,0,400,300);
        add(scroll);
        //---------------------------------Button-----------------------------------
        print = new JButton("print");
        print.setBounds(180,300,100,20);
        add(print);
        print.addActionListener((ActionEvent e)->{
            MessageFormat h = new MessageFormat("student");
            MessageFormat f = new MessageFormat("page 1");
            try {
                table.print(JTable.PrintMode.NORMAL,h,f);
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        });

    }
}
