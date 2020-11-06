package entd481.app.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entd481.app.database.User;
import entd481.app.database.UserDAO;

// User controller servlet for controlling all the User database related functions

@WebServlet(name = "UserManagement", urlPatterns = {"/UserManagement"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	UserDAO userDAO = null;     
 
    public UserController() {
        userDAO = new UserDAO();
    }

    // Calls methods based on URL action value
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action == null) {
			action = "LIST";
		}
		
		switch(action) {
			case "LIST":
				listUsers(request, response);
				break;
			case "EDIT":
				editUser(request, response);
				break;
			case "DELETE":
				deleteUser(request, response);
				break;
			case "EMAIL":
				emailUser(request, response);
				break;
			case "ADD":
				addUser(request, response);
				break;
			default:
				listUsers(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
	}

	// List users method calls DAE user list metod
	protected void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> list = userDAO.getUsers();
		
		request.setAttribute("list", list);
		
		dispatcher = request.getRequestDispatcher("/WEB-INF/userManagement.jsp");
		dispatcher.forward(request,  response);
	}
	
	// getUser from ID method calls DAO get by id method
	protected void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id =  request.getParameter("id");
		User user = null;
		try {
			user = userDAO.getById(Integer.parseInt(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("type", "Edit User");
		request.getSession().setAttribute("submitType", "Update");
		request.setAttribute("user", user);
		dispatcher = request.getRequestDispatcher("/AddUser");
		dispatcher.forward(request,  response);
	}
	
	// deleteUser method calls DAO delete user method
	protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id =  request.getParameter("id");
		if(userDAO.deleteUser(Integer.parseInt(id))) {
			request.setAttribute("status",  "User Deleted!");
		} else {
			request.setAttribute("status",  "Oops! Something went wrong.");
		}
		dispatcher = request.getRequestDispatcher("UserManagement?action=LIST");
		dispatcher.forward(request,  response);
	}
	
	// emailUser method calles emailservlet
	private void emailUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email =  request.getParameter("email");
		if (email != null) {
			request.setAttribute("email",  email);
		}
		request.getSession().setAttribute("message",  "");
		dispatcher = request.getRequestDispatcher("/Email");
		dispatcher.forward(request,  response);
	}
	
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("type", "Add User");
		request.getSession().setAttribute("submitType", "Add");
		dispatcher = request.getRequestDispatcher("/AddUser");
		dispatcher.forward(request,  response);
	}
	
}
