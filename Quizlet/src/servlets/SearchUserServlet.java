package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.AccountManager;
import user.User;

/**
 * Servlet implementation class SearchUserServlet
 */
@WebServlet("/SearchUserServlet")
public class SearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUserServlet() {
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
		boolean userExists = false;
		
		String currUser = request.getParameter("currUser");
		String user = request.getParameter("user");
		for (User u : am.accounts) {
			if (user.equals(u.getUserName())) {
				userExists = true;
				request.setAttribute("user", u);
				request.setAttribute("currUser", currUser);
				RequestDispatcher rd = request.getRequestDispatcher("UserPage.jsp");
				rd.forward(request, response);
			}
		}
		//user does not exist
		if (!userExists) {
			request.setAttribute("error", user);
			RequestDispatcher rd = request.getRequestDispatcher("HomepageUser.jsp");
			rd.forward(request, response);
		}
	}

}
