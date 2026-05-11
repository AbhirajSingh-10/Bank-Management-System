package bankManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Mini extends JFrame implements ActionListener {
    int accountId;
    JButton button;
    Mini(int accountId){
        this.accountId = accountId;

        JLabel label1 = new JLabel();
        JScrollPane scrollPane = new JScrollPane(label1);
        scrollPane.setBounds(20,140,350,220);
        scrollPane.setBackground(new Color(255,204,204));
        add(scrollPane);

        JLabel label2 = new JLabel("Mini Statement");
        label2.setBounds(150,20,200,20);
        label2.setFont(new Font("System",Font.BOLD,15));
        add(label2);

        JLabel label3 = new JLabel();
        label3.setBounds(20,80,300,20);
        add(label3);

        JLabel label4 = new JLabel();
        label4.setBounds(20,400,300,20);
        add(label4);

        try{
            Conn conn = new Conn();

            String cardQuery = """
                SELECT card_number
                FROM accounts
                WHERE account_id = ?
                """;

            PreparedStatement cardPs =
                    conn.connection.prepareStatement(
                            cardQuery
                    );

            cardPs.setInt(1, accountId);

            ResultSet cardRs = cardPs.executeQuery();

            if(cardRs.next()){

                String cardNumber =
                        String.valueOf(
                                cardRs.getLong("card_number")
                        );

                label3.setText(
                        "Card Number: "
                                + cardNumber.substring(0,4)
                                + "XXXXXXXX"
                                + cardNumber.substring(12)
                );
            }

        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Something went wrong. Try again.");
        }

        try{

            Conn conn = new Conn();

            String transactionQuery = """
                SELECT
                    transaction_type,
                    amount,
                    transaction_time
                FROM transactions
                WHERE account_id = ?
                ORDER BY transaction_time DESC
                LIMIT 10
                """;

            PreparedStatement transactionPs =
                    conn.connection.prepareStatement(
                            transactionQuery
                    );

            transactionPs.setInt(1, accountId);

            ResultSet rs =
                    transactionPs.executeQuery();

            StringBuilder miniStatement =
                    new StringBuilder("<html>");

            while(rs.next()){

                miniStatement.append(
                                rs.getTimestamp("transaction_time")
                        )
                        .append("&nbsp;&nbsp;&nbsp;")
                        .append(rs.getString("transaction_type"))
                        .append("&nbsp;&nbsp;&nbsp;Rs. ")
                        .append(rs.getDouble("amount"))
                        .append("<br><br>");
            }

            miniStatement.append("</html>");

            label1.setText(
                    miniStatement.toString()
            );

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

            ResultSet balanceRs =
                    balancePs.executeQuery();

            if(balanceRs.next()){

                double balance =
                        balanceRs.getDouble("balance");

                label4.setText(
                        "Your Total Balance is Rs "
                                + balance
                );
            }

        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Something went wrong. Please try again.");
        }

        button = new JButton("Exit");
        button.setBounds(20,500,100,25);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);


        getContentPane().setBackground(new Color(255,204,204));
        setSize(400,600);
        setLocation(20,20);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();

    }

    public static void main(String[] args) {
        new Mini(0);
    }
}
