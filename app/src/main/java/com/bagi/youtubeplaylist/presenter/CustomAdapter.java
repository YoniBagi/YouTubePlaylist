package com.bagi.youtubeplaylist.presenter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bagi.youtubeplaylist.R;
import com.bagi.youtubeplaylist.model.entities.PlayList;
import com.bagi.youtubeplaylist.model.entities.Video;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by asusX541u on 28/01/2018.
 */

public class CustomAdapter extends BaseExpandableListAdapter {
    private Context c;
    private ArrayList<PlayList> playLists;
    private LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<PlayList> playLists) {
        this.c = c;
        this.playLists = playLists;
        this.inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return playLists.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return playLists.get(groupPosition).getVideos().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return playLists.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return playLists.get(groupPosition).getVideos().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = inflater.inflate(R.layout.row_playlist,null);
        PlayList playList = (PlayList) getGroup(groupPosition);

        TextView textView = (TextView) convertView.findViewById(R.id.tvNamePlaylist);
        textView.setText(playList.toString());
        convertView.setBackgroundColor(Color.LTGRAY);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        //ONLY INFLATER XML ROW LAYOUT IF ITS NOT PRESENT,OTHERWISE REUSE IT
        if(convertView == null)
            convertView = inflater.inflate(R.layout.row_video,null);
        Video video = (Video) getChild(groupPosition, childPosition);

        TextView textView = (TextView) convertView.findViewById(R.id.tVTitle);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ivVideo);

        textView.setText(video.toString());
        Picasso.with(c).load(video.getThumb().replaceAll(" ","")).fit().into(imageView);
        //Picasso.with(c).load(video.getThumb()).fit().into(imageView);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
