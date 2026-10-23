import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeGUI extends JFrame implements ActionListener{

    JButton startButton;
    JFrame frame;
    JLabel midLabel;
    JPanel bottomPanel;
    JPanel middlePanel;

    public WelcomeGUI(){
        frame = new JFrame();
        frame.setSize(750, 750);

        //Scale and size the image into a panel
        ImageIcon welcomeImg = new ImageIcon("SongSparkWelcomeDesign.png");
        Image scaledImage = welcomeImg.getImage().getScaledInstance(850, 850, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        //Create label, panels, and buttons
        midLabel = new JLabel(scaledIcon);

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(242, 207, 238));
        
        middlePanel = new JPanel();
        middlePanel.setBackground(new Color(242, 207, 238));    

        startButton = new JButton("Click Here to Start!");
        startButton.setPreferredSize(new Dimension (200, 50));
        startButton.setFont(new Font("Rockwell", Font.PLAIN, 16));
        startButton.addActionListener(this);

        bottomPanel.add(startButton);
        middlePanel.add(midLabel);

        //Add panels to frame
        frame.setLayout(new BorderLayout());
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(middlePanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        //If start button is clicked, move to question one GUI
        if(event.getSource() == startButton){
            frame.getContentPane().removeAll();
            frame.repaint();
            QuestionOneGUI q1GUI = new QuestionOneGUI(frame);
        }
    }
    public static void main(String[] args){
        WelcomeGUI welcomeGUI = new WelcomeGUI();
    }

}