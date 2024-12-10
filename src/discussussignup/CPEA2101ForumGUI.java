/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package discussussignup;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author deezeewhytea
 */
public class CPEA2101ForumGUI extends javax.swing.JFrame {
    public static List<Post> Posts = new ArrayList<>();
    private static boolean postsInstantiated = false;
    /**
     * Creates new form CPEA2101ForumGUI
     */
    public CPEA2101ForumGUI() {
      if (!postsInstantiated) {
            // Only instantiate posts if they haven't been instantiated before
            User currentUser = UserDAO.getCurrentUser();
            String title1 = "Converting Time Domain Capacitance to Impedance";
            String title2 = "Converting Time Domain Inductance to Impedance";
            String title3 = "How can you tell if voltage or current leads in AC?";
            String content1 = "To convert time domain capacitance to impedance, use the following formula:\n" +
            "\n" +
            "Z(f) = 1 / (j 2π f C)\n" +
            "\n" +
            "Where Z(f) is the impedance at frequency f, j is the imaginary unit (j = √-1), f is the frequency in Hz, and C is the capacitance in Farads. This formula represents the impedance of a capacitor in the frequency domain, where impedance is inversely proportional to both the capacitance and the frequency.";
            String content2 = "To convert time domain inductance to impedance, use the following formula:\n" +
            "\n" +
            "Z(f) = j 2π f L\n" +
            "\n" +
            "Where Z(f) is the impedance at frequency f, j is the imaginary unit (j = √-1), f is the frequency in Hz, and L is the inductance in Henries. This formula represents the impedance of an inductor in the frequency domain, where impedance is directly proportional to both the inductance and the frequency.";
            String content3 = "In AC circuits, you can determine if voltage or current leads by looking at the phase relationship between them.\n" +
            "\n" +
            "Voltage leads current:\n" +
            "In a capacitive circuit, the voltage lags behind the current.\n" +
            "Inductive circuits are where the voltage leads the current. The voltage reaches its peak before the current does.\n" +
            "\nCurrent leads voltage:\n" +
            "In a resistive circuit, voltage and current are in phase, meaning neither leads nor lags.\n" +
            "Capacitive circuits: In these circuits, the current leads the voltage. This happens because capacitors resist changes in voltage and the current has to change first.\n" +
            "\nYou can determine this phase relationship by measuring the phase shift between the voltage and current waveforms. If the voltage waveform reaches its peak before the current waveform, voltage leads. If the current reaches its peak before the voltage, current leads.";

            // Add posts only if they haven't been added yet
            Posts.add(new Post(currentUser, title1, content1, "", ""));
            Posts.add(new Post(currentUser, title2, content2, "", ""));
            Posts.add(new Post(currentUser, title3, content3, "", ""));
            
            postsInstantiated = true;  // Set the flag to true after instantiating posts
        }

        initComponents();
        updateTable();
    }
    
 private void updateTable() {
        // Create a DefaultTableModel with the proper column names
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        // Clear the existing rows in case of any previous data
        model.setRowCount(0);

        // Loop through the Posts array and add data to the table
        for (Post post : Posts) {
            // Add a row with the post's title (subject) and the post content
            model.addRow(new Object[]{post.getTitle(), post.getContent()});
        }

        // Add MouseListener to the table
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable1.rowAtPoint(evt.getPoint());  // Get the row of the clicked item
                if (row >= 0) {
                    // Get the post title and content from the selected row
                    String postTitle = (String) jTable1.getValueAt(row, 0);
                    String postContent = (String) jTable1.getValueAt(row, 1);

                    // Find the post object that matches the clicked row
                    Post selectedPost = null;
                    for (Post post : Posts) {
                        if (post.getTitle().equals(postTitle) && post.getContent().equals(postContent)) {
                            selectedPost = post;
                            break;
                        }
                    }

                    // Open PostGUI with the selected post
                    if (selectedPost != null) {
                        // Create a new instance of PostGUI and pass the selected post
                        PostGUI postGUI = new PostGUI(selectedPost);  // Pass the selected post
                        postGUI.setVisible(true);
                        postGUI.pack();
                        postGUI.setLocationRelativeTo(null);
                        // Optionally, you can dispose of the current frame here
                        dispose(); // Close the current window if needed
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(6, 41, 35));
        jPanel2.setLayout(null);

        jButton1.setBackground(new java.awt.Color(6, 41, 35));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/discussussignup/home.png"))); // NOI18N
        jButton1.setText("Home");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.setIconTextGap(10);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(20, 110, 220, 110);

        jPanel3.setBackground(new java.awt.Color(22, 67, 67));
        jPanel3.setLayout(null);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/discussussignup/user.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(20, 30, 60, 50);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Hi User!");
        jPanel3.add(jLabel18);
        jLabel18.setBounds(80, 20, 110, 40);

        jButton3.setBackground(new java.awt.Color(6, 41, 35));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Sign Out");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);
        jButton3.setBounds(80, 60, 75, 23);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(0, 0, 240, 110);

        jPanel7.setBackground(new java.awt.Color(25, 61, 55));
        jPanel7.setLayout(null);

        jToggleButton1.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/discussussignup/forum.png"))); // NOI18N
        jToggleButton1.setText("Forums");
        jToggleButton1.setBorderPainted(false);
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.setFocusPainted(false);
        jToggleButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton1.setIconTextGap(13);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel7.add(jToggleButton1);
        jToggleButton1.setBounds(0, 0, 240, 110);

        jPanel2.add(jPanel7);
        jPanel7.setBounds(0, 220, 240, 110);

        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/discussussignup/notebook.png"))); // NOI18N
        jButton7.setText("EM-DECPE");
        jButton7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jButton7.setContentAreaFilled(false);
        jButton7.setVisible(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7);
        jButton7.setBounds(20, 550, 200, 110);

        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/discussussignup/notebook.png"))); // NOI18N
        jButton8.setText("CPEA 2101");
        jButton8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jButton8.setContentAreaFilled(false);
        jButton8.setVisible(false);
        jPanel2.add(jButton8);
        jButton8.setBounds(20, 330, 200, 110);

        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/discussussignup/notebook.png"))); // NOI18N
        jButton9.setText("CPE 2101L");
        jButton9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jButton9.setContentAreaFilled(false);
        jButton9.setIconTextGap(6);
        jButton9.setVisible(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton9);
        jButton9.setBounds(20, 440, 200, 110);

        jPanel1.setBackground(new java.awt.Color(0, 10, 4));
        jPanel1.setLayout(null);

        jPanel5.setBackground(new java.awt.Color(22, 67, 67));
        jPanel5.setLayout(null);

        jScrollPane1.setBackground(new java.awt.Color(22, 67, 67));
        jScrollPane1.setBorder(null);

        jTable1.setBackground(new java.awt.Color(22, 67, 67));
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject", "Content"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setGridColor(new java.awt.Color(22, 67, 67));
        jTable1.setIntercellSpacing(new java.awt.Dimension(50, 0));
        jTable1.setRowHeight(100);
        jTable1.setShowHorizontalLines(true);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(10);
        }

        jPanel5.add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 970, 610);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(30, 80, 970, 610);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/discussussignup/notebook.png"))); // NOI18N
        jLabel19.setText("CPEA 2101 Forums");
        jPanel1.add(jLabel19);
        jLabel19.setBounds(30, 20, 360, 50);

        jButton2.setBackground(new java.awt.Color(6, 41, 35));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/discussussignup/newpost.png"))); // NOI18N
        jButton2.setText("Create Post");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(845, 23, 150, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        Homepage session = new Homepage();
        session.setVisible(true);
        session.pack();
        session.setLocationRelativeTo(null);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        boolean isSelected = jToggleButton1.isSelected();
        jButton7.setVisible(isSelected);
        jButton8.setVisible(isSelected);
        jButton9.setVisible(isSelected);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
         this.dispose();
           OOPForumGUI oopforum = new OOPForumGUI();
           oopforum.setVisible(true);
           oopforum.pack();
           oopforum.setLocationRelativeTo(null);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        MakeForumGUI newPost = new MakeForumGUI();
        newPost.setVisible(true);
        newPost.pack();
        newPost.setLocationRelativeTo(null);          // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
          this.dispose();
           DEForumGUI deforum = new DEForumGUI();
           deforum.setVisible(true);
           deforum.pack();
           deforum.setLocationRelativeTo(null);       // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       this.dispose();
       SignInGUI signout = new SignInGUI();
       signout.setVisible(true);
       signout.pack();
       signout.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CPEA2101ForumGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CPEA2101ForumGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CPEA2101ForumGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CPEA2101ForumGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CPEA2101ForumGUI().setVisible(true);
            }
        });
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
