package model;

import view.Validation;

import java.util.Date;

public class Song {
    private String id;
    private String name;
    private Date releaseDate;
    private String artist;
    private String album;

    public Song(String id, String name, Date releaseDate, String artist, String album) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.album = album;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    @Override
    public String toString() {
        //%tF la in ra Date
        return String.format("ID: %s | Name: %s | Release Date: %s | Artist: %s | Album: %s\n",
                id, name, Validation.showDate(releaseDate), artist, album);
    }
}
