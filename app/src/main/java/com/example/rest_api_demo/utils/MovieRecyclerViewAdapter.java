package com.example.rest_api_demo.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rest_api_demo.R;
import com.example.rest_api_demo.models.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;

    private List<Movie> movieList;
    private OnRecycleClickListner onRecycleClickListner;


    public MovieRecyclerViewAdapter(List<Movie> movieList, OnRecycleClickListner onRecycleClickListner) {
        this.movieList = movieList;
        this.onRecycleClickListner = onRecycleClickListner;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem, parent, false), onRecycleClickListner);

            case VIEW_TYPE_LOADING:
                return new ProgresHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_item, parent, false));

            default:
                return null;


        }

    }

    @Override
    public int getItemViewType(int position) {

        if (isLoaderVisible) {
            return position == movieList.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

        holder.onBind(position);

    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public void addItems(List<Movie> movies) {
        movieList.addAll(movies);
        notifyDataSetChanged();
    }

    public void addLoading() {
        isLoaderVisible = true;
        movieList.add(new Movie());
        notifyItemInserted(movieList.size() - 1);
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = movieList.size() - 1;
        Movie movie = getItem(position);

        if (movie != null) {
            movieList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear()
    {
        movieList.clear();
        notifyDataSetChanged();
    }



    public Movie getItem(int position)
    {
        return movieList.get(position);
    }


    public class ProgresHolder extends BaseViewHolder
    {


        public ProgresHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }

        @Override
        protected void clear() {

        }
    }


    public class ViewHolder extends BaseViewHolder implements View.OnClickListener{


        @BindView(R.id.movieImage)
        ImageView movieImage;

        @BindView(R.id.movieName)
        TextView movieName;

        OnRecycleClickListner onRecycleClickListner;

        public ViewHolder(@NonNull View itemView,OnRecycleClickListner onRecycleClickListner) {
            super(itemView);

            ButterKnife.bind(this,itemView);
            this.onRecycleClickListner = onRecycleClickListner;
        }

        @Override
        public void onClick(View view) {

            onRecycleClickListner.onMovieClick(getItem(getAdapterPosition()));
        }

        @Override
        protected void clear() {

        }


        @Override
        public void onBind(int position) {
            super.onBind(position);

            Movie movie = movieList.get(position);
            Glide.with(itemView).load(movie.getMediumCoverImage()).fitCenter().into(movieImage);

            movieName.setText(movie.getTitleLong());
            itemView.setOnClickListener(this);
        }
    }


    public interface OnRecycleClickListner
    {
        void onMovieClick(Movie movie);
    }
}
