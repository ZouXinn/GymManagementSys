package BLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.text.StyledEditorKit.BoldAction;

import DAL.DBConnect;
import Model.*;
public class GetProOfJudgeHolder {
	//private Vector<DataOfProForJudge> 
	private String username;
	public GetProOfJudgeHolder(String username) {
		this.username = username;
	}
	public Vector<DataOfProForJudge> getPros(){
		Vector<DataOfProForJudge> ret = new Vector<DataOfProForJudge>();
		Connection con = DBConnect.getConnection();
		String sql = "select p_id,p_name,groupNum,ageGroup,r_gameID from project natural join referee_project natural join referee where r_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs != null && rs.next()) {
				DataOfProForJudge t = new DataOfProForJudge(rs.getString("p_id"),rs.getString("p_name"), rs.getString("ageGroup"),  rs.getString("groupNum"),rs.getString("r_gameID"));
				ret.add(t);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}	
		return ret;
	}
//	public boolean isCheif() {
//		Connection con = DBConnect.getConnection();
//		String sql = "select role from referee natural join referee_project where r_id = ?";
//		//String msql = "select role from referee_project"
//		try {
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, username);
//			ResultSet rs = ps.executeQuery();
//			if(rs != null && rs.next()) {
//				String s = rs.getString(0);
//				if(s.equals("1")) {//cheif
//					return true;
//				}else if(s.equals("0")){
//					return false;
//				}else {
//					throw new SQLException("role属性为空或不为0,1，错误!");
//				}
//			}
//		} catch (SQLException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//		return false;
//	}
}
