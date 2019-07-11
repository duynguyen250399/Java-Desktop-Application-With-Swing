
package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextEditor extends javax.swing.JFrame implements Runnable{

    Thread autoSave;
    String fileName = "data.txt";
    
    
    public TextEditor() throws IOException {
        initComponents();
        initUI();
        loadData();
        autoSave = new Thread(this);
        autoSave.start();
    }
    
    private void loadData() throws IOException{
        File f = new File(fileName);
        if(!f.exists()){
            f.createNewFile();
        }
        
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line = "";
        while((line = br.readLine()) != null){
            txtContent.append(line);
        }
        br.close();
       
    }
    
    private void saveToFile() throws IOException{
        File f = new File(fileName);
        PrintWriter pw = new PrintWriter(new FileWriter(f));
        pw.println(txtContent.getText());
        txtStatus.setText("Data saved");
        pw.close();
    }
    
    private void initUI(){
        setTitle("Smart Text Editior");
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtStatus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtContent = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtStatus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtStatus.setText("Status");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        txtContent.setColumns(20);
        txtContent.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtContent.setRows(5);
        jScrollPane1.setViewportView(txtContent);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TextEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new TextEditor().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtContent;
    private javax.swing.JLabel txtStatus;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while(true){
            String tmp1 = txtContent.getText();
            String tmp2 = txtContent.getText();
            while(tmp1.equals(tmp2)){
                tmp2 = txtContent.getText();
                txtStatus.setText("Data saved");
            }
            txtStatus.setText("Saving data......");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            try {
                saveToFile();
            } catch (IOException ex) {
                Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
