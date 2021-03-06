package util;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImage;


public class RedditPost {
	private int id;
	private String title;
	private String link;
	private AnalysisResults analysis;
	private ClassifiedImage image;

	public RedditPost(int id, String title, String link) {
		super();
		this.id = id;
		this.title = title;
		this.link = link;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ClassifiedImage getImage() {
		return image;
	}

	public void setImage(ClassifiedImage image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "RedditPost [title=" + title + ", link=" + link + "]";
	}

}
