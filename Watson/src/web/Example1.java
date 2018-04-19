package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.WatsonNaturalLanguage;


/**
 * Servlet implementation class WatsonServlet
 */
@WebServlet("/Example1")
public class Example1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("text");
		String url = request.getParameter("url");
		
		if (text != null && !text.isEmpty())  {
			WatsonNaturalLanguage wnl = new WatsonNaturalLanguage();
			request.setAttribute("output", wnl.analyzeText(text));
		} 
		
		request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Example1.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
