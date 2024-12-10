/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package discussussignup;

/**
 *
 * @author deezeewhytea
 */
public class Comment {
     private String text;
     private String date;

    // Constructor
    public Comment(String text, String date) {
        this.text = text;
        this.date = date;
    }

    // Getter
    public String getText() {
        return text;
    }

    // Setter
    public void setText(String text) {
        this.text = text;
    }
    
    public String getDate() {
        return date;
    }
}
