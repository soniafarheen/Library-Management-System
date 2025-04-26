
package Project;
import java.sql.*;

public class ConnectionProvider {
   private static final String DB_URL = "jdbc:mysql://localhost:3306/LMS";
   private static final String DB_OPTIONS = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
   
   public static Connection getCon() {
       // Try primary credentials first
       Connection con = tryConnect("root", "");
       if (con != null) return con;
       
       // Try fallback credentials
       con = tryConnect("lmsuser", "password123");
       if (con != null) return con;
       
       System.err.println("All connection attempts failed");
       return null;
   }
   
   private static Connection tryConnect(String user, String pass) {
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           return DriverManager.getConnection(DB_URL + DB_OPTIONS, user, pass);
       } catch (Exception e) {
           System.out.println("Connection attempt failed for user " + user + ": " + e.getMessage());
           return null;
       }
   }
}