import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class choose extends JFrame {
    JPanel p = new JPanel();
    JButton stu;
    JButton doc;
    public void show_screen(){
        stu = new JButton("student");
        doc = new JButton("doctor");
        stu.setBackground(Color.RED);
        stu.setForeground(Color.WHITE);
        doc.setBackground(Color.BLUE);
        doc.setForeground(Color.WHITE);
        stu.addActionListener((ActionEvent e)->{

        });
        doc.addActionListener((ActionEvent e)->{
            dispose();
            new first_screen().show_first_screen();
        });
        stu.addActionListener((ActionEvent e)->{
            dispose();
            new stu_screen();
        });
        p.add(stu);p.add(doc);
        //-----------------------------------Main form
        p.setLayout(new GridLayout(1,2));
        p.setBackground(Color.BLACK);
        setTitle("Choose");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500,400);
        add(p);
    }
}
