package com.mivashko.movieland.controller;

import com.mivashko.movieland.entity.Movie;
import com.mivashko.movieland.util.json.AdditionSqlParam;
import com.mivashko.movieland.exceptions.NoDataFound;
import com.mivashko.movieland.service.MovieService;
import com.mivashko.movieland.util.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class MovieController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;

    @Autowired
    private JsonConverter jsonConverter;

    @RequestMapping(value = "/movies", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> getAllMovies(
            @RequestParam(value = "rating", required = false) String ratingOrder,
            @RequestParam(value = "price", required = false) String priceOrder,
            @RequestParam(value = "page", defaultValue = "1" ) String page) throws Exception {
        log.info("Start request for all movies");
        List<Movie> movies = null;

        AdditionSqlParam sqlParams = new AdditionSqlParam();
        sqlParams.setPriceOrder(priceOrder);
        sqlParams.setRatingOrder(ratingOrder);
        sqlParams.setPage(page);

        try { movies = movieService.getAll(sqlParams);
        } catch (NoDataFound e) {
            e.printStackTrace();
        }
        String json = jsonConverter.toJson(movies);
        return movies.isEmpty()? new ResponseEntity<>("Movies missing in the database",
                HttpStatus.NO_CONTENT) :  new ResponseEntity<>(json, HttpStatus.OK );
    }
    @RequestMapping(value = "/movies/{movieId}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getMoviesById(@PathVariable("movieId") int movieId) throws Exception {
        log.info("Received request for retriewing movie with id {} ", movieId);

        Movie movie = movieService.getMovieById(movieId);
        if (movie == null) {
            log.warn("Movie not found by id={}", movieId);
            return null;
        }
        log.debug("Received movie, id={}", movieId);
        return jsonConverter.toVerboseJson(movie);
    }

    @RequestMapping(value = "/movies/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> searchMovies(@RequestBody String json) throws Exception {
        AdditionSqlParam searchParams = jsonConverter.parse(json);
        List<Movie> movies = movieService.search(searchParams);
        if (movies.isEmpty()) {
            return new ResponseEntity<>("Movies not found", HttpStatus.NO_CONTENT);         }
        String jsonResponse = jsonConverter.toJson(movies);
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/movies/poster/{movieId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getPoster(@PathVariable int movieId) {
        byte[] poster = movieService.getPoster(movieId);
        if (poster == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(poster, HttpStatus.OK);
    }
}
