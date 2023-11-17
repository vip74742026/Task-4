
package com.example.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
// src/main/java/com/example/blog/controller/PostController.java
package com.example.blog.controller;

import com.example.blog.model.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @GetMapping
    public List<Post> getPosts() {
        // In a real-world scenario, you'd fetch posts from a database
        // For simplicity, I'm using hardcoded data here
        return Arrays.asList(
                new Post(1, "First Post", "This is the content of the first post."),
                new Post(2, "Second Post", "This is the content of the second post.")
        );
    }
}
// src/main/java/com/example/blog/model/Post.java
package com.example.blog.model;

public class Post {
    private long id;
    private String title;
    private String content;

    public Post(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // Getters and setters
}

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Scanner fileIn; //input file connection
		PrintWriter fileOut; //HTML file connection
		String filenameIn; //original file's name
		String filenameOut; //new HTML file's name
		int dotIndex; //position of . in filename
		String line = null; // a line from the input file
		
		// 1. ask user for a file name (or file path)
		
		System.out.println("Enter file name or path");
		filenameIn = scanner.nextLine();
		
		// 2. check if file exists
		
		try {
			
			//3. rename .txt as .html 
			fileIn = new Scanner(new FileReader(filenameIn));
			dotIndex = filenameIn.lastIndexOf(".");
			if(dotIndex == -1) {
				filenameOut = filenameIn + ".html";				
			}
			else {
				filenameOut = filenameIn.substring(0,dotIndex) + ".html";
			}
			fileOut = new PrintWriter(filenameOut);
			
			// 4. determine if file is empty
			
			try {
				line = fileIn.nextLine();
			}
			catch(NoSuchElementException e) {
				System.out.println("Error: "+e.getMessage());
			}
			if(line==null) {
				System.out.println("This file is empty :(");
			}
			else {
				// 5. read each line and insert necessary <tags>
				fileOut.println("<html>");
				fileOut.println("<head>");
				fileOut.println("</head>");
				fileOut.println("<body>");
				fileOut.println(line);
				
				while(fileIn.hasNextLine()) {
					
					fileOut.println("<br>");
					line = fileIn.nextLine();
					
					if(line.isEmpty()) {
						fileOut.println("<br>");
					}
					else {
						fileOut.println(line);
					}
					
				}
				fileOut.println("</body>");
				fileOut.println("</html>");
				
				System.out.println("HTML file is processed :)");
			}
			fileIn.close();
			fileOut.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
		
	}
}