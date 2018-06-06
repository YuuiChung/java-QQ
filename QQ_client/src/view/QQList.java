package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import common.Message;
import thread.ManageQQChat;

public class QQList implements MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4047633480280905670L;
	/**
	 * 
	 */
	private JFrame frame;
	private JPanel myPanel;
	private JPanel friend;
	private ImageIcon backGround = null;
	private JTextField userId = null;
	private String userName;
	private String friendId;
	private JLabel label = null;
	private JLabel phone = null;
	private JLabel phone_state = null;
	private JLabel friends = null;
	private JLabel stranger = null;
	private JLabel blackList = null;
	private JLabel friends_label[] = null;
	private JButton head_close = null;
	private JButton diminish = null;
	private JScrollPane jsp = null;
	private JScrollBar bar = null;
	static Point origin = new Point();

	public static void main(String[] args) {
		new QQList("1");
	}

	public QQList(String userName) {
		this.userName = userName;

		frame = new JFrame();
		backGround = new ImageIcon("src/images/QQ_List.png");
		label = new JLabel(backGround);
		label.setBounds(0, 0, backGround.getIconWidth(), backGround
				.getIconHeight());

		userId = new JTextField();
		phone = new JLabel(" 我的设备", new ImageIcon("src/images/left.png"),
				JLabel.LEFT);
		friends = new JLabel(" 我的好友", new ImageIcon("src/images/left.png"),
				JLabel.LEFT);
		stranger = new JLabel(" 陌生人", new ImageIcon("src/images/left.png"),
				JLabel.LEFT);
		blackList = new JLabel(" 黑名单", new ImageIcon("src/images/left.png"),
				JLabel.LEFT);
		diminish = new JButton(new ImageIcon("src/images/List_diminish.png"));
		head_close = new JButton(new ImageIcon("src/images/List_close.png"));
		phone_state = new JLabel(new ImageIcon("src/images/phone_state.png"));

		head_close.setBounds(263, 5, 23, 20);
		diminish.setBounds(240, 5, 23, 20);
		userId.setBounds(5, 60, 110, 25);
		phone.setBounds(3, 208, backGround.getIconWidth(), 25);
		friends.setBounds(3, 234, backGround.getIconWidth(), 25);
		stranger.setBounds(3, 261, backGround.getIconWidth(), 25);
		blackList.setBounds(3, 289, backGround.getIconWidth(), 25);
		phone.addMouseListener(this);
		friends.addMouseListener(this);
		stranger.addMouseListener(this);
		blackList.addMouseListener(this);
		diminish.addMouseListener(this);
		head_close.addMouseListener(this);

		userId.setEditable(false);
		userId.setText(userName);
		userId.setFont(new Font("宋体", Font.BOLD, 18));
		userId.setBorder(BorderFactory.createEmptyBorder());
		diminish.setBorder(BorderFactory.createEmptyBorder());
		head_close.setBorder(BorderFactory.createEmptyBorder());

		friend = new JPanel(new GridLayout(10, 1, 4, 4));
		friends_label = new JLabel[10];
		for (int i = 0; i < friends_label.length; i++) {
			friends_label[i] = new JLabel((i + 1) + "", new ImageIcon(
					"src/images/QQ_head.png"), JLabel.LEFT);
			friends_label[i].setEnabled(false);
			if (friends_label[i].getText().equals(userName)) {
				friends_label[i].setEnabled(true);
			}
			friends_label[i].addMouseListener(this);
			friend.add(friends_label[i]);
		}

		jsp = new JScrollPane(friend);
		bar = jsp.getVerticalScrollBar();
		jsp.setBackground(new Color(230, 233, 237));
		friend.setBackground(new Color(230, 233, 237));
		bar.setBackground(new Color(230, 233, 237));
		jsp.setBorder(null);

		myPanel = (JPanel) frame.getContentPane();
		myPanel.setOpaque(false);
		myPanel.setLayout(null);
		myPanel.add(label);
		myPanel.add(userId);
		myPanel.add(phone);
		myPanel.add(jsp);
		myPanel.add(phone_state);
		myPanel.add(friends);
		myPanel.add(stranger);
		myPanel.add(blackList);
		myPanel.add(diminish);
		myPanel.add(head_close);

		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		frame.setBounds(1000, 0, backGround.getIconWidth(), backGround
				.getIconHeight());
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	// 更新在线好友情况
	public void updateQQList(Message m) {
		String onLineFriends[] = m.getContent().split(" ");
		for (int i = 0; i < onLineFriends.length; i++) {
			friends_label[Integer.parseInt(onLineFriends[i]) - 1]
					.setEnabled(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == phone && e.getClickCount() == 1) {
			jsp.setBounds(1, 300, 0, 0);
			friends.setBounds(3, 289, backGround.getIconWidth(), 25);
			stranger.setBounds(3, 316, backGround.getIconWidth(), 25);
			blackList.setBounds(3, 343, backGround.getIconWidth(), 25);
			phone_state.setBounds(0, 232, backGround.getIconWidth(), 56);
		} else if (e.getSource() == friends && e.getClickCount() == 1) {
			jsp.setBounds(1, 261, backGround.getIconWidth() - 2, 280);
			friends.setBounds(3, 234, backGround.getIconWidth(), 25);
			stranger.setBounds(3, 540, backGround.getIconWidth(), 25);
			blackList.setBounds(3, 567, backGround.getIconWidth(), 25);
			phone_state.setBounds(0, 0, 0, 0);
		} else if (e.getSource() == stranger && e.getClickCount() == 1) {
			jsp.setBounds(1, 289, backGround.getIconWidth() - 2, 280);
			friends.setBounds(3, 234, backGround.getIconWidth(), 25);
			stranger.setBounds(3, 261, backGround.getIconWidth(), 25);
			blackList.setBounds(3, 567, backGround.getIconWidth(), 25);
			phone_state.setBounds(0, 0, 0, 0);
		} else if (e.getSource() == blackList && e.getClickCount() == 1) {
			jsp.setBounds(1, 313, backGround.getIconWidth() - 2, 280);
			friends.setBounds(3, 234, backGround.getIconWidth(), 25);
			stranger.setBounds(3, 261, backGround.getIconWidth(), 25);
			blackList.setBounds(3, 289, backGround.getIconWidth(), 25);
			phone_state.setBounds(0, 0, 0, 0);
		}
		if (e.getSource() == head_close) {
			System.exit(0);
		} else if (e.getSource() == diminish) {
			frame.setState(Frame.ICONIFIED);
		}
		for (int i = 0; i < friends_label.length; i++) {
			if (e.getClickCount() == 2 && e.getSource() == friends_label[i]) {
				friendId = friends_label[i].getText();
				QQChat chat = new QQChat(userName, friendId);
				ManageQQChat.addQQChat(userName + " " + friendId, chat);
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
		} else if (e.getSource() == friends || e.getSource() == stranger
				|| e.getSource() == blackList) {
			head_close.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		for (int i = 0; i < friends_label.length; i++) {
			if (e.getSource() == friends_label[i]) {
				friends_label[i].setForeground(Color.RED);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		head_close.setBorder(BorderFactory.createEmptyBorder());
		for (int i = 0; i < friends_label.length; i++) {
			if (e.getSource() == friends_label[i]) {
				friends_label[i].setForeground(Color.BLACK);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		origin.x = e.getX();
		origin.y = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		Point p = frame.getLocation();
		frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
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
