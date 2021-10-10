package dev.mufa.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import dev.mufa.movieapp.R;
import dev.mufa.movieapp.model.MoviesModel;
import dev.mufa.movieapp.utils.Credential;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieVH> {
    private Context context;
    private List<MoviesModel> movies;
    private onSelectData onSelectData;

    private static OnItemClickedListener listener;

    public interface OnItemClickedListener {
        void onItemClicked(View itemView, int position);
    }

    public void setOnItemClickedListener(OnItemClickedListener listener) {
        this.listener = listener;
    }

    public interface onSelectData {
        void onSelected(MoviesModel moviesModel);
    }

    public MovieAdapter(Context context, List<MoviesModel> movies, MovieAdapter.onSelectData xSelectData) {
        this.context = context;
        this.movies = movies;
        this.onSelectData = xSelectData;
    }

    @NonNull
    @Override
    public MovieVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_movies,parent,false);
        return new MovieVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieVH holder, int position) {
        MoviesModel moviesModel = movies.get(position);
        holder.movie_poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectData.onSelected(moviesModel);
            }
        });
        Glide.with(context)
                .load(Credential.BASE_IMAGE+moviesModel.getposter_path())
                .into(holder.movie_poster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieVH extends RecyclerView.ViewHolder {
        private ImageView movie_poster;
        public MovieVH(@NonNull View itemView) {
            super(itemView);
            movie_poster = itemView.findViewById(R.id.movie_poster);
            movie_poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        listener.onItemClicked(itemView, getLayoutPosition());
                    }
                }
            });
        }
    }
}
