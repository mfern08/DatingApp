package com.example.dating_app;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MatchViewHolder extends RecyclerView.ViewHolder {
    ImageView matchImage;
    TextView matchName;
    Button likeButton;

    public MatchViewHolder(@NonNull View itemview){
        super(itemview);
        matchImage = itemview.findViewById(R.id.imageView);
        matchName = itemview.findViewById(R.id.matchName);
        likeButton = itemview.findViewById(R.id.likeButton);

        likeButton.setOnClickListener(view -> {
            String msg = "You liked" + matchName.getText().toString();
            likeButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
            Toast toast = Toast.makeText(view.getContext(),
                    msg, Toast.LENGTH_LONG);
            View tView = toast.getView();
            tView.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
            toast.show();
        });
    }

}
