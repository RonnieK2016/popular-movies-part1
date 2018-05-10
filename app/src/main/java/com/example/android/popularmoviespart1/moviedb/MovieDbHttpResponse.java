package com.example.android.popularmoviespart1.moviedb;

import com.example.android.popularmoviespart1.Constants;
import com.example.android.popularmoviespart1.JsonConstants;
import com.example.android.popularmoviespart1.domain.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by angelov on 5/6/2018.
 */

public class MovieDbHttpResponse {
    @Getter
    @Setter
    private Integer page;
    @Getter
    @Setter
    @SerializedName(JsonConstants.MOVIE_SERVICE_JSON_TOTAL_PAGES)
    private Integer totalPages;
    @Getter
    @Setter
    @SerializedName(JsonConstants.MOVIE_SERVICE_JSON_TOTAL_RESULTS)
    private Integer totalResults;
    @Getter
    @Setter
    private List<Movie> results;
}
