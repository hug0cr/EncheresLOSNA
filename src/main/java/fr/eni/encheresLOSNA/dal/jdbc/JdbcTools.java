/**
 * 
 */
package fr.eni.encheresLOSNA.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.encheresLOSNA.dal.Settings;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 10:22:30
 */
public class JdbcTools {
	private static String urldb = Settings.getProperties("url");
	private static String userdb = Settings.getProperties("user");
	private static String passworddb = Settings.getProperties("password");
	private static Connection con;

	public static Connection getConnection() throws SQLException {
		if (con == null) {
			con = DriverManager.getConnection(urldb, userdb, passworddb);
		}
		return con;
	}

	public static void closeConnection() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}
}
