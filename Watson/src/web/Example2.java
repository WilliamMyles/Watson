package web;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import util.RedditPost;
import util.WatsonNaturalLanguage;


/**
 * Servlet implementation class Example2
 */
@WebServlet("/Example2")
public class Example2 extends HttpServlet {
	private static Document doc;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sub = request.getParameter("sub");
		String maxStr = request.getParameter("max");
		int max = 5;
		
		if (maxStr != null && !maxStr.isEmpty()) {
			max = Integer.parseInt(maxStr);
		}
		
		if (sub != null && !sub.isEmpty()) {
			try {
				if (doc == null) {
					URL url = new URL("http://inline-reddit.com/feed/?subreddit="+sub);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestProperty("User-Agent", "Watson-News-Scraper 0.1");
					InputStream is = conn.getInputStream();

					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

					doc = dbFactory.newDocumentBuilder().parse(is);
				}

				NodeList nodes = doc.getElementsByTagName("item");
				ArrayList<RedditPost> posts = new ArrayList<RedditPost>();
				for (int i = 0; i < max && i < nodes.getLength(); i++) {
					Node node = nodes.item(i);
					RedditPost post = RedditPost.parse(node);
					posts.add(post);
				}
				WatsonNaturalLanguage wnl = new WatsonNaturalLanguage();
				for (int i = 0; i < posts.size(); i++) {
					wnl.analyzeRedditPost(posts.get(i));
				}
				
				request.setAttribute("posts", posts);

			} catch (SAXException | ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("/WEB-INF/jsp/Example2.jsp").forward(request, response);;
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
