package com.example.dating_app;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MatchesCardRecyclerViewAdapter extends RecyclerView.Adapter<MatchViewHolder> {
    private List <Match> matchList;
    private Context mContext;

    MatchesCardRecyclerViewAdapter(Context mContext, List<Match> matchList) {
        this.mContext = mContext;
        this.matchList = matchList;
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card, parent, false);
        return new MatchViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        if (matchList != null) {
            Match mBind = this.matchList.get(position);
            holder.matchName.setText(mBind.name);
            Picasso.get().load(mBind.imageUrl).into(holder.matchImage);
            if (mBind.liked){
                holder.likeButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
            }else {
                holder.likeButton.setBackgroundResource(R.drawable.ic_round_favorite_border_24);
            }

        }
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public void setMatchesList(List<Match> mSet) {

        this.matchList = mSet;
    }
}
