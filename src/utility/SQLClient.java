
package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public int readInt(String query){
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            set.next();
            return set.getInt(1);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
    public ArrayList<ArrayList<Integer>> readInts(String query, int first, int last){
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            ArrayList<ArrayList<Integer>> results = new ArrayList<>();
            while(set.next()){
                ArrayList<Integer> row = new ArrayList<>();
                for(int i = first; i <= last; i++){
                    row.add(set.getInt(i));
                }
                results.add(row);
            }
            return results;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
}
