package com.example.dating_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;





import java.util.List;
public class SettingsFragment extends Fragment implements View.OnClickListener {

    private Spinner remindTime;
    private Spinner distMax;
    private Spinner gender;
    private Spinner ageMin;
    private Spinner ageMax;
    private CheckBox privateStatus;
    private Button saveBtn;

    private SettingsViewModel settingsViewModel;

    @Override
    public void onCreate(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);
        setHasOptionsMenu(true);
    }

 @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        saveBtn = view.findViewById(R.id.save_settings);
        saveBtn.setOnClickListener(this);

        remindTime = view.findViewById(R.id.time_spinner);
        distMax = view.findViewById(R.id.dist_max);
        gender = view.findViewById(R.id.gender);
        ageMin = view.findViewById(R.id.age_min);
        ageMax = view.findViewById(R.id.age_max);
        privateStatus = view.findViewById(R.id.checkbox);

        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        final Observer<List<Settings>> getSettingsObserver = settings -> {
            if(settings == null || settings.size() <= 0){
                return;
            }

            Settings set = settings.get(settings.size()-1);

            if (set == null) {
                return;
            }

            remindTime.setSelection(set.getRemindTime());
            distMax.setSelection(set.getDistMax());
            gender.setSelection(getIndex(gender, set.getGender()));
            privateStatus.setChecked(set.isPrivateStatus());
            ageMin.setSelection(set.getAgeMin());
            ageMax.setSelection(set.getAgeMax());

        };

        settingsViewModel.loadSettings(this.getContext()).observe(this.getViewLifecycleOwner(), getSettingsObserver);

        return view;
    }

    public void saveSettings(View view) {
        Settings set = new Settings();
        set.setRemindTime(remindTime.getSelectedItemPosition());
        set.setDistMax(distMax.getSelectedItemPosition());
        set.setGender(gender.getSelectedItem().toString());
        set.setPrivateStatus(privateStatus.isChecked());
        set.setAgeMin(ageMin.getSelectedItemPosition());
        set.setAgeMax(ageMax.getSelectedItemPosition());

        settingsViewModel.saveSettings(this.getContext(), set);
        Toast.makeText(getActivity(), "Settings Saved", Toast.LENGTH_SHORT).show();
    }

    //private method of your class
    private int getIndex(Spinner spinner, String gender){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(gender)){
                return i;
            }
        }

        return 0;
    }

    @Override
    public void onClick(View v) {
        saveSettings(v);
    }

}
