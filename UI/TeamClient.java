package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeamClient extends Client implements ActionListener{
	private JPanel contentPane;
	//private String teamName;
	private JButton changeInfoBtn;
	private JButton changePswdBtn;
	private JButton exitBtn;
	private JButton arrangeCheckBtn;
	private JButton seeComNumBtn;
	private JLabel greetLabel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamClient frame = new TeamClient("team");
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
	public TeamClient(String username) {
		super(username);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		greetLabel = new JLabel("欢迎!"+this.username);
		greetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(greetLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		
		changeInfoBtn = new JButton("\u5F55\u5165\u4FE1\u606F");
		panel.add(changeInfoBtn);
		changeInfoBtn.addActionListener(this);
		
		changePswdBtn = new JButton("\u4FEE\u6539\u5BC6\u7801");
		changePswdBtn.addActionListener(this);
		panel.add(changePswdBtn);
		
		arrangeCheckBtn = new JButton("\u8D5B\u7A0B\u5B89\u6392");
		panel.add(arrangeCheckBtn);
		arrangeCheckBtn.addActionListener(this);
		
		seeComNumBtn = new JButton("\u67E5\u770B\u961F\u5458\u8D5B\u53F7");
		panel.add(seeComNumBtn);
		seeComNumBtn.addActionListener(this);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		exitBtn = new JButton("\u9000\u51FA");
		panel_1.add(exitBtn);
		exitBtn.addActionListener(this);
	}
	


	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == changeInfoBtn) {//点击录入信息
			SetInfoForm sif = new SetInfoForm(username,this);
			this.setVisible(false);
			sif.setVisible(true);		
		}else if(e.getSource() == changePswdBtn) {//点击修改密码
			ChangePswd frame = new ChangePswd(username,this);
			frame.setVisible(true);
			this.setVisible(false);		
		}else if(e.getSource()==exitBtn) {//点击退出
			this.dispose();
			System.exit(0);
		}else if(e.getSource()==seeComNumBtn) {//点击查看队员赛号
			ComNumberForm cnf = new ComNumberForm(this,username);
			System.out.println("seeComNumBtn clicked");
			this.setVisible(false);
			cnf.setVisible(true);
		}else {//赛程安排
			TeamArrangeView tav = new TeamArrangeView(this);
			this.setVisible(false);
			tav.setVisible(true);
		}
	}
}
