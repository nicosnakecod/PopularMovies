package android.example.com.popularmovies;

import android.content.Context;
import android.example.com.popularmovies.data.Movie;
import android.example.com.popularmovies.utilities.ImageUtils;
import android.example.com.popularmovies.utilities.NetworkUtils;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private List<Movie> mMoviesData = null;
    private static final String POSTER_SIZE = "w185";

    private final MoviesAdapterOnClickHandler mClickHandler;

    public interface MoviesAdapterOnClickHandler{
        void onClick(Movie movieData);
    }

    public MoviesAdapter(MoviesAdapterOnClickHandler clickHandler){
        this.mClickHandler = clickHandler;
    }


    public ArrayList<Movie> getMoviesData(){
        if(mMoviesData == null){
            return null;
        }
        return new ArrayList<>(mMoviesData);
    }

    public void setMoviesData(List<Movie> moviesData){
        if(moviesData != null){
            mMoviesData = new ArrayList<Movie>(moviesData);
            notifyDataSetChanged();
        }

    }


    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForGridItem =  R.layout.grid_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForGridItem, parent,
                shouldAttachToParentImmediately);
        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder holder, int position) {
        Movie singleMovieData = mMoviesData.get(position);
        ImageUtils.loadPosterImage(holder.mMoviesImageView.getContext(), singleMovieData.getMoviePoster(),
                holder.mMoviesImageView);
    }

    @Override
    public int getItemCount() {
        if(mMoviesData == null){
            return 0;
        }
        return mMoviesData.size();
    }



    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView mMoviesImageView;
        private final double heightToWidthRatio = 1.5;


        public MoviesAdapterViewHolder(View itemView) {
            super(itemView);
            Context context = itemView.getContext();
            mMoviesImageView = (ImageView) itemView.findViewById(R.id.iv_movies_thumbnail);

            int imageViewWidth = ImageUtils.calculateColumnWidth(context, ImageUtils.calculateNrOfColumns(context));
            int imageViewHeight = (int) (imageViewWidth * 1.5);
            mMoviesImageView.getLayoutParams().width = imageViewWidth;
            mMoviesImageView.getLayoutParams().height = imageViewHeight;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Movie movieData = mMoviesData.get(adapterPosition);
            mClickHandler.onClick(movieData);
        }
    }
}
