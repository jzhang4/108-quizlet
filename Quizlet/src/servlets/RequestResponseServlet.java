package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.AccountManager;
import user.DBConnection;
import user.User;

/**
 * Servlet implementation class RequestResponseServlet
 */
@WebServlet("/RequestResponseServlet")
public class RequestResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestResponseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		AccountManager am = (AccountManager) sc.getAttribute("AccountManager");
		DBConnection con = (DBConnection) sc.getAttribute("Connection");
		String user = request.getParameter("currUser").trim();
		String s = request.getParameter("sender").trim();
				
		String accepted = request.getParameter("AcceptRequest");
		String deleted = request.getParameter("DeleteRequest");
		
		User currUser = am.getAccount(user);
		User sender = am.getAccount(s);
		
		if (accepted != null) { //accepted

			currUser.acceptRequest(sender);
			sender.acceptedRequest(currUser);
			
			try {
				con.getStatement().executeUpdate("DELETE FROM requests WHERE senderID=" + sender.getID() + " AND recipientID = " + currUser.getID());
				con.getStatement().executeUpdate("INSERT INTO friends VALUES(" + sender.getID() + ", " + currUser.getID() + ")");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else { //deleted
					
			currUser.deleteRequest(sender);
			sender.deletedRequest(currUser);
			
			try {
				con.getStatement().executeUpdate("DELETE FROM requests WHERE senderID=" + sender.getID() + " AND recipientID = " + currUser.getID());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("user", am.getAccount(user));
		request.setAttribute("currUser", am.getAccount(user));
		request.setAttribute("am", am);
		RequestDispatcher rd = request.getRequestDispatcher("HomepageUser.jsp");
		rd.forward(request, response);
		
		
	}

}
