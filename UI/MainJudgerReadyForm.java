package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.GetPlayerFromPro;
import BLL.JudgeIPHolder;
import Model.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class MainJudgerReadyForm extends JudgerClient implements ActionListener{

	private JPanel contentPane;
	private JButton beginBtn;
	private JButton returnBtn;
	private DataOfProForJudge myData;
	private JudgerInitForm parent;
	private JLabel projectLabel,playersLabel,readyJudgersLabel;
	private Vector<PlayersForScores> mPlayersForScoresVect;
	int readyJudgeNum = 0;
	private DatagramSocket socket1;
	private DatagramSocket socket2;
	private DatagramSocket socket3;
	private DatagramPacket packet1;
	private DatagramPacket packet2;
	private DatagramPacket packet3;
	private String[] ips;
	private int[] roles;
	int[] p_9 = {9996, 9998, 9999};
	int[] p_8 = {8997, 8998, 8999};
	private JudgeIPHolder mJIpHolder;
	private Receive thread1;
	private Receive2 thread2;
	private Receive3 thread3;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJudgerReadyForm frame = new MainJudgerReadyForm(null,"main judger ready",null);
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
	public MainJudgerReadyForm(JudgerInitForm parent,String username,DataOfProForJudge data) {
		super(username);
		this.parent = parent;
		myData = data;
		ips = new String[3];
		roles = new int[3];
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
		
		JLabel label = new JLabel("\u4E3B\u88C1\u5224\u60A8\u597D\uFF0C\u8BF7\u786E\u8BA4\u6BD4\u8D5B\u4FE1\u606F\uFF01");
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(3, 1, 0, 0));
		
		projectLabel = new JLabel("");
		projectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(projectLabel);
		
		playersLabel = new JLabel("");
		playersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(playersLabel);
		
		readyJudgersLabel = new JLabel("");
		readyJudgersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(readyJudgersLabel);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("\u6BD4\u8D5B\u9879\u76EE\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u8FD0\u52A8\u5458\uFF1A");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5DF2\u5C31\u4F4D\u88C1\u5224\u6570\uFF1A");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		
		beginBtn = new JButton("\u5F00\u59CB\u6BD4\u8D5B");
		panel_2.add(beginBtn);
		beginBtn.addActionListener(this);
		
		returnBtn = new JButton("\u8FD4\u56DE");
		panel_2.add(returnBtn);
		returnBtn.addActionListener(this);
		//读取数据库对ports和ips进行初始化
		mJIpHolder = new JudgeIPHolder(username, Integer.parseInt(myData.getP_id()), 
				Integer.parseInt(myData.getAgeGroup()), Integer.parseInt(myData.getGroupNum()));
		mJIpHolder.insertIP();	
		updateIPsAndRoles();
		initTexts();
		thread1 = new Receive();
		thread1.start();
		thread2 = new Receive2();
		thread2.start();
		thread3 = new Receive3();
		thread3.start();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mJIpHolder.deleteIP();
				System.exit(0);
			}
		});	
		readyJudgersLabel.setText("0");
	}
	private void updateIPsAndRoles() {
		IpAndRole[] ipAndRoles = mJIpHolder.getOtherJudgesIPAndRole();
		for(int i = 0 ; i < ipAndRoles.length ; i++) {
			if(ipAndRoles[i]!=null) {
				ips[i] = ipAndRoles[i].getIP();
				roles[i] = ipAndRoles[i].getRole();
			}			
		}
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
		if(e.getSource() == beginBtn) {
			if(readyJudgeNum == 3) {//readyJudgeNum == 3
				updateIPsAndRoles();
				String bg = "begin";
				try {
					socket1 = new DatagramSocket();
					socket2 = new DatagramSocket();
					socket3 = new DatagramSocket();
					//int[] p = {8997,8998,8999};
				
					packet1 = new DatagramPacket(bg.getBytes(), bg.getBytes().length, InetAddress.getByName(ips[0]), p_8[roles[0]-1]);
					packet2 = new DatagramPacket(bg.getBytes(), bg.getBytes().length, InetAddress.getByName(ips[1]), p_8[roles[1]-1]);
					packet3 = new DatagramPacket(bg.getBytes(), bg.getBytes().length, InetAddress.getByName(ips[2]), p_8[roles[2]-1]);
					socket1.send(packet1);
					socket2.send(packet2);
					socket3.send(packet3);	
					
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				thread1.interrupt();
				thread2.interrupt();
				thread3.interrupt();
				MainJudgerMarkForm mjmf = new MainJudgerMarkForm(this.parent,username,myData,ips,roles);
				mjmf.setVisible(true);
				turnOff();
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "开始失败", "请等待3个分裁判全部准备", JOptionPane.ERROR_MESSAGE);
			}			
		}else if(e.getSource() == returnBtn) {
			if(parent != null) {
				parent.setVisible(true);
			}
			mJIpHolder.deleteIP();
			//给分裁判发送离开的消息？还是不发？
			dispose();
		}
	}

	private void turnOff() {
		// TODO 自动生成的方法存根
		socket1.close();
		socket2.close();
		socket3.close();
	}

	private class Receive extends Thread{//enter quit begin
		public void run() {
			try {
				DatagramSocket socket = new DatagramSocket(p_9[roles[0]-1]);
				DatagramPacket packet = new DatagramPacket(new byte[8192], 8192);
				while(true) {
					if(Thread.currentThread().isInterrupted()) {
						socket.close();
						break;
					}
					socket.receive(packet);
					byte[] arr = packet.getData();
					int len = packet.getLength();
					String message = new String(arr,0,len);
					//point1.setText("裁判1:"+message);
					if(message.equals("enter")) {
						readyJudgeNum++;
						readyJudgersLabel.setText(String.valueOf(readyJudgeNum));
					}else if(message.equals("quit")) {
						readyJudgeNum--;
						readyJudgersLabel.setText(String.valueOf(readyJudgeNum));
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	private class Receive2 extends Thread{
		public void run() {
			try {
				DatagramSocket socket = new DatagramSocket(p_9[roles[1]-1]);
				DatagramPacket packet = new DatagramPacket(new byte[8192], 8192);
				while(true) {
					if(Thread.currentThread().isInterrupted()) {
						socket.close();
						break;
					}
					socket.receive(packet);
					byte[] arr = packet.getData();
					int len = packet.getLength();
					String message = new String(arr,0,len);
					//point1.setText("裁判1:"+message);
					if(message.equals("enter")) {
						readyJudgeNum++;
						readyJudgersLabel.setText(String.valueOf(readyJudgeNum));
					}else if(message.equals("quit")) {
						readyJudgeNum--;
						readyJudgersLabel.setText(String.valueOf(readyJudgeNum));
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	private class Receive3 extends Thread{
		public void run() {
			try {
				DatagramSocket socket = new DatagramSocket(p_9[roles[2]-1]);
				DatagramPacket packet = new DatagramPacket(new byte[8192], 8192);
				while(true) {
					if(Thread.currentThread().isInterrupted()) {
						socket.close();
						break;
					}
					socket.receive(packet);
					byte[] arr = packet.getData();
					int len = packet.getLength();
					String message = new String(arr,0,len);
					//point1.setText("裁判1:"+message);
					if(message.equals("enter")) {
						readyJudgeNum++;
						readyJudgersLabel.setText(String.valueOf(readyJudgeNum));
					}else if(message.equals("quit")) {
						readyJudgeNum--;
						readyJudgersLabel.setText(String.valueOf(readyJudgeNum));
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
}
