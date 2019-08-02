package UI;
import DAL.*;
import BLL.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;

public class TeamSignUpForm extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField teamnameText;
	private JTextField usernameText;
	private JTextField pswdText;
	private JTextField surePswdText;
	private JButton sureBtn;
	private JButton returnBtn;
	private AdminClient parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamSignUpForm frame = new TeamSignUpForm(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TeamSignUpForm(AdminClient parent) {
		this.parent = parent;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("\u4EE3\u8868\u961F\u62A5\u9053");
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("\u4EE3\u8868\u961F\u540D\u79F0\uFF1A");
		panel_3.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("\u8D26\u53F7\u6CE8\u518C\uFF1A");
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		panel_3.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		panel_3.add(label_1);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(4, 1, 0, 0));
		
		teamnameText = new JTextField();
		teamnameText.setText("a");
		panel_4.add(teamnameText);
		teamnameText.setColumns(10);
		
		usernameText = new JTextField();
		usernameText.setText("b");
		panel_4.add(usernameText);
		usernameText.setColumns(10);
		
		pswdText = new JTextField();
		pswdText.setText("123456");
		panel_4.add(pswdText);
		pswdText.setColumns(10);
		
		surePswdText = new JTextField();
		surePswdText.setText("123456");
		panel_4.add(surePswdText);
		surePswdText.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		sureBtn = new JButton("\u786E\u8BA4\u62A5\u9053");
		panel_2.add(sureBtn);
		sureBtn.addActionListener(this);
		
		returnBtn = new JButton("\u8FD4\u56DE");
		panel_2.add(returnBtn);
		returnBtn.addActionListener(this);
	}

	private boolean signUp(String teamname,String username,String password,String surepswd) {
		if(password.equals(surepswd)&&!teamname.equals("")&&!username.equals("")) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == sureBtn) {
			String teamname = teamnameText.getText();
			String username = usernameText.getText();
			String password = pswdText.getText();
			String surepswd = surePswdText.getText();
//			Connection conn = DBConnect.getConnection();
//			try {
//				String sql = "select * from team where account ='"+username+"'";
//				System.out.println("1");
//				Statement stmt = conn.createStatement();
//				System.out.println("2");
//				ResultSet rs = stmt.executeQuery(sql);
//				System.out.println("3");
//				while(rs.next()) {
//					String pw = rs.getString("password");
//					System.out.println(pw);
//				}
//			}catch(SQLException ex) {
//				System.out.println("ex");
//			}
			
			if(signUp(teamname,username,password,surepswd)) {
				//JOptionPane.showMessageDialog(null, "注册成功！", "成功", JOptionPane.ERROR_MESSAGE);
				SignUpHolder suh = new SignUpHolder(teamname,username,password,surepswd);
				int tag = suh.signUp();
				if(tag == 0) {//注册成功
					int res=JOptionPane.showConfirmDialog(this, "注册成功，是否退出？", "成功", JOptionPane.YES_NO_OPTION);
					 if(res==JOptionPane.YES_OPTION){ //点击“是”后执行这个代码块
						 if(parent!=null) {
							 parent.setVisible(true);
						 }
						 this.dispose();				    
					 }
				}else if(tag == 1 ){//用户名已存在
					JOptionPane.showMessageDialog(null,  "用户名已存在，请重新输入","出错", JOptionPane.ERROR_MESSAGE);
				}else if(tag == -1) {
					System.out.println("SQL Exception");
				}else {//莫名失败
					int res=JOptionPane.showConfirmDialog(this, "注册失败，是否重试？", "失败", JOptionPane.YES_NO_OPTION);
					 if(res==JOptionPane.NO_OPTION){ //点击“否”后执行这个代码块
						 if(parent!=null) {
							 parent.setVisible(true);
						 }
						 this.dispose();				    
					 }
				}			 
			}else {
				int res=JOptionPane.showConfirmDialog(this, "注册失败,两次密码不匹配,或账户不能为空,是否继续？", "失败", JOptionPane.YES_NO_OPTION);
				 if(res==JOptionPane.NO_OPTION){ //点击“否”后执行这个代码块
					 if(parent!=null) {
						 parent.setVisible(true);
					 }
					 this.dispose();				    
				 }
			}			
			teamnameText.setText(null);
			usernameText.setText(null);
			pswdText.setText("123456");
			surePswdText.setText("123456");
		}else if(e.getSource() == returnBtn) {
			if(parent!=null) {
				parent.setVisible(true);
			}
			this.dispose();
		}
	}

}
