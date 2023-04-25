import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Frame extends JFrame {

    JPanel mainPnl;
    JPanel topPnl;
    JPanel displayPnl;
    JPanel bottomPnl;

    JTextArea displayTA;
    JScrollPane scroller;

    JButton fileBtn;
    JButton quitBtn;

    JLabel titleLbl;

    public Frame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTopPnl();
        mainPnl.add(topPnl, BorderLayout.NORTH);

        createDisplayPnl();
        mainPnl.add(displayPnl, BorderLayout.CENTER);

        createBottomPnl();
        mainPnl.add(bottomPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTopPnl() {
        topPnl = new JPanel();
        topPnl.setLayout(new GridLayout(2, 1));

        titleLbl = new JLabel("Recursive Lister", JLabel.CENTER);
        titleLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));

        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);

        topPnl.add(titleLbl);

        fileBtn = new JButton("Pick a file");
        topPnl.add(fileBtn);

        fileBtn.addActionListener((ActionEvent ae) -> {

            displayTA.setText("");

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int status = fileChooser.showOpenDialog(this);

            if (status == JFileChooser.APPROVE_OPTION) {

                File f = fileChooser.getSelectedFile();
                listFiles(f);
            }
        });
    }

    private void createDisplayPnl() {
        displayPnl = new JPanel(new GridLayout(1, 1));
        displayTA = new JTextArea(32,40);
        displayTA.setEditable(false);
        scroller = new JScrollPane(displayTA);
        displayPnl.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
        displayPnl.add(scroller);
    }

    private void createBottomPnl() {
        bottomPnl = new JPanel();
        bottomPnl.setLayout(new GridLayout(1, 2));

        quitBtn = new JButton("Quit");
        bottomPnl.add(quitBtn);

        quitBtn.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });
    }

    private void listFiles(File file) {

        if (file.isFile()) {

            displayTA.append(file + "\n");
        }
        else {

            File files[] = file.listFiles();

            if (files != null) {

                for (File f : files) {

                    displayTA.append(f + "\n");

                    if (f.isDirectory()) {

                        listFiles(f);
                    }
                }
            }
        }
    }
}