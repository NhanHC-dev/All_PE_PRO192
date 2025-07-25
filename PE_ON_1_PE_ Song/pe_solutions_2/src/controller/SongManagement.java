package controller;

import model.Song;
import model.SongList;
import view.Menu;
import view.SongListView;
import view.Validation;

import java.util.*;

public class SongManagement extends Menu<String> {
    public SongManagement(String title, String[] options) {
        super(title, options);
    }
    private SongList songList = new SongList();
    private SongListView songListView = new SongListView();
    @Override
    public void execute(int n) {
        switch (n) {
            case 1 -> songListView.listAll();
            case 2 -> songListView.addNewSong();
            case 3 -> songListView.searchByKeyword();
            case 4 -> songListView.update();
            case 5 -> songListView.saveToFile("output.txt");
            case 6 -> songListView.statisticByArtist();
            case 7 -> songListView.sortDesc();
            case 8 -> songListView.deleteSong();
            case 9 -> songListView.exit();
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
