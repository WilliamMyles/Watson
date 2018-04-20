package util;

import java.util.ArrayList;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.CategoriesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SemanticRolesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentOptions;


public class WatsonNaturalLanguage {

	private NaturalLanguageUnderstanding service;

	public WatsonNaturalLanguage() {
		service = new NaturalLanguageUnderstanding("2018-03-16", "<USERNAME>", "<PASSWORD>");
	}
	
	public String analyzeText(String text) {
		
		CategoriesOptions categories = new CategoriesOptions();
		
		ArrayList<String> targets = new ArrayList<>();
		targets.add("desert");
		targets.add("treasure ");
		EmotionOptions emotion = new EmotionOptions.Builder().targets(targets).build();
		
		EntitiesOptions entitiesOptions = new EntitiesOptions.Builder().emotion(true).sentiment(true).limit(2).build();
		KeywordsOptions keywordsOptions = new KeywordsOptions.Builder().emotion(true).sentiment(true).limit(2).build();
		SemanticRolesOptions semanticRoles = new SemanticRolesOptions.Builder().build();
		SentimentOptions sentiment = new SentimentOptions.Builder().targets(targets).build();

		Features features = new Features.Builder()
				.categories(categories)
				.emotion(emotion)
				.entities(entitiesOptions)
				.keywords(keywordsOptions)
				.semanticRoles(semanticRoles)
				.sentiment(sentiment).build();
		AnalyzeOptions parameters;
	
		parameters =  new AnalyzeOptions.Builder().text(text).features(features).build();
		
//		if (url) parameters = new AnalyzeOptions.Builder().url(text).features(features).build();		
//		else parameters =  new AnalyzeOptions.Builder().text(text).features(features).build();	
		
		AnalysisResults response = service.analyze(parameters).execute();
		return response.toString();
	}

	public void analyzeRedditPost(RedditPost post) {

		EmotionOptions emotion = new EmotionOptions.Builder().document(true).build();
		SentimentOptions sentiment = new SentimentOptions.Builder().document(true).build();

		Features features = new Features.Builder().emotion(emotion).sentiment(sentiment).build();
				
		AnalyzeOptions parameters = new AnalyzeOptions.Builder().url(post.getLink()).features(features).build();
		
		post.setAnalysis(service.analyze(parameters).execute());
	}
}
