package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quiz.MAQuestion;
import quiz.QRQuestion;
import quiz.Quiz;

/**
 * Servlet implementation class MAServlet
 */
@WebServlet("/MAServlet")
public class MAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MAServlet() {
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
		int numanswers = Integer.parseInt(request.getParameter("num"));
		
		boolean ordered = false; 
		if (request.getParameterValues("ordered") != null) ordered = true; 
		
		MAQuestion question = new MAQuestion(questname, numanswers, ordered);
		for (int i = 1; i < 6; i++) {
			String ans = request.getParameter("choice"+i);
			if (!ans.equals("")) question.addAnswer(ans);
		}
		quiz.addQuestion(question);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("QuestionTypeForm.html");
		dispatch.forward(request, response);
	}

}
