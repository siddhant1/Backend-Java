import java.sql.*;
import java.util.ResourceBundle;

public class jdbcDemo {
	
	static public Connection getConnection() throws ClassNotFoundException, SQLException {
		ResourceBundle rb = ResourceBundle.getBundle("db");
		
		Class.forName(rb.getString("driver"));
		Connection con = null;
		con = DriverManager.getConnection(
				   rb.getString("url"),rb.getString("userid"), rb.getString("password"));
		con.setAutoCommit(false);
		return con;
	}
  
	public static void write(int id, String name,int salary) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement("insert into emp(id,name,salary) values (?,?,?)");
		pstmt.setInt(1, id);
		pstmt.setString(2,name);
		pstmt.setInt(3, salary);
		int result = pstmt.executeUpdate();
		if(result>0) {
			con.commit();
		}else {
			con.rollback();
		}
		
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
//		write(10, "Ashubhai",4000);
		boolean isFound = false;
		Connection con = getConnection();
		if(con != null) {
			System.out.println("connected");
		}
		String query = "SELECT * from emp";
		PreparedStatement pstmt = con.prepareStatement(query);
//		pstmt.setString(1, "Siddhant");
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
