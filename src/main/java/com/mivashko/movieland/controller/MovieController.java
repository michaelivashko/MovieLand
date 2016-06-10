package com.mivashko.movieland.controller;

import com.mivashko.movieland.entity.Movie;
import com.mivashko.movieland.service.MovieService;
import com.mivashko.movieland.util.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        log.info("Sending request to get list of all movies");
        long startTime = System.currentTimeMillis();
        List<Movie> movies = movieService.getAll();
        String json = jsonConverter.toJson(movies);
        log.info("List of movies received. It took {} ms", System.currentTimeMillis() - startTime);
        return json;
    }
}
