package com.google.cloud;

import lombok.Data;

@Data
public class Pojo {
    
    private String projectId; 
    private String location; 

    private String imageUri;
    private String prompt;

    private int maxOutputTokens;
    
    private float temp; 

    private float topK;

    private float topP; 

}

