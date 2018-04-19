package util;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;


public class WatsonImageProcessing {
	private VisualRecognition service;

	public WatsonImageProcessing() {
		service = new VisualRecognition("2018-03-16", "4fc823a4f0b48045424f6f701cf94c0282b98164");
	}
	
	public void analyseRedditPost(RedditPost post) {
		ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
		  .url(post.getLink())
		  .build();		
		post.setImage(service.classify(classifyOptions).execute().getImages().get(0));
	}
}
