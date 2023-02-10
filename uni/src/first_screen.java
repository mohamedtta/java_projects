import javax.swing.*;
import java.awt.event.ActionEvent;
import database.user_database;

public class first_screen extends JFrame{
    JLabel user,pass;
    JTextField user_name;
    JPasswordField password;
    JButton singin ,singup, back;
    JPanel p = new JPanel();
    public void mssg(String title){
        JOptionPane.showMessageDialog(null, title);
    }

    public void show_first_screen(){
        //intialize user and pass label
        user = new JLabel("user name");
        pass = new JLabel("password");
        user.setBounds(100,70,80,25);
        pass.setBounds(100,100 ,80,25);
        p.add(user);p.add(pass);
        //intilaize username and password textfield
        user_name = new JTextField();
        password = new JPasswordField();
        user_name.setBounds(190,70,150,20);
        password.setBounds(190,103,150,20);
        p.add(user_name);p.add(password);

        //---------------------------intialize sing in, sign up and back button
        singin = new JButton("sign in");
        singup = new JButton("sign up");
        back = new JButton("back to home page");
        singin.setBounds(120,140,100,20);
        singup.setBounds(230,140,100,20);
        back.setBounds(125,180,200,20);
        p.add(singin);p.add(singup);p.add(back);
        //----------------------------Action of signup button
        singup.addActionListener((ActionEvent e)->{
            dispose();
            new second_screen().show_second_screen();
        });
        //-----------------------------Action of sign in
        singin.addActionListener((ActionEvent e)->{
            int i = user_database.check(user_name.getText() , String.valueOf(password.getPassword()));
            if (user_name.getText().isEmpty()) mssg("Please enter your username");
            else if (password.getPassword().length == 0) mssg("Please enter your password");
            else{
                switch (i){
                    case 2:
                        dispose();
                        new doctor(user_database.get_dep(user_name.getText())).show_doctor_screen();
                        break;
                    case 1:
                        mssg("Password is wrong");
                        password.setText("");
                        break;
                    case 0:
                        mssg("no such a user");
                        user_name.setText("");
                        password.setText("");
                        break;
                }
            }

        });
        //-------------------------------Action of back button
        back.addActionListener((ActionEvent e)->{
            dispose();
            new choose().show_screen();
        });


        //-----------------------------------Main form
        p.setLayout(null);
        setTitle("university");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500,400);
        add(p);
    }

}
