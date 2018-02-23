package pl.coderslab.warsztat2.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import pl.coderslab.warsztat2.model.UserGroup;
import pl.coderslab.warsztat2.model.Users;

public class UserGroupApp {

	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/krks02_warsztat2?useSSL=false", "root", "coderslab")) {
			testSave(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void testSave(Connection conn) throws SQLException {
		UserGroup ugroup = new UserGroup("krk03");
		ugroup.save(conn);
		System.out.println("Zapisano grupę!");
	}
}
