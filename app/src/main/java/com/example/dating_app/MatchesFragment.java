package com.example.dating_app;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.provider.Settings;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class MatchesFragment extends Fragment {

    public ArrayList matchList = new ArrayList();
    private MatchesViewModel viewModel = new MatchesViewModel();
    RecyclerView recyclerView;
    MatchesCardRecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);
        setHasOptionsMenu(true);

        if(getArguments() != null){
            matchList = getArguments().getParcelableArrayList(Constants.KEY_MATCHES);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(), 2,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayout);
        adapter = new MatchesCardRecyclerViewAdapter(getContext(),matchList);
        recyclerView.setAdapter(adapter);


        viewModel.getMatches(
                (ArrayList<Match> matches) -> {
                    adapter.setMatchesList(matches);
                    adapter.notifyDataSetChanged();
                });

        return view;
    }

    @Override
    public void onPause() {
        viewModel.clear();
        super.onPause();
    }


}

