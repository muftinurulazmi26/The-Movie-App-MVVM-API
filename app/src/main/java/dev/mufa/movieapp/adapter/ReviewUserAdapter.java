package dev.mufa.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import dev.mufa.movieapp.R;
import dev.mufa.movieapp.model.ReviewUserModel;
import dev.mufa.movieapp.model.TrailerModel;

public class ReviewUserAdapter extends RecyclerView.Adapter<ReviewUserAdapter.ReviewUserVH> {
    private Context mContext;
    private List<ReviewUserModel> items;
    private double Rating;

    public ReviewUserAdapter(Context mContext, List<ReviewUserModel> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @NonNull
    @Override
    public ReviewUserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_review,parent,false);
        return new ReviewUserVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewUserVH holder, int position) {
        ReviewUserModel reviewUserModel = items.get(position);
        holder.tvName.setText(reviewUserModel.getAuthorDetails().getUsername());
        Rating = reviewUserModel.getAuthorDetails().getRating();
        holder.tvContent.setText(reviewUserModel.getContent());
        holder.tvUpdateAt.setText(reviewUserModel.getUpdatedAt());

        float newValue = (float)Rating;
        holder.ratingBarUser.setNumStars(5);
        holder.ratingBarUser.setStepSize((float) 0.5);
        holder.ratingBarUser.setRating(newValue / 2);

//        Glide.with(mContext)
//                .load(reviewUserModel.getAuthorDetails().getAvatarPath())
//                .into(holder.pp_user);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ReviewUserVH extends RecyclerView.ViewHolder {
        private ImageView pp_user;
        private TextView tvName, tvContent, tvUpdateAt;
        private RatingBar ratingBarUser;

        public ReviewUserVH(@NonNull View itemView) {
            super(itemView);
            pp_user = itemView.findViewById(R.id.pp_user);
            tvName = itemView.findViewById(R.id.tvName);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvUpdateAt = itemView.findViewById(R.id.tvUpdateAt);
            ratingBarUser = itemView.findViewById(R.id.ratingBarUser);
        }
    }
}
