package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
	static Connection connection;
	static Statement stat;

	// INSERT INTO tabloadı (name, email, phone) VALUES ('John Smith','john@example.com', '555-1234');
	public static void insert(String query) {
		try {
			Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab",
					"root", "1907");
			Statement stat = (Statement) connection.createStatement();
			stat.executeUpdate(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// UPDATE tabloadı set ogrno WHERE okul=kocaeli;
	public static void update(String query) {
		try {
			Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab",
					"root", "1907");
			Statement stat = (Statement) connection.createStatement();
			stat.executeUpdate(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// DELETE * FROM tabloadı Where ogrno=3;
	public static void delete(String query) {
		try {
			Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/prolab",
					"root", "1907");
			Statement stat = (Statement) connection.createStatement();
			stat.executeUpdate(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}








