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
import quiz.Quiz;

/**
 * Servlet implementation class MCServlet
 */
@WebServlet("/MCServlet")
public class MCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MCServlet() {
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
		String answer = request.getParameter("answer");
		
		ArrayList<String> choices = new ArrayList<String>(); 
		choices.add(request.getParameter("choice1"));
		choices.add(request.getParameter("choice2"));
		choices.add(request.getParameter("choice3"));
		choices.add(request.getParameter("answer"));
		
		MCQuestion question = new MCQuestion(questname, answer, choices);
		
		quiz.addQuestion(question);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("QuestionTypeForm.html");
		dispatch.forward(request, response);
	}

}
