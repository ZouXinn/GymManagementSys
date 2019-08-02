package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.GetPlayerFromPro;
import Model.DataOfProForJudge;
import Model.PlayersForScores;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Vector;
//import java.util.Spliterators.IntIteratorSpliterator;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class NormalJudgerMarkForm extends JudgerClient implements ActionListener{
	private JPanel contentPane;
	private JTextField scoreText;
	private JButton sureBtn;
	private JLabel projectLabel,playersLabel,currentPlayerLabel;
	private JudgerInitForm parent;
	private DataOfProForJudge myData;
	private Vector<PlayersForScores> mPlayersForScoresVect;
	private int playerIndex = 0;
	private String ip;
	private int role;
	int[] p_7 = {7997, 7998, 7999};
	int[] p_6 = {6997, 6998, 6999};
	
	//i
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NormalJudgerMarkForm frame = new NormalJudgerMarkForm(null,"normal judger",null,null,0);
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
	public NormalJudgerMarkForm(JudgerInitForm parent,String username,DataOfProForJudge myData,String ip,int role) {
		super(username);
		this.parent = parent;
		this.myData = myData;
		this.ip = ip;
		this.role = role;
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
		
		JLabel label = new JLabel("\u5206\u88C1\u5224\u5458\u60A8\u597D\uFF0C\u6BD4\u8D5B\u5DF2\u6B63\u5F0F\u5F00\u59CB");
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel label_1 = new JLabel("\u6BD4\u8D5B\u9879\u76EE\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("\u8FD0\u52A8\u5458\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_2);
		
		JLabel lblNewLabel = new JLabel("\u5F53\u524D\u8FD0\u52A8\u5458\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(3, 1, 0, 0));
		
		projectLabel = new JLabel("");
		projectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(projectLabel);
		
		playersLabel = new JLabel("");
		playersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(playersLabel);
		
		currentPlayerLabel = new JLabel("");
		currentPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentPlayerLabel.setEnabled(false);
		panel_4.add(currentPlayerLabel);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new GridLayout(1, 3, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("\u5F97\u5206");
		panel_3.add(lblNewLabel_1);
		
		scoreText = new JTextField();
		panel_3.add(scoreText);
		scoreText.setColumns(10);
		
		sureBtn = new JButton("\u63D0\u4EA4");
		panel_3.add(sureBtn);
		sureBtn.addActionListener(this);
		initTexts();
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
		currentPlayerLabel.setText(mPlayersForScoresVect.get(playerIndex).getGameID());
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
		if(e.getSource() == sureBtn) {
			String message = scoreText.getText();
			try {
				DatagramSocket socket = new DatagramSocket();
				DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, InetAddress.getByName(ip), p_7[role-1]);
				socket.send(packet);
				socket.close();
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			scoreText.setText("已提交");
		}
	}

	private class Receive extends Thread{//enter quit begin
		public void run() {
			try {
				DatagramSocket socket = new DatagramSocket(p_6[role-1]);//role 解决了吗？
				DatagramPacket packet = new DatagramPacket(new byte[8192], 8192);
				while(true) {
					socket.receive(packet);
					byte[] arr = packet.getData();
					int len = packet.getLength();
					String message = new String(arr,0,len);
					//point1.setText("裁判1:"+message);
					if(message.equals("back")) {
						JOptionPane.showMessageDialog(null, "打回！", "分数被打回！请重新提交！", JOptionPane.ERROR_MESSAGE);
						scoreText.setText("");
					}else if(message.equals("wrong")) {
						JOptionPane.showMessageDialog(null, "错误！", "请提交整数或浮点数！", JOptionPane.ERROR_MESSAGE);
						scoreText.setText("");
					}else if(message.equals("next")) {
						playerIndex++;
						if(playerIndex < mPlayersForScoresVect.size()) {
							currentPlayerLabel.setText(mPlayersForScoresVect.get(playerIndex).getGameID());
							scoreText.setText("");
						}else {
							parent.mButtons.get(parent.selectedIndex).setText("finished");
							parent.mButtons.get(parent.selectedIndex).setEnabled(false);
							parent.setVisible(true);
							parent.selectedIndex = -1;
							dispose();
						}					
					}
				}		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
}
