package pl.coderslab.warsztat2.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import pl.coderslab.warsztat2.model.Excercise;

public class ExcerciseApp {

	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/krks02_warsztat2?useSSL=false", "root", "coderslab")) {
			//testSave(conn);
			//testGetById(conn);
			//testLoadAllExcercise(conn);
			testDelete(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void testSave(Connection conn) throws SQLException {
		Excercise excercise = new Excercise("Math", "Radius");
		excercise.save(conn);
		System.out.println("Zapisano zadanie!");
	}
	
	private static void testGetById(Connection conn) throws SQLException {
		Excercise excercise = Excercise.getById(1, conn);
		System.out.println(excercise);
	}
	
	private static void testLoadAllExcercise(Connection conn) throws SQLException {
		Excercise[] excercises = Excercise.loadAllExcercise(conn);
		for (Excercise e : excercises)
			System.out.println(e);
	}
	
	private static void testDelete(Connection conn) throws SQLException {
		Excercise excercise = Excercise.getById(4, conn);
		excercise.delete(conn);
		System.out.println("Usunięto zadanie!");
	}
}
