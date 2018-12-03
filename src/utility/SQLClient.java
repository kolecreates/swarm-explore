
package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLClient {
    private Connection connection;
    public SQLClient(){
        try
        {
          String myDriver = "com.mysql.cj.jdbc.Driver";
          String myUrl = "jdbc:mysql://localhost:3306/swarmexplore?serverTimezone=UTC";
          Class.forName(myDriver);
          this.connection = DriverManager.getConnection(myUrl, "root", "sesame");
        }
        catch (ClassNotFoundException e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
        }
    }
    
    public void write(String query){
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
