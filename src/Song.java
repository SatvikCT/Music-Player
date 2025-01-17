import java.util.Collection;
import java.util.Collections;

public class Song{
    String title;
    String artist;
    int total, current;
    Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
        this.total = 0;
        this.current = 0;
    }

    void play(){
        this.current += 1;
        this.total += 1;
    }

    void nextDay(){
        this.current = 0;
    }

    public static int playedCount = 0;


    @Override
    public String toString() {
        return title + " (" + artist + " " + current + ")";
    }
}