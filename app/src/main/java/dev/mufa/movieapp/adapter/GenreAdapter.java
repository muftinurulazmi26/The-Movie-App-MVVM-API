package dev.mufa.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.mufa.movieapp.R;
import dev.mufa.movieapp.model.GenresModel;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreVH> {
    private Context context;
    private List<GenresModel> genres;
    private int mSelected;

    private static OnItemClickedListener listener;

    public interface OnItemClickedListener {
        void onItemClicked(View itemView, int position);
    }

    public void setOnItemClickedListener(OnItemClickedListener listener) {
        this.listener = listener;
    }

    public GenreAdapter(Context context, List<GenresModel> genres) {
        this.context = context;
        this.genres = genres;
    }

    @NonNull
    @Override
    public GenreVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_genres,parent,false);
        return new GenreVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreVH holder, int position) {
        GenresModel genresModel = genres.get(position);
        holder.genre_name.setText(genresModel.getName());
        if (position == mSelected){
            holder.genre_name.setBackground(context.getResources().getDrawable(R.drawable.rounded_btn_blackdim_50dp));
        } else {
            holder.genre_name.setBackground(context.getResources().getDrawable(R.drawable.rounded_btn_orange_50dp));
        }
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public List<GenresModel> getGenres(){
        return genres;
    }

    public class GenreVH extends RecyclerView.ViewHolder {
        private TextView genre_name;

        public GenreVH(@NonNull View itemView) {
            super(itemView);
            genre_name = itemView.findViewById(R.id.genre_name);
            genre_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        listener.onItemClicked(itemView, getLayoutPosition());
                        mSelected = getLayoutPosition();
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }
}
