package controller;

import model.Song;
import model.SongList;
import view.Menu;
import view.Validation;

import java.util.*;

public class SongManagement extends Menu<String> {
    public SongManagement(String title, String[] options) {
        super(title, options);
    }
    private SongList songList = new SongList();
    @Override
    public void execute(int n) {
        switch (n) {
            case 1 -> songList.listAll();
            case 2 -> addNewSong();
            case 3 -> searchByKeyword();
            case 4 -> update();
            case 5 ->songList.saveToFile("output.txt");
            case 6 -> statisticByArtist();
            case 7 -> {
                System.out.println("Song sorted by name.");
                songList.listAll(Comparator.comparing(Song::getReleaseDate).reversed());
            }
            case 8 -> deleteSong();
            case 9 -> {
                System.out.println("Bye");
                System.exit(0);
            }
        }
    }

    private void deleteSong() {
        String id = Validation.getString("Enter song id to delete: ");
        songList.delete(v -> v.getId().equalsIgnoreCase(id));
        System.out.println("Song deleted (if it existed).\n");
    }

    private void update() {
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
            songList.listAll(result);
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


    public static void main(String[] args) {
        new SongManagement("Song Management", new String[]{
                "Display all the songs",
                "Add New Song",
                "Search Song by name or artist",
                "Update Song",
                "Export to file",
                "Statitics",
                "Sort desc",
                "Exit"
        }).run();
    }
}
