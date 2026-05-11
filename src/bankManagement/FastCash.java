package bankManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FastCash extends JFrame implements ActionListener {
    JButton b1,b2,b3,b4,b5,b6,b7;
    int accountId;
    FastCash(int accountId){
        this.accountId = accountId;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label = new JLabel("SELECT WITHDRAWL AMOUNT");
        label.setBounds(445,180,700,35);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System",Font.BOLD,23));
        l3.add(label);

        b1 = new JButton("Rs. 100");
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65,125,128));
        b1.setBounds(410,274,150,35);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("Rs. 500");
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(65,125,128));
        b2.setBounds(700,274,150,35);
        b2.addActionListener(this);
        l3.add(b2);

        b3 = new JButton("Rs. 1000");
        b3.setForeground(Color.WHITE);
        b3.setBackground(new Color(65,125,128));
        b3.setBounds(410,318,150,35);
        b3.addActionListener(this);
        l3.add(b3);

        b4 = new JButton("Rs. 2000");
        b4.setForeground(Color.WHITE);
        b4.setBackground(new Color(65,125,128));
        b4.setBounds(700,318,150,35);
        b4.addActionListener(this);
        l3.add(b4);

        b5 = new JButton("Rs. 5000");
        b5.setForeground(Color.WHITE);
        b5.setBackground(new Color(65,125,128));
        b5.setBounds(410,362,150,35);
        b5.addActionListener(this);
        l3.add(b5);

        b6 = new JButton("Rs. 10000");
        b6.setForeground(Color.WHITE);
        b6.setBackground(new Color(65,125,128));
        b6.setBounds(700,362,150,35);
        b6.addActionListener(this);
        l3.add(b6);

        b7 = new JButton("BACK");
        b7.setForeground(Color.WHITE);
        b7.setBackground(new Color(65,125,128));
        b7.setBounds(700,406,150,35);
        b7.addActionListener(this);
        l3.add(b7);

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new FastCash(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b7){
            dispose();
            new MainClass(accountId);
        }else{
            String amount = ((JButton)e.getSource()).getText().substring(4);

            try{
                double withdrawAmount = Double.parseDouble(amount);

                Conn conn = new Conn();

                String balanceQuery = """
                    SELECT balance
                    FROM accounts
                    WHERE account_id = ?
                    """;

                PreparedStatement balancePs =
                        conn.connection.prepareStatement(
                                balanceQuery
                        );

                balancePs.setInt(1, accountId);

                ResultSet rs =
                        balancePs.executeQuery();

                double currentBalance = 0;

                if(rs.next()){

                    currentBalance =
                            rs.getDouble("balance");
                }

                if(currentBalance < withdrawAmount){

                    JOptionPane.showMessageDialog(
                            null,
                            "Insufficient Balance"
                    );

                    return;
                }

                String transactionQuery = """
                    INSERT INTO transactions
                    (
                        account_id,
                        transaction_type,
                        amount
                    )
                    VALUES (?, ?, ?)
                    """;

                PreparedStatement transactionPs =
                        conn.connection.prepareStatement(
                                transactionQuery
                        );

                transactionPs.setInt(1, accountId);
                transactionPs.setString(2, "WITHDRAWAL");
                transactionPs.setDouble(3, withdrawAmount);

                transactionPs.executeUpdate();

                String updateBalanceQuery = """
                    UPDATE accounts
                    SET balance = balance - ?
                    WHERE account_id = ?
                    """;

                PreparedStatement updatePs =
                        conn.connection.prepareStatement(
                                updateBalanceQuery
                        );

                updatePs.setDouble(1, withdrawAmount);
                updatePs.setInt(2, accountId);

                updatePs.executeUpdate();


                JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");

                dispose();
                new MainClass(accountId);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        "Something went wrong"
                );
            }
        }
    }
}
