package pl.coderslab.warsztat2.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

import pl.coderslab.warsztat2.model.Users;

public class UsersApp {

	public static void main(String[] args) {
		//test();
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/krks02_warsztat2?useSSL=false", "root", "coderslab")) {
			//testSave(conn);
			//testGetById(conn);
			testLoadAllUsers(conn);
			//testDelete(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	private static void testDelete(Connection conn) throws SQLException {
		Users user = Users.getById(2, conn);
		user.delete(conn);
		System.out.println("Usunięto użytkownika!");
	}
	
	private static void testLoadAllUsers(Connection conn) throws SQLException {
		Users[] users = Users.loadAllUsers(conn);
		for (Users u : users)
			System.out.println(u);
	}

	private static void testSave(Connection conn) throws SQLException {
		//Users user = new Users("Maciek", "mkotek@gmail.pl", "kotek");
		//user.save(conn);
		//Users user2 = new Users("Maciek2", "mkotek2@gmail.pl", "kotek2");
		//user2.save(conn);
		//user2.setEmail("mkotek222@gmail.pl");
		//user2.save(conn);*/
		//Users user = new Users("Pudzian", "pudzianband@gmail.pl", "dominator");
		//user.save(conn);
		System.out.println("finished");
	}
	
	private static void testGetById(Connection conn) throws SQLException {
		Users user = Users.getById(2, conn);
		System.out.println(user);
	}

	static void test() {
		Users user = new Users("Maciek", "mkotek@gmail.pl", "kotek");
		System.out.println(user.getPassword());
		System.out.println(user.isPasswordCorrect("kote"));
		System.out.println(user.isPasswordCorrect("kotek"));
	}
}
