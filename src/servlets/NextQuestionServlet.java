package servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quiz.Question;
import quiz.Quiz;

/**
 * Servlet implementation class NextQuestionServlet
 */
@WebServlet("/NextQuestionServlet")
public class NextQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NextQuestionServlet() {
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
		Iterator<Question> it = (Iterator<Question>)(session.getAttribute("iterator"));	
		
		Question q = (Question)session.getAttribute("currentq");
		
		int points = q.checkAnswer(request.getParameterValues("answer"));

		int score = (int)session.getAttribute("score");
		session.setAttribute("score", score + points);
		
		request.setAttribute("points", points);
		
		if (quiz.isQuickCorrect()) {
			RequestDispatcher dispatch = request.getRequestDispatcher("QuickScore.jsp");
			dispatch.forward(request, response);
			return;
		}
		
		if (it.hasNext()) {
			RequestDispatcher dispatch = request.getRequestDispatcher("DisplayQuestion.jsp");
			dispatch.forward(request, response);
		} else {
			RequestDispatcher dispatch = request.getRequestDispatcher("DisplayScore.jsp");
			dispatch.forward(request, response);
		}
	}

}
