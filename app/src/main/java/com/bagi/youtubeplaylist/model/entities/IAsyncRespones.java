package com.bagi.youtubeplaylist.model.entities;

import java.util.ArrayList;

/**
 * Created by asusX541u on 11/02/2018.
 * This Interface for communication between classes FetchData and MainActivity.
 */

public interface IAsyncRespones {
    void processFinish(ArrayList<PlayList> playLists);
}
