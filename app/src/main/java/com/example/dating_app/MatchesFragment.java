package com.example.dating_app;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;


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


public class MatchesFragment extends Fragment {

    public ArrayList matchList = new ArrayList();
    private MatchesViewModel viewModel = new MatchesViewModel();
    SettingsViewModel settingsViewModel = new SettingsViewModel();
    RecyclerView recyclerView;
    MatchesCardRecyclerViewAdapter adapter;
    LocationManager locationManager;
    Location userLoc;
    Float maxDist = 16093.44f;


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

        locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
        userLoc = new Location(getProviderName());

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(), 2,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayout);
        adapter = new MatchesCardRecyclerViewAdapter(getContext(),matchList);
        recyclerView.setAdapter(adapter);
        gpsUpdates(view);
        getDistanceSetting();
        getMatches();




        return view;
    }

    @Override
    public void onPause() {
        viewModel.clear();
        super.onPause();
    }

    private boolean checkLocation() {
        if(!isLocationEnabled()) {
            showAlert();
        }
        return isLocationEnabled();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this.getContext());
        dialog.setTitle(R.string.enable_location)
                .setMessage(getString(R.string.location_message))
                .setPositiveButton(R.string.location_settings, (paramDialogInterface, paramInt) -> {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                })
                .setNegativeButton(R.string.location_cancel, (paramDialogInterface, paramInt) -> {
                });
        dialog.show();
    }


    public void gpsUpdates(View view) {
        if(!checkLocation()) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(getProviderName(), 0, 0, locationListener);
            Toast.makeText(this.getContext(), R.string.provider_started_running, Toast.LENGTH_LONG).show();
        }

    }

    String getProviderName() {

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);

        return locationManager.getBestProvider(criteria, true);
    }

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            userLoc.setLatitude(location.getLatitude());
            userLoc.setLongitude(location.getLongitude());
            getMatches();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}

        @Override
        public void onProviderEnabled(String s) {}

        @Override
        public void onProviderDisabled(String s) {}
    };

    public void getMatches(){
        viewModel.getMatches(
                (ArrayList<Match> matches) -> {
                    ArrayList<Match> over = new ArrayList<>();
                    float[] dist = new float[1];
                    for(Match match : matches){
                        Location.distanceBetween(Double.parseDouble(match.lat), Double.parseDouble(match.longitude), userLoc.getLatitude(), userLoc.getLongitude(), dist);
                        if(dist[0] > maxDist ){
                            over.add(match);
                        }
                    }
                    matches.removeAll(over);
                    adapter.setMatchesList(matches);
                    adapter.notifyDataSetChanged();
                });
    }

    public void getDistanceSetting() {
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        Float five = new Float(8046.72f);
        Float ten = new Float(16093.44f);
        Float fifteen = new Float(24140.16f);
        Float twentyfive = new Float(40233.6f);

        final Observer<List<com.example.dating_app.Settings>> getSettingsObserver = (new Observer<List<com.example.dating_app.Settings>>() {

            @Override
            public void onChanged(List<com.example.dating_app.Settings> settings) {
                ;
                if (settings == null || settings.size() <= 0) {
                    return;
                }

                com.example.dating_app.Settings set = settings.get(settings.size() - 1);

                if (set == null) {
                    return;
                }
                Float dist = 0f;
                switch (set.getDistMax()) {
                    case 0:
                        dist = five;
                        break;
                    case 1:
                        dist = ten;
                        break;
                    case 2:
                        dist = fifteen;
                        break;
                    case 3:
                        dist = twentyfive;
                        break;

                }
                setDistance(dist);
            }
        });
        settingsViewModel.loadSettings(this.getContext()).observe(this.getViewLifecycleOwner(),getSettingsObserver);
    }

    public void setDistance(Float f){
        this.maxDist = f;
    }
}

