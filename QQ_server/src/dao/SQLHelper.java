package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLHelper {

	private Connection ct = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/qq?useUnicode=true&characterEncoding=utf8";
	private String user = "root";
	private String sql_password = "yuhui123.";

	public boolean queryExecute(String userId, String password) {
		boolean b = false;
		try {
			// 1、加载驱动
			Class.forName(driver);
			// 2、得到连接
			ct = DriverManager.getConnection(url, user, sql_password);
			// 3、创建ps
			String sql = "select * from user where userId = ? and password = ?";
			ps = ct.prepareStatement(sql);
			// 给？赋值
			ps.setString(1, userId);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next())
				b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			b = false;
		} finally {
			this.close();
		}
		return b;
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (ct != null)
				ct.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
