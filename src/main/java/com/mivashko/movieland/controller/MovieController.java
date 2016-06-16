package com.mivashko.movieland.controller;

import com.mivashko.movieland.entity.Movie;
import com.mivashko.movieland.entity.Search;
import com.mivashko.movieland.service.MovieService;
import com.mivashko.movieland.util.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/movies")
public class MovieController {
  //  private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;

    @Autowired
    private JsonConverter jsonConverter;

    @RequestMapping(produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String getAllMovies() {
        List<Movie> movies = movieService.getAll();
        return jsonConverter.toJson(movies);
    }

    @RequestMapping(value = "/{movieId}", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getMoviesById(@PathVariable("movieId") int movieId) {
        Movie movie = movieService.getMovieById(movieId);
        return jsonConverter.toVerboseJson(movie);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> searchMovies(@RequestBody String json) {
        Search searchParams = jsonConverter.parse(json);
        List<Movie> movies = movieService.search(searchParams);
        if (movies.isEmpty()) {
            return new ResponseEntity<>("Movies not found", HttpStatus.BAD_REQUEST); //change
        }
        String jsonResponse = jsonConverter.toJson(movies);
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }



}
