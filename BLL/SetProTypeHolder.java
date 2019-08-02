package BLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DAL.*;
public class SetProTypeHolder {
	private int datas[][];//0 男子单杠  1男子双杠 2男子吊环  3男子自由体操 4男子鞍马 5男子蹦床 6男子跳马
	//7女子跳马  8女子高低杠 9女子平衡木 10 女子自由体操 11女子蹦床	
	public SetProTypeHolder(int datas[][]) {
		this.datas = datas;		
	}
	public boolean send() {
		Connection con = DBConnect.getConnection();
		String sql = "update project set p_type = ? where p_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			for(int i = 0 ; i < datas.length ; i++) {
				String type = String.valueOf(datas[i][0])+String.valueOf(datas[i][1])+
						String.valueOf(datas[i][2]);
				ps.setString(1, type);
				ps.setString(2, String.valueOf(i));
				ps.execute();
			}		
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return false;
		}		
		return true;
	}
}
