package com.securenotes;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.util.Base64;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Secure Notes App");

        JTextArea noteArea = new JTextArea(10, 30);

        JButton normalSaveButton = new JButton("Save Normally");

        JButton encryptedSaveButton = new JButton("Save Encrypted");

        JPanel panel = new JPanel();

        panel.add(noteArea);
        panel.add(normalSaveButton);
        panel.add(encryptedSaveButton);
        normalSaveButton.addActionListener(e -> {
            String note = noteArea.getText();

            try {
                FileWriter writer = new FileWriter("normal_notes.txt", true);
                writer.write(note + "\n");
                writer.close();

                JOptionPane.showMessageDialog(null, "Note saved normally.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error while saving note.");
            }
        });
       encryptedSaveButton.addActionListener(e -> {
    	   String note = noteArea.getText();
    	   String encryptedNote = Base64.getEncoder().encodeToString(note.getBytes());
    	   try {
    		   FileWriter writer = new FileWriter("encrypted_notes.txt", true);
    		   writer.write(encryptedNote + "\n");
    		   writer.close();
    		   
    		   JOptionPane.showMessageDialog(null, "Encrypted note saved.");
    	   }catch (IOException ex) {
    		   JOptionPane.showMessageDialog(null, "Error while saving encrypted note.");
    	   }
       });

        frame.add(panel);

        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}