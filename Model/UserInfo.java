package Model;

import java.io.Serializable;

public class UserInfo implements Serializable{
	private int identify;
	private String username;
	private String password;
	private String teamname;
	public UserInfo(int identify,String username,String password) {
		this.identify = identify;
		this.username = username;
		this.password = password;
		this.teamname = null;
	}
	public UserInfo(int identify,String username,String password,String teamname) {
		this.identify = identify;
		this.username = username;
		this.password = password;
		if(identify == 1) {
			this.teamname = teamname;
		}else {
			this.teamname = null;
		}
	}
	public int getIdentify() {
		return identify;
	}
	public String getUsername() {
		return username;
	}
	public String getPswd() {
		return password;
	}
	public String getTeamname() {
		return teamname;
	}
}
