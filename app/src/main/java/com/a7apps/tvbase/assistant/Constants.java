package com.a7apps.tvbase.assistant;

public class Constants {
    private static final String API_KEY = "8c192694ea899ac35ead1ae82c4d2cda";
    private static final String DISCOVER_MOVIE = "discover/movie";
    private static final String DISCOVER_TV = "discover/tv";
    private static final String SORT_BY = "&sort_by=popularity.desc";
    private static final String MOVIE_POPULAR = "movie/popular";
    private static final String TV_POPULAR = "tv/popular";
    private static final String BASE_MOVIE_URL = "https://api.themoviedb.org/3/movie/popular?api_key="+API_KEY+"&language=en-US&page=";
    private static final String BASE_TV_URL = "https://api.themoviedb.org/3/"+DISCOVER_TV+"?api_key="+API_KEY+"&language=en-US"+SORT_BY+"&page=";
    private static final String BASE_SEARCH_URL = "https://api.themoviedb.org/3/search/multi?api_key=8c192694ea899ac35ead1ae82c4d2cda&page=1&query=";
    private static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";

    public static final String TYPE_MOVIES = "movies";
    public static final String TYPE_SERIES = "series";
    public static final String TYPE_GENERAL = "general";

    public static String getBaseMovieUrl() {
        return BASE_MOVIE_URL;
    }

    public static String getBaseTvUrl() {
        return BASE_TV_URL;
    }

    public static String getIMAGE_URL() {
        return IMAGE_URL;
    }

    public static String getBaseSearchUrl(){
        return BASE_SEARCH_URL;
    }

    public static String movieById(String id){
        String url = "https://api.themoviedb.org/3/movie/"+id+"?api_key="+API_KEY;
        return url;
    }
    public static String serieById(String id){
        String url = "https://api.themoviedb.org/3/tv/"+id+"?api_key="+API_KEY;
        return url;
    }
}
