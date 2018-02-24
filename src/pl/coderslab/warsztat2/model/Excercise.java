package pl.coderslab.warsztat2.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Excercise {

	private int id = 0;
	private String title = "";
	private String description = "";
	
	public Excercise() {
		
	}

	public Excercise(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}
	
	public void save(Connection conn) throws SQLException {
		if (this.id == 0) {
			final String[] generatedKeys = {"id"};
			final String sql = "INSERT INTO excercise(id, title, description) "
					+ "VALUES(default, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(sql, generatedKeys);
			ps.setString(1, this.title);
			ps.setString(2, this.description);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} else {
			final String sql = "UPDATE excercise SET title=?, description=? "
					+ "WHERE id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.title);
			ps.setString(2, this.description);
			ps.setInt(3, this.id);
			ps.executeUpdate();
			ps.close();
		}
	}
	
	public static Excercise getById(int id, Connection conn) throws SQLException {
		Excercise excercise = null;
		if (id > 0) {
			final String sql = "SELECT id, title, description "
					+ "FROM excercise WHERE id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				excercise = new Excercise();
				excercise.id = rs.getInt("id");
				excercise.title = rs.getString("title");
				excercise.description = rs.getString("description");
			}
			rs.close();
			ps.close();
		}
		return excercise;
	}
	
	public static Excercise[] loadAllExcercise(Connection conn) throws SQLException {
		ArrayList<Excercise> excercises = new ArrayList<Excercise>();
		String sql = "SELECT * FROM excercise;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Excercise excercise = new Excercise();
			excercise.id = rs.getInt("id");
			excercise.title = rs.getString("title");
			excercise.description = rs.getString("description");
			excercises.add(excercise);
		}
		Excercise[] uArray = new Excercise[excercises.size()];
		uArray = excercises.toArray(uArray);
		return uArray;
	}
	
	public void delete(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM excercise WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, this.id);
			ps.executeUpdate();
			this.id = 0;
		}
	}

	@Override
	public String toString() {
		return "Excercise [id=" + id + ", title=" + title + ", description=" + description + "]";
	}
	
	
}
