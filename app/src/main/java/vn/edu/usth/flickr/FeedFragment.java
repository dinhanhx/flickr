package vn.edu.usth.flickr;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.people.PeopleInterface;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;
import com.flickr4java.flickr.stats.StatsInterface;
import com.flickr4java.flickr.stats.StatsSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class FeedFragment extends Fragment {

    private RecyclerView recyclerView;

    private ArrayList<String> mUrl = new ArrayList<String>();
    private ArrayList<String> mUsername = new ArrayList<String>();
    private ArrayList<String> mTitle = new ArrayList<String>();
    private ArrayList<String> mLike = new ArrayList<String>();

    private View feedView;

    private String apiKey;
    private String sharedKey;

    public FeedFragment(String apiKey, String sharedKey) {
        this.apiKey = apiKey;
        this.sharedKey = sharedKey;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        feedView = inflater.inflate(R.layout.fragment_imageragment, container, false);

//        RecyclerView recyclerView = (RecyclerView) feedView.findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
//        recyclerView.setHasFixedSize(true);
//        GalleryAdapter adapter = new GalleryAdapter(getActivity(), mUrl);
//        recyclerView.setAdapter(adapter);

        AsyncTask<Void, Void, Void> setupNewsTask = new AsyncTask<Void, Void, Void>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    setupNews();
                    // Print logs for mURL, mTitle, mLike
                    Log.i("mUrl", mUrl.toString());
                    Log.i("mTitle", mTitle.toString());
                    Log.i("mLike", mLike.toString());
                    Log.i("mUsername", mUsername.toString());
                } catch (FlickrException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        setupNewsTask.execute();
        return feedView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setupNews() throws FlickrException {
        Flickr f = new Flickr(apiKey, sharedKey, new REST());

        PhotosInterface pi = f.getPhotosInterface();
        PhotoList<Photo> popularPhotos = pi.getRecent(null, 27, 0);

        mUsername.clear();
        mTitle.clear();
        mLike.clear();
        mUrl.clear();

        popularPhotos.forEach(p -> {
            // Get username
            String userId = p.getOwner().getId();
            PeopleInterface peopleInterface = f.getPeopleInterface();
            String username = "";
            try {
                username = peopleInterface.getInfo(userId).getUsername();
            } catch (FlickrException e) {
                e.printStackTrace();
            }
            mUsername.add(username);

            // Get number of favorites
            mLike.add(String.valueOf(p.getStats().getFavorites()));

            // Get title
            mTitle.add(p.getTitle());

            // Get url
            mUrl.add(p.getSmallSquareUrl());
        });
    }
}