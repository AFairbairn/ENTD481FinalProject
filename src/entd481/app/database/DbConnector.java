package entd481.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Class for creating database connection
public class DbConnector {
    final static String CONNECTION_URL = "jdbc:mysql://localhost:3306/authority";
    final static String USER = "Admin";
    final static String PASSWORD = "Password123";
    final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    static Connection con = null;

    public static Connection getCon(){
        try {
        	Class.forName(DRIVER);
        	try {
			con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
    }
}
