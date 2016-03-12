package userPhotos;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This image servlet links images in a particular directory
 * specified by imagePath and servers them up the JSP so that 
 * they may be displayed to the user. Structure of this segment
 * of the project was adopted using prior work by BalusC.
 * 
 */
@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {
    private String imagePath;
    public void init() throws ServletException {
        this.imagePath = "/Users/jzhang/Downloads/ImageStorage/";	// MUST SET THIS TO A PATH ON YOUR COMPUTER
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestedImage = request.getPathInfo();				 // Get requested image by path info.
        // Check if file name is actually supplied to the request URI.
        if (requestedImage == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // Display Blank Image
            return;
        }
        // Decode the file name (might contain spaces and on) and prepare file object.
        File image = new File(imagePath, URLDecoder.decode(requestedImage, "UTF-8"));
        // Check if file actually exists in filesystem.
        if (!image.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // Display Blank Image
            return;
        }
        String contentType = getServletContext().getMimeType(image.getName());
        if (contentType == null || !contentType.startsWith("image")) { // Check if file is actually an image (avoid download of other files by hackers!).
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // Display Blank Image.
            return;
        }
        response.reset();
        response.setContentType(contentType);						// set the content type of the response
        response.setHeader("Content-Length", String.valueOf(image.length()));
        Files.copy(image.toPath(), response.getOutputStream());		// write the files to the output stream of the response
    }

}