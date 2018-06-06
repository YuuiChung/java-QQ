package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.ObjectOutputStream;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import thread.ManageClientConServerThread;
import common.Message;
import common.MessageType;

public class QQChat implements MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3924181728612346033L;
	/**
	 * @param args
	 */
	private JFrame frame;
	private JPanel myPanel;
	private JLabel label;
	private ImageIcon backGround;
	private JButton close = null;
	private JButton send = null;
	private JButton head_close = null;
	private JButton diminish = null;
	private Dimension screenSize = null;
	private JTextArea send_area = null;
	private JTextArea show_area = null;
	private JScrollPane send_jsp = null;
	private JScrollPane show_jsp = null;
	private int width;
	private int height;
	private String userId;
	private String friendId;
	static Point origin = new Point(); // 全局的位置变量，用于表示鼠标在窗口上的位置

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new QQChat("111", "222");
	}

	public QQChat(String userId, String friendId) {
		this.userId = userId;
		this.friendId = friendId;

		frame = new JFrame();
		backGround = new ImageIcon("src/images/QQ_Chat.jpg");
		label = new JLabel(backGround);
		label.setBounds(0, 0, backGround.getIconWidth(), backGround
				.getIconHeight());

		screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 得到屏幕的尺寸
		width = (screenSize.width - backGround.getIconWidth()) / 2;
		height = (screenSize.height - backGround.getIconHeight()) / 2;

		head_close = new JButton(new ImageIcon("src/images/QQ_headclose.png"));
		diminish = new JButton(new ImageIcon("src/images/diminish.png"));
		close = new JButton(new ImageIcon("src/images/QQ_Chatclose.png"));
		send = new JButton(new ImageIcon("src/images/QQ_Chatsend.png"));
		send_area = new JTextArea();
		send_jsp = new JScrollPane(send_area);
		show_area = new JTextArea();
		show_jsp = new JScrollPane(show_area);

		head_close.setBounds(610, 5, 29, 23);
		diminish.setBounds(570, 5, 29, 23);
		close.setBounds(480, 476, 71, 26);
		send.setBounds(555, 476, 88, 26);
		send_jsp.setBounds(2, 402, backGround.getIconWidth() - 3, 106);
		show_jsp.setBounds(2, 83, backGround.getIconWidth() - 3, 290);
		head_close.addMouseListener(this);
		diminish.addMouseListener(this);
		close.addMouseListener(this);
		send.addMouseListener(this);
		head_close.setBorder(BorderFactory.createEmptyBorder());
		diminish.setBorder(BorderFactory.createEmptyBorder());
		send_jsp.setBorder(null);
		show_jsp.setBorder(null);
		send_area.setBackground(new Color(225, 205, 207));
		show_area.setBackground(new Color(225, 205, 207));
		show_area.setEditable(false);

		myPanel = (JPanel) frame.getContentPane();
		myPanel.setOpaque(false);
		myPanel.setLayout(null);
		myPanel.add(label);
		myPanel.add(head_close);
		myPanel.add(diminish);
		myPanel.add(close);
		myPanel.add(send);
		myPanel.add(send_jsp);
		myPanel.add(show_jsp);

		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		frame.setBounds(width, height, backGround.getIconWidth(), backGround
				.getIconHeight());
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void showMessage(Message m) {
		String info = m.getTime() + "\r\n" + m.getSender() + "对" + m.getGeter()
				+ "说:  " + m.getContent() + "\r\n";
		show_area.append(info);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == head_close || e.getSource() == close) {
			frame.dispose();
		} else if (e.getSource() == diminish) {
			frame.setState(Frame.ICONIFIED);
		} else if (e.getSource() == send) {
			Message m = new Message();
			m.setSender(userId);
			m.setGeter(friendId);
			m.setContent(send_area.getText());
			m.setMesstype(MessageType.message_comm);
			m.setTime(new Date().toLocaleString());

			show_area.append(new Date().toLocaleString() + "\r\n" + userId
					+ "对" + friendId + "说:  " + send_area.getText() + "\r\n");
			send_area.setText("");
			// 发送给服务器
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManageClientConServerThread.getClientConServerThread(
								userId).getS().getOutputStream());
				oos.writeObject(m);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == head_close) {
			head_close
					.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else if (e.getSource() == diminish) {
			diminish.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		// 当鼠标按下的时候获得窗口当前的位置
		origin.x = e.getX();
		origin.y = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		// 当鼠标拖动时获取窗口当前位置
		Point p = frame.getLocation();
		// 设置窗口的位置
		// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
		frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
