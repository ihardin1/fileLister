import java.awt.BorderLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.JButton;

import javax.swing.JFileChooser;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTextArea;

public class ListFiles extends JFrame implements ActionListener {

    // declaring important UI components

    private JButton fileChooserBtn, quitBtn;

    private JTextArea output;

    private JLabel currDirLabel;

    // constructor to initialize the GUI

    public ListFiles() {

        // passing a title to super class constructor

        super("List Files");

        // creating a panel to hold the two buttons

        JPanel btnPanel = new JPanel();

        // creating file chooser button and quit button, adding to panel

        fileChooserBtn = new JButton("Choose Directory");

        quitBtn = new JButton("Quit");

        btnPanel.add(fileChooserBtn);

        btnPanel.add(quitBtn);

        // creating a text area

        output = new JTextArea(10, 50);

        // creating a label to display current directory under process

        currDirLabel = new JLabel("Current directory:");

        // adding button panel to the top, text area inside a scrollpane to the

        // center, and label to the bottom, using BorderLayout

        add(btnPanel, BorderLayout.PAGE_START);

        add(new JScrollPane(output));

        add(currDirLabel, BorderLayout.PAGE_END);

        // setting up and displaying the window

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(700, 700);

        setVisible(true);

        // adding action listeners to the buttons

        fileChooserBtn.addActionListener(this);

        quitBtn.addActionListener(this);

    }

    public static void main(String[] args) {

        // initializing the GUI

        new ListFiles();

    }

    @Override

    public void actionPerformed(ActionEvent e) {

        // finding which button is clicked

        if (e.getSource().equals(fileChooserBtn)) {

            // clearing output text area

            output.setText("");

            // creating a file chooser, setting to enable selection of

            // directories only

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            // showing file open dialog

            int status = fileChooser.showOpenDialog(this);

            // checking if the result of this operation indicating successful

            // file selection

            if (status == JFileChooser.APPROVE_OPTION) {

                // getting selected directory

                File f = fileChooser.getSelectedFile();

                // displaying the dir name on label

                currDirLabel.setText("Current directory: " + f);

                // calling the recursive method listFiles and passing the file

                // object

                listFiles(f);

            }

        } else if (e.getSource().equals(quitBtn)) {

            // exiting

            System.exit(0);

        }

    }

    // recursive helper method to list all the files and folders in directory

    // denoted by file

    private void listFiles(File file) {

        // if parameter is a file, simply printing file details to output text

        // area

        if (file.isFile()) {

            output.append(file + "\n");

        } else {

            // otherwise listing all files in directory

            File files[] = file.listFiles();

            // ensuring that resultant array is not null

            if (files != null) {

                // looping through each file in files array

                for (File f : files) {

                    // appending file details on output text area

                    output.append(f + "\n");

                    // if f is a directory, calling the method recursively,

                    // passing f as new directory

                    if (f.isDirectory()) {

                        listFiles(f);

                    }

                }

            }

        }

    }

}