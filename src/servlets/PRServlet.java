package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quiz.FBQuestion;
import quiz.PRQuestion;
import quiz.Quiz;

/**
 * Servlet implementation class PRServlet
 */
@WebServlet("/PRServlet")
public class PRServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PRServlet() {
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
		
		String url = request.getParameter("url");
		String answer = request.getParameter("answer1");
		
		PRQuestion question = new PRQuestion(url, answer);
		for (int i = 2; i < 5; i++) {
			String ans = request.getParameter("answer"+i);
			if (!ans.equals("")) question.addAnswer(ans);
		}
		quiz.addQuestion(question);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("QuestionTypeForm.html");
		dispatch.forward(request, response);
	}

}
