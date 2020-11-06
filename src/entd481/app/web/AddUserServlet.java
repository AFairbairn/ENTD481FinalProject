package entd481.app.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entd481.app.database.User;
import entd481.app.database.UserDAO;
/**
 * Servlet for invoking add and edit user functions and displaying the add user page
 */
@WebServlet(name = "AddUser", urlPatterns = {"/AddUser"})
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = null;
	
    public AddUserServlet() {
    	userDAO = new UserDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/addUser.jsp");
        dispatcher.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String fname = request.getParameter("fname");
	    String lname = request.getParameter("lname");
	    String email = request.getParameter("email");
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    RequestDispatcher dispatcher = null;
	        
	    if(fname.isEmpty() || lname.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()){
	    	request.setAttribute("message", "Please complete the whole form.");
			dispatcher = request.getRequestDispatcher("/WEB-INF/addUser.jsp");
			dispatcher.forward(request,  response);
		} else {
		    try {
		    	User u = new User();
		    	u.setFname(fname);
		    	u.setLname(lname);
		    	u.setEmail(email);
		    	u.setUsername(username);
		    	u.setPassword(password);
		    	
		    	if(id.isEmpty() || id == null) {
		    		if (userDAO.addUser(u)) {
		    			request.getSession().setAttribute("status", "User Added.");
		    		}
		    	} else {
		    		u.setId(Integer.parseInt(id));
		    		if(userDAO.updateUser(u)) {
		    			request.getSession().setAttribute("status", "User Updated.");
		    		}
		    	}

		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
	    dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
		dispatcher.forward(request,  response);
		return;
	}

}
