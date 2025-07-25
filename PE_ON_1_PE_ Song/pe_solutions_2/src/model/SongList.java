package model;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import view.Validation;

public class SongList extends ArrayList<Song> {
    public static final String FILE_PATH = "songs.txt";

    public SongList() {
        super();
        importFromFile("src/song.txt");
    }

    public void importFromFile(String FILE_PATH) {
        this.clear();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.err.println("File " + FILE_PATH + " not found.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                lineCount++;
                if (line.trim().isEmpty()) continue;
                try {
                    String[] parts = line.split(";");
                    if (parts.length != 5) {
                        System.err.println("Line " + lineCount + " skipped: wrong number of fields.");
                        continue;
                    }
                    String[] idPart = parts[0].split("=");
                    String[] namePart = parts[1].split("=");
                    String[] datePart = parts[2].split("=");
                    String[] artistPart = parts[3].split("=");
                    String[] albumPart = parts[4].split("=");
                    if (idPart.length < 2 || namePart.length < 2 || datePart.length < 2
                            || artistPart.length < 2 || albumPart.length < 2) {
                        System.err.println("Line " + lineCount + " skipped: missing key=value format.");
                        continue;
                    }
                    String id = idPart[1].trim();
                    String songName = namePart[1].trim().replaceAll("\"", "");
                    Date releaseDate = Validation.validStringToDate(datePart[1].trim());
                    String artist = artistPart[1].trim().replaceAll("\"", "");
                    String album = albumPart[1].trim().replaceAll("\"", "");

                    Song song = new Song(id, songName, releaseDate, artist, album);
                    this.add(song);
                } catch (Exception e) {
                    System.err.println("Line " + lineCount + " skipped due to parsing error: " + e.getMessage());
                }
            }
            System.out.println(" Load complete. " + this.size() + " song(s) loaded.");
        } catch (IOException e) {
            System.err.println("Cannot read file: " + e.getMessage());
        }
    }
    
    public void addSong(Song e) {
        this.add(e);
    }

    public List<Song> searchSong(Predicate<Song> predicate) {
        return this.stream().filter(predicate).collect(Collectors.toList());
    }
    public void delete(Predicate<Song> predicate) {
        this.removeIf(predicate);
    }
    public void saveToFile(String fName) {
        if (this.isEmpty()) {
            System.out.println("No song to save.");
            return;
        }
        try (FileWriter fileWriter = new FileWriter(fName);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            for (Song song : this) {
                printWriter.printf(
                        "id=%s;songName=\"%s\";releaseDate=%s;artist=\"%s\";album=\"%s\"\n",
                        song.getId(),
                        song.getName(),
                        Validation.showDate(song.getReleaseDate()),
                        song.getArtist(),
                        song.getAlbum()
                );
            }
            System.out.println("Songs saved to file: " + fName);
        } catch (IOException e) {
            System.err.println("Error saving songs: " + e.getMessage());
        }
    }

}
