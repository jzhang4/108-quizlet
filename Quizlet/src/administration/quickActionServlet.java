package administration;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class quickActionServlet
 */
@WebServlet("/quickActionServlet")
public class quickActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public quickActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Administrator values = (Administrator) session.getAttribute("currentStats");
		String typeOfAction = (String) request.getParameter("quickActionSelected");
		int successAdmin = -1;
		int successAnnounce = -1;
		int successRemove = -1;
		if (values == null){
			System.out.println("STUFF IS BAD");
		}
		if (typeOfAction.equals("admin")){		// trying to make somebody an administrator
			successAdmin = values.makeAdminUpdate((String)request.getParameter("username"));
		} else if(typeOfAction.equals("announce")){
			successAnnounce = values.logAnnounce((String)request.getParameter("newAnnouncement"));
		} else if (typeOfAction.equals("remove")){
			successRemove = values.removeUser((String) request.getParameter("userNameToRemove"));
		}
		request.setAttribute("successRemove", successRemove);
		request.setAttribute("successAdmin", successAdmin);
		request.setAttribute("successAnnounce", successAnnounce);
		String nextJSP = "/AdminHomePage.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
		
		
		
	}

}
