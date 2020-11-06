package entd481.app.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Login filter for ensuring access only to logged in users
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
       
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String path= request.getRequestURI();
	    if(path.endsWith(".css")){
	        chain.doFilter(request,response);
	        return;
	      }
		if (session == null || session.getAttribute("auth") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
		    dispatcher.forward(request, response);
		    return;
		}
		
		chain.doFilter(req, res);
		return;
	}

}
