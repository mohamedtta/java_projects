import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import database.user_database;
public class second_screen extends JFrame {
    JPanel p = new JPanel();
    JLabel user,pass,depart,note;
    JTextField username,password,department;
    JButton send,back;
    public void mssg(String title){
        JOptionPane.showMessageDialog(null, title);
    }

    public  void show_second_screen(){

        //------------------intialize user,pass,depart,note label
        user = new JLabel("user name");
        pass = new JLabel("password");
        depart = new JLabel("department");
        note = new JLabel("The departments are ( CS , IT and IS )");
        user.setBounds(10,50,80, 25);
        pass.setBounds(10,80,80, 25);
        depart.setBounds(10,110,100, 25);
        note.setBounds(220,110,290, 25);
        note.setForeground(Color.RED);
        p.add(user);p.add(pass);p.add(depart);p.add(note);

        //------------------intialize username,password,department textfeild
        username = new JTextField();
        password = new JPasswordField();
        department = new JTextField();
        username.setBounds(100,55,200,18);
        password.setBounds(100,85,200,18);
        department.setBounds(100,115,100,18);
        p.add(username);p.add(department);p.add(password);

        //----------------intialize send and back button
        send = new JButton("send data");
        send.setBounds(200,190,150,20);
        back = new JButton("back");
        back.setBounds(0,0,100,30);
        back.setBackground(Color.RED);
        back.setForeground(Color.white);
        p.add(send);p.add(back);
        //-----------------Action of back button
        back.addActionListener((ActionEvent e) ->{
            dispose();
            new first_screen().show_first_screen();
        });
        //-----------------------Action of send button
        send.addActionListener((ActionEvent e)->{
            if(username.getText().isEmpty()) mssg("please enter your username");
            else if(password.getText().isEmpty()) mssg("please enter your password");
            else if (department.getText().isEmpty()) mssg("Please enter your department");
            else{
                if (department.getText().equalsIgnoreCase("cs") || department.getText().equalsIgnoreCase("is")|| department.getText().equalsIgnoreCase("it")){
                    user_database.insert(username.getText(),password.getText(),department.getText());
                    mssg("insert user");
                    dispose();
                    new first_screen().show_first_screen();
                }
                else mssg("please enter valid department");

            }

        });

        //--------------------------------Main form
        p.setLayout(null);
        setTitle("Registration Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500,400);
        add(p);
    }
}
