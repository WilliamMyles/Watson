package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;


public class WatsonNaturalLanguage {
	private static final String CRED_LOC = "../../keys/NLcred.txt";

	private NaturalLanguageUnderstanding service;

	public WatsonNaturalLanguage() {
		try {
			BufferedReader credReader = new BufferedReader(new FileReader(this.getClass().getClassLoader()
					.getResource(CRED_LOC).getPath()));

			String username = credReader.readLine().trim();
			String pass = credReader.readLine().trim();

			credReader.close();

			service = new NaturalLanguageUnderstanding("2018-03-16", username, pass);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public AnalysisResults analyzeTextEntities(String text, int entNum) {
		EntitiesOptions entitiesOptions = new EntitiesOptions.Builder().emotion(true).sentiment(true).limit(entNum)
				.build();
		
		Features features = new Features.Builder().entities(entitiesOptions).build();
		
		AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(text).features(features).build();

		AnalysisResults response = service.analyze(parameters).execute();

		return response;
	}
}
