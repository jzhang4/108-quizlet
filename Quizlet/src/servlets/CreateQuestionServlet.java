package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quiz.Quiz;

/**
 * Servlet implementation class CreateQuestionServlet
 */
@WebServlet("/CreateQuestionServlet")
public class CreateQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuestionServlet() {
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
		String type = request.getParameter("qtype");
		RequestDispatcher dispatch; 
		switch (type) {
			case "qr": 
				dispatch = request.getRequestDispatcher("NewQRQuestion.html");
				dispatch.forward(request, response);
				break;
			case "fb":
				dispatch = request.getRequestDispatcher("NewFBQuestion.html");
				dispatch.forward(request, response);
				break;
			case "mc":
				dispatch = request.getRequestDispatcher("NewMCQuestion.html");
				dispatch.forward(request, response);
				break;
			case "pr":
				dispatch = request.getRequestDispatcher("NewPRQuestion.html");
				dispatch.forward(request, response);
				break;
			case "ma":
				dispatch = request.getRequestDispatcher("NewMAQuestion.html");
				dispatch.forward(request, response);
				break;
		}
	}

}
