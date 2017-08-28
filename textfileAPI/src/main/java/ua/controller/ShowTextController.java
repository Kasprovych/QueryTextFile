package ua.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import ua.model.QueryModel;

/**
 * Servlet implementation class ShowTextController
 */
public class ShowTextController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 @Inject
	    private QueryModel model;

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        response.setContentType("text/plain");
	        PrintWriter writer = response.getWriter();

	        List<String> allLines = model.getAllLines();
	        for (String allLine : allLines) {
	            writer.write(allLine + "\n");
	        }

	        writer.close();

	    }
}
