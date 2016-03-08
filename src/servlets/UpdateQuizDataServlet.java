package servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.PreparedStatement;
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

import org.json.simple.JSONObject;

import quiz.DBConnection;
import quiz.JSONCreator;
import quiz.Question;
import quiz.Quiz;

/**
 * Servlet implementation class UpdateQuizDataServlet
 */
@WebServlet("/UpdateQuizDataServlet")
public class UpdateQuizDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateQuizDataServlet() {
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
		
		PreparedStatement pstmt = connect.getPreparedStatement();
		
		Statement stmt = connect.getStatement();
		
		JSONObject obj = JSONCreator.jsonQuiz(quiz);
		StringWriter out = new StringWriter(); 
		obj.writeJSONString(out);
		
		String jsonText = out.toString();
		InputStream in = new ByteArrayInputStream(jsonText.getBytes());
		String name = quiz.getName();
		String username = "jaimiex";
		
		try {
			stmt.executeUpdate("DELETE FROM quizzes WHERE name = \"" + name +"\"");
			pstmt.setString(1, username);
			pstmt.setString(2, name);
			pstmt.setBinaryStream(3, in);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in.close();
        RequestDispatcher dispatch = request.getRequestDispatcher("DisplayQuiz.jsp");
		dispatch.forward(request, response);
	}

}
