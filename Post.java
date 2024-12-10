package discussussignup;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {
    private User user;
    private LocalDateTime dateTime;
    private String content;
    private String filePath; // Retained filePath
    private Forum commentsForum; // Forum associated with this post for comments

    public Post(User user, String content, String filePath, String imagePath) {
        this.user = user;
        this.dateTime = LocalDateTime.now();
        this.content = content;
        this.filePath = filePath; // Storing filePath
        this.commentsForum = new Forum("C" + System.currentTimeMillis(), "Comments on Post", user); // Generate unique ID for comments forum
    }

    public Forum getCommentsForum() {
        return commentsForum;
    }

    // Getter and Setter methods
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getFilePath() { return filePath; } // Getter for filePath
    public void setFilePath(String filePath) { this.filePath = filePath; } // Setter for filePath

    // Method to display the post with clickable file path
    public String displayPost() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        StringBuilder displayString = new StringBuilder();
        displayString.append("<html>");
        displayString.append("Posted by: ").append(user.getName()).append(" (User ID: ").append(user.getId()).append(")<br>");
        displayString.append("Date: ").append(dateTime.format(formatter)).append("<br>");
        displayString.append("Content:<br>").append(content).append("<br>");

        if (filePath != null) {
            displayString.append("<a href=\"").append(filePath).append("\">File: ").append(filePath).append("</a><br>");
        }
        
        displayString.append("</html>");
        return displayString.toString(); // Return HTML string
    }
}
