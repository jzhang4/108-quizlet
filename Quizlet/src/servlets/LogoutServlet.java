package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import administration.Achievements;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RUNNING DO GET");
		ServletContext sc = getServletContext();

		long loginTime = ((long)sc.getAttribute("loginTime"));
		long logoutTime = System.currentTimeMillis();
		long timeDiffForAchievement = 0;//1*10^7;
		System.out.println(timeDiffForAchievement);
		if (logoutTime - loginTime >= timeDiffForAchievement){
			Achievements achieveContainer = (Achievements)(request.getServletContext()).getAttribute("achieveLookUp");
			if (achieveContainer != null){
				String userName = (String)((request.getSession()).getAttribute("user"));
				achieveContainer.doUpdate("No Sunlight", userName);
			} else{
				System.err.println("Things went terribly wrong");
			}
		}
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("HomepageLogin.html");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println("RUNNING DO POST");
	}

}
