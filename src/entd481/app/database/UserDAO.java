package entd481.app.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// User data access object
public class UserDAO {
	Connection connect = DbConnector.getCon();
	PreparedStatement statement = null;
	ResultSet result = null;
	String sql = null;
	User user = null;	
	
	// Checks if user is in the database returns user object if user found
    public User checkLogin(String username, String password) throws SQLException,
    ClassNotFoundException {
        user = null;
    	connect = DbConnector.getCon();
		
		sql = "SELECT * FROM users WHERE username = ? and userpass = ?";
		statement = connect.prepareStatement(sql);
		statement.setString(1, username);
		statement.setString(2, password);
		
		result = statement.executeQuery();

		if (result.next()) {
		    user = new User();
		    user.setFname(result.getString("fname"));
		    user.setLname(result.getString("lname"));
		    user.setEmail(result.getString("email"));
		}
		
		connect.close();
		
		return user;
    }
    
    // addUser method returns true if successfully added
    public boolean addUser(User u) throws SQLException {
    	boolean flag = false;
    	try {
	    	connect = DbConnector.getCon();
			
			sql = "INSERT INTO users(fname, lname, username, userpass, email) VALUES (?, ?, ?, ?, ?)";
			statement = connect.prepareStatement(sql);
			statement.setString(1, u.getFname());
			statement.setString(2, u.getLname());
			statement.setString(3, u.getUsername());
			statement.setString(4, u.getPassword());
			statement.setString(5, u.getEmail());
			
			statement.executeUpdate();
			flag = true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}

		connect.close();
		return flag;
    }
    
    // Creates a list of all users in the database
    public List<User> getUsers() {
    	List<User> list = null;
    	Statement stmt = null;
    	try {
    		connect = DbConnector.getCon();
    		list = new ArrayList<User>();
    		sql = "SELECT * FROM users";
    		stmt = connect.createStatement();
    		result = stmt.executeQuery(sql);
    		while(result.next()) {
    			user = new User();
    			user.setId(result.getInt("id"));
    			user.setFname(result.getString("fname"));
    			user.setLname(result.getString("lname"));
    			user.setUsername(result.getString("username"));
    			user.setEmail(result.getString("email"));
    			list.add(user);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    
    // find user in database by ID returns user object if found
    public User getById(int id) throws SQLException {
        
    	connect = DbConnector.getCon();
    	user = new User();
		
		sql = "SELECT * FROM users WHERE id = ?";
		statement = connect.prepareStatement(sql);
		statement.setInt(1, id);
		
		result = statement.executeQuery();
		
		if (result.next()) {
			user.setId(result.getInt("id"));
		    user.setFname(result.getString("fname"));
		    user.setLname(result.getString("lname"));
		    user.setUsername(result.getString("username"));
		    user.setPassword(result.getString("userpass"));
		    user.setEmail(result.getString("email"));
		}
		
		connect.close();
		
		return user;
    }
    
    // Update a user in the database using user object returns true if successful
    public boolean updateUser(User u) {
    	boolean flag = false;
    	try {
    		sql = "UPDATE users SET fname='"+u.getFname()+"', lname='"+u.getLname()+"', username='"+u.getUsername()+"', userpass='"+u.getPassword()+"',  email='"+u.getEmail()+"' where id='"+u.getId()+"'";
    		connect = DbConnector.getCon();
    		statement = connect.prepareStatement(sql);
    		statement.executeUpdate();
    		flag = true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
		return flag;
    }
    
    // delete user from database from id returns true if successful
    public boolean deleteUser(int id) {
    	boolean flag = false;
    	try {
    		sql = "DELETE FROM users WHERE id="+id;
    		connect = DbConnector.getCon();
    		statement = connect.prepareStatement(sql);
    		statement.executeUpdate();
    		flag = true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
		return flag;
    }
}

