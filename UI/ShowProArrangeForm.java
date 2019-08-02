package UI;
import BLL.GetProInfoHolder;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class ShowProArrangeForm extends JFrame implements ActionListener,ItemListener{

	private JPanel contentPane;
	private TeamArrangeView parent;
	private int ageGroup;      //0：7-8   1：9-10    2：11-12
	private int proType; //0 男子单杠  1男子双杠 2男子吊环  3男子自由体操 4男子鞍马 5男子蹦床 6男子跳马
						//7女子跳马  8女子高低杠 9女子平衡木 10 女子自由体操 11女子蹦床
	//private boolean sex;   // true:boy   false:girl
	private JLabel infoLabel;
	private JComboBox groupBox;
	private JButton returnBtn;
	private JTextField playerText;
	private JTextField judgerText;
	private Vector<String> judgeVect,playerVect;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowProArrangeForm frame = new ShowProArrangeForm(null,0,0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ShowProArrangeForm(TeamArrangeView parent,int ageGroup,int proType) {
		this.parent = parent;
		this.ageGroup = ageGroup;
		this.proType = proType;
		//this.sex = sex;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		returnBtn = new JButton("\u8FD4\u56DE");
		panel.add(returnBtn, BorderLayout.WEST);
		returnBtn.addActionListener(this);
		
		JLabel label = new JLabel("\u8FD0\u52A8\u5458\u5206\u7EC4\u5982\u4E0B\uFF1A");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel label_1 = new JLabel("\u5F53\u524D\u9879\u76EE\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_1, BorderLayout.WEST);
		
		groupBox = new JComboBox();
		groupBox.setModel(new DefaultComboBoxModel(new String[] {"A\u7EC4", "B\u7EC4", "C\u7EC4", "D\u7EC4", "E\u7EC4", "F\u7EC4"}));
		groupBox.setSelectedIndex(0);
		groupBox.addItemListener(this);
		panel_2.add(groupBox, BorderLayout.EAST);
		
		infoLabel = new JLabel("");
		panel_2.add(infoLabel, BorderLayout.CENTER);
		setInfoLabelText();
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.WEST);
		panel_4.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel label_2 = new JLabel("\u8FD0\u52A8\u5458\uFF1A");
		label_2.setVerticalAlignment(SwingConstants.CENTER);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(label_2);
		
		JLabel label_3 = new JLabel("\u88C1\u5224\u5458\uFF1A");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(label_3);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new GridLayout(2, 1, 0, 0));
		
		playerText = new JTextField();
		playerText.setEnabled(false);
		panel_5.add(playerText);
		playerText.setColumns(10);
		
		judgerText = new JTextField();
		judgerText.setEnabled(false);
		panel_5.add(judgerText);
		judgerText.setColumns(10);
		//setPJInfo();
		getData();
		setData();
	}
	private void setData() {
		System.out.println("playerNum:"+playerVect.size());
		int tag = groupBox.getSelectedIndex();
		if(playerVect != null && playerVect.size() >= 1 && tag < playerVect.size()) {
			playerText.setText(playerVect.get(tag));
		}else {
			playerText.setText("no one");
		}
		if(judgeVect != null && judgeVect.size() >= 1 && tag < judgeVect.size()) {
			judgerText.setText(judgeVect.get(tag));
		}else {
			judgerText.setText("no one");
		}		
	}
	private void setInfoLabelText() {
		String sex = "",pro = "",group = "";
		switch(ageGroup) {
		case 0:
			group = "7-8岁";
			break;
		case 1:
			group = "9-10岁";
			break;
		case 2:
			group = "11-12岁";
			break;
		}
		switch(proType) {
		case 0:
			sex = "男子";
			pro = "单杠";
			break;
		case 1:
			sex = "男子";
			pro = "双杠";
			break;
		case 2:
			sex = "男子";
			pro = "吊环";
			break;
		case 3:
			sex = "男子";
			pro = "自由体操";
			break;
		case 4:
			sex = "男子";
			pro = "鞍马";
			break;
		case 5:
			sex = "男子";
			pro = "蹦床";
			break;
		case 6:
			sex = "男子";
			pro = "跳马";
			break;
		case 7:
			sex = "女子";
			pro = "跳马";
			break;
		case 8:
			sex = "女子";
			pro = "高低杠";
			break;
		case 9:
			sex = "女子";
			pro = "平衡木";
			break;
		case 10:
			sex = "女子";
			pro = "自由体操";
			break;
		case 11:
			sex = "女子";
			pro = "蹦床";
			break;
		}
		infoLabel.setText(sex+" "+group+" "+pro);
	}
	
	private void getData() {
		GetProInfoHolder getProInfoHolder = new GetProInfoHolder(ageGroup, proType);
		playerVect = getProInfoHolder.getPlayerInfo();
		judgeVect = getProInfoHolder.getJudgeInfo();
	}
	private void setPlayerInfo() {
		GetProInfoHolder getProInfoHolder = new GetProInfoHolder(ageGroup, proType);
		Vector<String> strings = getProInfoHolder.getPlayerInfo();
		String playerStr = "";
		while (!strings.isEmpty()) {
			String temp = strings.remove(strings.size()-1);
			playerStr += temp+"\t";
		}
		playerText.setText(playerStr);
	}
	private void setJudgeInfo() {
		GetProInfoHolder getProInfoHolder = new GetProInfoHolder(ageGroup, proType);
		Vector<String> strings = getProInfoHolder.getPlayerInfo();
		String judgeStr = "";
		while (!strings.isEmpty()) {
			String temp = strings.remove(strings.size()-1);
			judgeStr += temp+"\t";
		}
		judgerText.setText(judgeStr);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == returnBtn) {
//			GetProInfoHolder apih= new GetProInfoHolder(true,1,1,1);
//			apih.getPlayerInfo();
			if(parent != null) {
				parent.setVisible(true);
			}
			this.dispose();
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == groupBox &&e.getStateChange()==ItemEvent.SELECTED) {
			//setPJInfo();
			//setData();
			setData();
		}
	}
}
