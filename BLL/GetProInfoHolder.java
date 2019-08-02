package BLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import DAL.*;
public class GetProInfoHolder {
	private int ageGroup,proType;
	//private int tag;
	//private boolean sex;//true boy   false girl
	public GetProInfoHolder(int ageGroup,int proType) {
		//this.sex = sex;
		this.ageGroup = ageGroup;
		this.proType = proType;
	}
	public Vector<String> getPlayerInfo() {//待测试
		Vector<String> playerGroups = new Vector<String>();
		for(int i = 0 ; i < 6 ; i++) {//一个项目最多六组
//			String sql = "select a_gameID from athlete_project natural join athlete natural join project where p_id = '"+
//		String.valueOf(proType)+"' and groupNum = '"+String.valueOf(i)+"' and a_ageGroup = '"+String.valueOf(ageGroup)+"'";
			
			
			//String sql = "select a_gameID from athlete_project natural join athlete natural join project where p_id = ? and groupNum = ? and a_ageGroup = ?";
			String sql = "select a_gameID from athlete_project natural join athlete where p_id = ? and groupNum = ? and ageGroup = ?";
			Connection con = DBConnect.getConnection();
			PreparedStatement ps;
			//Statement st;
			try {
				//st = con.createStatement();
				//ResultSet rs = st.executeQuery(sql);
				ps = con.prepareStatement(sql);
				ps.setString(1, String.valueOf(proType));
				ps.setString(2, String.valueOf(i));
				ps.setString(3, String.valueOf(ageGroup));
				ResultSet rs = ps.executeQuery();
				String showingString = "";
				while(rs != null && rs.next()) {
					showingString += rs.getString("a_gameID")+" ";
					System.out.println("one record:"+showingString);
				}
				playerGroups.add(showingString);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}			
		}
		return playerGroups;
	}
	public Vector<String> getJudgeInfo() {//待测试
		Vector<String> judgeGroups = new Vector<String>();
		for(int i = 0 ; i < 6 ; i++) {
//			String sql = "select r_gameID from referee_project natural join referee natural join project where p_id = '"+
//					String.valueOf(proType)+"' and groupNum = '"+String.valueOf(i)+
//					"' and ageGroup = '"+String.valueOf(ageGroup)+"'"; 
//			String sql = "select r_gameID from referee_project natural join referee where p_id = '"+
//					String.valueOf(proType)+"' and groupNum = '"+String.valueOf(i)+
//					"' and ageGroup = '"+String.valueOf(ageGroup)+"'"; 
			String sql = "select r_gameID from referee_project natural join referee where p_id = ? and groupNum = ? and ageGroup = ?";
			Connection con = DBConnect.getConnection();
			PreparedStatement ps;
			//Statement st;
			try {
				//st = con.createStatement();
				ps = con.prepareStatement(sql);
				//ResultSet rs = st.executeQuery(sql);
				ps.setString(1, String.valueOf(proType));
				ps.setString(2, String.valueOf(i));
				ps.setString(3, String.valueOf(ageGroup));
				ResultSet rs = ps.executeQuery();
				String showingString = "";
				while(rs != null && rs.next()) {
					showingString += rs.getString("r_gameID")+" ";
				}
				judgeGroups.add(showingString);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return judgeGroups;
	}
}
