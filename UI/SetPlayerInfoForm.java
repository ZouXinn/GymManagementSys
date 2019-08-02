package UI;
import Model.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JSlider;

public class SetPlayerInfoForm extends JFrame implements ActionListener,ItemListener,ChangeListener{

	private JPanel contentPane;
	private SetInfoForm parent;
	private JPanel infoMainPanel;
	private JPanel bPanels[],gPanels[];
	private JTextField IDText;
	private JTextField nameText;
	private JPanel proPanel,boyProPanel,girlProPanel;
	private JComboBox sexBox;
	private CardLayout card;
	private JButton returnBtn,nextBtn,sureBtn;
	Vector<Player> boyVect;
	Vector<Player> girlVect;
	private JRadioButton[] boyRadios;//单杠 双杠 吊环  跳马 自由体操 鞍马 蹦床    按顺序
	private JRadioButton[] girlRadios;//跳马 高低杠  自由体操 平衡木 蹦床           按顺序
	private JLabel ageBarLabel;
	private JSlider ageSlider;
	//private JTextField 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetPlayerInfoForm frame = new SetPlayerInfoForm(null);
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
	public SetPlayerInfoForm(SetInfoForm parent) {
		this.parent = parent;
		boyVect = new Vector<Player>();
		girlVect = new Vector<Player>();
		boyRadios = new JRadioButton[7];
		girlRadios = new JRadioButton[5];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		returnBtn = new JButton("\u53D6\u6D88");
		returnBtn.addActionListener(this);
		panel_1.add(returnBtn, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("\u8FD0\u52A8\u5458\u4FE1\u606F\u5F55\u5165\uFF1A");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(label, BorderLayout.WEST);
		
		sureBtn = new JButton("\u786E\u5B9A");
		sureBtn.addActionListener(this);
		panel_2.add(sureBtn, BorderLayout.EAST);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(1, 3, 0, 0));
		
		
		sexBox = new JComboBox();
		panel_5.add(sexBox);
		sexBox.setModel(new DefaultComboBoxModel(new String[] {"\u7537", "\u5973"}));
		sexBox.setSelectedIndex(0);
		
		nextBtn = new JButton("\u4E0B\u4E00\u4E2A");
		panel_5.add(nextBtn);
		nextBtn.addActionListener(this);
		sexBox.addItemListener(this);
		
		infoMainPanel = new JPanel();
		panel.add(infoMainPanel, BorderLayout.CENTER);
		infoMainPanel.setLayout(new CardLayout(0, 0));
		
		JPanel boyInfoPanel = new JPanel();
		infoMainPanel.add(boyInfoPanel, "name_1972628928881");
		boyInfoPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel label_1 = new JLabel("\u57FA\u672C\u4FE1\u606F");
		boyInfoPanel.add(label_1, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		boyInfoPanel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.WEST);
		panel_6.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel label_2 = new JLabel("\u59D3\u540D:");
		panel_6.add(label_2);
		
		JLabel label_3 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7:");
		panel_6.add(label_3);
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel label_4 = new JLabel("\u5E74\u9F84:");
		panel_8.add(label_4);
		
		ageBarLabel = new JLabel("7");
		panel_8.add(ageBarLabel);
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new GridLayout(3, 1, 0, 0));
		
		nameText = new JTextField();
		panel_7.add(nameText);
		nameText.setColumns(10);
		
		IDText = new JTextField();
		panel_7.add(IDText);
		IDText.setColumns(10);
		
		ageSlider = new JSlider();
		ageSlider.setValue(7);
		ageSlider.setMaximum(12);
		ageSlider.setMinimum(7);
		ageSlider.addChangeListener(this);
		panel_7.add(ageSlider);
		
		proPanel = new JPanel();
		panel_3.add(proPanel);
		card = new CardLayout(0, 0);
		proPanel.setLayout(card);
		
		boyProPanel = new JPanel();
		proPanel.add(boyProPanel, "boy");
		boyProPanel.setLayout(new GridLayout(3, 3, 0, 0));
		
		boyRadios[0] = new JRadioButton("男子单杠");//男子单杠
		boyProPanel.add(boyRadios[0]);
		
		boyRadios[1] = new JRadioButton("男子双杠");//男子双杠
		boyProPanel.add(boyRadios[1]);
		
		boyRadios[2] = new JRadioButton("男子吊环");//男子吊环
		boyProPanel.add(boyRadios[2]);
		
		boyRadios[3] = new JRadioButton("男子自由体操");//男子自由体操 
		boyProPanel.add(boyRadios[3]);
		
		boyRadios[4] = new JRadioButton("男子鞍马");//男子鞍马
		boyProPanel.add(boyRadios[4]);
		
		boyRadios[5] = new JRadioButton("男子蹦床");//男子蹦床
		boyProPanel.add(boyRadios[5]);
		
		boyRadios[6] = new JRadioButton("男子跳马");//男子跳马
		boyProPanel.add(boyRadios[6]);
		
		girlProPanel = new JPanel();
		proPanel.add(girlProPanel, "girl");
		girlProPanel.setLayout(new GridLayout(2, 3, 0, 0));
		
		girlRadios[0] = new JRadioButton("女子跳马");//女子跳马 
		girlProPanel.add(girlRadios[0]);
		
		girlRadios[1] = new JRadioButton("女子高低杠");//女子高低杠 
		girlProPanel.add(girlRadios[1]);
		
		girlRadios[2] = new JRadioButton("女子平衡木");//女子平衡木
		girlProPanel.add(girlRadios[2]);
		
		girlRadios[3] = new JRadioButton("女子自由体操");//女子自由体操
		girlProPanel.add(girlRadios[3]);
		
		girlRadios[4] = new JRadioButton("女子蹦床");//女子蹦床
		girlProPanel.add(girlRadios[4]);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == sexBox &&e.getStateChange()==ItemEvent.SELECTED) {
			if(sexBox.getSelectedIndex() == 0) {//选中男生
				card.show(proPanel,"boy");
			}else {//选中女生
				card.show(proPanel,"girl");
			}
		}
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO 自动生成的方法存根
		ageBarLabel.setText(String.valueOf(ageSlider.getValue()));
	}
	//private void getBoyInfo
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == returnBtn) {
			if(parent != null) {
				parent.setVisible(true);
			}
			parent.son = null;
			this.dispose();
		}else if(e.getSource() == nextBtn) {
			boolean getIn = false;
			switch(sexBox.getSelectedIndex()) {
			case 0://男生
				for(int i = 0 ; i < 7;i++) {
					if(boyRadios[i].isSelected()) {
						getIn = true;
						break;
					}
				}
				break;
			case 1:
				for(int i = 0 ; i < 5;i++) {
					if(girlRadios[i].isSelected()) {
						getIn = true;
						break;
					}
				}
				break;
			}
			if(!nameText.getText().equals("")&&!IDText.getText().equals("")&&getIn) {
				saveNew();
				displayNew();
			}else {//弹窗警告
				if(nameText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "姓名不能为空","错误！", JOptionPane.ERROR_MESSAGE);
				}else if(IDText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "ID不能为空", "错误！",JOptionPane.ERROR_MESSAGE);
				}else if(!getIn){
					JOptionPane.showMessageDialog(null, "至少参加一项比赛", "错误！",JOptionPane.ERROR_MESSAGE);
				}
			}
		}else if(e.getSource() == sureBtn) {
			boolean getIn = false;
			switch(sexBox.getSelectedIndex()) {
			case 0://男生
				for(int i = 0 ; i < 7;i++) {
					if(boyRadios[i].isSelected()) {
						getIn = true;
						break;
					}
				}
				break;
			case 1:
				for(int i = 0 ; i < 5;i++) {
					if(girlRadios[i].isSelected()) {
						getIn = true;
						break;
					}
				}
				break;
			}
			if(!nameText.getText().equals("")&&!IDText.getText().equals("")&&getIn) {
				int res=JOptionPane.showConfirmDialog(null, "确定？", "是否确定？", JOptionPane.YES_NO_OPTION);
			    if(res==JOptionPane.YES_OPTION){ //点击“是”后执行这个代码块
			    	saveNew();
			    	if(parent!=null) {
			    		parent.boyVect = this.boyVect;
			    		parent.girlVect = this.girlVect;
			    		parent.setVisible(true);			    		
			    	}
			    	this.dispose();
				}else{ //点击“否”后执行这个代码块
					return;
				} 
				
				
			}else {//弹窗警告
				if(nameText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "姓名不能为空","错误！", JOptionPane.ERROR_MESSAGE);
				}else if(IDText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "ID不能为空", "错误！",JOptionPane.ERROR_MESSAGE);
				}else if(!getIn){
					JOptionPane.showMessageDialog(null, "至少参加一项比赛", "错误！",JOptionPane.ERROR_MESSAGE);
				}
			}
			System.out.println("parent boy num:" + parent.boyVect.size());
			System.out.println("parent girl num:" + parent.girlVect.size());
		}
	}
	private void displayNew() {
		ageBarLabel.setText(String.valueOf(7));
		nameText.setText("");
		IDText.setText("");
		ageSlider.setValue(7);
		for(int i = 0 ; i < 7;i++) {
			boyRadios[i].setSelected(false);
		}
		for(int i = 0 ; i < 5;i++) {
			girlRadios[i].setSelected(false);
		}
		sexBox.setSelectedIndex(0);
		card.show(proPanel,"boy");
	}
	private void saveNew() {
		switch(sexBox.getSelectedIndex()) {
		case 0://选中男生				
			boolean[] temp = new boolean[7];
			for(int i = 0 ; i < 7 ; i++) {
				temp[i] = boyRadios[i].isSelected();
			}
			Player p = new Player(nameText.getText(),sexBox.getSelectedIndex()== 0 ? true:false,ageSlider.getValue(),IDText.getText(),temp);
			boyVect.add(p);
			break;
		case 1://选中女生
			boolean[] temp1 = new boolean[5];
			for(int i = 0 ; i < 5 ; i++) {
				temp1[i] = girlRadios[i].isSelected();
			}
			//girlVect.add(temp1);
			Player p1 = new Player(nameText.getText(),sexBox.getSelectedIndex()== 0 ? true:false,ageSlider.getValue(),IDText.getText(),temp1);
			girlVect.add(p1);
			break;
		default:
			System.out.println("保存出错");
		}
	}
}
