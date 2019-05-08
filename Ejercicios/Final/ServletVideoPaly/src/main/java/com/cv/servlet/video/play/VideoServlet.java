package com.cv.servlet.video.play;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VideoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String fileName = request.getParameter("filename");
		fileName = "/"+fileName + ".mp4";
		ServletContext ct = getServletContext();
		InputStream input = ct.getResourceAsStream(fileName);
		//response.setContentType("video/quicktime"); //Use this for VLC player
		response.setContentType("video/mp4");

		response.setHeader("Content-Disposition", "inline; filename=\""
				+ fileName + "\"");
		OutputStream output = response.getOutputStream();

		byte[] buffer = new byte[2096];
		int read = 0;
		while ((read = input.read(buffer)) != -1) {
			output.write(buffer, 0, read);
		}
		input.close();
		output.flush();
		output.close();
	}
        
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
