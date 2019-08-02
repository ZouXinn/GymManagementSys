package BLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAL.*;
import Model.DataOfProForJudge;
public class PushScoreHolder {
	private String a_gameID;
	private String p_id;
	//private String r_id;
	private String score;
	private String score1;
	private String score2;
	private String score3;
	private int score1Role,score2Role,score3Role;
	private String r1_gameID,r2_gameID,r3_gameID;
	private String r_gameID;
	private DataOfProForJudge myData;
	public PushScoreHolder(DataOfProForJudge myData,String p_id,String a_gameID,String r_gameID,int role1,int role2,int role3,
			String score,String score1,String score2,String score3) {
		this.myData = myData;
		this.a_gameID = a_gameID;
		this.p_id = p_id;
		this.r_gameID = r_gameID;
		this.score = score;
		this.score1Role = role1;
		this.score2Role = role2;
		this.score3Role = role3;
		this.score1 = score1;
		this.score2 = score2;
		this.score3 = score3;
		getr_123_gameID();
	}
	private void getr_123_gameID() {
		Connection connection = DBConnect.getConnection();
		String sql = "select r_gameID,role from referee_project where p_id = ? and ageGroup = ? and "+
		"groupNum = ? and role <> ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, p_id);
			preparedStatement.setString(2, myData.getAgeGroup());
			preparedStatement.setString(3, myData.getGroupNum());
			preparedStatement.setInt(4, 0);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet != null && resultSet.next()) {
				int tRole = resultSet.getInt("role");
				if(tRole == score1Role) {
					r1_gameID = resultSet.getString("r_gameID");
				}else if(tRole == score2Role) {
					r2_gameID = resultSet.getString("r_gameID");
				}else if(tRole == score3Role) {
					r3_gameID = resultSet.getString("r_gameID");
				}
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void pushScore() {
		Connection connection = DBConnect.getConnection();
		String sql = "insert into athlete_score(a_gameID,p_id,r_gameID,score) values(?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, a_gameID);
			preparedStatement.setString(2, p_id);
			preparedStatement.setString(3, r_gameID);
			preparedStatement.setString(4, score);
			preparedStatement.execute();
			preparedStatement.setString(3, r1_gameID);
			preparedStatement.setString(4, score1);
			preparedStatement.execute();
			preparedStatement.setString(3, r2_gameID);
			preparedStatement.setString(4, score2);
			preparedStatement.execute();
			preparedStatement.setString(3, r3_gameID);
			preparedStatement.setString(4, score3);
			preparedStatement.execute();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
