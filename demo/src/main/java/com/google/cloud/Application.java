/*

//This is the standard main() method for SpringBoot Applications. Include this for thymeleaf UI or microservice design.

package com.google.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	//main() method
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}	

 */


//The codes below are used to test Gemini Pro Vision with a single picture and a prompt. Output is streamed into VS Code's terminal.

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
 
 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;
 
 import java.io.IOException;
 
 
 @SpringBootApplication
 public class Application {
	 
	 //main method
	 public static void main(String[] args) throws IOException {
		 SpringApplication.run(Application.class, args);
 
 
	 // TODO(developer): Replace these variables
	 String projectId = "groovy-reserve-409912";
	 String location = "us-central1";
	 String modelName = "gemini-pro-vision";	
 
	 //calling input() method
	 input(projectId, location, modelName);
	 }
 
 
	 // Definition of input() method that takes 3 parameters
	 public static void input(String projectId, String location, String modelName) throws IOException {
		 
		 //one-time initialization of new VertexAI instance
		 try (VertexAI vertexAI = new VertexAI(projectId, location);) {
 
		 //Set parameters for GenAI model (max_output_tokens,temp,top-K, top-P)	
		 GenerationConfig generationConfig = GenerationConfig.newBuilder()
											 .setMaxOutputTokens(2048)
											 .setTemperature(0.4f)
											 .setTopK(32)
											 .setTopP(1)
											 .build();
 
		 /* Image files must be in Google Cloud Storage
		 URI of image file - Only image/png and image/jpeg are supported. 258 tokens per image. 
		 Only gsutil of syntax gs://xxx.jpg and gs://yyy.png are supported.
		  */
		 String imageUri = "gs://images_genai/Gordon Ramsey Beef Wellington.jpg";
 
		 //query from user	
		 String query="Please give me the receipe to make this dish?";
 
		 //gets Generative AI model
		 GenerativeModel model = new GenerativeModel(modelName, generationConfig,vertexAI);
 
		 //an ArrayList to store inputs to GenAI model
		 List<Content> contents = new ArrayList<>();	
 
		 //pass in image bytes and query about image
		 contents.add(ContentMaker
				 .fromMultiModalData(
					 PartMaker.fromMimeTypeAndData("image/jpeg", imageUri), 
					 query));
 
		 //model's generateContentStream method
		 ResponseStream<GenerateContentResponse> responseStream = model.generateContentStream(contents);
 
		 responseStream.stream().forEach(System.out::println);
		 }
	 }
 
	 
 }
 

