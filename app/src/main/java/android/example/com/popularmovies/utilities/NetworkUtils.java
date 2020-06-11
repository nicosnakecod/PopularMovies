package android.example.com.popularmovies.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {
    private static final String api_key = "58805f67e972e203a3e4cba8d3e54e00";

    private static final String GRID_MOVIES_URL =
            "https://api.themoviedb.org/3/movie/";

    private static final String THUMBNAIL_URL =
            "https://image.tmdb.org/t/p/";

    private static final String YOUTUBE_URL =
            "https://www.youtube.com/watch/";


    private static final String sort_popularity = "popular";
    private static final String sort_top_rated = "top_rated";

    private static final String query_trailers = "videos";
    private static final String query_reviews = "reviews";

    private final static String API_KEY_PARAM = "api_key";

    private final static String YOUTUBE_ID_PARAM = "v";


    public static URL buildPopularityUrl(){
        return buildSortUrl(sort_popularity);
    }

    public static URL buildTopRatedUrl(){
        return buildSortUrl(sort_top_rated);
    }


    private static URL buildSortUrl(String sortType){

        Uri builtUri = Uri.parse(GRID_MOVIES_URL).buildUpon()
                .appendEncodedPath(sortType)
                .appendQueryParameter(API_KEY_PARAM, api_key)
                .build();

        return buildUrl(builtUri);
    }


    private static URL buildUrl(Uri builtUri){

        URL url = null;

        try{
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static Uri buildPosterUri(String thumbSize, String thumbPath){

        return Uri.parse(THUMBNAIL_URL).buildUpon()
                .appendEncodedPath(thumbSize)
                .appendEncodedPath(thumbPath)
                .build();
    }

    public static URL buildTrailersUrl(String filmId){
        return buildTrailersOrReviewsUrl(filmId, query_trailers);
    }

    public static URL buildReviewsUrl(String filmId){
        return buildTrailersOrReviewsUrl(filmId, query_reviews);
    }

    private static URL buildTrailersOrReviewsUrl(String filmId, String queryType){
        Uri builtUri = Uri.parse(GRID_MOVIES_URL).buildUpon()
                .appendEncodedPath(filmId)
                .appendEncodedPath(queryType)
                .build();
        return buildUrl(builtUri);
    }

    public static URL buildYoutubeTrailerUrl(String youtubeFilmId){
        Uri builtUri = Uri.parse(YOUTUBE_URL).buildUpon()
                .appendQueryParameter(YOUTUBE_ID_PARAM, youtubeFilmId)
                .build();
        return buildUrl(builtUri);
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
