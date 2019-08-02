package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import BLL.AutoFunction;

public class AdminClient extends Client implements ActionListener{

	private JPanel contentPane;
	private JButton setProTypeBtn;
	private JButton teamSignUpBtn;
	private JButton judgerArrangeBtn;
	private JButton exitBtn;
	private JButton playerGameNumArrBtn;
	private JButton checkRankBtn;
	private JButton dividualGradeBtn;
	private JButton judgeNumArrBtn;
	private JButton player_pro_connect_btn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminClient frame = new AdminClient();
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
	public AdminClient() {
		super("admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("\u4F60\u597D\uFF0C\u7BA1\u7406\u5458");
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(3, 3, 0, 0));
		
		setProTypeBtn = new JButton("\u6BD4\u8D5B\u7C7B\u578B\u8BBE\u7F6E");
		panel_1.add(setProTypeBtn);
		setProTypeBtn.addActionListener(this);
		
		teamSignUpBtn = new JButton("\u4EE3\u8868\u961F\u62A5\u9053");
		panel_1.add(teamSignUpBtn);
		teamSignUpBtn.addActionListener(this);
		
		
		
		playerGameNumArrBtn = new JButton("\u8D5B\u53F7\u5B89\u6392");
		panel_1.add(playerGameNumArrBtn);
		playerGameNumArrBtn.addActionListener(this);
		
		judgeNumArrBtn = new JButton("裁判号安排");
		panel_1.add(judgeNumArrBtn);
		judgeNumArrBtn.addActionListener(this);
		
		player_pro_connect_btn = new JButton("运动员分组");
		panel_1.add(player_pro_connect_btn);
		player_pro_connect_btn.addActionListener(this);
		
		judgerArrangeBtn = new JButton("裁判员分配");
		panel_1.add(judgerArrangeBtn);
		judgerArrangeBtn.addActionListener(this);
		
		checkRankBtn = new JButton("\u6392\u540D\u67E5\u770B");
		panel_1.add(checkRankBtn);
		checkRankBtn.addActionListener(this);
		
		dividualGradeBtn = new JButton("\u4E2A\u4EBA\u5168\u80FD\u6210\u7EE9\u67E5\u8BE2");
		panel_1.add(dividualGradeBtn);
		dividualGradeBtn.addActionListener(this);
		
		exitBtn = new JButton("\u9000\u51FA");
		panel_1.add(exitBtn);
		exitBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitBtn) {
			this.dispose();
			System.exit(0);
		}else if(e.getSource() == setProTypeBtn) {
			SetProTypeForm sptf = new SetProTypeForm(this);
			this.setVisible(false);
			sptf.setVisible(true);
		}else if(e.getSource() ==teamSignUpBtn) {
			TeamSignUpForm tsuf= new TeamSignUpForm(this);
			tsuf.setVisible(true);
			this.setVisible(false);
		}else if(e.getSource() == judgerArrangeBtn) {//裁判分到项目组
			try {
				AutoFunction.allocateRefereeToGtoup();
				JOptionPane.showConfirmDialog(null, "成功!");
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null,  "分配失败！", "失败",JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource() == playerGameNumArrBtn) {//赛号分配
			try {
				AutoFunction.autoSorta_id();
				JOptionPane.showConfirmDialog(null, "成功!");
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null,  "分配失败！", "失败",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == judgeNumArrBtn) {//裁判号分配
			try {
				AutoFunction.allocateRefereeGameID();
				JOptionPane.showConfirmDialog(null, "成功!");
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null,  "分配失败！", "失败",JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource() == player_pro_connect_btn) {//运动员自动分组
			try {
				AutoFunction.allocateAthleteToGroup();
				JOptionPane.showConfirmDialog(null, "成功!");
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null,  "分配失败！", "失败",JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource() == checkRankBtn) {//查看不同类别的团体或个人排名
			ShowRankForm srf = new ShowRankForm(this);
			this.setVisible(false);
			srf.setVisible(true);
		}else if(e.getSource() == dividualGradeBtn) {//查看个人全能成绩
			ShowPersonalRankForm sprf = new ShowPersonalRankForm(this);
			this.setVisible(false);
			sprf.setVisible(true);
		}
	}

}
