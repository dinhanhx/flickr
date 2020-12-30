package vn.edu.usth.flickram;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class NotificationFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<String> mUrl = new ArrayList<>(Arrays.asList(
            "No One liked your avatar on 2020 Decembre 1st",
            "No One liked your avatar on 2019 Novembre 1st",
            "No One liked your avatar on 2018 Octobre 1st",
            "No One liked your avatar on 2017 Septembre 1st",
            "No One liked your avatar on 2016 August 1st",
            "No One liked your avatar on 2020 Decembre 1st",
            "No One liked your avatar on 2019 Novembre 1st",
            "No One liked your avatar on 2018 Octobre 1st",
            "No One liked your avatar on 2017 Septembre 1st",
            "No One liked your avatar on 2016 August 1st",
            "No One liked your avatar on 2020 Decembre 1st",
            "No One liked your avatar on 2019 Novembre 1st",
            "No One liked your avatar on 2018 Octobre 1st",
            "No One liked your avatar on 2017 Septembre 1st",
            "No One liked your avatar on 2016 August 1st"
    ));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_notification, container, false);

        RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.recycler_view_notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        NotificationAdapter adapter = new NotificationAdapter(getActivity(), mUrl);
        recyclerView.setAdapter(adapter);

        return rootview;
    }
}