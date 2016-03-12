package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.AccountManager;
import user.DBConnection;
import user.User;

/**
 * Servlet implementation class ChangePrivacyServlet
 */
@WebServlet("/ChangePrivacyServlet")
public class ChangePrivacyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePrivacyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		AccountManager am = (AccountManager) sc.getAttribute("AccountManager");
		DBConnection con = (DBConnection) sc.getAttribute("Connection");
		HttpSession session = request.getSession();
		
		User cu = am.getAccount((String)session.getAttribute("user"));
		cu.changePrivacy();
		int changeTo = (cu.privacyOn()) ? 0 : 1;
		
		try {
			con.getStatement().executeUpdate("UPDATE users SET privacyOn=" + changeTo + " WHERE id=" + cu.getID());
		} catch (SQLException e) {
			e.printStackTrace();
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
