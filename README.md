# Java-Springboot-Langchain-Germini


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

If you wish to follow the sample package import codes in Google's documentation [here](https://cloud.google.com/vertex-ai/docs/generative-ai/multimodal/send-chat-prompts-gemini?hl=en#gemini-chat-samples-java), you need to have your directory structure as src/main/java/com/google/cloud/[file name]. If you are using your own "Group" and "Artifact" names, change the sample codes from Google's documentation to fit your requirement.
![Vertex AI package import in Java file](https://github.com/TCLee-tech/Java-Springboot-Langchain-Germini/blob/f4a4cc3f672800055b6551ae1b9685909e9dce13/package%20import%20path.jpg)

<hr>

### Open Java application in Visual Studio Code
1. Open Visual Studio Code.
2. From "Explorer" icon [Ctrl+Shift+E], click on "open a Java project folder".
3. Select the Java application folder you downloaded from Spring Initializr.
   
![VSCode](https://github.com/TCLee-tech/Java-Springboot-Langchain-Germini/blob/0f1724aafd9776d14cf0a9838a411c6cd02737d1/VSCode%20open%20Java%20project.jpg)

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

References:  
[Google Cloud Libraries Bill-of-Materials](https://github.com/googleapis/java-cloud-bom)   
[Vertex AI API for Java](https://cloud.google.com/java/docs/reference/google-cloud-vertexai/latest/overview#use-the-vertexai-api-for-java)  
[Cloud Run client library](https://cloud.google.com/java/docs/reference/google-cloud-run/latest/overview#use-the-cloud-run-for-java)
[Apache Maven Compiler Plugin](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-compiler-plugin/3.12.1)  
[Codelabs for Spring Boot on GCP](https://codelabs.developers.google.com/spring)  
[Google Cloud Client Library for Java](https://github.com/googleapis/google-cloud-java)  

### Google Cloud CLI
The Google Cloud CLI gives you command line access to GCP services such as gcloud, bq and gsutil from your own computer. Follow this [guide](https://cloud.google.com/sdk/docs/install) if you need it.  

Google Cloud CLI needs Python. If you have python installed on your computer, or prefers [Anaconda](https://www.anaconda.com/download), update your computer system's environment variables to point to your Python installation. You need to add variable: "CLOUDSDK_PYTHON" and value: "C:\path_to_your\anaconda3\python.exe" to the User variables section.

### Enable Vertex AI Gemini API in Google Cloud



https://cloud.google.com/vertex-ai/docs/generative-ai/start/quickstarts/quickstart-multimodal?hl=en#gemini-beginner-samples-java

https://cloud.google.com/vertex-ai/docs/generative-ai/multimodal/send-chat-prompts-gemini?hl=en

https://cloud.google.com/java/getting-started/jib

https://spring.io/guides/gs/serving-web-content/

https://glaforge.dev/posts/2023/12/13/get-started-with-gemini-in-java/
