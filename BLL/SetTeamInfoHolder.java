package BLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import DAL.*;
import Model.*;
public class SetTeamInfoHolder {//队伍、运动员信息
	private String username;
	private Coach coach;
	private Judge judge;
	private Doctor doctor;
	private Leader leader;
	private Vector<Player> boyPlayers,girlPlayers;
	public SetTeamInfoHolder(Leader leader,Doctor doctor,Coach coach,Judge judge,
			Vector<Player> boyPlayers,Vector<Player> girlPlayers,String username) {
		this.boyPlayers = boyPlayers;
		this.girlPlayers = girlPlayers;
		this.coach = coach;
		this.judge = judge;
		this.doctor = doctor;
		this.leader = leader;
		this.username = username;
	}
	public void send() {
		sendOthers();
		sendPlayer();
	}
	private void sendOthers() {//0 leader  1 doctor 2 coach 
		//应该没问题了
		Connection con = DBConnect.getConnection();
		String team_otherSql = "insert into team_otherpp (t_id,m_id) values(?,?)";
		String otherppSql = "insert into otherpp(m_name,m_id,m_sex,m_phone,m_role) values(?,?,?,?,?)";
		
		String judgeSql = "insert into referee (r_name,r_id,r_sex,r_phone) values(?,?,?,?)";
		String team_judgeSql = "insert into team_referee (t_id,r_id) values(?,?)";
		try {
			PreparedStatement ps = null;
			//leader
			ps = con.prepareStatement(otherppSql);
			ps.setString(1, leader.getName());
			ps.setString(2, leader.getID());
			ps.setBoolean(3, leader.getSex());
			ps.setString(4, leader.getPhone());
			ps.setString(5, "0");
			ps.execute();
			ps.close();
			ps = con.prepareStatement(team_otherSql);
			ps.setString(1, username);
			ps.setString(2, leader.getID());
			ps.execute();
			ps.close();
			//doctor
			ps = con.prepareStatement(otherppSql);
			ps.setString(1, doctor.getName());
			ps.setString(2, doctor.getID());
			ps.setBoolean(3, doctor.getSex());
			ps.setString(4, doctor.getPhone());
			ps.setString(5, "1");
			ps.execute();
			ps.close();
			ps = con.prepareStatement(team_otherSql);
			ps.setString(1, username);
			ps.setString(2, doctor.getID());
			ps.execute();
			ps.close();
			//coach
			ps = con.prepareStatement(otherppSql);
			ps.setString(1, coach.getName());
			ps.setString(2, coach.getID());
			ps.setBoolean(3, coach.getSex());
			ps.setString(4, coach.getPhone());
			ps.setString(5, "2");
			ps.execute();
			ps.close();
			ps = con.prepareStatement(team_otherSql);
			ps.setString(1, username);
			ps.setString(2, coach.getID());
			ps.execute();
			ps.close();
			//judge
			ps = con.prepareStatement(judgeSql);
			ps.setString(1, judge.getName());
			ps.setString(2, judge.getID());
			ps.setBoolean(3, judge.getSex());
			ps.setString(4, judge.getPhone());
			System.out.println("judge phone:"+judge.getPhone());
			ps.execute();
			ps.close();
			ps = con.prepareStatement(team_judgeSql);
			ps.setString(1, username);
			ps.setString(2, coach.getID());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	private void sendPlayer() {
		/*		问题：[女生没有？或者说是最后一个运动员没有？]  
		 * 		
		 * 
		 * */
		
		String sql= "insert into athlete (a_id,a_name,a_sex,a_age,a_ageGroup) values(?,?,?,?,?) ";
		String player_project = "insert into athlete_project (a_id,p_id,ageGroup) values(?,?,?)";
		String player_team = "insert into team_athlete (t_id,a_id) values(?,?)";
		Connection con = DBConnect.getConnection();
		try {
			PreparedStatement ps = null;
			while(!boyPlayers.isEmpty()) {
				ps = con.prepareStatement(sql);
				Player t = boyPlayers.remove(boyPlayers.size()-1);
				boolean[] pros = t.getPros();
				ps.setString(1, t.getIDNumber());
				ps.setString(2, t.getName());
				ps.setBoolean(3, t.getSex());
				ps.setString(4, String.valueOf(t.getAge()));
				String group = "";
				if(t.getAge()>=7&&t.getAge()<=8) {
					group = "0";//7-8
				}else if(t.getAge()>=9&&t.getAge()<=10) {
					group = "1";//9-10
				}else {
					group = "2";//11-12
				}
				ps.setString(5, group);
				ps.execute();
				ps.close();				
				for(int i = 0 ; i < pros.length ; i++) {
					if(pros[i] == true) {
						ps = con.prepareStatement(player_project);
						ps.setString(1, t.getIDNumber());
						ps.setString(2, String.valueOf(i));
						ps.setString(3, group);
						ps.execute();
						ps.close();
					}
				}
				ps = con.prepareStatement(player_team);
				ps.setString(1, username);
				ps.setString(2, t.getIDNumber());
				ps.execute();
				ps.close();
			}
			while(!girlPlayers.isEmpty()) {
				ps = con.prepareStatement(sql);
				Player t = girlPlayers.remove(girlPlayers.size()-1);
				boolean[] pros = t.getPros();
				ps.setString(1, t.getIDNumber());
				ps.setString(2, t.getName());
				ps.setBoolean(3, t.getSex());
				ps.setString(4, String.valueOf(t.getAge()));
				String group = "";
				if(t.getAge()>=7&&t.getAge()<=8) {
					group = "0";//7-8
				}else if(t.getAge()>=9&&t.getAge()<=10) {
					group = "1";//9-10
				}else {
					group = "2";//11-12
				}
				ps.setString(5, group);
				ps.execute();
				ps.close();	
				System.out.println("girl inserted in athlete");
				for(int i = 0 ; i < pros.length ; i++) {
					if(pros[i] == true) {
						ps = con.prepareStatement(player_project);
						ps.setString(1, t.getIDNumber());
						ps.setString(2, String.valueOf(i+7));
						ps.setString(3, group);
						ps.execute();
						ps.close();
						System.out.println("girl inserted in athlete_project");
					}
				}
				ps = con.prepareStatement(player_team);
				ps.setString(1, username);
				ps.setString(2, t.getIDNumber());
				ps.execute();
				ps.close();
				System.out.println("girl inserted in athlete_team");
			}		
		}catch(SQLException ex) {
			
		}
	}
	
}
