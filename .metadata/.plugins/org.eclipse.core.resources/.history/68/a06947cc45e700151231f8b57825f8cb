package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import user.AccountManager;
import user.DBConnection;


/**
 * Servlet implementation class CreateAccountServlet
 */
@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		AccountManager am = (AccountManager) sc.getAttribute("AccountManager");
		DBConnection con = (DBConnection) sc.getAttribute("Connection");
		
		request.setAttribute("am", am);
		
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		if (am.accountExists(user)) {
			request.setAttribute("user", user);
			request.setAttribute("currUser", am.getAccount(user));
			RequestDispatcher rd = request.getRequestDispatcher("username-taken.jsp");
			rd.forward(request, response);
		} else {
			am.newAccount(user, password, con.getStatement());
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("am", am.getAccount(user));
			request.setAttribute("currUser", am.getAccount(user));
			RequestDispatcher rd = request.getRequestDispatcher("HomepageUser.jsp");
			rd.forward(request, response);
		}
	}
}
