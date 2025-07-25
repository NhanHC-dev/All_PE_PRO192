
package view;

import model.Song;
import model.SongList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class SongListView {
    private SongList songList = new SongList();
public void deleteSong() {
        String id = Validation.getString("Enter song id to delete: ");
        songList.delete(v -> v.getId().equalsIgnoreCase(id));
        System.out.println("Song deleted (if it existed).\n");
    }

    public void update() {
        String id = Validation.getString("Enter Song ID want to update: ");
         Song result = songList.searchSong(i -> i.getId().equalsIgnoreCase(id)).get(0);
        if (result.getId() == null) {
            System.out.println("Song with ID \" + id + \" not found.");
        } else {
            System.out.println(result);
            String newName = Validation.getString("Enter new song name: ");
            String newArtist = Validation.getString("Enter new artist: ");
            Date newDate = Validation.checkValidDate("Enter new release date (dd-MM-yyyy): ");
            result.setName(newName);
            result.setArtist(newArtist);
            result.setReleaseDate(newDate);
            System.out.println("Song updated successfully.");
        }
    }

    public void addNewSong() {
        String id ;
        while (true){
            id = Validation.getString("Enter Song Code: ");
            if (isSongIdDuplicated(id)) {
                System.out.println("Code is duplicated, please try again.");
            } else {
                break;
            }
        }
        String name = Validation.getString("Enter name: ");
        Date releaseDate = Validation.checkValidDate("Enter release date (dd-MM-yyyy): ");
        String artist = Validation.getString("Enter Artist: ");
        String album = Validation.getString("Enter Album: ");
        songList.addSong( new Song(id, name, releaseDate, artist, album));
        System.out.println("Song added successfully.");
    }

    private boolean isSongIdDuplicated(String code) {
        return songList.stream().anyMatch(v -> v.getId().equalsIgnoreCase(code));
    }
    public void searchByKeyword() {
        String keyword = Validation.getString("Enter Keyword: ").toLowerCase();
        List<Song> result = songList.searchSong(
                s -> s.getName().toLowerCase().contains(keyword) || s.getArtist().toLowerCase().contains(keyword));
        if (result.isEmpty()) {
            System.out.println("Song not found.");
        }else {
            System.out.println("Song found with keyword: " + keyword);
            this.listAll(result);
        }
    }
    public void statisticByArtist() {
        List<String> processedArtists = new ArrayList<>();
        for (Song song : songList) {
            String artist = song.getArtist();
            if (!processedArtists.contains(artist)) {
                List<Song> songsByArtist = songList.searchSong(s -> s.getArtist().equalsIgnoreCase(artist));
                System.out.println(artist + ": " + songsByArtist.size() + " song(s)");
                processedArtists.add(artist);
                for (Song s : songsByArtist) {
                    System.out.println("   - " + s.getName() + " (Released: " + Validation.showDate(s.getReleaseDate()) + ")");
                }
            }
        }
    }
    public void listAll(){
        listAll(songList, null);
    }
    public void listAll(List<Song> list){
        listAll(list, null);
    }
    public void listAll(Comparator<Song> comparator){
        listAll(songList, comparator);
    }
    public void listAll(List<Song> list, Comparator<Song> comparator){
        int total = list.size();
            if (total <= 0) {
                System.out.println(" Nothing to print!");
                return;
            }
            if (comparator != null) {
                list.sort(comparator);
            }
            System.out.println("List Songs:");
            System.out.println("--------------------------------");
            list.forEach(System.out::print);
            System.out.println("--------------------------------");
            System.out.println("Total: " + total + " Songs.");
    }

    public void saveToFile(String string) {
        songList.saveToFile(string);
    }
    public void sortDesc(){
        System.out.println("Song sorted by name.");
        this.listAll(Comparator.comparing(Song::getReleaseDate).reversed());
    }
    public void exit() {
        System.out.println("Bye");
        System.exit(0);
    }
}