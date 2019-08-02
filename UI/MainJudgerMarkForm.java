package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.GetPlayerFromPro;
import BLL.JudgeIPHolder;
import BLL.PushScoreHolder;
import Model.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;

public class MainJudgerMarkForm extends JudgerClient implements ActionListener{

	private JPanel contentPane;
	private JTextField scoreDText;
	private JTextField scorePText;
	private JLabel projectLabel,playersLabel,currentPlayerLabel,score1Label,score2Label,score3Label;
	private JButton back1Btn;
	private JButton back2Btn;
	private JButton back3Btn;
	private JButton sureBtn;
	private double score1 = -1,score2 = -1,score3 = -1;
	private JudgerInitForm parent;
	private DataOfProForJudge myData;
	private Vector<PlayersForScores> mPlayersForScoresVect;
	private int playerIndex = 0;
	private String[] ips;
	private int[] roles;
	int[] p_7 = {7997, 7998, 7999};
	int[] p_6 = {6997, 6998, 6999};
	private JudgeIPHolder mJIpHolder;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJudgerMarkForm frame = new MainJudgerMarkForm(null,"main Judger",null,null,null);
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
	public MainJudgerMarkForm(JudgerInitForm parent,String username,DataOfProForJudge myData,String[] ips,int[] roles) {
		super(username);
		this.parent = parent;
		this.myData = myData;
		this.ips = ips;
		this.roles = roles;
		GetPlayerFromPro gpfp = new GetPlayerFromPro(myData);
		mPlayersForScoresVect = gpfp.getPlayers();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("\u4E3B\u88C1\u5224\u60A8\u597D\uFF0C\u6BD4\u8D5B\u5DF2\u6B63\u5F0F\u5F00\u59CB\uFF01");
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 5, 0, 0));
		
		JLabel lblD = new JLabel("D:");
		lblD.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblD);
		
		scoreDText = new JTextField();
		panel_1.add(scoreDText);
		scoreDText.setColumns(10);
		
		JLabel lblP = new JLabel("P:");
		lblP.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblP);
		
		scorePText = new JTextField();
		panel_1.add(scorePText);
		scorePText.setColumns(10);
		
		sureBtn = new JButton("\u63D0\u4EA4");
		panel_1.add(sureBtn);
		sureBtn.addActionListener(this);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new GridLayout(6, 0, 0, 0));
		
		JLabel label_1 = new JLabel("\u6BD4\u8D5B\u9879\u76EE\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(label_1);
		
		JLabel lblNewLabel = new JLabel("\u8FD0\u52A8\u5458\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5F53\u524D\u8FD0\u52A8\u5458\uFF1A");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u88C1\u52241\uFF1A");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u88C1\u52242\uFF1A");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u88C1\u52243\uFF1A");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_4);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new GridLayout(3, 1, 0, 0));
		
		projectLabel = new JLabel("");
		panel_5.add(projectLabel);
		
		playersLabel = new JLabel("");
		panel_5.add(playersLabel);
		
		currentPlayerLabel = new JLabel("");
		panel_5.add(currentPlayerLabel);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new GridLayout(3, 1, 0, 0));
		
		score1Label = new JLabel("");
		panel_7.add(score1Label);
		
		score2Label = new JLabel("");
		panel_7.add(score2Label);
		
		score3Label = new JLabel("");
		panel_7.add(score3Label);
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.EAST);
		panel_8.setLayout(new GridLayout(3, 1, 0, 0));
		
		mJIpHolder = new JudgeIPHolder(username, Integer.parseInt(myData.getP_id()), 
				Integer.parseInt(myData.getAgeGroup()), Integer.parseInt(myData.getGroupNum()));
		
		back1Btn = new JButton("\u6253\u56DE");
		panel_8.add(back1Btn);
		back1Btn.addActionListener(this);
		
		back2Btn = new JButton("\u6253\u56DE");
		panel_8.add(back2Btn);
		back2Btn.addActionListener(this);
		
		back3Btn = new JButton("\u6253\u56DE");
		panel_8.add(back3Btn);
		back3Btn.addActionListener(this);
		initTexts();
		updateIPsAndRoles();
		currentPlayerLabel.setText(mPlayersForScoresVect.get(playerIndex).getGameID());
		new Receive().start();
		new Receive2().start();
		new Receive3().start();
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
	private void updateIPsAndRoles() {
		IpAndRole[] ipAndRoles = mJIpHolder.getOtherJudgesIPAndRole();
		for(int i = 0 ; i < ipAndRoles.length ; i++) {
			ips[i] = ipAndRoles[i].getIP();
			roles[i] = ipAndRoles[i].getRole();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == back1Btn) {
			String back = "back";
			try {
				DatagramSocket socket1 = new DatagramSocket();
				DatagramPacket packet1 = new DatagramPacket(back.getBytes(), back.getBytes().length, InetAddress.getByName(ips[0]), p_6[roles[0]-1]);
				socket1.send(packet1);
				score1Label.setText("");
			} catch (SocketException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}catch (UnknownHostException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}catch (IOException e3) {
				// TODO: handle exception
				e3.printStackTrace();
			}
		}else if(e.getSource() == back2Btn) {
			String back = "back";
			try {
				DatagramSocket socket2 = new DatagramSocket();
				DatagramPacket packet2 = new DatagramPacket(back.getBytes(), back.getBytes().length, InetAddress.getByName(ips[1]), p_6[roles[1]-1]);
				socket2.send(packet2);
				score2Label.setText("");
			} catch (SocketException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}catch (UnknownHostException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}catch (IOException e3) {
				// TODO: handle exception
				e3.printStackTrace();
			}
			
		}else if(e.getSource() == back3Btn) {
			String back = "back";
			try {
				DatagramSocket socket3 = new DatagramSocket();
				DatagramPacket packet3 = new DatagramPacket(back.getBytes(), back.getBytes().length, InetAddress.getByName(ips[2]), p_6[roles[2]-1]);
				socket3.send(packet3);
				score3Label.setText("");
			} catch (SocketException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}catch (UnknownHostException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}catch (IOException e3) {
				// TODO: handle exception
				e3.printStackTrace();
			}
		}else if(e.getSource() == sureBtn) {
			String back = "next";
			double dScore1=0.0,dScore2=0.0,dScore3=0.0,pDouble=0.0,dDouble=0.0;
			try {
				dScore1 = Double.parseDouble(score1Label.getText());
				dScore2 = Double.parseDouble(score2Label.getText());
				dScore3 = Double.parseDouble(score3Label.getText());
				String pString = scorePText.getText(),dString = scoreDText.getText();
				pDouble = Double.parseDouble(pString);
				dDouble = Double.parseDouble(dString);
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "请确定是否有三个给分，以及给分格式是否正确", "提交失败", JOptionPane.ERROR_MESSAGE);
				return;
			}
//			try {
//				DatagramSocket socket3 = new DatagramSocket();
//				DatagramPacket packet3 = new DatagramPacket(back.getBytes(), back.getBytes().length, InetAddress.getByName(ips[2]), p_6[roles[2]-1]);
//				socket3.send(packet3);
//			} catch (SocketException e1) {
//				// TODO 自动生成的 catch 块
//				e1.printStackTrace();
//			}catch (UnknownHostException e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}catch (IOException e3) {
//				// TODO: handle exception
//				e3.printStackTrace();
//			}
			//提交到数据库
			PlayersForScores playersForScores = mPlayersForScoresVect.get(playerIndex);
			double dScore = dDouble-pDouble;
			PushScoreHolder pushScoreHolder = new PushScoreHolder(myData,myData.getP_id(), playersForScores.getGameID(),
					myData.getR_gameId(), roles[0],roles[1],roles[2],String.valueOf(dScore),String.valueOf(dScore1),
					String.valueOf(dScore2),String.valueOf(dScore3));
//			PushScoreHolder pushScoreHolder = new PushScoreHolder(myData,myData.getP_id(), playersForScores.getGameID(),
//					myData.getR_gameId(), roles[0],roles[1],roles[2],"0","1","2","3");
			pushScoreHolder.pushScore();
			//给分裁判发消息"next"
			String next = "next";
			try {
				DatagramSocket socket1 = new DatagramSocket();
				DatagramPacket packet1 = new DatagramPacket(back.getBytes(), back.getBytes().length, InetAddress.getByName(ips[0]), p_6[roles[0]-1]);
				socket1.send(packet1);
				DatagramSocket socket2 = new DatagramSocket();
				DatagramPacket packet2 = new DatagramPacket(back.getBytes(), back.getBytes().length, InetAddress.getByName(ips[1]), p_6[roles[1]-1]);
				socket1.send(packet2);
				DatagramSocket socket3 = new DatagramSocket();
				DatagramPacket packet3 = new DatagramPacket(back.getBytes(), back.getBytes().length, InetAddress.getByName(ips[2]), p_6[roles[2]-1]);
				socket1.send(packet3);
				socket1.close();
				socket2.close();
				socket3.close();
			} catch (SocketException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}catch (UnknownHostException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}catch (IOException e3) {
				// TODO: handle exception
				e3.printStackTrace();
			}	
			playerIndex++;
			if(playerIndex < mPlayersForScoresVect.size()) {
				currentPlayerLabel.setText(mPlayersForScoresVect.get(playerIndex).getGameID());
				score1Label.setText("");
				score2Label.setText("");
				score3Label.setText("");
				scoreDText.setText("");
				scorePText.setText("");
			}else {//finished
				parent.mButtons.get(parent.selectedIndex).setText("finished");
				parent.mButtons.get(parent.selectedIndex).setEnabled(false);
				parent.setVisible(true);
				parent.selectedIndex = -1;
				this.dispose();
			}		
		}
	}
	
	private class Receive extends Thread{//enter quit begin
		public void run() {
			while(true) {
				try {
					DatagramSocket socket = new DatagramSocket(p_7[roles[0]-1]);
					DatagramPacket packet = new DatagramPacket(new byte[8192], 8192);
					while(true) {
						socket.receive(packet);
						byte[] arr = packet.getData();
						int len = packet.getLength();
						String message = new String(arr,0,len);
						score1 = Double.parseDouble(message);
						score1Label.setText(message);
					}				
				}catch (NumberFormatException e) {
					// TODO: handle exception
					//发送信息告诉分裁判发送正确的形式
					String wrong = "wrong";
					try {
						DatagramSocket socket1 = new DatagramSocket();
						DatagramPacket packet1 = new DatagramPacket(wrong.getBytes(), wrong.getBytes().length, InetAddress.getByName(ips[0]), p_6[roles[0]-1]);
						socket1.send(packet1);
					} catch (SocketException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}catch (UnknownHostException e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}catch (IOException e3) {
						// TODO: handle exception
						e3.printStackTrace();
					}
					score1 = -1;
				}catch (SocketException e) {
					System.out.println("Create Socket Failed!");
					e.printStackTrace();
					System.exit(1);
				}
				catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
			
		}	
	}
	private class Receive2 extends Thread{
		public void run() {
			while(true) {
				try {
					DatagramSocket socket = new DatagramSocket(p_7[roles[1]-1]);
					DatagramPacket packet = new DatagramPacket(new byte[8192], 8192);
					while(true) {
						socket.receive(packet);
						byte[] arr = packet.getData();
						int len = packet.getLength();
						String message = new String(arr,0,len);
						score2 = Double.parseDouble(message);
						score2Label.setText(message);
					}				
				}catch (NumberFormatException e) {
					// TODO: handle exception
					//发送信息告诉分裁判发送正确的形式
					String wrong = "wrong";
					try {
						DatagramSocket socket2 = new DatagramSocket();
						DatagramPacket packet2 = new DatagramPacket(wrong.getBytes(), wrong.getBytes().length, InetAddress.getByName(ips[1]), p_6[roles[1]-1]);
						socket2.send(packet2);
					} catch (SocketException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}catch (UnknownHostException e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}catch (IOException e3) {
						// TODO: handle exception
						e3.printStackTrace();
					}
					score2 = -1;
				}catch (SocketException e) {
					System.out.println("Create Socket Failed!");
					e.printStackTrace();
					System.exit(1);
				}
				catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
			
		}	
	}
	private class Receive3 extends Thread{
		public void run() {
			while(true) {
				try {
					DatagramSocket socket = new DatagramSocket(p_7[roles[2]-1]);
					DatagramPacket packet = new DatagramPacket(new byte[8192], 8192);
					while(true) {
						socket.receive(packet);
						byte[] arr = packet.getData();
						int len = packet.getLength();
						String message = new String(arr,0,len);
						score3 = Double.parseDouble(message);
						score3Label.setText(message);
					}
					
				}catch (NumberFormatException e) {
					// TODO: handle exception
					//发送信息告诉分裁判发送正确的形式
					String wrong = "wrong";
					try {
						DatagramSocket socket3 = new DatagramSocket();
						DatagramPacket packet3 = new DatagramPacket(wrong.getBytes(), wrong.getBytes().length, InetAddress.getByName(ips[2]), p_6[roles[2]-1]);
						socket3.send(packet3);
					} catch (SocketException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}catch (UnknownHostException e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}catch (IOException e3) {
						// TODO: handle exception
						e3.printStackTrace();
					}
					score3 = -1;	
				}catch (SocketException e) {
					System.out.println("Create Socket Failed!");
					e.printStackTrace();
					System.exit(1);
				}
				catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		}	
	}
}
