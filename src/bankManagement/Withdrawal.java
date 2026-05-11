package bankManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Withdrawal extends JFrame implements ActionListener {
    int accountId;

    TextField textField;
    JButton b1,b2;
    public Withdrawal(int accountId) {
        this.accountId = accountId;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1 = new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460,180,700,35);
        l3.add(label1);

        JLabel label2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(460,220,400,35);
        l3.add(label2);


        textField = new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460,260,320,25);
        textField.setFont(new Font("Raleway", Font.BOLD,22));
        l3.add(textField);

        b1 = new JButton("WITHDRAW");
        b1.setBounds(700,362,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700,406,150,35);
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            try{
                String amountText = textField.getText().trim();

                if(amountText.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Please enter amount you want to withdraw");
                    return;
                }

                double amount;

                try {

                    amount = Double.parseDouble(amountText);

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter valid numeric amount"
                    );

                    return;
                }

                if (amount <= 0) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Amount must be greater than 0"
                    );

                    return;
                }

                if (amount > 10000) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Maximum withdrawal limit is Rs. 10,000"
                    );

                    return;
                }

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
                if(currentBalance < amount){

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
                transactionPs.setDouble(3, amount);

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

                updatePs.setDouble(1, amount);
                updatePs.setInt(2, accountId);

                updatePs.executeUpdate();

                JOptionPane.showMessageDialog(null, "Rs. " + amount + " Withdrawn Successfully");

                dispose();

                new MainClass(accountId);


            } catch (Exception E) {
                    E.printStackTrace();

                    JOptionPane.showMessageDialog(null, "Something went wrong");
            }
        } else if (e.getSource()==b2) {
            dispose();
            new MainClass(accountId);
        }
    }

    public static void main(String[] args) {
        new Withdrawal(0);
    }
}
