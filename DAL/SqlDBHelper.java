package DAL;
import java.sql.*;


public class SqlDBHelper {
	//Connection connetcion = DBConnect.getConnection();
	//Statement stmt;
	//ResultSet rs;
	//PreparedStatement pStmt;
		
	public ResultSet select(String sql) {
		ResultSet rs = null;
		try {
			PreparedStatement pStmt = DBConnect.getPreparedStmt(sql);
			//System.out.println(String.valueOf(stmt));
			rs = pStmt.executeQuery(sql);
			//System.out.println("rs");
		}catch(SQLException ex){
			System.out.println("error");
		}
		return rs;
	}
	public boolean delete(String sql) {
		try {
			PreparedStatement pStmt = DBConnect.getPreparedStmt(sql);
			pStmt.executeUpdate(sql);
		}catch(SQLException ex){
			return false;
		}
		return true;
	}
	public boolean update(String sql) {
		try {
			PreparedStatement pStmt = DBConnect.getPreparedStmt(sql);
			pStmt.executeUpdate(sql);
		}catch(SQLException ex){
			return false;
		}
		return true;
	}
	public boolean insert(String sql) {
		try {
			PreparedStatement pStmt = DBConnect.getPreparedStmt(sql);
			pStmt.executeUpdate();
		}catch(SQLException ex){
			System.out.println("insert error");
			return false;
		}
		return true;
	}
}
