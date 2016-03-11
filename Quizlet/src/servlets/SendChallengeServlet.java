package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.lang.Math.toIntExact;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.AccountManager;
import user.DBConnection;
import user.Message;
import user.User;

/**
 * Servlet implementation class SendChallengeServlet
 */
@WebServlet("/SendChallengeServlet")
public class SendChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendChallengeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext sc = getServletContext();
		AccountManager am = (AccountManager) sc.getAttribute("AccountManager");
		DBConnection con = (DBConnection) sc.getAttribute("Connection");
		
		User cu = am.getAccount((String)session.getAttribute("user"));
		User u = am.getAccount(request.getParameter("recipient"));
		
		int uID = u.getID();
		
		if (cu.isFriends(uID)) {
			String quiz = request.getParameter("quizname");
			Long score = Long.valueOf(request.getParameter("score")).longValue();
			
			
			Message m = new Message("Challenge", cu.getUserName(), u.getUserName(), null, null, -1, false, quiz, score);
			
			try {
				con.getStatement().executeUpdate("INSERT INTO messages (type, sender, recipient, subject, message, quiz, score) VALUES(\"Challenge\", \"" + cu.getUserName() + "\", \"" + u.getUserName() 
				+ "\", \"" + m.getSubject() + "\", \""+ m.getMessage() + "\", \"" + quiz + "\", " + toIntExact(score) + ");");
				ResultSet rs = con.getStatement().executeQuery("SELECT * FROM messages WHERE sender = \"" + cu.getUserName() + "\" AND recipient=\"" + u.getUserName() + "\" AND message= \"" + m.getMessage() + "\";");
				if (rs.next()) {
					int ID = rs.getInt("id");
					m.setID(ID);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cu.addSentMessage(m);
			u.addReceivedMessage(m);
			request.setAttribute("user", u);
			request.setAttribute("currUser", cu);
			
			RequestDispatcher rd = request.getRequestDispatcher("QuizSummaryPage.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("error", "You can only send challenges to friends!");
			
			RequestDispatcher rd = request.getRequestDispatcher("QuizSummaryPage.jsp");
			rd.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
