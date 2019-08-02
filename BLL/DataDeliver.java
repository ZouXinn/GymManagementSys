package BLL;

import java.sql.Connection;

import DAL.CheckIn;

public class DataDeliver {
	private Connection con;
	
	private void func(){
		

	}
	public static void main(String[] args) {
		//CheckIn.check(account, pswd, identify)
		try {
			int result = CheckIn.check("33", "6116", 2);
			System.out.print("result = " + result);
		}catch(Exception e) {		
		}
	}
}
