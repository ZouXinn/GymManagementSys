package BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.print.attribute.standard.RequestingUserName;

import DAL.DBConnect;
import Model.DataOfProForJudge;
import Model.PlayersForScores;

public class GetPlayerFromPro {
	private DataOfProForJudge myData;
	public GetPlayerFromPro(DataOfProForJudge myData) {
		this.myData = myData;
	}
	public Vector<PlayersForScores> getPlayers(){
		Vector<PlayersForScores> ret = new Vector<PlayersForScores>();
		Connection con = DBConnect.getConnection();
		String sql = "select a_gameID,a_ageGroup,a_sex from athlete natural join athlete_project "+
		"where p_id = ? and groupNum = ? and a_ageGroup = ? order by sequenceNum";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, myData.getP_id());
			ps.setString(2, myData.getGroupNum());
			ps.setString(3, myData.getAgeGroup());
			ResultSet rs = ps.executeQuery();
			while(rs != null && rs.next()) {
				ret.add(new PlayersForScores(rs.getString("a_gameID"), rs.getString("a_ageGroup"), rs.getBoolean("a_sex")));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
		return ret;
	}
}
