package com.mivashko.movieland.dao.impl;

import com.mivashko.movieland.dao.MovieDao;
import com.mivashko.movieland.dao.impl.mapper.MovieRowMapper;
import com.mivashko.movieland.dao.impl.mapper.VerboseMovieRowMapper;
import com.mivashko.movieland.entity.Movie;
import com.mivashko.movieland.entity.Search;
import com.mivashko.movieland.service.ReviewService;
import com.mivashko.movieland.util.SearchQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class MovieDaoImpl implements MovieDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private String queryGetAllMovie;
    @Autowired
    private String queryGetMoviesById;
    @Autowired
    private String queryGetReviewByMovieId;
    @Autowired
    private String queryGetBySearchParam;

    @Autowired
    private ReviewService reviewService;

    private final MovieRowMapper movieRowMapper = new MovieRowMapper();
    private final VerboseMovieRowMapper verboseMovieRowMapper = new VerboseMovieRowMapper();
    private final SearchQueryBuilder searchQueryBuilder = new SearchQueryBuilder();

    public List<Movie> getAll() {
        log.info("Start query for getting  all movie from DB");
        return jdbcTemplate.query(queryGetAllMovie, movieRowMapper);
    }

    @Override
    public Movie getMovieById(int id) {
        long startTime = System.currentTimeMillis();
        log.info("Start query for getting  movie with id {} from DB");
        Movie movie =jdbcTemplate.queryForObject(queryGetMoviesById, new Object[]{id}, verboseMovieRowMapper);
        movie.setReviewList(reviewService.getByMovieId(id));
        log.info("Finish query to get review from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return movie;
    }

/*    @Override
    public List<Movie> search(Search search) {
        return jdbcTemplate.query(searchQueryBuilder.buildSearchQuery(search, queryGetAllMovie), movieRowMapper);
    }*/

    public List<Movie> search(Search search) {
        log.info("Start query search movies");
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("name_original",search.getEngName() );
        parameter.addValue("release_year",search.getYear() );
        List<Movie> movies = jdbcTemplate.query(queryGetBySearchParam, (PreparedStatementSetter) parameter, movieRowMapper);
        return movies;
    }

}


