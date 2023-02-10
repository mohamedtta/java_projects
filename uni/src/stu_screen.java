
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import database.student_database;

public class stu_screen  extends JFrame{
    JLabel user,pass;
    JTextField user_name;
    JPasswordField password;
    JButton singin , back;
    JLabel wrongPass = new JLabel("Your password is wrong");
    JLabel wrongUser = new JLabel("No such a username");
    JLabel emptyUser = new JLabel("username is empty");
    JLabel emptyPass = new JLabel("Password is empty");
    JPanel p = new JPanel();
    public void mssg(String title){
        JOptionPane.showMessageDialog(null, title);
    }

    public stu_screen(){
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

        //---------------------------intialize sing in and back button
        singin = new JButton("sign in");
        back = new JButton("back");
        singin.setBounds(190,140,100,20);
        back.setBounds(0,0,100,20);
        p.add(singin);p.add(back);
        //-----------------------------Action of sign in
        singin.addActionListener((ActionEvent e)->{
            int x = student_database.check(user_name.getText(), String.valueOf(password.getPassword()));
            wrongUser.setVisible(false);
            p.add(wrongUser);
            wrongUser.setBounds(190,85,200,20);
            wrongUser.setForeground(Color.RED);
            wrongPass.setVisible(false);
            p.add(wrongPass);
            wrongPass.setBounds(190,118,200,20);
            wrongPass.setForeground(Color.RED);
            wrongPass.setFont(new Font("Arial",Font.PLAIN,13));
            wrongUser.setFont(new Font("Arial",Font.PLAIN,13));

            emptyPass.setVisible(false);
            p.add(emptyPass);
            emptyPass.setBounds(190,118,200,20);
            emptyPass.setForeground(Color.RED);
            emptyPass.setFont(new Font("Arial",Font.PLAIN,13));
            emptyUser.setVisible(false);
            p.add(emptyUser);
            emptyUser.setBounds(190,85,200,20);
            emptyUser.setForeground(Color.RED);
            emptyUser.setFont(new Font("Arial",Font.PLAIN,13));

            if(user_name.getText().isEmpty()) emptyUser.setVisible(true);
            else if (password.getPassword().length == 0) emptyPass.setVisible(true);
            else{
                switch (x){
                    case 1:
                        wrongPass.setVisible(true);
                        password.setText("");
                        break;
                    case 2:
                        dispose();
                        new stu_degree(user_name.getText());
                        break;
                    case 0:
                        wrongUser.setVisible(true);
                        user_name.setText("");
                        password.setText("");

                }

            }

        });
        //-----------------------------Action of back button
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



