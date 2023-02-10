import javax.swing.*;
import java.awt.event.ActionEvent;
import database.student_database;
public class Add extends JPanel {
    String depar;
    JLabel fname , lname , addr , dep , user , pass;
    JTextField firstname , lastname , address , department , username , password;
    JButton send;
    public void mssg(String title){
        JOptionPane.showMessageDialog(null, title);
    }

    public Add(){
        setLayout(null);
        //-------------------------labels-----------------------------------------------------
        fname = new JLabel("First name");
        lname = new JLabel("last name");
        addr = new JLabel("address");
        dep = new JLabel("department");
        user = new JLabel("username");
        pass = new JLabel("password");
        fname.setBounds(50,50,100,25);
        lname.setBounds(50,80,100,25);
        addr.setBounds(50,110,100,25);
        dep.setBounds(50,140,100,25);
        user.setBounds(50,170,100,25);
        pass.setBounds(50,200,100,25);
        add(fname);add(addr);add(lname);add(dep);add(user);add(pass);
        //-----------------------text fields---------------------------------
        firstname = new JTextField();
        lastname = new JTextField();
        department = new JTextField();
        address = new JTextField();
        username = new JTextField();
        password = new JTextField();
        firstname.setBounds(150,50,120,20);
        lastname.setBounds(150,80,120,20);
        address.setBounds(150,110,120,20);
        department.setBounds(150,140,120,20);
        username.setBounds(150,170,120,20);
        password.setBounds(150,200,120,20);
        add(firstname);add(lastname);add(department);add(address);add(username);add(password);
        //----------------------------button----------------------------------------
        send = new JButton("send data");
        send.setBounds(120,250,150,20);
        add(send);
        send.addActionListener((ActionEvent e)->{
            mssg(firstname.getText()+" "+lastname.getText()+" Added successfully");
            student_database.insert_student(firstname.getText() , lastname.getText(),address.getText(),department.getText(),username.getText(),password.getText());
            firstname.setText("");lastname.setText("");address.setText("");department.setText("");username.setText("");password.setText("");
        });

    }
}
