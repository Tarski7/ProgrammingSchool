package pl.coderslab.warsztat2.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import pl.coderslab.warsztat2.model.Solution;

public class SolutionApp {

	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/krks02_warsztat2?useSSL=false", "root", "coderslab")) {
			//testSave(conn);
			//testGetById(conn);
			//testLoadAllSolution(conn);
			//testDelete(conn);
			//testLoadAllByExcerciseId(conn);
			testLoadAllByUserId(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void testSave(Connection conn) throws SQLException {
		Solution solution = new Solution(new Date(2018-01-18), new Date(2018-02-15), "Battle of Limanowa", 1, 4);
		solution.save(conn);
		System.out.println("Zapisano rozwiązanie!");
	}
	
	private static void testGetById(Connection conn) throws SQLException {
		Solution solution = Solution.getById(4, conn);
		System.out.println(solution);
	}
	
	private static void testLoadAllSolution(Connection conn) throws SQLException {
		Solution[] solutions = Solution.loadAllSolution(conn);
		for (Solution s : solutions)
			System.out.println(s);
	}
	
	private static void testDelete(Connection conn) throws SQLException {
		Solution solution = Solution.getById(8, conn);
		solution.delete(conn);
		System.out.println("Usunięto rozwiązanie!");
	}
	
	private static void testLoadAllByUserId(Connection conn) throws SQLException {
		Solution[] solutions = Solution.loadAllByUserId(4, conn);
		for (Solution s : solutions)
			System.out.println(s);
	}
	
	private static void testLoadAllByExcerciseId(Connection conn) throws SQLException {
		Solution[] solutions = Solution.loadAllByExerciseId(2, conn);
		for (Solution s : solutions)
			System.out.println(s);
	}
}
