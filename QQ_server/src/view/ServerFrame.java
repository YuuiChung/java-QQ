package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.ServerConClient;

public class ServerFrame implements ActionListener {

	/**
	 * @param args
	 */
	private JFrame frame;
	private JPanel mypanel;
	private JButton start;
	private JButton close;

	public static void main(String[] args) {
		// TODO Auto-generated method stuEb
		new ServerFrame();
	}

	public ServerFrame() {
		frame = new JFrame();
		mypanel = new JPanel();
		mypanel = (JPanel) frame.getContentPane();
		start = new JButton("启动服务器");
		close = new JButton("关闭服务器");
		start.setFocusPainted(false);
		close.setFocusPainted(false);
		start.addActionListener(this);
		close.addActionListener(this);

		mypanel.setLayout(new FlowLayout());
		mypanel.add(start);
		mypanel.add(close);

		frame.setBounds(400, 300, 300, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("服务器");
		frame.setResizable(false);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == start) {
			new ServerConClient();
		} else if (e.getSource() == close) {
			System.exit(0);
		}
	}

}
