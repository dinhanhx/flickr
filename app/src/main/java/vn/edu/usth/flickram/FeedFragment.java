package vn.edu.usth.flickram;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class FeedFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<String> mUrl = new ArrayList<>(Arrays.asList(
            "https://i.imgur.com/zuG2bGQ.jpg",
            "https://i.imgur.com/ovr0NAF.jpg",
            "https://i.imgur.com/n6RfJX2.jpg",
            "https://i.imgur.com/qpr5LR2.jpg",
            "https://i.imgur.com/pSHXfu5.jpg",
            "https://i.imgur.com/3wQcZeY.jpg",
            "https://i.imgur.com/zuG2bGQ.jpg",
            "https://i.imgur.com/ovr0NAF.jpg",
            "https://i.imgur.com/n6RfJX2.jpg",
            "https://i.imgur.com/qpr5LR2.jpg",
            "https://i.imgur.com/pSHXfu5.jpg",
            "https://i.imgur.com/3wQcZeY.jpg",
            "https://i.imgur.com/zuG2bGQ.jpg",
            "https://i.imgur.com/ovr0NAF.jpg",
            "https://i.imgur.com/n6RfJX2.jpg",
            "https://i.imgur.com/qpr5LR2.jpg",
            "https://i.imgur.com/pSHXfu5.jpg",
            "https://i.imgur.com/3wQcZeY.jpg",
            "https://i.imgur.com/zuG2bGQ.jpg",
            "https://i.imgur.com/ovr0NAF.jpg",
            "https://i.imgur.com/n6RfJX2.jpg",
            "https://i.imgur.com/qpr5LR2.jpg",
            "https://i.imgur.com/pSHXfu5.jpg",
            "https://i.imgur.com/3wQcZeY.jpg"
    ));


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View feedView = inflater.inflate(R.layout.fragment_imageragment, container, false);

        RecyclerView recyclerView = (RecyclerView) feedView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setHasFixedSize(true);
        GalleryAdapter adapter = new GalleryAdapter(getActivity(), mUrl);
        recyclerView.setAdapter(adapter);


        return feedView;
    }
}