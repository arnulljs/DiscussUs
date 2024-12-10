package discussussignup;

import java.util.ArrayList;
import java.util.List;

public class Forum {
    private String forumId;
    private String title;
    private User creator;
    private List<Post> posts;

    public Forum(String forumId, String title, User creator) {
        this.forumId = forumId;
        this.title = title;
        this.creator = creator;
        this.posts = new ArrayList<>();
    }

    // Getter and Setter methods
    public String getForumId() {
        return forumId;
    }

    public void setForumId(String forumId) {
        this.forumId = forumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    // Add a post to the forum
    public void addPost(Post post) {
        posts.add(post);
        System.out.println("Post successfully added to the forum!\n");
    }

    // Display posts in the forum
    public void displayPosts() {
        if (posts.isEmpty()) {
            System.out.println("No posts available in the forum.\n");
        } else {
            System.out.println("Posts in '" + title + "':");
            for (Post post : posts) {
                System.out.println(post.displayPost());
                System.out.println("---------------------------------------------------");
            }
        }
    }
}
