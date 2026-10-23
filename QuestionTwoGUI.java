import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import java.io.File;
import javax.sound.sampled.Clip;

public class QuestionTwoGUI extends JFrame implements ActionListener{

    JFrame frame;
    int numSongs;
    JPanel bottomPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JButton submitButton;
    JLabel midLabel;
    JButton genre1;
    JButton genre2;
    JButton genre3;
    JButton genre4;
    JButton genre5;
    JButton popPreview;
    JButton classicalPreview;
    JButton musicalTheaterPreview;
    JButton kpopPreview;
    JButton randomPreview;
    boolean genre1Selected = false;
    boolean genre2Selected = false;
    boolean genre3Selected = false;
    boolean genre4Selected = false;
    boolean genre5Selected = false;
    Color buttonColor = new Color(244, 169, 195);
    Color backColor = new Color(242, 207, 238);
    Font playFont = new Font("Rockwell", Font.PLAIN, 20);

    public QuestionTwoGUI(JFrame parentFrame, int numSongs){
        //Use the same frame from and inherit the number of songs
        frame = parentFrame;
        this.numSongs = numSongs;

        frame.getContentPane().setBackground(backColor);
        bottomPanel.setBackground(backColor);
        rightPanel.setBackground(backColor);
        topPanel.setBackground(backColor);

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40));

        rightPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Image to be displayed
        ImageIcon questionImg = new ImageIcon("SongSparkQ2Design.png");
        Image scaledImage = questionImg.getImage().getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        midLabel = new JLabel(scaledIcon);
        frame.add(midLabel, BorderLayout.CENTER);

        //Create submit button
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension (400, 50));
        submitButton.addActionListener(this);
        submitButton.setFont(new Font("Rockwell", Font.PLAIN, 30));

        //Create genre buttons
        genre1 = createGenreButton("Pop");
        genre2 = createGenreButton("Classical");
        genre3 = createGenreButton("Musical Theater");
        genre4 = createGenreButton("K-Pop");
        genre5 = createGenreButton("Random");

        //Create preview buttons
        popPreview = new JButton("Pop Music Preview");
        popPreview.setFont(playFont);
        popPreview.addActionListener(this);
        
        classicalPreview = new JButton("Classical Music Preview");
        classicalPreview.setFont(playFont);
        classicalPreview.addActionListener(this);

        musicalTheaterPreview = new JButton("Musical Theater Preview");
        musicalTheaterPreview.setFont(playFont);
        musicalTheaterPreview.addActionListener(this);

        kpopPreview = new JButton("K-Pop Music Preview");
        kpopPreview.setFont(playFont);
        kpopPreview.addActionListener(this);

        randomPreview = new JButton("Random Music Preview");
        randomPreview.setFont(playFont);
        randomPreview.addActionListener(this);

        //Add components to panels
        bottomPanel.add(popPreview);
        bottomPanel.add(classicalPreview);
        bottomPanel.add(musicalTheaterPreview);
        bottomPanel.add(kpopPreview);
        bottomPanel.add(randomPreview);

        rightPanel.add(genre1);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(genre2);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(genre3);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(genre4);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(genre5);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(submitButton);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.setBorder(new EmptyBorder(0, 30, 50, 60));
        topPanel.setBorder(new EmptyBorder(140, 100, 30, 100));
        
        //Add panels to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.setVisible(true);
    }

    public JButton createGenreButton(String genre){
        JButton button = new JButton(genre);
        button.setFont(new Font("Rockwell", Font.ITALIC, 50));
        button.setBackground(buttonColor);
        button.addActionListener(this);
        return button;
    }

    public void changeButtonColor(JButton button){
        if(button.getBackground() == Color.LIGHT_GRAY){
            button.setBackground(buttonColor);
        } else {
            button.setBackground(Color.LIGHT_GRAY);
        }
    }


    public void actionPerformed(ActionEvent event){
        if(event.getSource() == submitButton){
            // If the user does not check any buttons, don't do anything
            if(!(genre1Selected || genre2Selected || genre3Selected || genre4Selected || genre5Selected)){
                return;
            }

            //Add all selected genres to an ArrayList
            ArrayList<String> selectedGenres = new ArrayList<String>();
            if(genre1Selected){
                selectedGenres.add(genre1.getText());
            }
            if(genre2Selected){
                selectedGenres.add(genre2.getText());
            }
            if(genre3Selected){
                selectedGenres.add(genre3.getText());
            }
            if(genre4Selected){
                selectedGenres.add(genre4.getText());
            }
            if(genre5Selected){
                selectedGenres.add(genre5.getText());
            }

            //Move to next UI
            frame.getContentPane().removeAll();
            frame.repaint();
            
            QuestionThreeGUI q3GUI = new QuestionThreeGUI(frame, numSongs, selectedGenres);
        }

        //If a genre button is clicked, change its color and selection status
        if(event.getSource() == genre1){
            genre1Selected = !genre1Selected;
            changeButtonColor(genre1);
        }
        if(event.getSource() == genre2){
            genre2Selected = !genre2Selected;
            changeButtonColor(genre2);
        }
        if(event.getSource() == genre3){
            genre3Selected = !genre3Selected;
            changeButtonColor(genre3);
        }
        if(event.getSource() == genre4){
            genre4Selected = !genre4Selected;
            changeButtonColor(genre4);
        }
        if(event.getSource() == genre5){
            genre5Selected = !genre5Selected;
            changeButtonColor(genre5);
        }

        //If a preview button is clicked, play an example clip from the possible songs
        if(event.getSource() == popPreview){
            StdAudio.play("PopClip.wav");
        }
        else if(event.getSource() == musicalTheaterPreview){
            StdAudio.play("MTClip.wav");
        }
        else if(event.getSource() == classicalPreview){
            StdAudio.play("ClassicalClip.wav");
        }
        else if(event.getSource() == kpopPreview){
            StdAudio.play("KPopClip.wav");
        }
        else if(event.getSource() == randomPreview){
            StdAudio.play("RandomClip.wav");
        }
    }
}
