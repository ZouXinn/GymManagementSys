package Model;

public class IpAndRole {
	private String ip;
	private int role;
	public IpAndRole(String ip,int role) {
		this.ip = ip;
		this.role = role;
	}
	public String getIP() {
		return ip;
	}
	public int getRole() {
		return role;
	}
}
