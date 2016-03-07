package servlets;

import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import quiz.DBConnection;
import quiz.JSONCreator;
import quiz.Quiz;

/**
 * Servlet implementation class TakeDataQuizServlet
 */
@WebServlet("/TakeDataQuizServlet")
public class TakeDataQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeDataQuizServlet() {
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
		ServletContext context = getServletContext(); 
		DBConnection connect = (DBConnection)(context.getAttribute("Connection"));
		
		HttpSession session = request.getSession();
        
        	String name = request.getParameter("quizname");
        
        	Statement stmt = connect.getStatement();
        	String quizstr = "";
        	try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM quizzes");
			
			while(rs.next()) {
				String quizname = rs.getString("name");
				if (quizname.equals(name)) {
					Blob quizblob = rs.getBlob("quiz");
					byte[] bdata = quizblob.getBytes(1, (int)quizblob.length());
					quizstr = new String(bdata);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        	JSONParser parser = new JSONParser(); 
        
        	Quiz quiz =null; 
        
        	try {
			Object obj = parser.parse(quizstr);
			JSONObject jobj = (JSONObject)obj;
			quiz = JSONCreator.getQuiz(jobj);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        	session.setAttribute("quiz", quiz);
        	RequestDispatcher dispatch = request.getRequestDispatcher("TakeQuizServlet");
		dispatch.forward(request, response);
        
	}

}
