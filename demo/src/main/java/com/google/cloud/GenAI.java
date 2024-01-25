package com.google.cloud;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.Content;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.GenerationConfig;
import com.google.cloud.vertexai.generativeai.preview.ContentMaker;
import com.google.cloud.vertexai.generativeai.preview.GenerativeModel;
import com.google.cloud.vertexai.generativeai.preview.PartMaker;
import com.google.cloud.vertexai.generativeai.preview.ResponseStream;

import java.util.*;
import java.util.stream.Collectors;

import java.io.IOException;


public class GenAI {
	
String projectId = "groovy-reserve-409912";
String location = "us-central1";
String modelName = "gemini-pro-vision";	

int max = 2048;
float temperature = 0.4f;
float topK = 32f;
float topP = 1f;	

//query from user	
String prompt = "Can you give me the receipe to make this dish?";

/* 	Image files must be in Google Cloud Storage
	URI of image file - Only image/png and image/jpeg are supported. 258 tokens per image. 
	Only gsutil of syntax gs://xxx.jpg and gs://yyy.png are supported.
*/
String imageUri = "gs://images_genai/Gordon Ramsey Beef Wellington.jpg";

// comment: above block of variables, from line 20 to line 36, can be removed on integration with service logic.


public String input(String projectId, String location, String modelName) throws IOException {

	//one-time initialization of new VertexAI instance
	try (VertexAI vertexAI = new VertexAI(projectId, location);) {
		

		//Set parameters for GenAI model (max_output_tokens,temp,top-K, top-P)	
		GenerationConfig generationConfig = GenerationConfig.newBuilder()
											.setMaxOutputTokens(max)
											.setTemperature(temperature)
											.setTopK(topK)
											.setTopP(topP)
											.build();

		//gets Generative AI model
		GenerativeModel model = new GenerativeModel(modelName, generationConfig, vertexAI);

		//an ArrayList to store inputs to GenAI model
		List<Content> contents = new ArrayList<>();	

		//pass in image bytes and query about image
		contents.add(ContentMaker
				.fromMultiModalData(
					PartMaker.fromMimeTypeAndData("image/jpeg", imageUri), 
					prompt));

		//model's generateContentStream method
		ResponseStream<GenerateContentResponse> responseStream = model.generateContentStream(contents);

        return responseStream.stream().map(Object::toString).collect(Collectors.joining(""));

		}
	}

	
}




