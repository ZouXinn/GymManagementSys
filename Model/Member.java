package Model;

public class Member {
	protected String name;
	protected boolean sex;
	protected String id;
	protected String phone;
	public Member(String name,boolean sex,String id,String phone) {
		this.name = name;
		this.sex = sex;
		this.id = id;
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public boolean getSex() {
		return sex;
	}
	public String getID() {
		return id;
	}
	public String getPhone() {
		return phone;
	}
}
