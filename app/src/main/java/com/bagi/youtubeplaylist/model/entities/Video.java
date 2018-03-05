package com.bagi.youtubeplaylist.model.entities;

/**
 * Created by asusX541u on 28/01/2018.
 */

public class Video {
    private String title;
    private String link;
    private String thumb;

    public Video(String title, String link, String thumb) {
        this.title = title;
        this.link = link;
        this.thumb = thumb;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }


    @Override
    public String toString() {
        return title;
    }
}
