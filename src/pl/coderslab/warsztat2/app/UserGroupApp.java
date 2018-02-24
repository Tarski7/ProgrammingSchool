package pl.coderslab.warsztat2.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import pl.coderslab.warsztat2.model.UserGroup;

public class UserGroupApp {

	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/krks02_warsztat2?useSSL=false", "root", "coderslab")) {
			//testSave(conn);
			//testGetById(conn);
			//testDelete(conn);
			testLoadAllUserGroup(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void testSave(Connection conn) throws SQLException {
		UserGroup ugroup = new UserGroup("krk04");
		ugroup.save(conn);
		System.out.println("Zapisano grupę!");
	}
	
	private static void testGetById(Connection conn) throws SQLException {
		UserGroup userGroup = UserGroup.getById(2, conn);
		System.out.println(userGroup);
	}
	
	private static void testLoadAllUserGroup(Connection conn) throws SQLException {
		UserGroup[] userGroup = UserGroup.loadAllUserGroup(conn);
		for (UserGroup ug : userGroup)
			System.out.println(ug);
	}
	
	private static void testDelete(Connection conn) throws SQLException {
		UserGroup userGroup = UserGroup.getById(3, conn);
		userGroup.delete(conn);
		System.out.println("Usunięto grupę!");
	}
}
