package com.bagi.youtubeplaylist.model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asusX541u on 28/01/2018.
 */

public class PlayList {
    private String listTitle;
    private ArrayList<Video> videos;

    public PlayList(String listTitle, ArrayList<Video> videos) {
        this.listTitle = listTitle;
        this.videos = videos;
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return listTitle;
    }
}
