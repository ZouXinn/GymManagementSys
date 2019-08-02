package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class FinishedForm extends JudgerClient implements ActionListener{

	private JPanel contentPane;
	private JButton sureBtn;
	private JButton exitBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinishedForm frame = new FinishedForm("finished");
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
	public FinishedForm(String username) {
		super(username);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("\u6BD4\u8D5B\u5DF2\u7ED3\u675F\uFF0C\u5206\u6570\u5DF2\u4E0A\u4F20\uFF01");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		
		sureBtn = new JButton("\u786E\u8BA4");
		panel.add(sureBtn);
		sureBtn.addActionListener(this);
		
		exitBtn = new JButton("\u767B\u51FA");
		panel.add(exitBtn);
		exitBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == sureBtn) {
			
		}else if(e.getSource() == exitBtn) {
			
		}
	}

}
