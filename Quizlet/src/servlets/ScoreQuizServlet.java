package servlets;

import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import quiz.Question;
import quiz.Quiz;
import quiz.ScoreBoard;
import user.DBConnection;

/**
 * Servlet implementation class ScoreQuizServlet
 */
@WebServlet("/ScoreQuizServlet")
public class ScoreQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreQuizServlet() {
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
        
        for (int i = 1; i <= quiz.getSize(); i++) {
        	Question q = quiz.getQuestion(i);
        	int points = q.checkAnswer(request.getParameterValues("answer"+i));

    		int score = (int)session.getAttribute("score");
    		session.setAttribute("score", score + points);
        }
        
        ServletContext context = getServletContext(); 
		DBConnection connect = (DBConnection)(context.getAttribute("Connection"));

        
        String name = quiz.getName();
        String username = (String)session.getAttribute("username");
        int score = (int)session.getAttribute("score");
        
        Statement stmt = connect.getStatement();
        PreparedStatement pstmt = connect.getPreparedStatement2();
        
        try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM quizzes");
			
			while(rs.next()) {
				String quizname = rs.getString(2);
				if (quizname.equals(name)) {
					Blob boardblob = rs.getBlob(7);
					ScoreBoard sb = new ScoreBoard(boardblob);
					long newtime = System.currentTimeMillis(); 
					long time = newtime - (long)session.getAttribute("time");
					time /= 1000; 
					
					sb.addScore(username, score, time, newtime);
					byte[] sbytes = sb.boardToBytes();
					
					pstmt.setBytes(1, sbytes);
					pstmt.setString(2, name);
					pstmt.execute();
					
					long bestscore = rs.getLong(6); 
					if (score > bestscore) stmt.executeUpdate("UPDATE quizzes SET highscore = "+score+" WHERE name = \""+name+"\"");
					break; 
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        RequestDispatcher dispatch = request.getRequestDispatcher("DisplayScore.jsp");
		dispatch.forward(request, response);
	}

}
