package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.DBConnection;
import user.Message;
import java.sql.*;
import user.AccountManager;
import user.User;

/**
 * Servlet implementation class ViewMessageServlet
 */
@WebServlet("/ViewMessageServlet")
public class ViewMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext sc = getServletContext();
		DBConnection con = (DBConnection)sc.getAttribute("Connection");
		AccountManager am = (AccountManager) sc.getAttribute("AccountManager");
		
		int id = Integer.valueOf(request.getParameter("id"));
		String currUser = request.getParameter("currUser");
		User u = am.getAccount(currUser);
		
		
		request.setAttribute("currUser", u);
		request.setAttribute("user", u);
		String sender = "";
		String type = "";
		String recipient = "";

		
		try {
			ResultSet rs = con.getStatement().executeQuery("SELECT * FROM messages WHERE id=" + id);
			while (rs.next()) {
				type = rs.getString("type");

				sender = rs.getString("sender");
				recipient = rs.getString("recipient");
				String subject = rs.getString("subject");
				String message = rs.getString("message");
				String quiz = rs.getString("quiz");
				Long score = rs.getLong("score");
				Message m = new Message(type, sender, recipient, message, subject, id, true, quiz, score);
				
				User recipientUser = am.getAccount(recipient);
				recipientUser.markRead(m);
				
				request.setAttribute("Message", m);
			}
			if (type.equals("Request")) {
				int recipientID = am.getAccount(recipient).getID();
				int senderID = am.getAccount(sender).getID();
								
				rs = con.getStatement().executeQuery("SELECT * FROM requests WHERE senderID=" + senderID + " AND recipientID=" + recipientID);
				if (rs.next()) {
					request.setAttribute("Request", true);
				}
			}
			
			
			if (!u.getUserName().equals(sender)) {
				con.getStatement().executeUpdate("UPDATE messages SET recipientRead=1 WHERE id=" + id);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("MessagePage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
