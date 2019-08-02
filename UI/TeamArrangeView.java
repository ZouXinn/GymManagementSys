package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.soap.SOAPFactory;

import BLL.GetProInfoHolder;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import java.awt.CardLayout;

public class TeamArrangeView extends JFrame implements ActionListener,ItemListener{

	private JPanel contentPane;
	private TeamClient parent;
	private JComboBox sexBox;
	private JComboBox ageBox;
	private CardLayout card;
	private JPanel panel_1,girlPanel,boyPanel;
	private JButton returnBtn,BdanGangBtn,BshuangGangBtn,BdiaoHuanBtn,BtiCaoBtn,BanMaBtn,BbengChuangBtn,BtiaoMaBtn;
	private JButton GtiaoMaBtn,GgaoDiGangBtn,GpingHengMuBtn,GtiCaoBtn,GbengChuangBtn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamArrangeView frame = new TeamArrangeView(null);
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
	public TeamArrangeView(TeamClient parent) {
		this.parent = parent;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		returnBtn = new JButton("\u8FD4\u56DE");
		returnBtn.addActionListener(this);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		panel.add(returnBtn);
		
		sexBox = new JComboBox();
		sexBox.setModel(new DefaultComboBoxModel(new String[] {"\u7537\u5B50", "\u5973\u5B50"}));
		sexBox.setSelectedIndex(0);
		//sexBox.addActionListener(this);
		sexBox.addItemListener(this);
		
		ageBox = new JComboBox();
		ageBox.setModel(new DefaultComboBoxModel(new String[] {"7-8\u5C81\u7EC4", "9-10\u5C81\u7EC4", "11-12\u5C81\u7EC4"}));
		ageBox.addActionListener(this);
		panel.add(ageBox);
		panel.add(sexBox);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		card = new CardLayout(0, 0);
		panel_1.setLayout(card);
		
		boyPanel = new JPanel();
		panel_1.add(boyPanel, "boy");
		boyPanel.setLayout(new GridLayout(3, 3, 0, 0));
		
		BdanGangBtn = new JButton("\u5355\u6760");
		boyPanel.add(BdanGangBtn);
		BdanGangBtn.addActionListener(this);
		
		BshuangGangBtn = new JButton("\u53CC\u6760");
		boyPanel.add(BshuangGangBtn);
		BshuangGangBtn.addActionListener(this);
		
		BdiaoHuanBtn = new JButton("\u540A\u73AF");
		boyPanel.add(BdiaoHuanBtn);
		BdiaoHuanBtn.addActionListener(this);
		
		BtiCaoBtn = new JButton("\u81EA\u7531\u4F53\u64CD");
		boyPanel.add(BtiCaoBtn);
		BtiCaoBtn.addActionListener(this);
		
		BanMaBtn = new JButton("\u978D\u9A6C");
		boyPanel.add(BanMaBtn);
		BanMaBtn.addActionListener(this);
		
		BbengChuangBtn = new JButton("\u8E66\u5E8A");
		boyPanel.add(BbengChuangBtn);
		BbengChuangBtn.addActionListener(this);
		
		BtiaoMaBtn = new JButton("\u8DF3\u9A6C");
		boyPanel.add(BtiaoMaBtn);
		BtiaoMaBtn.addActionListener(this);
		
		girlPanel = new JPanel();
		panel_1.add(girlPanel, "girl");
		girlPanel.setLayout(new GridLayout(2, 3, 0, 0));
		
		GtiaoMaBtn = new JButton("\u8DF3\u9A6C");
		girlPanel.add(GtiaoMaBtn);
		GtiaoMaBtn.addActionListener(this);
		
		GgaoDiGangBtn = new JButton("\u9AD8\u4F4E\u6760");
		girlPanel.add(GgaoDiGangBtn);
		GgaoDiGangBtn.addActionListener(this);
		
		GpingHengMuBtn = new JButton("\u5E73\u8861\u6728");
		girlPanel.add(GpingHengMuBtn);
		GpingHengMuBtn.addActionListener(this);
		
		GtiCaoBtn = new JButton("\u4F53\u64CD");
		girlPanel.add(GtiCaoBtn);
		GtiCaoBtn.addActionListener(this);
		
		GbengChuangBtn = new JButton("\u8E66\u5E8A");
		girlPanel.add(GbengChuangBtn);
		GbengChuangBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == returnBtn) {
			if(parent!=null) {
				parent.setVisible(true);
			}
			this.dispose();
		}else if(e.getSource() == BdanGangBtn) {
			ShowProArrangeForm spaf = null;
			switch(ageBox.getSelectedIndex()) {
			case 0://7-8
				spaf = new ShowProArrangeForm(this,0,0);
				break;
			case 1://9-10
				spaf = new ShowProArrangeForm(this,1,0);
				break;
			case 2://11-12
				spaf = new ShowProArrangeForm(this,2,0);
				break;
			}
			this.setVisible(false);
			if(spaf!=null) {
				spaf.setVisible(true);
			}				
		}else if(e.getSource() == BshuangGangBtn) {
			ShowProArrangeForm spaf = null;
			switch(ageBox.getSelectedIndex()) {
			case 0://7-8
				spaf = new ShowProArrangeForm(this,0,1);
				break;
			case 1://9-10
				spaf = new ShowProArrangeForm(this,1,1);
				break;
			case 2://11-12
				spaf = new ShowProArrangeForm(this,2,1);
				break;
			}
			this.setVisible(false);
			if(spaf!=null) {
				spaf.setVisible(true);
			}	
		}else if(e.getSource() == BdiaoHuanBtn) {
			ShowProArrangeForm spaf = null;
			switch(ageBox.getSelectedIndex()) {
			case 0://7-8
				spaf = new ShowProArrangeForm(this,0,2);
				break;
			case 1://9-10
				spaf = new ShowProArrangeForm(this,1,2);
				break;
			case 2://11-12
				spaf = new ShowProArrangeForm(this,2,2);
				break;
			}
			this.setVisible(false);
			if(spaf!=null) {
				spaf.setVisible(true);
			}	
		}else if(e.getSource() == BtiCaoBtn) {
			ShowProArrangeForm spaf = null;
			switch(ageBox.getSelectedIndex()) {
			case 0://7-8
				spaf = new ShowProArrangeForm(this,0,3);
				break;
			case 1://9-10
				spaf = new ShowProArrangeForm(this,1,3);
				break;
			case 2://11-12
				spaf = new ShowProArrangeForm(this,2,3);
				break;
			}
			this.setVisible(false);
			if(spaf!=null) {
				spaf.setVisible(true);
			}	
		}else if(e.getSource() == BanMaBtn) {
			ShowProArrangeForm spaf = null;
			switch(ageBox.getSelectedIndex()) {
			case 0://7-8
				spaf = new ShowProArrangeForm(this,0,4);
				break;
			case 1://9-10
				spaf = new ShowProArrangeForm(this,1,4);
				break;
			case 2://11-12
				spaf = new ShowProArrangeForm(this,2,4);
				break;
			}
			this.setVisible(false);
			if(spaf!=null) {
				spaf.setVisible(true);
			}	
		}else if(e.getSource() == BbengChuangBtn) {
			ShowProArrangeForm spaf = null;
			switch(ageBox.getSelectedIndex()) {
			case 0://7-8
				spaf = new ShowProArrangeForm(this,0,5);
				break;
			case 1://9-10
				spaf = new ShowProArrangeForm(this,1,5);
				break;
			case 2://11-12
				spaf = new ShowProArrangeForm(this,2,5);
				break;
			}
			this.setVisible(false);
			if(spaf!=null) {
				spaf.setVisible(true);
			}	
		}else if(e.getSource() == BtiaoMaBtn) {
			ShowProArrangeForm spaf = null;
			switch(ageBox.getSelectedIndex()) {
			case 0://7-8
				spaf = new ShowProArrangeForm(this,0,6);
				break;
			case 1://9-10
				spaf = new ShowProArrangeForm(this,1,6);
				break;
			case 2://11-12
				spaf = new ShowProArrangeForm(this,2,6);
				break;
			}
			this.setVisible(false);
			if(spaf!=null) {
				spaf.setVisible(true);
			}	
		}else if(e.getSource() == GtiaoMaBtn) {
			ShowProArrangeForm spaf = null;
			switch(ageBox.getSelectedIndex()) {
			case 0://7-8
				spaf = new ShowProArrangeForm(this,0,7);
				break;
			case 1://9-10
				spaf = new ShowProArrangeForm(this,1,7);
				break;
			case 2://11-12
				spaf = new ShowProArrangeForm(this,2,7);
				break;
			}
			if(spaf!=null) {
				spaf.setVisible(true);
			}	
		}else if(e.getSource() == GgaoDiGangBtn) {
			ShowProArrangeForm spaf = null;
			switch(ageBox.getSelectedIndex()) {
			case 0://7-8
				spaf = new ShowProArrangeForm(this,0,8);
				break;
			case 1://9-10
				spaf = new ShowProArrangeForm(this,1,8);
				break;
			case 2://11-12
				spaf = new ShowProArrangeForm(this,2,8);
				break;
			}
			this.setVisible(false);
			if(spaf!=null) {
				spaf.setVisible(true);
			}	
		}else if(e.getSource() == GpingHengMuBtn) {
			ShowProArrangeForm spaf = null;
			switch(ageBox.getSelectedIndex()) {
			case 0://7-8
				spaf = new ShowProArrangeForm(this,0,9);
				break;
			case 1://9-10
				spaf = new ShowProArrangeForm(this,1,9);
				break;
			case 2://11-12
				spaf = new ShowProArrangeForm(this,2,9);
				break;
			}
			this.setVisible(false);
			if(spaf!=null) {
				spaf.setVisible(true);
			}	
		}else if(e.getSource() == GtiCaoBtn) {
			ShowProArrangeForm spaf = null;
			switch(ageBox.getSelectedIndex()) {
			case 0://7-8
				spaf = new ShowProArrangeForm(this,0,10);
				break;
			case 1://9-10
				spaf = new ShowProArrangeForm(this,1,10);
				break;
			case 2://11-12
				spaf = new ShowProArrangeForm(this,2,10);
				break;
			}
			this.setVisible(false);
			if(spaf!=null) {
				spaf.setVisible(true);
			}	
		}else if(e.getSource() == GbengChuangBtn) {
			ShowProArrangeForm spaf = null;
			switch(ageBox.getSelectedIndex()) {
			case 0://7-8
				spaf = new ShowProArrangeForm(this,0,11);
				break;
			case 1://9-10
				spaf = new ShowProArrangeForm(this,1,11);
				break;
			case 2://11-12
				spaf = new ShowProArrangeForm(this,2,11);
				break;
			}
			this.setVisible(false);
			if(spaf!=null) {
				spaf.setVisible(true);
			}	
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == sexBox &&e.getStateChange()==ItemEvent.SELECTED) {
			if(sexBox.getSelectedIndex() == 0) {//选中男生
				card.show(panel_1,"boy");
			}else {//选中女生
				card.show(panel_1, "girl");
			}
		}
		
	}

}
