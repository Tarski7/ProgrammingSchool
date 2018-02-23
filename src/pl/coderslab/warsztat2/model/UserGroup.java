package pl.coderslab.warsztat2.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserGroup {

	private int id = 0;
	private String name;
	
	public UserGroup() {
		
	}
	
	public UserGroup(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public void save(Connection conn) throws SQLException {
		if (this.id == 0) {
			final String[] generatedKeys = {"id"};
			final String sql = "INSERT INTO user_group(id, name) "
					+ "VALUES(default, ?);";
			PreparedStatement ps = conn.prepareStatement(sql, generatedKeys);
			ps.setString(1, this.name);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} else {
			final String sql = "UPDATE user_group SET name=? WHERE id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.name);
			ps.setInt(2, this.id);
			ps.executeUpdate();
			ps.close();
		}
	}
	
	//	Wczytanie użytkownika z bazy danych po Id
	public static UserGroup getById(int id, Connection conn) throws SQLException {
		UserGroup userGroup = null;
		if (id > 0) {
			final String sql = "SELECT id, name "
					+ "FROM user_group WHERE id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userGroup = new UserGroup();
				userGroup.id = rs.getInt("id");
				userGroup.name = rs.getString("name");
			}
			rs.close();
			ps.close();
		}
		return userGroup;
	}
	
	//	Metoda wczytująca wiele obiektów z bazy danych
	public static UserGroup[] loadAllUserGroup(Connection conn) throws SQLException {
		ArrayList<UserGroup> listUserGroup = new ArrayList<UserGroup>();
		String sql = "SELECT * FROM user_group;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			UserGroup userGroup = new UserGroup();
			userGroup.id = rs.getInt("id");
			userGroup.name = rs.getString("name");
			listUserGroup.add(userGroup);
		}
		UserGroup[] uArray = new UserGroup[listUserGroup.size()];
		uArray = listUserGroup.toArray(uArray);
		return uArray;
	}
	
	public void delete(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM user_group WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, this.id);
			ps.executeUpdate();
			this.id = 0;
		}
	}
	
	@Override
	public String toString() {
		return "UserGroup [id=" + id + ", name=" + name + "]";
	}
	
}
