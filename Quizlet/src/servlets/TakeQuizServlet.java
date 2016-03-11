package servlets;

import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

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
import user.DBConnection;

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
		ServletContext context = getServletContext(); 
		DBConnection connect = (DBConnection)(context.getAttribute("Connection"));
		
		if (quiz.isRandom()) quiz.shuffleQuiz();
		Iterator<Question> it = quiz.iterator();
		session.setAttribute("iterator", it);
		
		session.setAttribute("index", 1);
		
		session.setAttribute("score", 0);
		
		long time = System.currentTimeMillis();
		session.setAttribute("time", time);

        Statement stmt = connect.getStatement();

        try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM quizzes");
			
			while(rs.next()) {
				String quizname = rs.getString(2);
		
				if (quizname.equals(quiz.getName())) {
					long taken = rs.getLong(3); 
					taken++;
					stmt.executeUpdate("UPDATE quizzes SET numtaken = "+taken+" WHERE name = \""+quizname+"\"");
					
					break; 
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
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
