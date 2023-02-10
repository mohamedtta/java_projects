import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.text.*;

public class Home extends GUI{
    GUI home ;
    public Home(){
        JPanel p = new JPanel();
        p.setLayout(null);
        String SeledItem;
        Date date = new Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
       java.sql.Time sqltime = new java.sql.Time(date.getTime());

        try {
            Image img = ImageIO.read(getClass().getResource("/home/mo/IdeaProjects/shop/img/c.ico"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        Refresh.addActionListener((ActionEvent e)->{
           home.dispose();
            Home home = new Home();
        });
        insertItem.addActionListener((ActionEvent e)->{
            JPanel p1 = new JPanel();
            window("insertion page" , p1);
            p1.setLayout(null);
            JLabel ItemName = new JLabel("Item Name: ");
            JTextField ItemNameText = new JTextField();
            JLabel countItem = new JLabel("Number of this Item");
            JTextField countItemText = new JTextField();
            JLabel priceone = new JLabel("price of each one");
            JTextField priceonetext = new JTextField();
            JLabel priceAll = new JLabel("Total price");
            JTextField priceAllText = new JTextField();
            JButton additem = new JButton("Add Item");
            ItemName.setBounds(10,10,100,25);
            ItemNameText.setBounds(170,10,100,25);
            countItem.setBounds(10,60,150,25);
            countItemText.setBounds(170,60,100,25);
            priceone.setBounds(10,110,150,25);
            priceonetext.setBounds(170,110,100,25);
            additem.setBounds(90,170,150,50);
            p1.add(ItemName);p1.add(ItemNameText);p1.add(countItemText);p1.add(countItem);p1.add(priceone);p1.add(priceonetext);p1.add(additem);p1.add(priceAllText);p1.add(priceAll);
            //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            additem.addActionListener((ActionEvent e1)->{
                String name =ItemNameText.getText();
                if(ItemNameText.getText().isEmpty()) Mssg("Please enter the name of the item you want to insert");
                else if(countItemText.getText().isEmpty()) Mssg("Please enter how many item you want add from this item");
                else if(priceonetext.getText().isEmpty()) Mssg("Please enter the price of the item you want to insert");
                else if(item_found("items",name)) Mssg("This item is already exist you can go to Added items to add this item");
                else{
                    int Price = Integer.parseInt(priceonetext.getText()), Counter = Integer.parseInt(countItemText.getText()), total = Price * Counter;
                    dispose();
                    String query = "insert into items (`item_name` , `price`,`count_item` ,`total_price`) values ('"+name+"','"+Price+"' , '"+Counter+"' , '"+total+"')";
                    try {
                        DB.smt = con.prepareStatement(query);
                    }catch (SQLException e3){
                        System.out.println(e3.getMessage());
                    }
                    try {
                        DB.smt.execute(query);
                    }catch (SQLException e3){
                        System.out.println(e3.getMessage());
                    }
                        String query2 ="insert into inserted values('"+name+"',"+Counter+","+Price+","+total+",'"+sqldate+"','"+sqltime+"')";
                        try {
                            DB.smt = con.prepareStatement(query2);
                        }catch (SQLException e3){
                            System.out.println(e3.getMessage());
                        }
                        try {
                            DB.smt.execute(query2);
                        }catch (SQLException e3){
                            System.out.println(e3.getMessage());
                        }

                    }
                    ItemNameText.setText("");priceonetext.setText("");countItemText.setText("");





            });

        });
        sell_item.addActionListener((ActionEvent e)->{
            JPanel p1 = new JPanel();
            window("Buy an item" , p1);
            p1.setLayout(null);
            JLabel ItemName = new JLabel("Item Name: ");
            JTextField ItemNameText = new JTextField();
            JLabel countItem = new JLabel("Number of this Item");
            JTextField countItemText = new JTextField();
            JButton Buyitem = new JButton("purchase");
            ItemName.setBounds(10,10,100,25);
            ItemNameText.setBounds(170,10,100,25);
            countItem.setBounds(10,60,150,25);
            countItemText.setBounds(170,60,100,25);
            Buyitem.setBounds(180,170,150,50);
            p1.add(ItemName);p1.add(ItemNameText);p1.add(countItemText);p1.add(countItem);p1.add(Buyitem);
            Buyitem.addActionListener((ActionEvent e2)-> {
                if(ItemNameText.getText().isEmpty())Mssg("Please enter the name of the item");
                else if (countItemText.getText().isEmpty())Mssg("pleasee enter how many of item you want to sell");
                else {
                    int counter = Integer.parseInt(countItemText.getText());
                    String name = ItemNameText.getText();
                    int count = getcounter(name);
                    if(counter > count){
                        Mssg("You have only " + count + " items from this product");
                    }
                    else {
                        int newcount = count - counter;
                        setcounter(newcount,name);
                        int price = getPrice(name);
                        int total = newcount *price;
                        setTotal(total,name);
                        int tot = counter * price;
                       String query ="insert into selled values('"+name+"',"+counter+","+price+","+tot+",'"+sqldate+"','"+sqltime+"')";
                       try {
                           DB.smt = con.prepareStatement(query);
                       }catch (SQLException e3){
                           System.out.println(e3.getMessage());
                       }
                        try {
                            DB.smt.execute(query);
                        }catch (SQLException e3){
                            System.out.println(e3.getMessage());
                        }
                    }
                    countItemText.setText("");ItemNameText.setText("");

                }

            });
        });
        Add_Item.addActionListener((ActionEvent e)->{
            JPanel p1 = new JPanel();
            window("Buy an item" , p1);
            p1.setLayout(null);
            JLabel ItemName = new JLabel("Item Name: ");
            JTextField ItemNameText = new JTextField();
            JLabel countItem = new JLabel("Number of Items Added");
            JTextField countItemText = new JTextField();
            JButton Additem = new JButton("Add");
            ItemName.setBounds(10,10,100,25);
            ItemNameText.setBounds(170,10,100,25);
            countItem.setBounds(10,60,150,25);
            countItemText.setBounds(170,60,100,25);
            Additem.setBounds(90,170,150,50);
            p1.add(ItemName);p1.add(ItemNameText);p1.add(countItemText);p1.add(countItem);p1.add(Additem);
            Additem.addActionListener((ActionEvent e1)->{
                if(ItemNameText.getText().isEmpty()) Mssg("Please Enter the name of the item you want to add");
                else if(String.valueOf(countItemText.getText()).isEmpty()) Mssg("Please enter how many item you want to add");
                else {
                    int counter = Integer.parseInt(countItemText.getText());
                    String name = ItemNameText.getText();
                    if(item_found("items" , name)){
                        int new_size = counter + getcounter(name);
                        setcounter(new_size , name);
                        int price = getPrice(name);
                        int total = new_size * price;
                        int tot = counter * price;
                        setTotal(total,name);
                        String query2 ="insert into Added values('"+name+"',"+counter+","+price+","+tot+",'"+sqldate+"','"+sqltime+"')";
                        try {
                            DB.smt = con.prepareStatement(query2);
                        }catch (SQLException e3){
                            System.out.println(e3.getMessage());
                        }
                        try {
                            DB.smt.execute(query2);
                        }catch (SQLException e3){
                            System.out.println(e3.getMessage());
                        }
                    }
                    else Mssg("Not founded");
                    countItemText.setText("");ItemNameText.setText("");

                }



            });

        });
        del.addActionListener((ActionEvent e)->{
            JPanel p1 = new JPanel();
            window("delete Page" , p1);
            p1.setLayout(null);
            JLabel ItemName = new JLabel("Item Name: ");
            JTextField ItemNameText = new JTextField();
            JButton deleteitem = new JButton("Delete");
            ItemName.setBounds(10,10,100,25);
            ItemNameText.setBounds(170,10,100,25);
            deleteitem.setBounds(90,170,150,50);
            p1.add(ItemName);p1.add(ItemNameText);p1.add(deleteitem);
            deleteitem.addActionListener((ActionEvent e2)-> {
                if(ItemNameText.getText().isEmpty()) Mssg("Please enter the name of the item");
                else{
                    String name = ItemNameText.getText();
                    String query2 = "select * from items where item_name ='"+name+"'";
                    try {
                        DB.smt = con.prepareStatement(query2);
                    }catch (SQLException e1){
                        System.out.println(e1.getMessage());
                    }
                    try {
                        ResultSet r = DB.smt.executeQuery(query2);
                        if(r.next()) {
                            String query = "Delete from items where item_name ='" + name + "'";
                            try {
                                DB.smt = con.prepareStatement(query);
                            } catch (SQLException e1) {
                                System.out.println(e1.getMessage());
                            }
                            try {
                                DB.smt.execute(query);
                            } catch (SQLException e1) {
                                System.out.println(e1.getMessage());
                            }
                        }else {
                            Mssg("No such a that item");
                            ItemNameText.setText("");
                        }
                    }catch (SQLException e1){
                        System.out.println(e1.getMessage());
                    }
                    ItemNameText.setText("");

                }

            });


        });
        Search.addActionListener((ActionEvent e)->{
            JPanel p1 = new JPanel();
            window("delete Page" , p1);
            p1.setLayout(null);
            JLabel ItemName = new JLabel("Item Name: ");
            JTextField ItemNameText = new JTextField();
            JButton Searchitem = new JButton("search");
            ItemName.setBounds(10,10,100,25);
            ItemNameText.setBounds(170,10,100,25);
            Searchitem.setBounds(90,170,150,50);
            p1.add(ItemName);p1.add(ItemNameText);p1.add(Searchitem);
            Searchitem.addActionListener((ActionEvent e1)->{
                String name = ItemNameText.getText();
                if(ItemNameText.getText().isEmpty())Mssg("Please enter the name of the Item");
                else{
                    String query = "select * from items where item_name ='"+name+"'";
                    try {
                        DB.smt = con.prepareStatement(query);
                    }catch (SQLException e3){
                        System.out.println(e3.getMessage());
                    }
                    try {
                        ResultSet r = DB.smt.executeQuery(query);
                        if(r.next()){
                            String [] col = {"id","item_name","price","count_item","total_price"};
                            String[][] names = new String[1][5] ;
                            names[0][0] = String.valueOf(r.getInt("id"));
                            names[0][1] = r.getString("item_name");
                            names[0][2] = String.valueOf(r.getInt("price"));
                            names[0][3] =String.valueOf(r.getInt("count_item"));
                            names[0][4] =String.valueOf(r.getInt("total_price"));
                            JTable table = new JTable(names,col);
                            JScrollPane scroll = new JScrollPane(table);
                            scroll.setBounds(0,60,500,100);
                            p1.add(scroll);
                        }else {
                            char[] n = name.toCharArray();
                            int number = getnames().size();
                            for(int i=0 ; i<number ; i++){
                                String li = getnames().get(i).toString();
                                char[] ch =li.toCharArray();
                                   if(n.length==1 && ch.length >=1){
                                       if(n[0] == ch[0]) Mssg("Not found you mean "+li);
                                    }
                                   else if(n.length==2 && ch.length >=2){
                                        if(n[0] == ch[0] && n[1]==ch[1]) Mssg("Not found you mean "+li);
                                    }else if(n.length==3 && ch.length >=3){
                                        if(n[0] == ch[0] && n[1]==ch[1] && n[2] == n[2]) Mssg("Not found you mean "+li);
                                    }else if(n.length==4 && ch.length >=4){
                                        if(n[0] == ch[0] && n[1]==ch[1] && n[2] == n[2] && n[3] ==n[3]) Mssg("Not found you mean "+li);
                                    }
                                }
                                }


                    }catch (SQLException e3){
                        System.out.println(e3.getMessage());
                    }
                }
           });
        });
        history.addActionListener((ActionEvent e)->{
            JFrame f = new JFrame();
            JPanel p2 = new JPanel();
            JLabel selled = new JLabel("Selled Items");
            JLabel inserted = new JLabel("Inserted Items");
            JLabel Add = new JLabel("Added Items");
            p2.setLayout(null);
            DefaultTableModel tableModel = showhistory("selled");
            JTable table = new JTable(tableModel);
            JScrollPane scroll = new JScrollPane(table);
            scroll.setBounds(0,40,500,100);

            DefaultTableModel tableModel2 =  showhistory("inserted");
            JTable table2 = new JTable(tableModel2);
            JScrollPane scroll2 = new JScrollPane(table2);
            scroll2.setBounds(0,190,500,100);

            DefaultTableModel tableModel3 =  showhistory("Added");
            JTable table3 = new JTable(tableModel3);
            JScrollPane scroll3 = new JScrollPane(table3);
            scroll3.setBounds(0,340,500,100);


            selled.setBounds(130,0,400,40);
            selled.setFont(new Font("Serif", Font.BOLD, 30));
            inserted.setBounds(130,150,400,40);
            inserted.setFont(new Font("Serif", Font.BOLD, 30));
            Add.setBounds(130,300,400,40);
            Add.setFont(new Font("Serif", Font.BOLD, 30));
            p2.add(scroll);p2.add(scroll2);p2.add(scroll3);
            p2.add(selled);p2.add(inserted);p2.add(Add);
            f.add(p2);
            f.setVisible(true);
            f.setSize(800,800);
        });


        DefaultTableModel DBTable = gettable();
        JTable table = new JTable(DBTable);
        JScrollPane scroll = new JScrollPane(table);





        home = new GUI("Home Page");
        home.add(p);
        p.add(insertItem);
        p.add(Search);
        p.add(sell_item);
        p.add(Add_Item);
        p.add(del);
        p.add(Refresh);
        p.add(history);
        p.add(scroll);
        insertItem.setBounds(615,30,170,50);
        sell_item.setBounds(615,90,170,50);
        Add_Item.setBounds(615,150,170,50);
        del.setBounds(615,210,170,50);
        Search.setBounds(615,270,170,50);
        history.setBounds(615,330,170,50);
        Refresh.setBounds(615,700,170,50);

        scroll.setBounds(0,10,600,800);
    }

}
