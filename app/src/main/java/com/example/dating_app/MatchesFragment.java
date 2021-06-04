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
    SettingsViewModel settingsViewModel;
    LocationManager locationManager;
    Location loc;
    Float maxRange = 16093.44f;

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

        locationManager = (LocationManager)this.getActivity().getSystemService(Context.LOCATION_SERVICE);
        loc = new Location(getProvider());

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(), 2,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayout);
        adapter = new MatchesCardRecyclerViewAdapter(getContext(),matchList);
        recyclerView.setAdapter(adapter);
        updateGps(view);
        getDistance();
        getMatches();

        return view;
    }

    @Override
    public void onPause() {
        //viewModel.clear();
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
        dialog.setTitle(R.string.loc_enable)
                .setMessage(getString(R.string.loc_message))
                .setPositiveButton(R.string.loc_setting, (paramDialogInterface, paraInt) -> {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                })
                .setNegativeButton(R.string.loc_cancel, (paramDialogInterface, paramInt) -> {
                });
        dialog.show();
    }

    public void updateGps(View view) {
        if(!checkLocation()) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60 * 1000, 10, locationListener);
        }
    }

    String getProvider() {

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);

        return locationManager.getBestProvider(criteria, true);
    }

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            loc.setLatitude(location.getLatitude());
            loc.setLongitude(location.getLongitude());
            getMatches();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}

        @Override
        public void onProviderEnabled(String s) {}

        @Override
        public void onProviderDisabled(String s) {}
    };

    public void getMatches() {
        viewModel.getMatches(
                (ArrayList<Match> matches) -> {
                    ArrayList<Match> over = new ArrayList<>();
                    float[] distance = new float[1];
                    for(Match match : matches){
                        Location.distanceBetween(Double.parseDouble(match.lat), Double.parseDouble(match.longitude), loc.getLatitude(), loc.getLongitude(), distance);
                        if(distance[0] > maxRange){
                            over.add(match);
                        }
                    }
                    matches.removeAll(over);
                    adapter.setMatchesList(matches);
                    adapter.notifyDataSetChanged();
                });
    }
    public void getDistance() {
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        Float five = Float.valueOf(8046.72f);
        Float ten = Float.valueOf(16093.44f);
        Float fifteen = Float.valueOf (24140.16f);
        Float twentyfive = Float.valueOf(40233.6f);

        final Observer<List<com.example.dating_app.Settings>> getSettingObserver = (settings ->  {

                if (settings == null || settings.size() <= 0) {
                    return;
                }

                com.example.dating_app.Settings setDist = settings.get(settings.size() - 1);

                if (setDist == null) {
                    return;
                }

                Float range = 0f;
                switch (setDist.getDistMax()) {
                    case 0:
                        range = five;
                        break;
                    case 1:
                        range = ten;
                        break;
                    case 2:
                        range = fifteen;
                        break;
                    case 3:
                        range = twentyfive;
                        break;

                }
                setDist(range);
        });

        settingsViewModel.loadSettings(this.getContext()).observe(this.getViewLifecycleOwner(),getSettingObserver);
    }

    public void setDist(Float f){
        this.maxRange = f;
    }


}

