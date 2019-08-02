package BLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import DAL.*;
import Model.GameNumOfName;
public class GetPlayerIDHolder {
	private String username;
	private Vector<GameNumOfName> dataVect;
	public GetPlayerIDHolder(String username) {
		this.username = username;
		dataVect = new Vector<GameNumOfName>();
	}
	public Vector<GameNumOfName> getNameAndNum() {//未测试
		Connection con = DBConnect.getConnection();
		String sql = "select a_name,a_gameID from athlete natural join team_athlete where t_id = '"+username+"'";
		//String sql = "select a_name,a_gameID from athlete,team_athlete where team_athlete.a_id = athlete.a_id and t_id = '"+username+"'";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			GameNumOfName t = null;
			while(rs.next()) {
				String name = rs.getString("a_name");
				String gameID = rs.getString("a_gameID");
				t = new GameNumOfName(name,gameID);
				dataVect.add(t);
			}
			return dataVect;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return null;
	}
}
