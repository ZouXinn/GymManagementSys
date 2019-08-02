package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAL.CheckIn;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField passWordText;
	private JTextField userNameText;
	private boolean isAdmin;
	private JButton loginBtn;
	private JButton exitBtn;
	private JComboBox idComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//isAdmin = false;//初始化不是系统管理员
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Engravers MT", Font.BOLD, 25));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLogin, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		JLabel label = new JLabel("\u8EAB\u4EFD\uFF1A");
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		loginBtn = new JButton("\u767B\u5F55");
		loginBtn.addActionListener(this);
		panel_1.add(loginBtn);
		
		exitBtn = new JButton("\u9000\u51FA");
		exitBtn.addActionListener(this);
		panel_1.add(exitBtn);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		userNameText = new JTextField();
		panel_2.add(userNameText);
		userNameText.setColumns(10);
		
		passWordText = new JTextField();
		panel_2.add(passWordText);
		passWordText.setColumns(10);
		
		idComboBox = new JComboBox();
		idComboBox.setModel(new DefaultComboBoxModel(new String[] {"\u7BA1\u7406\u5458", "\u961F\u4F0D", "\u88C1\u5224"}));
		idComboBox.setSelectedIndex(0);
		panel_2.add(idComboBox);
	}
	private boolean check(String username,String pswd,int identify) {
		try {
			int flag = CheckIn.check(username, pswd, identify);
			if(flag==0) {//密码正确
				return true;
			}else {//账号不存在或密码错误
				return false;
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == loginBtn) {//点击了登录
			if(check(userNameText.getText(),passWordText.getText(),idComboBox.getSelectedIndex())) {
				switch(idComboBox.getSelectedIndex()){
				case 0:
					AdminClient ac = new AdminClient();
					ac.setVisible(true);
					this.dispose();
					break;
				case 1:
					TeamClient tc = new TeamClient(userNameText.getText());
					tc.setVisible(true);
					this.dispose();
					break;
				case 2:
					JudgerInitForm jif = new JudgerInitForm(userNameText.getText());
					jif.setVisible(true);
					this.dispose();
					break;
				}
				this.dispose();
			}else {//密码错误				
				JOptionPane.showMessageDialog(null,  "密码错误或账号不存在，请重新输入", "登陆失败",JOptionPane.ERROR_MESSAGE);
				passWordText.setText("");
			}
		}else if(e.getSource() == exitBtn) {//点击了退出
			this.dispose();
			System.exit(0);
		}
	}

}
