package userPhotos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.omg.CORBA.portable.OutputStream;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final String path = "/Users/liamneath/Downloads/ImageStorage/"; // YOU MUST CHANGE THIS FOR IT TO WORK ON YOUR COMPUTER
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
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
		// TODO Auto-generated method stub
	    Part filePart = request.getPart("file"); 				  // Retrieves <input type="file" name="file">
	    InputStream fileContent = filePart.getInputStream();
	    byte[] buffer = new byte [fileContent.available()];			// byte buffer to hold the encoding data
	    fileContent.read(buffer);
	    String newFile = path + request.getParameter("imageName") + ".jpg";	// this is where the file will be mapped to 
	    // System.out.println(newFile);
	    File targetFile = new File(newFile);						// create a new file in the specified directory 
	    FileOutputStream outStream = new FileOutputStream(targetFile);
	    outStream.write(buffer);
	    outStream.close();		
	    
	    UserPhoto photoLoader = (UserPhoto)(request.getServletContext()).getAttribute("photoAssign");
	    photoLoader.setToCustom(request.getParameter("imageName"));
	    
	    // writing to that file and closing the stream right after
	    String nextJSP = "/HomepageUser.jsp";						
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);						// forward back to the HomePage. The uploaded image should
	    doGet(request,response);									// now be there
	}
}
