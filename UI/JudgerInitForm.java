package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BLL.GetProOfJudgeHolder;
import BLL.JudgeIPHolder;
import DAL.DBConnect;
import Model.DataOfProForJudge;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.TextField;

public class JudgerInitForm extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton exitBtn;
	private String username;
	private Vector<DataOfProForJudge> myDataVect;
	private int proAccount;
	private JPanel mPanel;
	private Vector<JTextField> mTexts;
	Vector<JButton> mButtons;
	private boolean []isCheif;//是否是主裁判
	int selectedIndex = -1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JudgerInitForm frame = new JudgerInitForm("aa");
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
	public JudgerInitForm(String username) {
		this.username = username;
		getData();//为myDataRect赋值
		proAccount = myDataVect.size();
		mTexts = new Vector<JTextField>();
		mButtons = new Vector<JButton>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("\u60A8\u9700\u8981\u53C2\u52A0\u7684\u573A\u6B21\u5982\u4E0B\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		mPanel = new JPanel();
		panel_1.add(mPanel, BorderLayout.CENTER);
		//mPanel.setLayout(new GridLayout(0, 1, 0, 0));
		initPanel();
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		exitBtn = new JButton("\u767B\u51FA");
		panel_2.add(exitBtn);
		exitBtn.addActionListener(this);
		setTexts();
	}
	private void setTexts() {
		for(int i = 0 ; i < proAccount ; i++) {
			String proName = myDataVect.get(i).getProName();
			String ageGroup = myDataVect.get(i).getAgeGroup();
			String groupNum = myDataVect.get(i).getGroupNum();
			//String 
			if(ageGroup.equals("0")) {//7-8
				ageGroup = "7-8岁";
			}else if(ageGroup.equals("1")) {//9-10
				ageGroup = "9-10岁";
			}else if(ageGroup.equals("2")) {//11-12
				ageGroup = "11-12岁";
			}
			String ts = groupNum;
			groupNum = "第"+ts+"组";			
			mTexts.get(i).setText(proName+" "+ageGroup+" "+groupNum);
		}
	}
	private void initPanel() {
		mPanel.setLayout(new GridLayout(proAccount, 1));
		System.out.println(proAccount);
		for(int i = 0 ; i < proAccount ; i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			JTextField textField= new JTextField();
			JButton button = new JButton("进入");
			panel.add(textField,BorderLayout.CENTER);
			panel.add(button,BorderLayout.EAST);
			button.addActionListener(this);
			mTexts.add(textField);
			mButtons.add(button);
			mPanel.add(panel);
		}
	}
	private void getData() {
		GetProOfJudgeHolder gpojh = new GetProOfJudgeHolder(username);
		myDataVect = gpojh.getPros();
		isCheif = new boolean[myDataVect.size()];
		int[] roles = new int[myDataVect.size()]; 
		Connection connection = DBConnect.getConnection();
		String sql = "select role from referee_project where p_id = ? and r_gameID = ?"+
		"and groupNum = ? and ageGroup = ?";
		for(int i = 0 ; i < myDataVect.size() ; i++) {
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1,myDataVect.get(i).getP_id());
				ps.setString(2, myDataVect.get(i).getR_gameId());
				ps.setString(3, myDataVect.get(i).getGroupNum());
				ps.setString(4, myDataVect.get(i).getAgeGroup());
				ResultSet rSet = ps.executeQuery();
				if(rSet!=null && rSet.next()) {
					roles[i] = rSet.getInt("role");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for(int i = 0 ; i < roles.length ; i++) {
			isCheif[i] = roles[i]==0?true:false;
			System.out.println(roles[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == exitBtn) {
			dispose();
			System.exit(0);
		}else {
			for(int i = 0 ; i < proAccount ; i++) {
				if(e.getSource() == mButtons.get(i)) {//选中第i个		
					if(isCheif[i]) {//是主裁判
						MainJudgerReadyForm mjrf = new MainJudgerReadyForm(this,username,myDataVect.get(i));
						this.setVisible(false);
						mjrf.setVisible(true);
					}else {//是分裁判
						try {
							JudgeIPHolder jHolder = new JudgeIPHolder(username, Integer.parseInt(myDataVect.get(i).getP_id()), 
									Integer.parseInt(myDataVect.get(i).getAgeGroup()), Integer.parseInt(myDataVect.get(i).getGroupNum()));
							String mainJudgeIp = jHolder.getMainJudgeIP();
							if(mainJudgeIp == null) {
								JOptionPane.showMessageDialog(null, "进入失败", "请等待主裁判进入", JOptionPane.ERROR_MESSAGE);
							}else {
								NormalJudgerReadyForm njrf = new NormalJudgerReadyForm(this,username,myDataVect.get(i));
								this.setVisible(false);
								njrf.setVisible(true);
							}						
						} catch (IOException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
					}
					selectedIndex = i;
				}
			}
		}
	}

}
