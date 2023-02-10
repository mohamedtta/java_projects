import javax.swing.*;
import java.awt.event.ActionEvent;

public class doctor extends JFrame {
    JTabbedPane tab;
    String dep;
    Add a1;
    degree d1;
    STD_DEG std;
    update up;
    JButton logout;
    JButton refresh;
    public doctor(String dep){
        this.dep = dep;
    }

    public void show_doctor_screen(){
        tab = new JTabbedPane();
        a1 = new Add();
        d1  = new degree(dep );
        std = new STD_DEG(dep);
        up = new update(dep);
        tab.addTab("student" , a1);
        tab.addTab("degree" , d1);
        tab.addTab("student with degree" , std);
        tab.addTab("update", up);
        tab.setBounds(0,20,500,380);
        add(tab);
        //------------------------------------------logout-------------------------------------
        logout = new JButton("logout");
        logout.setBounds(0,0,100,20);
        logout.addActionListener((ActionEvent e)->{
            dispose();
            new first_screen().show_first_screen();
        });
        add(logout);
        //-------------------------------------refresh--------------------------------------------
        refresh = new JButton("refresh");
        refresh.setBounds(395,0,100,20);
        refresh.addActionListener((ActionEvent e)->{
            dispose();
            new doctor(dep).show_doctor_screen();

        });
        add(refresh);
        //----------------Main form
        setTitle("Doctor");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500,400);

    }
}
