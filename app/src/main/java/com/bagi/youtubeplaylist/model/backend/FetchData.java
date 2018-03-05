package com.bagi.youtubeplaylist.model.backend;

import android.content.Context;
import android.os.AsyncTask;

import com.bagi.youtubeplaylist.model.entities.IAsyncRespones;
import com.bagi.youtubeplaylist.presenter.CustomAdapter;
import com.bagi.youtubeplaylist.presenter.MainActivity;
import com.bagi.youtubeplaylist.model.entities.PlayList;
import com.bagi.youtubeplaylist.model.entities.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * The class connecting to internet so we must to extend AsyncTask.
 */

public class FetchData extends AsyncTask<Void,Void,Void> {
    String data ="";
    ArrayList<Video> listTitle;
    ArrayList<PlayList> playLists;
   // Context mContext;
   // private WeakReference<MainActivity> mainActivityWeakReference;

    public IAsyncRespones delegate = null;

    // class AsyncTask cannot to render the UI so we need context of MainActivity, this class responsible on UI.
/*    public FetchData(Context mContext, MainActivity mainActivity) {
        this.mContext = mContext;
        this.mainActivityWeakReference = new WeakReference<MainActivity>(mainActivity);
    }*/

    @Override
    protected Void doInBackground(Void... voids) {
        try {

            playLists = new ArrayList<PlayList>();
            URL url = new URL("https://api.myjson.com/bins/genpx");
            //connction to url
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //read the data from url
            InputStream inputStream = httpURLConnection. getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null) {
                line = bufferedReader.readLine();
                data += line;
            }

            JSONObject json = new JSONObject(data);
            JSONArray playlistArr = json.getJSONArray("Playlists");

            for (int i =0; i< playlistArr.length();i++){
                listTitle = new ArrayList<Video>();
                JSONObject ObjectplayList = playlistArr.getJSONObject(i);
                String namePlaylist = ObjectplayList.getString("ListTitle");
                JSONArray jsonArrItem= playlistArr.getJSONObject(i).getJSONArray("ListItems");
                listTitle.clear();
                for(int j=0; j<jsonArrItem.length(); j++){
                    JSONObject item = jsonArrItem.getJSONObject(j);
                    Video video = new Video(item.getString("Title"), item.getString("link"), item.getString("thumb"));
                    listTitle.add(video);
                }

                PlayList playList = new PlayList(namePlaylist,listTitle);
                playLists.add(playList);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        delegate.processFinish(playLists);
     /*   CustomAdapter customAdapter = new CustomAdapter(mContext,playLists);
        MainActivity mainActivity = mainActivityWeakReference.get();
        mainActivity.getExpandableLV().setAdapter(customAdapter);*/
    }


}
