package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.AutoFunction;
import Model.RankCell;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class ShowRankForm extends JFrame implements ActionListener,ItemListener{

	private JPanel contentPane;
	private JButton searchBtn,returnBtn;
	private JComboBox proBox,ageBox,typeBox;
	private JTable table;
	private JLabel ageLabel;
	private AdminClient parent;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowRankForm frame = new ShowRankForm(null);
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
	public ShowRankForm(AdminClient parent) {
		this.parent = parent;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(1, 6, 0, 0));
		JPanel retPanel = new JPanel();
		contentPane.add(retPanel, BorderLayout.SOUTH);
		returnBtn = new JButton("返回");
		retPanel.add(returnBtn);	
		returnBtn.addActionListener(this);
		
		JLabel label = new JLabel("\u9879\u76EE\uFF1A");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label);
		
		proBox = new JComboBox();
		proBox.setModel(new DefaultComboBoxModel(new String[] {"\u7537\u5B50\u5355\u6760", "\u7537\u5B50\u53CC\u6760", "\u7537\u5B50\u540A\u73AF", "\u7537\u5B50\u81EA\u7531\u4F53\u64CD", "\u7537\u5B50\u978D\u9A6C", "\u7537\u5B50\u8E66\u5E8A", "\u7537\u5B50\u8DF3\u9A6C", "\u5973\u5B50\u8DF3\u9A6C", "\u5973\u5B50\u9AD8\u4F4E\u6760", "\u5973\u5B50\u5E73\u8861\u6728", "\u5973\u5B50\u81EA\u7531\u4F53\u64CD", "\u5973\u5B50\u8E66\u5E8A"}));
		proBox.setSelectedIndex(0);
		//proBox.addItemListener(this);
		panel_2.add(proBox);
		
		ageLabel = new JLabel("\u5E74\u9F84\u7EC4\uFF1A");
		ageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(ageLabel);
		
		ageBox = new JComboBox();
		ageBox.setModel(new DefaultComboBoxModel(new String[] {"7-8", "9-10", "11-12"}));
		ageBox.setSelectedIndex(0);
		//ageBox.addItemListener(this);
		panel_2.add(ageBox);
		
		JLabel label_2 = new JLabel("\u7C7B\u578B\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_2);
		
		typeBox = new JComboBox();
		typeBox.setModel(new DefaultComboBoxModel(new String[] {"\u4E2A\u4EBA", "\u56E2\u4F53"}));
		typeBox.setSelectedIndex(0);
		typeBox.addItemListener(this);
		panel_2.add(typeBox);
		
		searchBtn = new JButton("\u67E5\u8BE2");
		panel.add(searchBtn, BorderLayout.EAST);
		searchBtn.addActionListener(this);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		String[] headers = { "排名", "赛号", "得分" };
		Object[][] cellData = null;

		DefaultTableModel model = new DefaultTableModel(cellData, headers) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);//表头不可左右交换
		table.getTableHeader().setResizingAllowed(false);//表头大小不可改变
		
		JScrollPane jsp = new JScrollPane(table);
		panel_1.add(jsp);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchBtn) {
			int proType = proBox.getSelectedIndex();
			if(typeBox.getSelectedIndex() == 0) {//个人
				int ageGroup = ageBox.getSelectedIndex();
				try {
					String[] headers = { "排名", "赛号", "得分" };
					RankCell[] rankCells = AutoFunction.getRankCellof(proType, ageGroup);
					Object[][] cellData = new String[rankCells.length][3];
					for(int i = 0 ; i < rankCells.length ; i++) {
						cellData[i][0] = String.valueOf(rankCells[i].getRank());
						cellData[i][1] = rankCells[i].getGameID();
						cellData[i][2] = String.valueOf(rankCells[i].getScore());
					}
					DefaultTableModel model = new DefaultTableModel(cellData, headers) {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};

					table.setModel(model);
					table.getTableHeader().setReorderingAllowed(false);//表头不可左右交换
					table.getTableHeader().setResizingAllowed(false);//表头大小不可改变
				}catch(Exception ex) {
					ex.printStackTrace();
				}			
			}else {//团体
				try {
					String[] headers = { "排名", "队伍", "得分" };
					RankCell[] rankCells = AutoFunction.getRankCellofTeam(proType);
					Object[][] cellData = new String[rankCells.length][3];
					for(int i = 0 ; i < rankCells.length ; i++) {
						cellData[i][0] = String.valueOf(rankCells[i].getRank());
						cellData[i][1] = rankCells[i].getGameID();
						cellData[i][2] = String.valueOf(rankCells[i].getScore());
					}
					DefaultTableModel model = new DefaultTableModel(cellData, headers) {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					table.setModel(model);
					table.getTableHeader().setReorderingAllowed(false);//表头不可左右交换
					table.getTableHeader().setResizingAllowed(false);//表头大小不可改变
					System.out.print("length:"+rankCells.length);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(e.getSource() == returnBtn) {
			if(parent != null) {
				parent.setVisible(true);
			}
			this.dispose();
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == typeBox && e.getStateChange() == ItemEvent.SELECTED) {
			if(typeBox.getSelectedIndex() == 0) {//个人
				ageLabel.setVisible(true);
				ageBox.setVisible(true);
			}else {//团体
				ageLabel.setVisible(false);
				ageBox.setVisible(false);
			}
		}
	}

}
