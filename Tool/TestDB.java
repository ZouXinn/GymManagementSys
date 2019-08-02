package Tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAL.DBConnect;


public class TestDB {
	private static Connection con = DBConnect.getConnection();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int id = 3;
//		String name = "ping";
//		String rule = "128";
		
//		String sql2 = "INSERT team (name,account,password) VALUES(?,?,?)";
//		PreparedStatement ptmt;
//		try {
//			ptmt = con.prepareStatement(sql2);
//			ptmt.setString(1, "a");
//			ptmt.setString(2, "m");
//			ptmt.setString(3, "d");
//			ptmt.execute();
//			System.out.println("注册数据已插入logint数据表中");
//		} catch (SQLException ex) {
//			// TODO Auto-generated catch block
//			ex.printStackTrace();
//		}
//		
		
		//String otherppSql = "insert into otherpp (name,id,sex,phone,character) values(?,?,?,?,?)";
//		String otherppSql = "insert into otherpp(m_name,m_id,m_phone,m_role,m_sex) values(?,?,?,?,?)";
//		PreparedStatement ptmt;
//		try {
//			ptmt = con.prepareStatement(otherppSql);
////			ptmt.setString(1, "a");
////			ptmt.setString(2, "p");
////			//ptmt.setBoolean(3, true);
////			ptmt.setString(3, "bool");
////			ptmt.setString(4, "aa");
////			ptmt.setString(5, "bb");
//			ptmt.setString(1, "a");
//			ptmt.setString(2, "b");
//			ptmt.setString(3, "c");
//			ptmt.setString(4, "d");
//			ptmt.setBoolean(5,true);
//			//ptmt.setString(5, "e");
//			ptmt.execute();
//			System.out.println("注册数据已插入logint数据表中");
//		} catch (SQLException ex) {
//			// TODO Auto-generated catch block
//			ex.printStackTrace();
//		}
		
		
		
		String username = "whu";
		Connection conn = DBConnect.getConnection();
		try {
			String sql = "select t_attachment FROM team where t_id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
		
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				String att = rs.getString("t_attachment");
				if(att == null) {
					System.out.println("it is nullptr");//获得空指针
				}else {
					System.out.println("the value is"+att);
				}
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
//		PreparedStatement ps;
//		ResultSet rs;
//		Connection conn = DBConnect.getConnection();
//		String username = "b";
//		String pw;
//		try {
//			String sql = "select * from team where account = '"+ username+"'";
//			//String sql = "select * from team where account = 'b'";
//			//String sql = "select * from team";
//			String Isql = "insert into team (name,account,password) values('a','b','c')";
//			ps = conn.prepareStatement(sql);//conn.createStatement();
//			System.out.println("1");
//			rs = ps.executeQuery();
//			System.out.println("2");
//			while (rs.next()) {
//				pw = rs.getString("password");
//				System.out.println(pw);
//			}
//			ps = conn.prepareStatement(Isql);
//			System.out.println("3");
//			ps.executeQuery();
//			System.out.println("4");
//		} catch (SQLException ex) {
//			// TODO Auto-generated catch block
//			//ex.printStackTrace();
//			System.out.println("ex");
//		}
		
	}

}
