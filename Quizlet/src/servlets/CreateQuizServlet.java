package servlets;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.simple.JSONObject;

import com.mysql.jdbc.Statement;

import user.DBConnection;
import quiz.JSONCreator;
import quiz.Quiz;
import quiz.ScoreBoard;

/**
 * Servlet implementation class CreateQuizServlet
 */
@WebServlet("/CreateQuizServlet")
public class CreateQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuizServlet() {
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
		
		//take out this line once we integrate with users
        session.setAttribute("username", "jaimiex");
        
		ServletContext context = getServletContext(); 
		DBConnection connect = (DBConnection)(context.getAttribute("Connection"));
		
		PreparedStatement pstmt = connect.getPreparedStatement();
		
		
		JSONObject obj = JSONCreator.jsonQuiz(quiz);
		StringWriter out = new StringWriter(); 
		obj.writeJSONString(out);
		
		String jsonText = out.toString();
		InputStream in = new ByteArrayInputStream(jsonText.getBytes());
		String name = quiz.getName();
		String username = (String)session.getAttribute("username");
		
		long time = System.currentTimeMillis();
		
		ScoreBoard sb = new ScoreBoard(); 
		byte[] sbytes = sb.boardToBytes();
		
		try {
			pstmt.setString(1, username);
			pstmt.setString(2, name);
			pstmt.setLong(3, 0);
			pstmt.setLong(4, time);
			pstmt.setBinaryStream(5, in);
			pstmt.setLong(6, 0);
			pstmt.setBytes(7, sbytes);
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in.close();
		RequestDispatcher dispatch = request.getRequestDispatcher("QuizSummaryPage.jsp");
		dispatch.forward(request, response);
	}

}
