package UI;
import BLL.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class SetProTypeForm extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton returnBtn;
	private JButton sureBtn;
	private AdminClient parent;
	private JComboBox boxes[][];
	private String strings[];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetProTypeForm frame = new SetProTypeForm(null);
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
	public SetProTypeForm(AdminClient parent) {
		this.parent = parent;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 331);
		//待修改strings值
		strings = new String[12];
		strings[0] = "男子单杠";
		strings[1] = "男子双杠";
		strings[2] = "男子吊环";
		strings[3] = "男子跳马";
		strings[4] = "男子自由体操";
		strings[5] = "男子鞍马";
		strings[6] = "男子蹦床";
		strings[7] = "女子跳马";
		strings[8] = "女子高低杠";
		strings[9] = "女子平衡木";
		strings[10] = "女子自由体操";
		strings[11] = "女子蹦床";
		
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
		
		JLabel label = new JLabel("\u8BBE\u7F6E\u6BD4\u8D5B\u7C7B\u578B");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label, BorderLayout.CENTER);
		
		sureBtn = new JButton("\u63D0\u4EA4");
		panel.add(sureBtn, BorderLayout.EAST);
		sureBtn.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(6, 2, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_1.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 4, 0, 0));
		
		JLabel lblNewLabel = new JLabel("\u7537\u5B50\u5355\u6760");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblNewLabel);
		
		boxes = new JComboBox[12][3];
		
		boxes[0][0] = new JComboBox();
		boxes[0][0].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[0][0].setSelectedIndex(0);
		panel_8.add(boxes[0][0]);
		
		boxes[0][1] = new JComboBox();
		boxes[0][1].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[0][1].setSelectedIndex(0);
		panel_8.add(boxes[0][1]);
		
		boxes[0][2] = new JComboBox();
		boxes[0][2].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[0][2].setSelectedIndex(0);
		panel_8.add(boxes[0][2]);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 4, 0, 0));
		
		JLabel label_1 = new JLabel("\u7537\u5B50\u53CC\u6760");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(label_1);
		
		boxes[1][0] = new JComboBox();
		boxes[1][0].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[1][0].setSelectedIndex(0);
		panel_3.add(boxes[1][0]);
		
		boxes[1][1] = new JComboBox();
		boxes[1][1].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[1][1].setSelectedIndex(0);
		panel_3.add(boxes[1][1]);
		
		boxes[1][2] = new JComboBox();
		boxes[1][2].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[1][2].setSelectedIndex(0);
		panel_3.add(boxes[1][2]);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 4, 0, 0));
		
		JLabel label_2 = new JLabel("\u7537\u5B50\u540A\u73AF");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(label_2);
		
		boxes[2][0] = new JComboBox();
		boxes[2][0].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[2][0].setSelectedIndex(0);
		panel_4.add(boxes[2][0]);
		
		boxes[2][1] = new JComboBox();
		boxes[2][1].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[2][1].setSelectedIndex(0);
		panel_4.add(boxes[2][1]);
		
		boxes[2][2] = new JComboBox();
		boxes[2][2].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[2][2].setSelectedIndex(0);
		panel_4.add(boxes[2][2]);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 4, 0, 0));
		
		JLabel lblNanzi = new JLabel("\u7537\u5B50\u81EA\u7531\u4F53\u64CD");
		lblNanzi.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNanzi);
		
		boxes[3][0] = new JComboBox();
		boxes[3][0].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[3][0].setSelectedIndex(0);
		panel_5.add(boxes[3][0]);
		
		boxes[3][1] = new JComboBox();
		boxes[3][1].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[3][1].setSelectedIndex(0);
		panel_5.add(boxes[3][1]);
		
		boxes[3][2] = new JComboBox();
		boxes[3][2].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[3][2].setSelectedIndex(0);
		panel_5.add(boxes[3][2]);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 4, 0, 0));
		
		JLabel label_3 = new JLabel("\u7537\u5B50\u978D\u9A6C");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(label_3);
		
		boxes[4][0] = new JComboBox();
		boxes[4][0].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[4][0].setSelectedIndex(0);
		panel_6.add(boxes[4][0]);
		
		boxes[4][1] = new JComboBox();
		boxes[4][1].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[4][1].setSelectedIndex(0);
		panel_6.add(boxes[4][1]);
		
		boxes[4][2] = new JComboBox();
		boxes[4][2].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[4][2].setSelectedIndex(0);
		panel_6.add(boxes[4][2]);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 4, 0, 0));
		
		JLabel label_4 = new JLabel("\u7537\u5B50\u8E66\u5E8A");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(label_4);
		
		boxes[5][0] = new JComboBox();
		boxes[5][0].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[5][0].setSelectedIndex(0);
		panel_7.add(boxes[5][0]);
		
		boxes[5][1] = new JComboBox();
		boxes[5][1].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[5][1].setSelectedIndex(0);
		panel_7.add(boxes[5][1]);
		
		boxes[5][2] = new JComboBox();
		boxes[5][2].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[5][2].setSelectedIndex(0);
		panel_7.add(boxes[5][2]);
		
		JPanel panel_10 = new JPanel();
		panel_1.add(panel_10);
		panel_10.setLayout(new GridLayout(1, 4, 0, 0));
		
		JLabel label_5 = new JLabel("\u7537\u5B50\u8DF3\u9A6C");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_10.add(label_5);
		
		boxes[6][0] = new JComboBox();
		boxes[6][0].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[6][0].setSelectedIndex(0);
		panel_10.add(boxes[6][0]);
		
		boxes[6][1] = new JComboBox();
		boxes[6][1].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[6][1].setSelectedIndex(0);
		panel_10.add(boxes[6][1]);
		
		boxes[6][2] = new JComboBox();
		boxes[6][2].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[6][2].setSelectedIndex(0);
		panel_10.add(boxes[6][2]);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 4, 0, 0));
		
		JLabel label_6 = new JLabel("\u5973\u5B50\u8DF3\u9A6C");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_6);
		
		boxes[7][0] = new JComboBox();
		boxes[7][0].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[7][0].setSelectedIndex(0);
		panel_2.add(boxes[7][0]);
		
		boxes[7][1] = new JComboBox();
		boxes[7][1].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[7][1].setSelectedIndex(0);
		panel_2.add(boxes[7][1]);
		
		boxes[7][2] = new JComboBox();
		boxes[7][2].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[7][2].setSelectedIndex(0);
		panel_2.add(boxes[7][2]);
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);
		panel_9.setLayout(new GridLayout(1, 4, 0, 0));
		
		JLabel label_7 = new JLabel("\u5973\u5B50\u9AD8\u4F4E\u6760");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(label_7);
		
		boxes[8][0] = new JComboBox();
		boxes[8][0].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[8][0].setSelectedIndex(0);
		panel_9.add(boxes[8][0]);
		
		boxes[8][1] = new JComboBox();
		boxes[8][1].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[8][1].setSelectedIndex(0);
		panel_9.add(boxes[8][1]);
		
		boxes[8][2] = new JComboBox();
		boxes[8][2].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[8][2].setSelectedIndex(0);
		panel_9.add(boxes[8][2]);
		
		JPanel panel_12 = new JPanel();
		panel_1.add(panel_12);
		panel_12.setLayout(new GridLayout(1, 4, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("\u5973\u5B50\u5E73\u8861\u6728");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_12.add(lblNewLabel_1);
		
		boxes[9][0] = new JComboBox();
		boxes[9][0].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[9][0].setSelectedIndex(0);
		panel_12.add(boxes[9][0]);
		
		boxes[9][1] = new JComboBox();
		boxes[9][1].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[9][1].setSelectedIndex(0);
		panel_12.add(boxes[9][1]);
		
		boxes[9][2] = new JComboBox();
		boxes[9][2].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[9][2].setSelectedIndex(0);
		panel_12.add(boxes[9][2]);
		
		JPanel panel_11 = new JPanel();
		panel_1.add(panel_11);
		panel_11.setLayout(new GridLayout(1, 4, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("\u5973\u5B50\u81EA\u7531\u4F53\u64CD");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_11.add(lblNewLabel_2);
		
		boxes[10][0] = new JComboBox();
		boxes[10][0].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[10][0].setSelectedIndex(0);
		panel_11.add(boxes[10][0]);
		
		boxes[10][1] = new JComboBox();
		boxes[10][1].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[10][1].setSelectedIndex(0);
		panel_11.add(boxes[10][1]);
		
		boxes[10][2] = new JComboBox();
		boxes[10][2].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[10][2].setSelectedIndex(0);
		panel_11.add(boxes[10][2]);
		
		JPanel panel_13 = new JPanel();
		panel_1.add(panel_13);
		panel_13.setLayout(new GridLayout(1, 4, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("\u5973\u5B50\u8E66\u5E8A");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_13.add(lblNewLabel_3);
		
		boxes[11][0] = new JComboBox();
		boxes[11][0].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[11][0].setSelectedIndex(0);
		panel_13.add(boxes[11][0]);
		
		boxes[11][1] = new JComboBox();
		boxes[11][1].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[11][1].setSelectedIndex(0);
		panel_13.add(boxes[11][1]);
		
		boxes[11][2] = new JComboBox();
		boxes[11][2].setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxes[11][2].setSelectedIndex(0);
		panel_13.add(boxes[11][2]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == sureBtn) {
			int datas[][] = new int[12][3];
			for(int i = 0 ; i < 12 ; i++) {
				for(int j = 0 ; j <3 ; j++) {
					datas[i][j] = boxes[i][j].getSelectedIndex()+1;
				}
			}
			SetProTypeHolder holder = new SetProTypeHolder(datas);
			boolean res = holder.send();
			if(res) {//成功
				JOptionPane.showConfirmDialog(null, "设置成功!");
			}else {//失败
				JOptionPane.showMessageDialog(null,  "设置失败","出错", JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource() == returnBtn) {
			if(parent != null) {
				parent.setVisible(true);
			}
			this.dispose();
		}
	}

}
