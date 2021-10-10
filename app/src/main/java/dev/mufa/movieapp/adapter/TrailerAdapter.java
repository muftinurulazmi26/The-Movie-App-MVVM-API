package dev.mufa.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.mufa.movieapp.R;
import dev.mufa.movieapp.model.TrailerModel;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerVH> {
    private Context mContext;
    private List<TrailerModel> items;

    public TrailerAdapter(Context mContext, List<TrailerModel> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @NonNull
    @Override
    public TrailerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_trailer,parent,false);
        return new TrailerVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerVH holder, int position) {
        TrailerModel trailerModel = items.get(position);
        holder.btnTrailer.setText(trailerModel.getType());
        holder.btnTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=" + trailerModel.getKey()));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class TrailerVH extends RecyclerView.ViewHolder {
        private Button btnTrailer;

        public TrailerVH(@NonNull View itemView) {
            super(itemView);
            btnTrailer = itemView.findViewById(R.id.btnTrailer);
        }
    }
}
