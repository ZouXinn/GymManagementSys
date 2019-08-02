package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.DataOfProForJudge;
import Model.IpAndRole;
import Model.PlayersForScores;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import BLL.*;

public class NormalJudgerReadyForm extends JudgerClient implements ActionListener {

	private JPanel contentPane;
	private JButton returnBtn;
	private DataOfProForJudge myData;
	private JudgerInitForm parent;
	private JLabel projectLabel,playersLabel;
	private Vector<PlayersForScores> mPlayersForScoresVect;
	private JudgeIPHolder mJIpHolder;
	private String mMainJudgeIP;
	int role ;
	int[] p_9 = {9996, 9998, 9999};
	int[] p_8 = {8997, 8998, 8999};
	String ip = "";    //此处补全数据库中主裁判ip
	private DatagramSocket socket;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NormalJudgerReadyForm frame = new NormalJudgerReadyForm(null,"normal judger ready",null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public NormalJudgerReadyForm(JudgerInitForm parent,String username,DataOfProForJudge data) throws IOException {
		super(username);
		this.parent =parent;
		myData = data;
		GetPlayerFromPro gpfp = new GetPlayerFromPro(data);
		mPlayersForScoresVect = gpfp.getPlayers();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("\u5206\u88C1\u5224\u60A8\u597D\uFF0C\u8BF7\u786E\u8BA4\u6BD4\u8D5B\u4FE1\u606F");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		panel_1.add(panel_3, BorderLayout.SOUTH);
		
		JLabel label_1 = new JLabel("\u60A8\u5DF2\u5C31\u4F4D\uFF0C\u8BF7\u7B49\u5F85\u603B\u88C1\u5224\u5BA3\u5E03\u6BD4\u8D5B\u5F00\u59CB");
		panel_3.add(label_1);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new GridLayout(2, 1, 0, 0));
		
		projectLabel = new JLabel("");
		projectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(projectLabel);
		
		playersLabel = new JLabel("");
		playersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(playersLabel);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.WEST);
		panel_5.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("\u6BD4\u8D5B\u9879\u76EE\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u8FD0\u52A8\u5458\uFF1A");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		returnBtn = new JButton("\u8FD4\u56DE");
		panel_2.add(returnBtn);
		returnBtn.addActionListener(this);
		initTexts();
		mJIpHolder = new JudgeIPHolder(username, Integer.parseInt(myData.getP_id()), 
				Integer.parseInt(myData.getAgeGroup()), Integer.parseInt(myData.getGroupNum()));
		mJIpHolder.insertIP();
		mMainJudgeIP = mJIpHolder.getMainJudgeIP();
		String message = "enter";
		socket = new DatagramSocket();
		role = mJIpHolder.getMyRole();
		DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, InetAddress.getByName(mMainJudgeIP), p_9[role-1]);
		socket.send(packet);
		socket.close();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mJIpHolder.deleteIP();
				System.exit(0);
			}
		});	
		new Receive().start();
	}
	private void initTexts() {
		String projects = myData.getProName()+" ";
		if(myData.getAgeGroup().equals("0")) {
			projects += "7-8岁 ";
		}else if(myData.getAgeGroup().equals("1")) {
			projects += "9-10岁 ";
		}else if(myData.getAgeGroup().equals("2")) {
			projects += "11-12岁 ";
		}
		projects += myData.getGroupNum()+"组";
		projectLabel.setText(projects);
		if(mPlayersForScoresVect != null) {
			String players = "";
			for(int i = 0 ; i < mPlayersForScoresVect.size() ; i++) {
				players += mPlayersForScoresVect.get(i).getGameID()+" ";
			}
			playersLabel.setText(players);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
		if(e.getSource() == returnBtn) {
			String message = "quit";
			try {
				socket = new DatagramSocket();
				DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, InetAddress.getByName(mMainJudgeIP), p_9[role-1]);
				socket.send(packet);
				socket.close();
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
			if(parent != null) {
				parent.setVisible(true);
			}
			mJIpHolder.deleteIP();//删除自己的ip
			dispose();
		}
	}
	private class Receive extends Thread{//enter quit begin
		public void run() {
			try {
				DatagramSocket socket = new DatagramSocket(p_8[role-1]);//
				DatagramPacket packet = new DatagramPacket(new byte[8192], 8192);
				while(true) {
					socket.receive(packet);
					byte[] arr = packet.getData();
					int len = packet.getLength();
					String message = new String(arr,0,len);
					//point1.setText("裁判1:"+message);
					if(message.equals("begin")) {
						NormalJudgerMarkForm normalJudgerMarkForm = new NormalJudgerMarkForm(parent, username, myData,mMainJudgeIP,role);
						normalJudgerMarkForm.setVisible(true);
						dispose();
					}
				}		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
}
