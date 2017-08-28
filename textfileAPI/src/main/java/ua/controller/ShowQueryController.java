package ua.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import ua.model.ParameterModel;
import ua.model.QueryModel;

/**
 * Servlet implementation class ShowQueryController
 */
public class ShowQueryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 @Inject
	    private QueryModel query;

	    @Inject
	    private ParameterModel param;

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        String q = param.getQuery(req.getParameter("q"));
	        int limit = param.getLimit(req.getParameter("limit"));
	        int length = param.getLength(req.getParameter("length"));

	        resp.setContentType("application/json");
	        PrintWriter writer = resp.getWriter();

	        writer.write(query.getInfo(q, limit, length).toString());

	        writer.close();
	    }

}
