package com.mivashko.movieland.service;

import com.mivashko.movieland.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAll();
    Movie getMovieById(int movieId);
}

