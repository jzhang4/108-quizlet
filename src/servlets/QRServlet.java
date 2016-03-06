package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quiz.MCQuestion;
import quiz.QRQuestion;
import quiz.Quiz;

/**
 * Servlet implementation class QRServlet
 */
@WebServlet("/QRServlet")
public class QRServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QRServlet() {
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
		
		String questname = request.getParameter("question");
		String answer = request.getParameter("answer1");
		
		QRQuestion question = new QRQuestion(questname, answer);
		for (int i = 2; i < 5; i++) {
			String ans = request.getParameter("answer"+i);
			if (!ans.equals("")) question.addAnswer(ans);
		}
		quiz.addQuestion(question);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("QuestionTypeForm.html");
		dispatch.forward(request, response);
	}

}
