import java.time.LocalDate;
import java.util.*;

public class MusicPlayer{

    HashMap<LocalDate, List<Song>> dateTop = new HashMap<>();
    HashMap<String, List<Song>> artistTop = new HashMap<>();
    List<Song> allTop = new ArrayList<>();
    HashMap<String, List<Song>> artistSongs = new HashMap<>();
    HashMap<String, Song> allSongs = new HashMap<>();

    LocalDate currentDate = LocalDate.now();

    MusicPlayer() {
        dateTop.put(currentDate, new ArrayList<>());
        for(int i = 0; i < 10; i++){
            dateTop.get(currentDate).add(new Song("null", "null"));
            allTop.add(new Song("null", "null"));
        }
    }

    void addSong(String title, String artist) {
        Song song = new Song(title, artist);
        allSongs.put(title, song);
        if(!artistSongs.containsKey(artist)) {
            artistSongs.put(artist, new ArrayList<>());
            artistTop.put(artist, new ArrayList<>());
            for(int i = 0; i < 10; i++){
                artistTop.get(artist).add(new Song("null", "null"));
            }
        }
        artistSongs.get(artist).add(song);
    }

    void playSong(String title) {

         Song song = allSongs.get(title);
         song.play();
         if(song.total > allTop.get(9).total){
             adjustAllTops(song);
         }
         if(song.current > dateTop.get(currentDate).get(9).current) {
             adjustDateTop(song);
         }
         if(song.total > artistTop.get(song.artist).get(9).total){
             adjustArtistTop(song);
         }
    }

    void nextDay(){
        for(String song: allSongs.keySet()) {
            allSongs.get(song).nextDay();
        }
        currentDate = currentDate.plusDays(1);
        dateTop.put(currentDate, new ArrayList<>());
        for(int i = 0; i < 10; i++){
            dateTop.get(currentDate).add(new Song("null", "null"));
        }
    }

    void getSongsByArtist(String artist) {
        System.out.println("Top song by " + artist + " are:");
        for(Song song: artistTop.get(artist)) {
            if(!song.title.equals("null"))System.out.println(song);
        }
    }

    void getTopSongs() {
        System.out.println("All time top songs are:");
        for(Song song: allTop) {
            if(!song.title.equals("null"))System.out.println(song);
        }
    }


    void getTopSongsByArtist(String artist) {
        System.out.println("Top song by " + artist + " are:");
        for(Song song: artistTop.get(artist)) {
            if(!song.title.equals("null"))System.out.println(song);
        }
    }

    void getTopSongsByDate(LocalDate date){
        System.out.println("Top songs on " + date + " are:");
        for(Song song: dateTop.get(date)) {
            if(!song.title.equals("null"))System.out.println(song);
        }
    }

    void adjuster(Song song, List<Song> currentDateList) {
        currentDateList.remove(song);

        int idx = currentDateList.size() - 1;
        while (idx >= 0 && song.current > currentDateList.get(idx).total) {
            idx--;
        }

        currentDateList.add(idx + 1, song);

        if (currentDateList.size() > 10) {
            currentDateList.remove(10);
        }
    }

    void adjustArtistTop(Song song) {

        List<Song> currentArtistList = artistTop.get(song.artist);
        adjuster(song, currentArtistList);
    }

    void adjustAllTops(Song song) {
        adjuster(song, allTop);
    }

    void adjustDateTop(Song song) {

        List<Song> currentDateList = dateTop.get(currentDate);
        adjuster(song, currentDateList);
    }

}
