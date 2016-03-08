package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quiz.DBConnection;
import quiz.Quiz;

/**
 * Servlet implementation class QuizFormServlet
 */
@WebServlet("/QuizFormServlet")
public class QuizFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		
		ServletContext context = getServletContext(); 
		DBConnection connect = (DBConnection)(context.getAttribute("Connection"));

		Statement stmt = connect.getStatement(); 
		ResultSet rs;
		try {
			rs = stmt.executeQuery("SELECT * FROM quizzes");
			while (rs.next()) {
				String name2 = rs.getString("name");
				if (name.equals(name2)) {
					RequestDispatcher dispatch = request.getRequestDispatcher("UsedQuizName.html");
					dispatch.forward(request, response);
					return; 
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		String description = request.getParameter("description");
		Quiz quiz = new Quiz(name, description);
		HttpSession session = request.getSession(); 
		session.setAttribute("quiz", quiz);
		
		boolean random = false; 
		if (request.getParameterValues("random") != null) random = true;
		quiz.setRandom(random);
		
		boolean multi = false; 
		if (request.getParameterValues("multi") != null) multi = true;
		quiz.setPage(multi);
		
		boolean correct = false; 
		if (request.getParameterValues("correct") != null) correct = true;
		quiz.setCorrect(correct);
		
		boolean practice = false; 
		if (request.getParameterValues("practice") != null) practice = true;
		quiz.setPractice(practice);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("QuestionTypeForm.html");
		dispatch.forward(request, response);
	}

}
