package discussussignup;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {
    private User user;
    private LocalDateTime dateTime;
    private String content;
    private String filePath;
    private String imagePath;
    private Forum commentsForum; // Forum associated with this post for comments

    public Post(User user, String content, String filePath, String imagePath) {
        this.user = user;
        this.dateTime = LocalDateTime.now();
        this.content = content;
        this.filePath = filePath;
        this.imagePath = imagePath;
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
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    

    // Method to display the post
    public String displayPost() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Posted by: " + user.getName() + " (User ID: " + user.getId() + ")\n" +
               "Date: " + dateTime.format(formatter) + "\n" +
               "Content:\n" + content + "\n" +
               (filePath != null ? "File: " + filePath + "\n" : "") +
               (imagePath != null ? "Image attached\n" : "");
    }

    public JLabel getImageLabel() {
        if (imagePath != null && !imagePath.isEmpty()) {
            ImageIcon icon = new ImageIcon(imagePath);
            if (icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {
                return new JLabel(icon);
            }
        }
        return null;
    }
}
