package com.example.android.popularmoviespart1.dao;

import com.example.android.popularmoviespart1.domain.Movie;
import com.example.android.popularmoviespart1.listeners.HttpResponseListener;

import java.util.List;

/**
 * Created by angelov on 5/1/2018.
 */

public interface MoviesAccessService {

    void getPopularMovies(HttpResponseListener listener, int currentPage);

    void getTopRatedMovies(HttpResponseListener listener, int currentPage);

}
