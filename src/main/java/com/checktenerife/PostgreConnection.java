package com.checktenerife;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConnection {

	private static String DATABASE = "bigdata"; //selección de la BD
	private static String USER = "ull"; //usuario de la BD
	private static String PASSWORD = "ull"; //contraseña de la BD
	private static String DRIVER = "org.postgresql.Driver";
	private static String LINK = "jdbc:postgresql://localhost/" + DATABASE; //dirección de la BD

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		return DriverManager.getConnection(LINK, USER, PASSWORD);
	}
}
