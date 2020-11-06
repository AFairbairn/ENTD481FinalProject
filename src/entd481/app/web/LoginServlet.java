package entd481.app.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entd481.app.database.User;
import entd481.app.database.UserDAO;

/**
 * Servlet for controlling and displaying the login page
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDAO userDAO = null; 
	
    public LoginServlet() {
    	userDAO = new UserDAO();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/login.jsp").forward(request, response);
    	return;
	}
    
    // Forwards if user succesfully logged in
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String destination = "login.jsp";
        
		if(username.isEmpty() || password.isEmpty()){
			String message = "Please give both a username and password.";
			request.setAttribute("message", message);
		} else {
			try {
				User auth = userDAO.checkLogin(username, password);
				if (auth != null) {
					HttpSession session = request.getSession(true);
					session.setMaxInactiveInterval(600);//in seconds
					session.setAttribute("auth", auth);
					destination = "/WEB-INF/home.jsp";
				} else {
					String message = "Oops! Something went wrong, please try again.";
					request.setAttribute("message", message);
				}

			} catch (SQLException | ClassNotFoundException ex) {
				throw new ServletException(ex);
			}

		}
        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
        return;
	}

}
