package bankManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JLabel label1, label2, label3;
    JTextField textField;
    JPasswordField passwordField;
    JButton button1, button2, button3;
    Login(){
        super("Bank Management System");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bank.png"));
        Image i11 = i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel image1 = new JLabel(i12);
        image1.setBounds(350,10,100,100);
        add(image1);

        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("icons/card.png"));
        Image i21 = i2.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i22 = new ImageIcon(i21);
        JLabel image2 = new JLabel(i22);
        image2.setBounds(630,350,100,100);
        add(image2);


        label1 = new JLabel("WELCOME TO ATM");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("AvantGarde",Font.BOLD,38));
        label1.setBounds(230,125,450,40);
        add(label1);

        label2 = new JLabel("CARD NO:");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("Ralway",Font.BOLD,28));
        label2.setBounds(150,190,375,30);
        add(label2);

        textField = new JTextField(15);
        textField.setBounds(325,190,230,30);
        textField.setFont(new Font("Ariel", Font.BOLD,14));
        add(textField);

        label3 = new JLabel("PIN:");
        label3.setForeground(Color.WHITE);
        label3.setFont(new Font("Ralway",Font.BOLD,28));
        label3.setBounds(150,250,375,30);
        add(label3);

        passwordField = new JPasswordField(15);
        passwordField.setBounds(325,250,230,30);
        passwordField.setFont(new Font("Ariel",Font.BOLD,14));
        add(passwordField);

        button1 = new JButton("SIGN IN");
        button1.setFont(new Font("Ariel", Font.BOLD,14));
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.BLACK);
        button1.setBounds(300,300,100,30);
        button1.addActionListener(this);
        add(button1);


        button2 = new JButton("CLEAR");
        button2.setFont(new Font("Ariel", Font.BOLD,14));
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.BLACK);
        button2.setBounds(430,300,100,30);
        button2.addActionListener(this);
        add(button2);


        button3 = new JButton("SIGN UP");
        button3.setFont(new Font("Ariel", Font.BOLD,14));
        button3.setForeground(Color.WHITE);
        button3.setBackground(Color.BLACK);
        button3.setBounds(300,350,230,30);
        button3.addActionListener(this);
        add(button3);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icons/backbg.png"));
        Image i31 = i3.getImage().getScaledInstance(850,480, Image.SCALE_DEFAULT);
        ImageIcon i32 = new ImageIcon(i31);
        JLabel image3 = new JLabel(i32);
        image3.setBounds(0,0,850,480);
        add(image3);


        setLayout(null);
        setSize(850,480);
        setLocation(400,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource()== button1){
                Conn c = new Conn();
                String card_no = textField.getText();
                String pin = new String(passwordField.getPassword());

                String q = "select * from login where cardno = '"+card_no+"' and pin = '"+pin+"'";
                ResultSet resultSet = c.statement.executeQuery(q);
                if(resultSet.next()){
                    setVisible(false);
                    new MainClass(pin);
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number Or Pin");
                }
            }else if(e.getSource()==button2){
                textField.setText("");
                passwordField.setText("");
            }else if(e.getSource()==button3){
                new SignUp();
                setVisible(false);
            }
        }catch (Exception exp){
            exp.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
