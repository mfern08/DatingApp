package com.example.dating_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ProfileFragment extends Fragment {

    private SecondActivity.Attachment attachment;
    private FragmentManager fManager;
    private TextView name;
    private TextView occupation;
    private TextView info;
    private TextView age;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View veiw = inflater.inflate(R.layout.fragment_profile, container, false);

        name = veiw.findViewById(R.id.name);
        age = veiw.findViewById(R.id.date);
        occupation = veiw.findViewById(R.id.occupation);
        info = veiw.findViewById(R.id.info);

        name.setText(this.attachment.name);
        age.setText(this.attachment.age);
        occupation.setText(this.attachment.occupation);
        info.setText(this.attachment.info);

        return veiw;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putString(Constants.KEY_NAME, attachment.name);
        outState.putString(Constants.KEY_AGE, attachment.age);
        outState.putString(Constants.KEY_OCC, attachment.occupation);
        outState.putString(Constants.KEY_INFO, attachment.info);
    }

    void setAttach(SecondActivity.Attachment attach){
        this.attachment = attach;
    }


}
