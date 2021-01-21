package vn.edu.usth.flickr;

import android.annotation.SuppressLint;
import android.content.Context;
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
import android.widget.SearchView;
import android.widget.Toast;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchFragment extends Fragment {

    private ArrayList<String> mUrl = new ArrayList<String>();
    private GalleryAdapter adapter;

    private String apiKey;
    private String sharedSecret;

    public SearchFragment(String apiKey, String sharedSecret) {
        this.apiKey = apiKey;
        this.sharedSecret = sharedSecret;
    }

    String text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View search_fragment = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView recyclerView = (RecyclerView) search_fragment.findViewById(R.id.recycler_view_search);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setHasFixedSize(true);
        adapter = new GalleryAdapter(getActivity(), mUrl);
        recyclerView.setAdapter(adapter);

        SearchView searchView = (SearchView) search_fragment.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                text = query;
                get_search_results();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return search_fragment;
    }

    private void get_search_results(){
        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, Void> searchForText = new AsyncTask<Void, Void, Void>() {
            @RequiresApi(api = Build.VERSION_CODES.N)

            @Override
            protected void onPreExecute(){
                adapter.clearUrlList();
                Toast.makeText(getActivity(),"Loading. Please wait.", Toast.LENGTH_LONG).show();
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    search(text);
                } catch (FlickrException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result){
                adapter.changeUrlList(mUrl);
            }
        };

        searchForText.execute();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void search(String text) throws FlickrException {
        Flickr f = new Flickr(apiKey, sharedSecret, new REST());

        PhotosInterface photos = f.getPhotosInterface();
        SearchParameters parameters = new SearchParameters();
        parameters.setMedia("photos");
        parameters.setExtras(Stream.of("media").collect(Collectors.toSet()));
        parameters.setText(text);

        mUrl.clear();

        PhotoList<Photo> results = photos.search(parameters, 30, 1 );
        results.forEach(p -> {
            mUrl.add(p.getSmallSquareUrl());
        });
    }
}