package bankManagement;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {

    int accountId;
    JLabel label2;
    JButton b1;
    BalanceEnquiry(int accountId){
        this.accountId = accountId;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1 = new JLabel("Your Current Balance is Rs ");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(430,180,700,35);
        l3.add(label1);

        label2 = new JLabel();
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(430,220,400,35);
        l3.add(label2);

        b1 = new JButton("Back");
        b1.setBounds(700,406,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        try{
            Conn conn = new Conn();

            String query = """
                SELECT balance
                FROM accounts
                WHERE account_id = ?
                """;

            PreparedStatement ps =
                    conn.connection.prepareStatement(query);

            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                double balance = rs.getDouble("balance");

                label2.setText("Rs. " + balance);

            }else{

                label2.setText("Account not found");
            }

        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Something went wrong. Try again");
        }

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        new MainClass(accountId);
    }

    public static void main(String[] args) {
        new BalanceEnquiry(0);
    }
}
