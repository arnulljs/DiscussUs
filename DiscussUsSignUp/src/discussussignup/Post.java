package discussussignup;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {
    private User user;
    private LocalDateTime dateTime;
    private String content;
    private String filePath;  // The file path for the attached file
    private String imagePath; // Path for the image (PNG/JPG)

    // Constructor for post with file and image path
    public Post(User user, String content, String filePath, String imagePath) {
        this.user = user;
        this.dateTime = LocalDateTime.now();
        this.content = content;
        this.filePath = filePath;
        this.imagePath = imagePath; // Store image path
    }

    // Getter and Setter methods
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /// Method to display the post with an image if present
    public String displayPost() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // File info (could be a regular file or an image)
        String fileInfo = (filePath == null || filePath.isEmpty()) ? "No file uploaded" : "File: " + filePath;

        // Image info and displaying the image
        String imageInfo = (imagePath == null || imagePath.isEmpty()) ? "" : "Image: ";

        return "Posted by: " + user.getName() + " (User ID: " + user.getId() + ")\n" +
               "Date: " + dateTime.format(formatter) + "\n" +
               "Content:\n" + content + "\n" + // Ensures Content is on the next line
               fileInfo + "\n" +
               imageInfo;  // No need to display image path in text anymore
    }


    // Method to return ImageIcon to display the image in the GUI
    public ImageIcon getImageIcon() {
        if (imagePath != null && !imagePath.isEmpty()) {
            // Try loading the image from the path
            ImageIcon icon = new ImageIcon(imagePath);
            if (icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {
                return icon; // Valid ImageIcon
            } else {
                System.out.println("Image loading failed: " + imagePath);
            }
        }
        return null; // No image to display
    }

    // Method to return a JLabel with the ImageIcon (can be added directly to a panel in the UI)
    public JLabel getImageLabel() {
        ImageIcon icon = getImageIcon();
        if (icon != null) {
            return new JLabel(icon); // Returns a JLabel that displays the image
        }
        return null; // No image to display
    }
}
