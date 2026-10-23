import java.time.LocalDate;

public class Song {
    private String title;
    private String artist;
    private double songLength;
    private int songLengthSeconds;
    private LocalDate releaseDate;
    private String genre;

    public Song(String title, String artist, double songLength, LocalDate releaseDate, String genre){
        this.title = title;
        this.artist = artist;
        this.songLength = songLength;
        songLengthSeconds = (int)(songLength * 60) + (int)((songLength%1)*100);
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    //Getter methods
    public String getTitle(){
        return title;
    }

    public String getArtist(){
        return artist;
    }

    public int getSongLength(){
        return songLengthSeconds;
    }

    public LocalDate getReleaseDate(){
        return releaseDate;
    }

    public String getGenre(){
        return genre;
    }

}
