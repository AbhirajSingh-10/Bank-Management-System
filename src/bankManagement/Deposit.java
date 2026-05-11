package bankManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Deposit extends JFrame implements ActionListener {
    int accountId;
    TextField textField;

    JButton b1, b2;
    Deposit(int accountId){
        this.accountId = accountId;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1 = new JLabel("ENETR AMOUNT YOU WANT TO DEPOSIT");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460,180,400,35);
        l3.add(label1);

        textField = new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460,230,320,25);
        textField.setFont(new Font("Raleway", Font.BOLD,22));
        l3.add(textField);

        b1 = new JButton("DEPOSIT");
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
        try {
            if (e.getSource() == b1) {
                String amountText = textField.getText().trim();

                if (amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Deposit");
                    return;
                }

                double amount;

                try{
                    amount = Double.parseDouble(amountText);
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Invalid amount");
                    return;
                }

                if(amount<=0){
                    JOptionPane.showMessageDialog(null,"Amount must be greater than 0");
                    return;
                }

                Conn conn = new Conn();

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
                transactionPs.setString(2, "DEPOSIT");
                transactionPs.setDouble(3, amount);

                transactionPs.executeUpdate();

                String balanceQuery = """
                    UPDATE accounts
                    SET balance = balance + ?
                    WHERE account_id = ?
                    """;

                PreparedStatement balancePs =
                        conn.connection.prepareStatement(
                                balanceQuery
                        );

                balancePs.setDouble(1, amount);
                balancePs.setInt(2, accountId);

                balancePs.executeUpdate();

                JOptionPane.showMessageDialog(
                        null,
                        "Rs. " + amount + " Deposited Successfully"
                );

                dispose();

                new MainClass(accountId);

            } else if (e.getSource() == b2) {
                dispose();
                new MainClass(accountId);
            }
        } catch (Exception E) {
            E.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Something went wrong"
            );
        }
    }

    public static void main(String[] args) {
        new Deposit(0);
    }
}
