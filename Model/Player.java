package Model;

import java.io.Serializable;

public class Player implements Serializable{
	private String name;
	private boolean sex;//true表示男  false表示女
	private int age;
	private String IDNumber;
	private boolean[] pros; //boy   单杠 	双杠 	吊环  	跳马 	自由体操		 鞍马	 蹦床    	按顺序
							//girl 	跳马 	高低杠  	自由体操 	平衡木 	蹦床           					按顺序
	public Player(String name,boolean sex,int age,String IDNumber,boolean[] pros) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.IDNumber = IDNumber;
		this.pros = pros;
	}
	public String getName() {
		return name;
	}
//	public void setName(String name) {
//		this.name = name;
//	}
	public boolean getSex() {
		return sex;
	}
//	public void setSex(boolean sex) {
//		this.sex = sex;
//	}
	public int getAge() {
		return age;
	}
//	public void setAge(int age) {
//		this.age = age;
//	}
	public String getIDNumber() {
		return IDNumber;
	}
//	public void setIDNumber(String IDNumber) {
//		this.IDNumber = IDNumber;
//	}
	public boolean[] getPros() {
		return pros;
	}
//	public void setPros(boolean[] pros) {
//		this.pros = pros;
//	}
}
