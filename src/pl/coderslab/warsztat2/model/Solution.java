package pl.coderslab.warsztat2.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Solution {

	private int id = 0;
	private Date created;
	private Date updated;
	private String description = "";
	private int excerciseId;
	private int usersId;
	
	public Solution() {
		
	}

	public Solution(Date created, Date updated, String description, int excerciseId, int usersId) {
		this.created = created;
		this.updated = updated;
		this.description = description;
		this.excerciseId = excerciseId;
		this.usersId = usersId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getExcerciseId() {
		return excerciseId;
	}

	public void setExcerciseId(int excerciseId) {
		this.excerciseId = excerciseId;
	}

	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}

	public int getId() {
		return id;
	}
	
	public void save(Connection conn) throws SQLException {
		if (this.id == 0) {
			final String[] generatedKeys = {"id"};
			final String sql = "INSERT INTO solution(id, created, updated, description, excercise_id, users_id) "
					+ "VALUES(default, ?, ?, ?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(sql, generatedKeys);
			ps.setDate(1, new java.sql.Date (this.created.getTime()));
			ps.setDate(2, new java.sql.Date (this.updated.getTime()));
			ps.setString(3, this.description);
			ps.setInt(4, this.excerciseId);
			ps.setInt(5, this.usersId);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} else {
			final String sql = "UPDATE solution SET updated=?, description=?, excercise_id=? "
					+ " users_id=? WHERE id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date (this.updated.getTime()));
			ps.setString(2, this.description);
			ps.setInt(3, this.excerciseId);
			ps.setInt(4, this.usersId);
			ps.setInt(5, this.id);
			ps.executeUpdate();
			ps.close();
		}
	}
	
	public static Solution getById(int id, Connection conn) throws SQLException {
		Solution solution = null;
		if (id > 0) {
			final String sql = "SELECT id, created, updated, description, excercise_id, users_id "
					+ "FROM solution WHERE id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				solution = new Solution();
				solution.id = rs.getInt("id");
				solution.created = rs.getDate("created");
				solution.updated = rs.getDate("updated");
				solution.description = rs.getString("description");
				solution.excerciseId = rs.getInt("excercise_id");
				solution.usersId = rs.getInt("users_id");
			}
			rs.close();
			ps.close();
		}
		return solution;
	}
	
	public static Solution[] loadAllSolution(Connection conn) throws SQLException {
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String sql = "SELECT * FROM solution;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Solution solution = new Solution();
			solution.id = rs.getInt("id");
			solution.created = rs.getDate("created");
			solution.updated = rs.getDate("updated");
			solution.description = rs.getString("description");
			solution.excerciseId = rs.getInt("excercise_id");
			solution.usersId = rs.getInt("users_id");
			solutions.add(solution);
		}
		Solution[] uArray = new Solution[solutions.size()];
		uArray = solutions.toArray(uArray);
		return uArray;
	}
	
	public void delete(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM solution WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, this.id);
			ps.executeUpdate();
			this.id = 0;
		}
	}
	
	public static Solution[] loadAllByUserId(int userId, Connection conn) throws SQLException {
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String sql = "SELECT * FROM solution WHERE users_id=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Solution solution = new Solution();
			solution.id = rs.getInt("id");
			solution.created = rs.getDate("created");
			solution.updated = rs.getDate("updated");
			solution.description = rs.getString("description");
			solution.excerciseId = rs.getInt("excercise_id");
			solution.usersId = rs.getInt("users_id");
			solutions.add(solution);
		}
		Solution[] uArray = new Solution[solutions.size()];
		uArray = solutions.toArray(uArray);
		return uArray;
	}
	
	public static Solution[] loadAllByExerciseId(int excerciseId, Connection conn) throws SQLException {
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String sql = "SELECT * FROM solution WHERE excercise_id=? ORDER BY created DESC;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, excerciseId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Solution solution = new Solution();
			solution.id = rs.getInt("id");
			solution.created = rs.getDate("created");
			solution.updated = rs.getDate("updated");
			solution.description = rs.getString("description");
			solution.excerciseId = rs.getInt("excercise_id");
			solution.usersId = rs.getInt("users_id");
			solutions.add(solution);
		}
		Solution[] uArray = new Solution[solutions.size()];
		uArray = solutions.toArray(uArray);
		return uArray;
	}

	@Override
	public String toString() {
		return "Solution [id=" + id + ", created=" + created + ", updated=" + updated + ", description=" + description
				+ ", excerciseId=" + excerciseId + ", usersId=" + usersId + "]";
	}
	
}
