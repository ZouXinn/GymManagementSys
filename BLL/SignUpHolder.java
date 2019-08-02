package BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAL.*;

public class SignUpHolder {//处理账号注册信息
	private String teamName,userName,passWord,surePswd;
	public SignUpHolder(String teamName,String userName,String passWord,String surePswd) {
		this.teamName = teamName;
		this.userName = userName;
		this.passWord = passWord;
		this.surePswd = surePswd;		
	}
	public int signUp() {//0 成功  1 用户名重复  2失败
		SqlDBHelper helper = new SqlDBHelper();
		String checkSql = "select * from team where t_id = '"+userName+"'";
		String insertSql = "insert into team (t_name,t_id,t_password) values(?,?,?)";//代表队插入
		ResultSet rs = helper.select(checkSql);
		try {
			if(rs != null && !rs.next()) {//结果集为空，即用户名未重复，可以使用
				Connection conn = DBConnect.getConnection();
				PreparedStatement ps = conn.prepareStatement(insertSql);
				ps.setString(1, teamName);
				ps.setString(2, userName);
				ps.setString(3, passWord);
				try{
				ps.execute();
				return 0;
				}catch(SQLException ex) {
					ex.printStackTrace();
					return 2;
				}									
			}else {//用户名已存在
				return 1;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return -1;
		}		
		
	}
	
}
