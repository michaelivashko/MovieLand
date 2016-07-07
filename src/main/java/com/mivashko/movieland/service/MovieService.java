package com.mivashko.movieland.service;

import com.mivashko.movieland.entity.Movie;
import com.mivashko.movieland.util.json.AdditionSqlParam;

import java.util.List;

public interface MovieService {

  //  List<Movie> getAll(String ratingOrder, String priceOrder) throws Exception;
    List<Movie> getAll(AdditionSqlParam sqlParams) throws Exception;
    Movie getMovieById(int movieId) throws Exception;
    List<Movie> search(AdditionSqlParam additionSqlParam) throws Exception;

    byte[] getPoster(int movieId);

}

