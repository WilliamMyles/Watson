package util;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;


public class RedditPost {
	private String title;
	private String link;
	private AnalysisResults analysis;

	public RedditPost(String title, String link) {
		super();
		this.title = title;
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public AnalysisResults getAnalysis() {
		return analysis;
	}

	public void setAnalysis(AnalysisResults analysis) {
		this.analysis = analysis;
	}

	public static RedditPost parse(Node node) {
		String title = null;
		String link = null;

		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node currNode = nodes.item(i);
			if (currNode.getNodeName().equals("title")) {
				title = currNode.getTextContent();
			} else if (currNode.getNodeName().equals("link")) {
				link = currNode.getTextContent();;
			}
		}
		return new RedditPost(title, link);
	}

	@Override
	public String toString() {
		return "RedditPost [title=" + title + ", link=" + link + "]";
	}

}
