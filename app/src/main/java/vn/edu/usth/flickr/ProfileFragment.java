package vn.edu.usth.flickr;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;


import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.people.PeopleInterface;
import com.flickr4java.flickr.people.User;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private ArrayList<String> mUrl = new ArrayList<String>();
    private GalleryAdapter adapter;

    private View profile_fragment;

    private String apiKey;
    private String sharedSecret;
    private String userId;

    public ProfileFragment(String apiKey, String sharedSecret, String userId) {
        this.apiKey = apiKey;
        this.sharedSecret = sharedSecret;
        this.userId = userId;
    }

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        profile_fragment =  inflater.inflate(R.layout.fragment_profile, container, false);

        RecyclerView recyclerView = (RecyclerView) profile_fragment.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setHasFixedSize(true);
        adapter = new GalleryAdapter(getActivity(), mUrl);
        recyclerView.setAdapter(adapter);
        get_profile();
        return profile_fragment;
    }

    private void get_profile(){
        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, Void> setProfileAvatarTask = new AsyncTask<Void, Void, Void>() {
            @RequiresApi(api = Build.VERSION_CODES.N)

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    setupProfile();
                } catch (FlickrException e) {
                    e.printStackTrace();
                }
                return null;
            }

        };
        setProfileAvatarTask.execute();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setupProfile() throws FlickrException {
        Flickr f = new Flickr(apiKey, sharedSecret, new REST());

        PeopleInterface people = f.getPeopleInterface();
        User user = people.getInfo(userId);

        mUrl.clear();

        PhotoList<Photo> photos = people.getPublicPhotos(userId, 30, 1);
        photos.forEach(p -> {
            mUrl.add(p.getSmallSquareUrl());
        });

        String avatarUrl = user.getSecureBuddyIconUrl();
        RequestQueue rq = Volley.newRequestQueue(getActivity().getApplicationContext());
        Response.Listener<Bitmap> rl = new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                ImageView avatar = (ImageView) profile_fragment.findViewById(R.id.avatar);
                avatar.setImageBitmap(response);

                // I know this thing should NOT be in onResponse
                // I put it here because I don't know how to update UI in the main thread
                // Therefore, I take advantage of Volley to update
                // It's smart in some way
                TextView userinfo = (TextView) profile_fragment.findViewById(R.id.userinfo);
                StringBuilder userinfoText = new StringBuilder();
                userinfoText.append(user.getUsername());
                userinfoText.append("\n");
                userinfoText.append(user.getPhotosCount());
                userinfoText.append(" photos");
                userinfo.setText(userinfoText.toString());
            }
        };
        ImageRequest ir = new ImageRequest(avatarUrl, rl, 0, 0,
                ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, null);
        rq.add(ir);
    }
}