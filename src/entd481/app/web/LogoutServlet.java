package entd481.app.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet for logging out a user
 */
@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // Forwards to login page after destroying the session if it still exists
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session !=null) {
			session.invalidate();
		}
		session = request.getSession(false);
		request.setAttribute("message", "Logged Out");
		RequestDispatcher dispatcher = request.getRequestDispatcher("./login");
	    dispatcher.forward(request, response);
	}

}
