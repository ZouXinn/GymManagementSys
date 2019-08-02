package DAL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Tool.Global;

//import com.mysql.cj.xdevapi.Statement;

public class DBConnect {
//	private static final String URL="jdbc:mysql://"+Global.ip+"/gymnastics_system" + 
//			"?serverTimezone=UTC";
	private static final String URL="jdbc:mysql://"+Global.ip+"/sports_sys" + 
			"?serverTimezone=UTC";
	private static final String USER="customer";
	//private static final String PASSWORD="....1234";
	private static final String PASSWORD="....1234";
	private static Connection conn = null;
 
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	public static Connection getConnection(){//√ª”√
		return conn;
	}
	public static Statement getStmt() throws SQLException{
		return conn.createStatement();
	}
	public static PreparedStatement getPreparedStmt(String sql) throws SQLException{
		return conn.prepareStatement(sql);
	}
}
