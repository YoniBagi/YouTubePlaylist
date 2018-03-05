package com.bagi.youtubeplaylist.presenter;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.bagi.youtubeplaylist.R;
import com.bagi.youtubeplaylist.model.backend.FetchData;
import com.bagi.youtubeplaylist.model.entities.IAsyncRespones;
import com.bagi.youtubeplaylist.model.entities.PlayList;
import com.bagi.youtubeplaylist.model.entities.Video;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class MainActivity extends YouTubeBaseActivity implements IAsyncRespones {

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    private ExpandableListView expandableLV;
    public static final String KEY = "AIzaSyCZlh5rZq10F9G9CkX-hkrVZ2Mu1gV-ZUY";
    YouTubePlayer player;

/*    public ExpandableListView getExpandableLV() {
        return expandableLV;
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.listVideoYT);
        expandableLV = (ExpandableListView) findViewById(R.id.mExpandableListView);

        //FetchData process = new FetchData(getApplicationContext(), this);
        FetchData process = new FetchData();
        //this to set delegate/listener back to this class
        process.delegate = this;
        process.execute();

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                    player = youTubePlayer;
                //player.loadVideo("g4LioRCDNno");
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }
        };
        youTubePlayerView.initialize(KEY,onInitializedListener);


        expandableLV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                PlayList playList = (PlayList) parent.getItemAtPosition(groupPosition);
                Video video =  playList.getVideos().get(childPosition);
                String idVideo = getIdVideo(video.getLink());
                player.loadVideo(idVideo);
                return false;
            }
        });

        // To only expand one child of an ExpandableListView at a time, thus opening a second child would close the previously opened child
        expandableLV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                    expandableLV.collapseGroup(previousGroup);
                previousGroup = groupPosition;

            }
        });
    }

    public String getIdVideo(String link){
        return link.substring(link.lastIndexOf("=")+1);
    }

    @Override
    public void processFinish(ArrayList<PlayList> playLists) {
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),playLists);
        expandableLV.setAdapter(customAdapter);
    }
}


