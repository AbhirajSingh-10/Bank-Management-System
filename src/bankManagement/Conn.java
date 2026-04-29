package bankManagement;

import java.sql.*;


public class Conn {
    java.sql.Connection connection;
    Statement statement;
    public Conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem","root","mysql@123");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
