package bankManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Mini extends JFrame implements ActionListener {
    String cardNumber;
    JButton button;
    Mini(String cardNumber){
        this.cardNumber = cardNumber;

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
            Conn c = new Conn();

            ResultSet resultSet = c.statement.executeQuery("select * from bank_transactions where card_number = '"+cardNumber+"' order by transaction_time desc limit 10");

            while(resultSet.next()){
                label3.setText(
                        "Card Number:  " +
                                cardNumber.substring(0,4) +
                                "XXXXXXXX" +
                                cardNumber.substring(12)
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            int balance = 0;
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from bank_transactions where card_number = '"+cardNumber+"'");

            while (resultSet.next()){
                label1.setText(label1.getText() + "<html>"+resultSet.getString("transaction_time")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("amount")+ "<br><br></html>");
                if (resultSet.getString("transaction_type").equals("Deposit")){
                    balance += Integer.parseInt(resultSet.getString("amount"));
                }else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }

            label4.setText("Your Total Balance is Rs "+balance);

        }catch (Exception e){
            e.printStackTrace();
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
        new Mini("");
    }
}
