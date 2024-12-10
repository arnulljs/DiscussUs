package discussussignup;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private String username;
    private User user;
    private LocalDateTime dateTime;
    private String content;
    private String filePath;
    private String imagePath;
    private String title;  // New field for the post title
    private List<Comment> comments;

    // Modify the constructor to accept title
    public Post(User user, String title, String content, String filePath, String imagePath) {
        this.user = user;
        this.dateTime = LocalDateTime.now();
        this.content = content;
        this.filePath = filePath;
        this.imagePath = imagePath;
        this.title = title;  // Set the title
        this.comments = new ArrayList<>(); // Initialize the comments list
    }

    // Getter and Setter methods for the title
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPostedBy() {
    return user != null ? UserDAO.getLoggedInUsername() : "Unknown";
    
}
    public String getContent() {
    return content;  // Return the content of the post
}
    public String getUsername() {
        return username;
    }
    
     // Getter for comments
    public List<Comment> getComments() {
        return comments;
    }

    // Method to add a comment to the post
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    
    public String getFilePath(){
        return filePath;
    }

    // Other existing methods...
}