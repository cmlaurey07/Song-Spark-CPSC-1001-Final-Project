import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuestionOneGUI extends JFrame implements ActionListener {
    
    JFrame frame;
    JLabel label;
    JPanel bottomPanel;
    JSlider slider;
    JLabel midLabel;
    JButton submitButton;

    public QuestionOneGUI(JFrame parentFrame){
        //Use the same frame from WelcomeGUI
        frame = parentFrame;
        frame.getContentPane().setBackground(new Color(242, 207, 238));

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(242, 207, 238));

        //Create submit button
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension (200, 50));
        submitButton.addActionListener(this);
        submitButton.setFont(new Font("Rockwell", Font.PLAIN, 16));

        //Scale and size the image into a panel
        ImageIcon questionImg = new ImageIcon("SongSparkQ1Design.png");
        Image scaledImage = questionImg.getImage().getScaledInstance(900, 850, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        midLabel = new JLabel(scaledIcon);

        //Create slider
        slider = new JSlider(JSlider.HORIZONTAL, 5, 15, 10);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setPreferredSize(new Dimension(400, 100));
        
        //Add components to panels and frame
        bottomPanel.add(slider);
        bottomPanel.add(submitButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(midLabel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        //If submit button is clicked, move to question two GUI
        if(event.getSource() == submitButton){
            int numSongs = slider.getValue();
            frame.getContentPane().removeAll();
            frame.repaint();
            QuestionTwoGUI q2GUI = new QuestionTwoGUI(frame, numSongs);
        }
    }
}
