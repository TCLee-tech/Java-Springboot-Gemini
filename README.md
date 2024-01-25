# Java-Springboot-Germini

### Install Java and Maven (if you have not done so)
Check if you have installed JDK and Maven.
If you need to, install [JDK 21](https://www.oracle.com/java/technologies/downloads/#jdk21-windows) and the [Maven binary zip archive](https://maven.apache.org/download.cgi)   

![JRE and Maven installation](https://github.com/TCLee-tech/Java-Springboot-Gemini/blob/edf93545d127d8ce2aa4071ea81ade0aa8092942/jdk%20and%20maven%20install.jpg)  

Add the following entries to your computer's system environment variables:      

Variable name: JAVA_HOME	Variable value: C:\Program Files\Java\jdk-21 [path to jdk exe file]  
Variable name: Path		Variable value: C:\Program Files\Java\jdk-21\bin    

Variable name: MAVEN_HOME	Variable value: C:\Program Files\apache-maven-3.9.6 [path to unzipped Maven folder]  
Variable name: Path		Variable value: %MAVEN_HOME%\bin  

To verify, in cmd.exe, enter
`java --version` and `mvn -version`.
You should get info on your java and maven installations if successful.

<hr>

### Create a Java Spring application
1. Go to [Spring Initializr](https://start.spring.io/)
2. Select configuration. For example:
   - Project: Maven
   - Language: Java
   - Spring Boot: 3.3.0 (SNAPSHOT)
   - Project Metadata
     -   Group: com.example // package name, default.
     -   Artifact: demo // application name, default.
     -   Description: [your description]
     -   Packaging: Jar
     -   Java: 21
    - Dependencies. For SpringBoot MVC, add:
      - Spring Web
      - Spring Boot DevTools
      - Thymeleaf
3. Click "GENERATE" at bottom of page.
4. The project is downloaded as a zipped folder. Extract the application sub-folder.

![Spring initializr choices](https://github.com/TCLee-tech/Java-Springboot-Langchain-Germini/blob/cf569ad5d605b19f0f3ba69e0679528cf954e137/spring%20initializr%20sample.jpg)  

If you wish to follow the sample package import codes in Google's documentation [here](https://cloud.google.com/vertex-ai/docs/generative-ai/multimodal/send-chat-prompts-gemini?hl=en#gemini-chat-samples-java), pay attention to your directory structure: src/main/java/com/google/cloud/[file name]. If you are using your own "Group" and "Artifact" names, change the directory path to fit your requirement.
![Vertex AI package import in Java file](https://github.com/TCLee-tech/Java-Springboot-Langchain-Germini/blob/f4a4cc3f672800055b6551ae1b9685909e9dce13/package%20import%20path.jpg)

<hr>

### Open Java application in Visual Studio Code
1. Open Visual Studio Code.
2. From "Explorer" icon [Ctrl+Shift+E], click on "open a Java project folder".
3. Select the Java application folder you downloaded from Spring Initializr.
   
![VSCode](https://github.com/TCLee-tech/Java-Springboot-Langchain-Germini/blob/0f1724aafd9776d14cf0a9838a411c6cd02737d1/VSCode%20open%20Java%20project.jpg)

* You can also create the Java project from within VS Code.

<hr>

### Install Cloud Code (if you have not done so)
1. In VS Code, click on "Extensions" icon [Ctrl+Shift+X] and search for "Google Cloud Code". Install.
   - [Reference Guide](https://cloud.google.com/code/docs/vscode/install#install)

![Extensions for Cloud Code](https://github.com/TCLee-tech/Java-Springboot-Langchain-Germini/blob/51dcda042a3c086440d5396aa691f8c799d5b223/Cloud%20Code%20in%20VSCode.jpg)

2. Authenticate and log in to Google Cloud.
   - get yourself a [Google Cloud Platform account](https://cloud.google.com) and create a project, if you don't have one.
   - click on **Cloud Code - Sign in** in the VS Code status bar.
     ![Cloud code sign in](https://github.com/TCLee-tech/Java-Springboot-Langchain-Germini/blob/51dcda042a3c086440d5396aa691f8c799d5b223/Cloud%20code%20sign%20in.jpg)
   - follow the instructions and log in.
   - for reference, see this [section of the guide](https://cloud.google.com/code/docs/vscode/install#log_in_to)
4. If you want to code with Duet AI's assistance, follow the instructions [here](https://cloud.google.com/code/docs/vscode/write-code-duet-ai#enable-duet-ai-in-cloud-code) to activate Duet AI.

<hr>

### Update Maven build file (pom.xml).
1. Add the Google Cloud Libraries Bill-of-Materials (BOM) and Vertex AI's library  
   - In VS Code, in your Java application folder, open pom.xml and add the following codes:
```
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>com.google.cloud</groupId>
      <artifactId>libraries-bom</artifactId>
      <version>26.29.0</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>

<dependencies>
  <dependency>
    <groupId>com.google.cloud</groupId>
    <artifactId>google-cloud-vertexai</artifactId>
  </dependency>
</dependencies>
```
2. Add dependency for Cloud Run, if you are using Cloud Run
```
---
 <dependency>
   <groupId>com.google.cloud</groupId>
   <artifactId>google-cloud-run</artifactId>
 </dependency>
...
```
3. Change compiler plugin to Apache Maven
```
...
<build>
	<plugins>
		<plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-compiler-plugin</artifactId>
			<version>3.12.1</version>
			<configuration>
				<source>21</source>
				<target>21</target>
			</configuration>
		</plugin>
	</plugins>
</build>
...
```
4. Add jib-maven-plugin
```
<plugin>
	<groupId>com.google.cloud.tools</groupId>
	<artifactId>jib-maven-plugin</artifactId>
	<version>3.4.0</version>
	<configuration>
	  <from>
		<image>gcr.io/distroless/java21-debian12</image>
	  </from>
	  <to>
		<image>gcr.io/[GCP_Project_ID]/[IMAGE_NAME]</image>
	  </to>
	</configuration>
</plugin>
```
  - replace [GCP_Project_ID] with the name of your GCP Project.
  - replace [IMAGE_NAME] with your preferred image name.
  - as of the time of writing, Jib only supports base image for Java 17. So, [jib-maven-plugin](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin#configuration) is configured here to use the [distroless JRE-21 base image](https://github.com/GoogleContainerTools/distroless/issues/1405).  

References:  
[Google Cloud Libraries Bill-of-Materials](https://github.com/googleapis/java-cloud-bom)   
[Java client library for Vertex AI](https://cloud.google.com/java/docs/reference/google-cloud-vertexai/latest/overview#use-the-vertexai-api-for-java) - if you use Gradle to import dependencies, there is info here.   
[Java client library for Cloud Run](https://cloud.google.com/java/docs/reference/google-cloud-run/latest/overview#use-the-cloud-run-for-java)  
[MVN Repository page for Apache Maven Compiler Plugin](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-compiler-plugin/3.12.1)  
[Codelabs for Spring Boot on GCP](https://codelabs.developers.google.com/spring)  
[Google Cloud Java Client Libraries Github](https://github.com/googleapis/google-cloud-java)  
[jib-maven-plugin in Maven Central Repository](https://central.sonatype.com/artifact/com.google.cloud.tools/jib-maven-plugin/overview)

5. Add Project Lombok (optional).
   - if you wish to use @Data annotation in Plain Old Java Object(POJO) model and not type out getters and setters, you can add Project Lombok.
```
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
	<dependency>
    		<groupId>org.projectlombok</groupId>
    		<artifactId>lombok</artifactId>
		<version>1.18.30</version>
		<scope>provided</scope>
	</dependency>
```
<hr>

### Google Cloud CLI
The Google Cloud CLI gives you command line access to GCP services such as gcloud, bq and gsutil from your own computer. Follow this [guide](https://cloud.google.com/sdk/docs/install) if you need it.  

Google Cloud CLI needs Python. If you have python installed on your computer, or prefers [Anaconda](https://www.anaconda.com/download), update your computer system's environment variables to point to your Python installation. You need to add variable: "CLOUDSDK_PYTHON" and value: "C:\path_to_your\anaconda3\python.exe" to the User variables section.

### Authenticate from your local environment to Google Cloud
On your computer, in the Google Cloud SDK Shell created when you installed Google Cloud CLI, enter
```
gcloud auth application-default login
```
Log in with your GCP username and password.

<hr>

### Enable Cloud Run API (one time) 
In the Google Cloud SDK Shell,
```
gcloud services enable run.googleapis.com
```
### Enable Artifact Registry API (one time)
```
gcloud services enable artifactregistry.googleapis.com
```
Authenticate to your private Docker repositories in Artifact Registry
```
gcloud auth configure-docker
```

### Enable Vertex AI Gemini API in Google Cloud (one time)

```
gcloud services enable aiplatform.googleapis.com
```

https://cloud.google.com/vertex-ai/docs/generative-ai/start/quickstarts/quickstart-multimodal?hl=en#gemini-beginner-samples-java

https://cloud.google.com/vertex-ai/docs/generative-ai/multimodal/send-chat-prompts-gemini?hl=en


https://spring.io/guides/gs/serving-web-content/

https://glaforge.dev/posts/2023/12/13/get-started-with-gemini-in-java/

<hr>

### Deploy application to Cloud Run 

[Cloud Run](https://cloud.google.com/run?hl=en) is severless for containers.  
First, build OCI-compliant images using [Jib](https://github.com/GoogleContainerTools/jib). Jib does not require Dockerfile and is daemonless. In the VS Code terminal, run:
```
mvn compile jib:build
```

Reference: [Building Java containers with jib](https://cloud.google.com/java/getting-started/jib)  

Shipping the code. To deploy image to Cloud Run, in the Google Cloud SDK shell, run:
```
gcloud run deploy <your name for the Cloud Run Service> \
--image gcr.io/[PROJECT_ID]/[IMAGE_NAME] \
--region [REGION] \
--platform managed \
--allow-unauthenticated
```
<hr>

### Thymeleaf UI

You may want a html page where you can enter values, attach files (need code modification). One option for server-side rendering of a UI is to use Thymeleaf. There are alternatives to Thymeleaf. There is also an option to code a frontend interface (Javascript, Python, React, NextJS, Angular etc).   

For thymeleaf,
   - a "controller" that maps the GET, POST methods
   - a html page (called index.html in my file) under `src/main/resource/static/templates/xxx.html` folder
   - any css needs to go under `src/main/resource/static/css/style.css`

TemplateController.java
```
package com.google.cloud;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
// don't name the class below "Controller" to avoid confusion with the @Controller annotation. Custom annovations are Java classes.
public class TemplateController {

    /*
    a reference to a Service interface -> class that will do actual work of calling GenAI model
    private final ServiceInterface service;

    public UseController (ServiceInterface service) {
        this.service = service;
    }
     */

    @GetMapping("/")
    public String showUserEntryForm (Model model) {

        //adding the attribute(key-value pair)
        model.addAttribute("pojo", new Pojo());
        //returning the view name
        return "index";
    }

    //when user submits form, this method is called
    @PostMapping("/")
    public String submitForm(@ModelAttribute("pojo") Pojo pojo, Model model) {
        System.out.println(pojo);    
        //call to service interface/class for work with GenAI model
        //service.usePojo(pojo.toParameters())
        return "redirect:index";
    }
}    
```  

index.html
```
<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org" lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" th:href="@{/css/style.css}" />
    <title>UI for Google's Gemini Pro Vision</title>
</head>

<body>
    <h1> Welcome to the test page for Google's Gemini Pro Vision</h1>

    <form action="#" th:action="@{/}" method="POST" th:object="${pojo}">

    <div class="input-field">    
        <div><label for="projectId">Project ID: </label></div>
        <input id="projectId" type="text" size="100" th:field="*{projectId}" />   
    </div>
    
    <div class="input-field">    
        <div><label for="location">Model location: </label></div>
        <input id="location" type="text" size="100" th:field="*{location}" />   
    </div>

    <div class="input-field">    
    <div><label for="imageUri">URI to your image file: </label></div>
    <input id="imageUri" type="text" size="100" th:field="*{imageUri}" />   
    </div>

    <div class="input-field">
    <div><label for="prompt">Prompt:</label></div>
    <textarea id="prompt" th:field="*{prompt}" rows="4" cols="50"></textarea>
    </div>    
    
    <fieldset>
        <legend>Gemini Pro Vision model settings (default provided):</legend>

        <div>
        <div><label for="maxOutputTokens">Max Output Tokens</label></div>
        <input id="maxOutputTokens" type="number" min="1" max="2048" placeholder="2048" th:field="*{maxOutputTokens}" />
        </div>
        
        <div>
        <div><label for="temp">Temperature</label></div>
        <input id="temp" type="number" min="0" max="1" step="0.01" placeholder="0.4" th:field="*{temp}" />
        </div>

        <div>
        <div><label for="topK">Top K</label></div>
        <input id="topK" type="number" min="1" max="40" step="0.01" placeholder="32" th:field="*{topK}" />
        </div>

        <div>
        <div><label for="topP">Top P</label></div>
        <input id="topP" type="number" min="0" max="1" step="0.01" placeholder="1" th:field="*{topP}" />   
        </div>

    </fieldset>

    <input type="submit" value="Submit" />
    
    </form>    

</body>

</html>
```

style.css   
```
h1 {
    color:blue;
    font-size: 40px;
}

.input-field {
    margin: 2rem;
    line-height: 200%;
}

fieldset {
    box-sizing: border-box;
    padding: 2rem;
    border-radius: 1rem;
    background-color: hsl(0, 0%, 100%);
    border: 4px solid hsl(0, 0%, 90%);
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 2rem;
    line-height: 200%;
    margin: 2rem;
  }

  input[type=submit]{
    background-color: #04AA6D;
    border: none;
    color: white;
    padding: 16px 32px;
    text-decoration: none;
    margin: 2rem;
    cursor: pointer;
}
```   
There is a service interface and a service class that contains the business logic, does the work of calling the GenAI model (to be modified according to needs).

<hr>

### Codes that call the Gemini Pro Vision model with 2 modalities of input - picture and text

Alternatively, the following codes will call the Gemini multi-modal model with a picture in Google Cloud Storage and your prompt/query, but the response from the Gemini model will be streamed in the VS Code terminal.   
You will need to remove the Thymeleaf UI codes and modify the TemplateController.java codes.

```   
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
public class Application2 {
	
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
				PartMaker.fromMimeTypeAndData("image/jpeg", imageUri), query)
				);

		//model's generateContentStream method
		ResponseStream<GenerateContentResponse> responseStream = model.generateContentStream(contents);

		responseStream.stream().forEach(System.out::println);
		}
	}	
}
```   
The above codes may be modified to suit your game. There is a List<Content> arrayList to hold the inputs to the AI model. This may be "memory" for simple use cases.




