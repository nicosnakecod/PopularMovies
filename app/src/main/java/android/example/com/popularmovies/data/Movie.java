package android.example.com.popularmovies.data;

import android.widget.ImageView;

import java.util.Date;

public class Movie {

    private int movieId;
    private String originalTitle;
    private ImageView moviePoster;
    private String plotSynopsis;
    private Double userAverageRating;
    private Date releaseDate;


    public Movie(){

    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public ImageView getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(ImageView moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        this.plotSynopsis = plotSynopsis;
    }

    public Double getUserAverageRating() {
        return userAverageRating;
    }

    public void setUserAverageRating(Double userAverageRating) {
        this.userAverageRating = userAverageRating;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
