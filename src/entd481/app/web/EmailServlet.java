package entd481.app.web;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * Servlet for sending email and displaying email jsp page
 */
@WebServlet("/Email")
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/email.jsp");
        dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (sendEmail(request, response)) {
			request.getSession().setAttribute("message", "Email Sent!");
			request.setAttribute("email",  "");
		} else {
			request.getSession().setAttribute("message", "Mesage failed to send!");
		}
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/email.jsp");
		dispatcher.forward(request,  response);
    }
	
	// Returns true if the email successfully sent
	protected boolean sendEmail (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String from = request.getParameter("sessionEmail");
		String to = request.getParameter("recipient");
        String subject = request.getParameter("subject");
        String messageText = request.getParameter("message");
		boolean flag = false;
		
		// Setup for smtp server. Here using mailtrap to test.
		String host = "smtp.mailtrap.io";
		
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", "2525");
	    props.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
	    
	    
	    // Get the Session object.
	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	    	@Override protected PasswordAuthentication getPasswordAuthentication() {
	    	// mailtrap random gen auth for testing
	        return new PasswordAuthentication("c1b02bc049a3af", "c5ede660a61c76");
	    }});
		
	    try {
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	        message.setSubject(subject);
	        message.setText(messageText);
	        Transport.send(message);
	        flag = true;
	    } catch (MessagingException e) {
	    	e.printStackTrace();
	    }
	    
		return flag;
	}
}
