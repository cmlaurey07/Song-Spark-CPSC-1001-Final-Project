import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PlaylistHelper {
    
    private ArrayList<String> playlistGenres;
    private ArrayList<Song> allSongs;
    private ArrayList<Song> playlistSongs;
    private int numSongs;

    public PlaylistHelper(ArrayList<String> playlistGenres, int numSongs){
        this.playlistGenres = playlistGenres;
        this.numSongs = numSongs; 
        allSongs = new ArrayList<>();
        playlistSongs = new ArrayList<>();
    }

    //Load songs from files based on selected genres
    public ArrayList<String> loadPossibleSongs(){
        ArrayList<String> playlistSongs = new ArrayList<>();
        
        for(String genre: playlistGenres){
            if(genre.equals("Pop")){
                try(Scanner scan = new Scanner(new File("Pop.txt"))){
                    loadSongsByGenre(genre, allSongs, scan);
                }
                catch(FileNotFoundException e){
                    System.out.println("File not found");
                }
            }
            else if(genre.equals("Classical")){
                try(Scanner scan = new Scanner(new File("Classical.txt"))){
                    loadSongsByGenre(genre, allSongs, scan);
                }
                catch(FileNotFoundException e){
                    System.out.println("File not found");
                }
            }
            else if(genre.equals("Musical Theater")){
                try(Scanner scan = new Scanner(new File("MusicalTheater.txt"))){
                    loadSongsByGenre(genre, allSongs, scan);
                }
                catch(FileNotFoundException e){
                    System.out.println("File not found");
                }
            }
            else if(genre.equals("K-Pop")){
                try(Scanner scan = new Scanner(new File("KPop.txt"))){
                    loadSongsByGenre(genre, allSongs, scan);
                }
                catch(FileNotFoundException e){
                    System.out.println("File not found");
                }
            }
            else if(genre.equals("Random")){
                try(Scanner scan = new Scanner(new File("Random.txt"))){
                    loadSongsByGenre(genre, allSongs, scan);
                }
                catch(FileNotFoundException e){
                    System.out.println("File not found");
                }
            }
        }
        return playlistSongs;
    }

    //Helper method to load songs from a specific genre file
    public static void loadSongsByGenre(String genre, ArrayList<Song> allSongs, Scanner scan){
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String songTitle = line.substring(0, line.indexOf(";"));

            String artist = line.substring(line.indexOf(";") + 1, line.indexOf("|"));

            double songLength = Double.parseDouble(line.substring(line.indexOf("|") + 1, line.indexOf("/")));

            String releaseDateString = line.substring(line.indexOf("/") + 1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate releaseDate = LocalDate.parse(releaseDateString, formatter);

            Song song = new Song(songTitle, artist, songLength, releaseDate, genre);
            allSongs.add(song);
        }
    }

    //Creates a random playlist based on loaded songs
    public ArrayList<Song> generatePlaylist(){
        ArrayList<Integer> chosenIndexes = new ArrayList<Integer>();
        for(int i = 0; i < numSongs; i++){
            int randomIndex = (int)(Math.random() * allSongs.size());
            if(chosenIndexes.contains(randomIndex)){
                i--;
                continue;
            }
            else{
                Song randomSong = allSongs.get(randomIndex);
                playlistSongs.add(randomSong);
                chosenIndexes.add(randomIndex);
            }
        }
        return playlistSongs;
    }
}
