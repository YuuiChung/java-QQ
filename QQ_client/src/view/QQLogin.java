package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import thread.ManageClientConServerThread;
import thread.ManageQQList;

import model.LoginCheck;

import common.Message;
import common.MessageType;
import common.User;

public class QQLogin implements ActionListener, MouseListener,
		MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2566506664940636255L;
	/**
	 * @param args
	 */
	private JFrame frame;
	private JPanel myPanel;
	private JLabel user = null;
	private JLabel label = null;
	private JLabel password = null;
	private JLabel new_user = null;
	private JLabel find_password = null;
	private JButton login = null;
	private JButton close = null;
	private JCheckBox auto = null;
	private JComboBox state = null;
	private JTextField user_field = null;
	private ImageIcon backGround = null;
	private ImageIcon login_bg = null;
	private JPasswordField password_field = null;
	private JCheckBox reme_password = null;
	private ImageIcon states[] = new ImageIcon[6];
	static Point origin = new Point();

	public static void main(String[] args) {
		new QQLogin();
	}

	public QQLogin() {

		frame = new JFrame();
		backGround = new ImageIcon("src/images/login.png");
		label = new JLabel(backGround);
		user = new JLabel("ÕËºÅ£º");
		password = new JLabel("ÃÜÂë£º");
		new_user = new JLabel("×¢²áÐÂÕËºÅ");
		find_password = new JLabel("È¡»ØÃÜÂë");
		close = new JButton("ÍË³ö");
		reme_password = new JCheckBox("¼Ç×¡ÃÜÂë", true);
		for (int i = 0; i < states.length; i++) {
			states[i] = new ImageIcon("src/images/" + (i + 1) + ".png");
		}
		state = new JComboBox(states);
		auto = new JCheckBox("×Ô¶¯µÇÂ¼");
		login_bg = new ImageIcon("src/images/qq.png");
		login = new JButton(login_bg);
		user_field = new JTextField();
		password_field = new JPasswordField();

		user.setForeground(new Color(222, 252, 255));
		password.setForeground(new Color(222, 252, 255));
		new_user.setForeground(new Color(71, 131, 181));
		find_password.setForeground(new Color(71, 131, 181));
		close.setForeground(new Color(71, 131, 181));
		reme_password.setForeground(new Color(71, 131, 181));
		auto.setForeground(new Color(71, 131, 181));
		auto.setBackground(new Color(1, 68, 120));
		reme_password.setBackground(new Color(1, 68, 120));
		close.setBackground(new Color(0, 69, 120));
		user_field.setBackground(new Color(228, 234, 248));
		password_field.setBackground(new Color(228, 234, 248));

		login.setFocusPainted(false);
		close.setFocusPainted(false);
		auto.setFocusPainted(false);
		reme_password.setFocusPainted(false);
		login.addActionListener(this);
		close.addActionListener(this);

		close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		new_user.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		find_password.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		close.setBorder(null);
		user_field.setBorder(new EmptyBorder(0, 0, 0, 0));
		password_field.setBorder(new EmptyBorder(0, 0, 0, 0));

		user.setBounds(53, 80, 50, 25);
		password.setBounds(53, 120, 50, 25);
		user_field.setBounds(100, 80, 192, 25);
		password_field.setBounds(100, 120, 192, 25);
		new_user.setBounds(302, 80, 90, 25);
		find_password.setBounds(302, 120, 70, 25);
		login.setBounds(100, 190, 194, 31);
		close.setBounds(302, 193, 56, 25);
		reme_password.setBounds(95, 157, 78, 20);
		auto.setBounds(180, 157, 78, 20);
		state.setBounds(300, 157, 40, 20);
		label.setBounds(0, 0, backGround.getIconWidth(), backGround
				.getIconHeight());

		myPanel = (JPanel) frame.getContentPane();
		myPanel.setOpaque(false);
		myPanel.setLayout(null);
		myPanel.add(label);
		myPanel.add(user);
		myPanel.add(password);
		myPanel.add(user_field);
		myPanel.add(password_field);
		myPanel.add(new_user);
		myPanel.add(find_password);
		myPanel.add(login);
		myPanel.add(close);
		myPanel.add(reme_password);
		myPanel.add(auto);
		myPanel.add(state);

		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		frame.setBounds(420, 220, backGround.getIconWidth(), backGround
				.getIconHeight());
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == login) {
			User u = new User();
			u.setUserId(user_field.getText().trim());
			u.setPassword(new String(password_field.getPassword()));
			if (new LoginCheck().checkUser(u)) {
				QQList list = new QQList(u.getUserId());
				ManageQQList.addQQList(u.getUserId(), list);
				try {
					ObjectOutputStream oos = new ObjectOutputStream(
							ManageClientConServerThread
									.getClientConServerThread(u.getUserId())
									.getS().getOutputStream());
					Message m = new Message();
					
					m.setSender(u.getUserId());
					m.setMesstype(MessageType.message_wantget_OnlineFriend);
					oos.writeObject(m);
				} catch (Exception e1) {
				}
				frame.dispose();
			} else {
				JOptionPane.showMessageDialog(frame, "ÃÜÂë´íÎó£¬ÇëÖØÐÂÊäÈë",
						"ÃÜÂë´íÎó£¬ÇëÖØÐÂÊäÈë", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else if (e.getSource() == close) {
			System.exit(0);
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

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
