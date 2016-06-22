package com.mivashko.movieland.dao;

import com.mivashko.movieland.entity.Movie;
import com.mivashko.movieland.entity.SqlParam;

import java.util.List;

public interface MovieDao {
    List<Movie> getAll(String ratingOrder, String priceOrder) throws Exception;
    Movie getMovieById(int movieId) throws Exception;
    List<Movie> search(SqlParam sqlParam) throws Exception;


}



