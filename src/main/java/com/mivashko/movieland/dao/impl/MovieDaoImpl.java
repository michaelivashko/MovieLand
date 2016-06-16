package com.mivashko.movieland.dao.impl;

import com.mivashko.movieland.dao.MovieDao;
import com.mivashko.movieland.dao.impl.mapper.MovieRowMapper;
import com.mivashko.movieland.dao.impl.mapper.VerboseMovieRowMapper;
import com.mivashko.movieland.entity.Movie;
import com.mivashko.movieland.entity.Search;
import com.mivashko.movieland.util.SearchQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieDaoImpl implements MovieDao {
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private String queryGetAllMovie;
    @Autowired
    private String queryGetMoviesById;
    @Autowired
    private String queryGetReviewById;

    private final MovieRowMapper movieRowMapper = new MovieRowMapper();
    private final VerboseMovieRowMapper verboseMovieRowMapper = new VerboseMovieRowMapper();
    private final SearchQueryBuilder searchQueryBuilder = new SearchQueryBuilder();

    public List<Movie> getAll() {
        return jdbcTemplate.query(queryGetAllMovie, movieRowMapper);
    }

    @Override
    public Movie getMovieById(int id) {
         return jdbcTemplate.queryForObject(queryGetMoviesById, new Object[]{id}, verboseMovieRowMapper);
    }

/*    @Override
    public List<String> getReviewById(int id) {
        return jdbcTemplate.queryForList(queryGetReviewById, new Object[]{id}, String.class);
    }*/

    @Override
    public List<Movie> search(Search search) {
        return jdbcTemplate.query(searchQueryBuilder.buildSearchQuery(search, queryGetAllMovie), movieRowMapper);
    }
}


