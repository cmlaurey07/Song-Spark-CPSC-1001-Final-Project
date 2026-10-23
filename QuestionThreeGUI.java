import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

public class QuestionThreeGUI extends JFrame implements ActionListener{
    
    JFrame frame;
    JPanel middlePanel;
    JPanel topPanel;
    JPanel bottomPanel;
    JPanel rightPanel;
    JPanel leftPanel;
    int numSongs;
    ArrayList<String> selectedGenres;
    ArrayList<Song> finalPlaylist;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton restartButton;
    Font sortingFont = new Font("Rockwell", Font.PLAIN, 18);
    Font songFont = new Font("Rockwell", Font.PLAIN, 16);
    Color backColor = new Color(242, 207, 238);
    Color playlistColor = new Color(224, 246, 249);

    public QuestionThreeGUI(JFrame frame, int numSongs, ArrayList<String> selectedGenres){

        this.frame = frame;
        this.numSongs = numSongs;   
        this.selectedGenres = selectedGenres;

        frame.getContentPane().setBackground(backColor);

        //Create PlaylistHelper object to generate playlist
        PlaylistHelper playlistObject = new PlaylistHelper(selectedGenres, numSongs);
        playlistObject.loadPossibleSongs();
        finalPlaylist = playlistObject.generatePlaylist();
        
        //Make the panels and add components
        middlePanel = displayPlaylist(finalPlaylist, middlePanel);

        topPanel = new JPanel();
        topPanel.setBackground(backColor);
        JLabel titleLabel = new JLabel("Your Generated Playlist:");
        titleLabel.setFont(new Font("Rockwell", Font.PLAIN, 40));
        topPanel.add(titleLabel);
        topPanel.setBorder(new EmptyBorder(30, 100, 30, 100));

        bottomPanel = new JPanel();
        bottomPanel.setBackground(backColor);
        bottomPanel.setBorder(new EmptyBorder(30, 60, 60, 60));

        rightPanel = new JPanel();
        rightPanel.setBackground(backColor);
        rightPanel.setBorder(new EmptyBorder(30, 40, 50, 40));
        
        leftPanel = new JPanel();
        leftPanel.setBackground(backColor);
        leftPanel.setBorder(new EmptyBorder(30, 40, 50, 40));

        //Create restart button
        restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Rockwell", Font.PLAIN, 16));
        restartButton.addActionListener(this);
        bottomPanel.add(restartButton);

        //Add panels to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.setVisible(true);
    }

    public JPanel displayPlaylist(ArrayList<Song> playlist, JPanel middlePanel){
        middlePanel = new JPanel(new GridLayout(numSongs + 1, 4));
        middlePanel.setBackground(playlistColor);
        
        //Create sorting buttons
        button1 = sortButton("Title", "(alphabetically)");
        button2 = sortButton("Artist/Composer", "(alphabetically)");
        button3 = sortButton("Length (s)", "(low to high)");
        button4 = sortButton("Release Date", "(old to new)");

        middlePanel.add(button1);
        middlePanel.add(button2);
        middlePanel.add(button3);
        middlePanel.add(button4);
        
        //Create labels for each song attribute and add to panel
        for(int i = 0; i < numSongs; i++){
            JLabel songLabel = new JLabel(playlist.get(i).getTitle(), SwingConstants.CENTER);
            songLabel.setFont(songFont);
            JLabel artistLabel = new JLabel(playlist.get(i).getArtist(), SwingConstants.CENTER);
            artistLabel.setFont(songFont);
            JLabel lengthLabel = new JLabel(String.valueOf(playlist.get(i).getSongLength()), SwingConstants.CENTER);
            lengthLabel.setFont(songFont);
            JLabel dateLabel = new JLabel(playlist.get(i).getReleaseDate().toString(), SwingConstants.CENTER);
            dateLabel.setFont(songFont);

            middlePanel.add(songLabel);
            middlePanel.add(artistLabel);
            middlePanel.add(lengthLabel);
            middlePanel.add(dateLabel);
        }
        return middlePanel;
    }

    //Create sort button method
    public JButton sortButton(String area, String order){
        JButton button = new JButton();
        button = new JButton("<html>Sort By " + area + "<br>" + order + "</html>");
        button.setFont(sortingFont);
        button.addActionListener(this);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        return button;
    }

    //Sort playlist by title
    public ArrayList<Song> sortByTitle(ArrayList<Song> playlist){
        String[] titles = new String[playlist.size()];
        for(int i = 0; i < playlist.size(); i++){
            titles[i] = playlist.get(i).getTitle();
        }
        Arrays.sort(titles);
        ArrayList<Song> sortedPlaylist = new ArrayList<Song>();

        for(int i = 0; i < titles.length; i++){
            for(int j = 0; j < playlist.size(); j++){
                if(titles[i].equals(playlist.get(j).getTitle())){
                    sortedPlaylist.add(playlist.get(j));
                    break;
                }
            }
        }
        return sortedPlaylist;
    }

    //Sort playlist by artist
    public ArrayList<Song> sortByArtist(ArrayList<Song> playlist){
        String[] artists = new String[playlist.size()];
        for(int i = 0; i < playlist.size(); i++){
            artists[i] = playlist.get(i).getArtist();
        }

        Arrays.sort(artists);
        ArrayList<Song> sortedPlaylist = new ArrayList<Song>();
        HashSet<String> chosenSongs = new HashSet<String>();

        for(int i = 0; i < artists.length; i++){
            for(int j = 0; j < playlist.size(); j++){
                if(artists[i].equals(playlist.get(j).getArtist())){
                    if(!chosenSongs.contains(playlist.get(j).getTitle())){
                        sortedPlaylist.add(playlist.get(j));
                        chosenSongs.add(playlist.get(j).getTitle());
                        break;
                    }
                    else{
                        continue;
                    }
                }
            }
        }
        return sortedPlaylist;
    }

    //Sort playlist by length
    public ArrayList<Song> sortByLength(ArrayList<Song> playlist){
        ArrayList<Song> sortedPlaylist = new ArrayList<Song>();
        for(int i = 0; i < playlist.size(); i++){
            if(sortedPlaylist.size() == 0){
                sortedPlaylist.add(playlist.get(i));
            }
            else{
                for(int j = 0; j < sortedPlaylist.size(); j++){
                    if(playlist.get(i).getSongLength() < sortedPlaylist.get(j).getSongLength()){
                        sortedPlaylist.add(j, playlist.get(i));
                        break;
                    }
                    else if(playlist.get(i).getSongLength() == sortedPlaylist.get(j).getSongLength()){
                        sortedPlaylist.add(j, playlist.get(i));
                        break;
                    }
                    else if(j == sortedPlaylist.size() - 1){
                        sortedPlaylist.add(playlist.get(i));
                        break;
                    }
                }
            }
        }
        return sortedPlaylist;
    }

    //Sort playlist by release date
    public ArrayList<Song> sortByDate(ArrayList<Song> playlist){
        LocalDate[] dates = new LocalDate[playlist.size()];
        HashSet<String> chosenSongs = new HashSet<String>();
        
        for(int i = 0; i < playlist.size(); i++){
            dates[i] = playlist.get(i).getReleaseDate();
        }

        Arrays.sort(dates);
        ArrayList<Song> sortedPlaylist = new ArrayList<Song>();
        for(int i = 0; i < dates.length; i++){
            for(int j = 0; j < playlist.size(); j++){
                if(dates[i].equals(playlist.get(j).getReleaseDate())){
                    if(chosenSongs.contains(playlist.get(j).getTitle())){
                        continue;
                    }
                    else{
                        sortedPlaylist.add(playlist.get(j));
                        chosenSongs.add(playlist.get(j).getTitle());
                        break;
                    }
                }
            }
        }
        return sortedPlaylist;
    }

    //Display sorted playlist
    public void displaySortedPlaylist(ArrayList<Song> sortedPlaylist){
        JPanel newMiddlePanel = new JPanel();
        frame.getContentPane().remove(middlePanel);
        newMiddlePanel = displayPlaylist(sortedPlaylist, newMiddlePanel);
        middlePanel = newMiddlePanel;

        frame.add(middlePanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        //If a sort button is clicked, sort the playlist accordingly and display
        if(event.getSource() == button1){
            ArrayList<Song> sortedPlaylist = sortByTitle(finalPlaylist);
            displaySortedPlaylist(sortedPlaylist);
        }
        if(event.getSource() == button2){
            ArrayList<Song> sortedPlaylist = sortByArtist(finalPlaylist);
            displaySortedPlaylist(sortedPlaylist);
        }
        if(event.getSource() == button3){
            ArrayList<Song> sortedPlaylist = sortByLength(finalPlaylist);
            displaySortedPlaylist(sortedPlaylist);
        }
        if(event.getSource() == button4){
            ArrayList<Song> sortedPlaylist = sortByDate(finalPlaylist);
            displaySortedPlaylist(sortedPlaylist);
        }

        //If restart button is clicked, go back to Welcome GUI
        if(event.getSource() == restartButton){
            frame.getContentPane().removeAll();
            frame.repaint();
            frame.dispose();
            WelcomeGUI welcomeGUI = new WelcomeGUI();
        }
    }
}
