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
 * Servlet implementation class TakeQuizServlet
 */
@WebServlet("/TakeQuizServlet")
public class TakeQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeQuizServlet() {
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
		
		if (quiz.isRandom()) quiz.shuffleQuiz();
		Iterator<Question> it = quiz.iterator();
		session.setAttribute("iterator", it);
		
		session.setAttribute("index", 1);
		
		session.setAttribute("score", 0);
		
		long time = System.currentTimeMillis();
		session.setAttribute("time", time);
		
		if (it.hasNext()) {
			RequestDispatcher dispatch;
			if (quiz.isMulti()) dispatch = request.getRequestDispatcher("DisplayQuestion.jsp");
			else dispatch = request.getRequestDispatcher("DisplaySingle.jsp");
			dispatch.forward(request, response);
		} else {
			RequestDispatcher dispatch = request.getRequestDispatcher("NewQuizForm.html");
			dispatch.forward(request, response);
		}
	}

}
