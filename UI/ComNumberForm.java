package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import BLL.GetPlayerIDHolder;
import Model.GameNumOfName;

public class ComNumberForm extends JFrame implements ActionListener{

	private JPanel contentPane;
	private TeamClient parent;
	private String username;
	private JButton returnBtn;
	private JTextArea infoArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComNumberForm frame = new ComNumberForm(null,"a");
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
	public ComNumberForm(TeamClient parent,String username) {
		this.parent = parent;
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 300);
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
		
		infoArea = new JTextArea();
		contentPane.add(infoArea, BorderLayout.CENTER);
		infoArea.setEditable(false);
		showInfo();
	}
	private void showInfo() {
		GetPlayerIDHolder gpidh = new GetPlayerIDHolder(username);
		Vector<GameNumOfName> data = gpidh.getNameAndNum();
		String res = "";
		while(!data.isEmpty()) {
			GameNumOfName t = data.remove(data.size()-1);
			res += "Name:"+t.getName()+"\t Id:"+t.getGameNum()+"\n";
		}
		infoArea.setText(res);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == returnBtn) {
			if(parent!=null) {
				parent.setVisible(true);
			}
			this.dispose();
		}
	}

}
