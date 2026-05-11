package bankManagement;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

public class SignUp extends JFrame implements ActionListener {

    JRadioButton r1, r2,r3, m1,m2;
    JButton next;

    JTextField textName, textFname, textEmail, textAdd, textCity, textPin, textState;
    JDateChooser dateChooser;

    Random ran = new Random();

    long first4 = 1000 + ran.nextInt(9000);

    String first = String.valueOf(first4);

    SignUp(){
        super("APPLICATION FORM");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bank.png"));
        Image i11 = i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel image1 = new JLabel(i12);
        image1.setBounds(25,10,100,100);
        add(image1);

        JLabel label = new JLabel("APPLICATION FORM NO. "+ first);
        label.setBounds(160,20,600,40);
        label.setFont(new Font("Ralway", Font.BOLD, 38));
        add(label);

        JLabel label2 = new JLabel("Page 1");
        label2.setFont(new Font("Raleway",Font.BOLD,22));
        label2.setBounds(330,70,600,30);
        add(label2);

        JLabel label3 = new JLabel("Personal Details");
        label3.setFont(new Font("Raleway", Font.BOLD, 22));
        label3.setBounds(290,90,600,30);
        add(label3);

        JLabel labelName = new JLabel("Name :");
        labelName.setFont(new Font("Raleway",Font.BOLD,20));
        labelName.setBounds(100,190,100,30);
        add(labelName);

        textName = new JTextField();
        textName.setFont(new Font("Raleway", Font.BOLD,14));
        textName.setBounds(300,190,400,30);
        add(textName);

        JLabel labelFName = new JLabel("Father's Name :");
        labelFName.setFont(new Font("Raleway",Font.BOLD,20));
        labelFName.setBounds(100,240,200,30);
        add(labelFName);

        textFname = new JTextField();
        textFname.setFont(new Font("Raleway", Font.BOLD,14));
        textFname.setBounds(300,240,400,30);
        add(textFname);

        JLabel dob = new JLabel("Date of Birth :");
        dob.setFont(new Font("Raleway",Font.BOLD,20));
        dob.setBounds(100,340,200,30);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setForeground(new Color(105,105,105));
        dateChooser.setBounds(300,340,400,30);
        add(dateChooser);

        JLabel labelG = new JLabel("Gender :");
        labelG.setFont(new Font("Raleway", Font.BOLD,20));
        labelG.setBounds(100,290,200,30);
        add(labelG);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Raleway", Font.BOLD,14));
        r1.setBackground(new Color(222,255,228));
        r1.setBounds(300,290,60,30);
        add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Raleway", Font.BOLD,14));
        r2.setBackground(new Color(222,255,228));
        r2.setBounds(450,290,90,30);
        add(r2);

        r3 = new JRadioButton("Other");
        r3.setBackground(new Color(222,255,228));
        r3.setBounds(635,290,100,30);
        r3.setFont(new Font("Raleway", Font.BOLD,14));
        add(r3);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);
        buttonGroup.add(r3);


        JLabel labelEmail = new JLabel("Email address :");
        labelEmail.setFont(new Font("Railway", Font.BOLD,20));
        labelEmail.setBounds(100,390,200,30);
        add(labelEmail);

        textEmail = new JTextField();
        textEmail.setFont(new Font("Ralway", Font.BOLD,14));
        textEmail.setBounds(300,390,400,30);
        add(textEmail);

        JLabel labelMs = new JLabel("Marital Status :");
        labelMs.setFont(new Font("Raleway", Font.BOLD, 20));
        labelMs.setBounds(100,440,200,30);
        add(labelMs);

        m1 = new JRadioButton("Married");
        m1.setBounds(300,440,100,30);
        m1.setBackground(new Color(222,255,228));
        m1.setFont(new Font("Raleway", Font.BOLD,14));
        add(m1);

        m2 = new JRadioButton("Unmarried");
        m2.setBackground(new Color(222,255,228));
        m2.setBounds(450,440,100,30);
        m2.setFont(new Font("Raleway", Font.BOLD,14));
        add(m2);


        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(m1);
        buttonGroup1.add(m2);

        JLabel labelAdd = new JLabel("Address :");
        labelAdd.setFont(new Font("Raleway", Font.BOLD, 20));
        labelAdd.setBounds(100,490,200,30);
        add(labelAdd);

        textAdd = new JTextField();
        textAdd.setFont(new Font("Raleway",Font.BOLD, 14));
        textAdd.setBounds(300,490,400,30);
        add(textAdd);

        JLabel labelCity = new JLabel("City :");
        labelCity.setFont(new Font("Raleway", Font.BOLD, 20));
        labelCity.setBounds(100,540,200,30);
        add(labelCity);

        textCity = new JTextField();
        textCity.setFont(new Font("Raleway",Font.BOLD, 14));
        textCity.setBounds(300,540,400,30);
        add(textCity);

        JLabel labelPin = new JLabel("Pin Code :");
        labelPin.setFont(new Font("Raleway", Font.BOLD, 20));
        labelPin.setBounds(100,590,200,30);
        add(labelPin);

        textPin = new JTextField();
        textPin.setFont(new Font("Raleway",Font.BOLD, 14));
        textPin.setBounds(300,590,400,30);
        add(textPin);

        JLabel labelstate = new JLabel("State :");
        labelstate.setFont(new Font("Raleway", Font.BOLD, 20));
        labelstate.setBounds(100,640,200,30);
        add( labelstate);

        textState = new JTextField();
        textState.setFont(new Font("Raleway",Font.BOLD, 14));
        textState.setBounds(300,640,400,30);
        add(textState);

        next = new JButton("Next");
        next.setFont(new Font("Raleway", Font.BOLD,14));
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setBounds(620,710,80,30);
        next.addActionListener(this);
        getRootPane().setDefaultButton(next);
        add(next);




        getContentPane().setBackground(new Color(222,255,228));
        setLayout(null);
        setSize(850,800);
        setLocation(360,40);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String formno = first;
        String name = textName.getText().trim();
        String fname = textFname.getText().trim();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().trim();
        String gender = null;
        if(r1.isSelected()){
            gender = "Male";
        }else if(r2.isSelected()){
            gender = "Female";
        }else if(r3.isSelected()){
            gender = "Other";
        }

        String email = textEmail.getText().trim();

        String marital = null;
        if(m1.isSelected()){
            marital = "Married";
        }else if(m2.isSelected()){
            marital = "Unmarried";
        }
        String address = textAdd.getText().trim();
        String city = textCity.getText().trim();
        String pincode = textPin.getText().trim();
        String state = textState.getText().trim();

        try{
            if(name.isEmpty() ||
                    fname.isEmpty() ||
                    dob.isEmpty() ||
                    gender == null ||
                    marital == null ||
                    address.isEmpty() ||
                    city.isEmpty() ||
                    pincode.isEmpty() ||
                    state.isEmpty()){

                JOptionPane.showMessageDialog(
                        null,
                        "Please fill all fields");

                return;
            }
            if(!email.matches(
                    "^[A-Za-z0-9+_.-]+@(.+)$")){

                JOptionPane.showMessageDialog(
                        null,
                        "Invalid Email");

                return;
            }

            if(!pincode.matches("\\d{6}")){

                JOptionPane.showMessageDialog(
                        null,
                        "Invalid Pin code");

                return;
            }

            Conn conn = new Conn();

            String query = """
                INSERT INTO customers
                (
                    full_name,
                    father_name,
                    dob,
                    gender,
                    email,
                    marital_status,
                    address,
                    city,
                    pincode,
                    state
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

            PreparedStatement ps =
                    conn.connection.prepareStatement(
                            query,
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );

            ps.setString(1, name);
            ps.setString(2, fname);
            ps.setString(3, dob);
            ps.setString(4, gender);
            ps.setString(5, email);
            ps.setString(6, marital);
            ps.setString(7, address);
            ps.setString(8, city);
            ps.setString(9, pincode);
            ps.setString(10, state);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()){

                int customerId = rs.getInt(1);

                new SignUp2(customerId);

                dispose();
            }


        }catch (Exception E){
            E.printStackTrace();
            JOptionPane.showMessageDialog(null,"Something went wrong. Please try again.");

        }
    }

    public static void main(String[] args) {
        new SignUp();
    }
}
