package com.mivashko.movieland.controller;

import com.mivashko.movieland.entity.Movie;
import com.mivashko.movieland.service.MovieService;
import com.mivashko.movieland.util.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/v1/movies")
public class MovieController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;

    @Autowired
    private JsonConverter jsonConverter;

    @RequestMapping(produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String getAllMovies() {
        List<Movie> movies = movieService.getAll();
        return jsonConverter.JsonManualConverter(movies);
    }

    @RequestMapping(value = "/{movieId}", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getMoviesById(@PathVariable("movieId") int movieId) {
        Movie movie = movieService.getMovieById(movieId);
        return jsonConverter.toVerboseJson(movie);
    }
}
