package servlets;

import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
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

import quiz.Question;
import quiz.Quiz;
import quiz.ScoreBoard;
import user.DBConnection;

/**
 * Servlet implementation class ScoreQuizServlet
 */
@WebServlet("/ScoreQuizServlet")
public class ScoreQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreQuizServlet() {
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
		HttpSession session = request.getSession();
        Quiz quiz = (Quiz)(session.getAttribute("quiz"));
        
        //take out this line once we integrate with users        
        for (int i = 1; i <= quiz.getSize(); i++) {
        	Question q = quiz.getQuestion(i);
        	
        	//error handling for empty answers -Serena
        	if (request.getParameterValues("answer"+i) == null) {
        		request.setAttribute("error", "You left a question blank!");
        		session.setAttribute("quiz", quiz);
        		RequestDispatcher dispatch = request.getRequestDispatcher("/TakeQuizServlet");
        		dispatch.forward(request, response);
        	}
        	
        	int points = q.checkAnswer(request.getParameterValues("answer"+i));

    		int score = (int)session.getAttribute("score");
    		session.setAttribute("score", score + points);
        }
        
        RequestDispatcher dispatch = request.getRequestDispatcher("DisplayScore.jsp");
		dispatch.forward(request, response);
	}

}
