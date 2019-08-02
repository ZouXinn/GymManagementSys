package DAL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckIn {
	private static Connection connection = DBConnect.getConnection();
	public static int check(String account,String pswd,int identify) throws IOException
	{// 0 密码正确    1 账号不存在       2 密码错误     
		//0 admin  1 team  2 judge
		int result = -1;
		if(identify == 0)
		{
			if(account.equals("admin")&&pswd.equals("admin"))
			{
				result = 0;		//
			}
			else {
				result = 2;   //
			}
		}
		if(identify == 1)
		{
			String password = null;
			Statement stmt;
			ResultSet rs;
			
			try {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("SELECT * FROM team WHERE t_id='" + account+"'");
				while (rs.next()) {
					password = rs.getString("t_password");
				}
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			if(pswd.equals(password)) {
				System.out.println("team succsses!");
				result = 0;
			}					
			else {
				System.out.println("team sorry!");
				if(password == null) {System.out.println("You have to register first!"); result = 1;}
				else {System.out.println("Your password is wrong");result = 2;}
			}
		}
		else if(identify == 2)
		{
			String password = null;
			Statement stmt;
			ResultSet rs;
			
			try {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("select r_phone from referee where r_id=" + "'"+account+"'");
				while (rs.next()) {
					password = rs.getString("r_phone");//id为账号，电话为密码
				}
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			if(pswd.equals(password)) {
				System.out.println("referee succsses!");
				result = 0;
			}		
			else {
				System.out.println("referee sorry!");
				if(password == null) {System.out.println("You have to register first!"); result = 1;}
				else {System.out.println("Your password is wrong");result = 2;}
			}
		}
		return result;
	}
}

