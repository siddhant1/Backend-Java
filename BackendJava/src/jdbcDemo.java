import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class jdbcDemo {
	
	static public Connection getConnection() throws ClassNotFoundException, SQLException {
		ResourceBundle rb = ResourceBundle.getBundle("db");
		
		Class.forName(rb.getString("driver"));
		Connection con = null;
		con = DriverManager.getConnection(
				   rb.getString("url"),rb.getString("userid"), rb.getString("password"));
		return con;
	}
  
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		boolean isFound = false;
		Connection con = getConnection();
		if(con != null) {
			System.out.println("connected");
		}
		String query = "SELECT * from emp where name=?;";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, "Siddhant");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			isFound = true;
			System.out.println(rs.getInt("id")+ "   "+rs.getString("name"));
		}
		if(!isFound) {
			System.out.println("No record Found");
		}
		
	}
}
