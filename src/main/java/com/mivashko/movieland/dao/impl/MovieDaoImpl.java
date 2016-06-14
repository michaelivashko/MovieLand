package com.mivashko.movieland.dao.impl;

import com.mivashko.movieland.dao.MovieDao;
import com.mivashko.movieland.dao.impl.mapper.MovieRowMapper;
import com.mivashko.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieDaoImpl implements MovieDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String queryGetAllMovie;

    private final MovieRowMapper movieRowMapper = new MovieRowMapper();

    public List<Movie> getAll() {
        List<Movie> movieList =  jdbcTemplate.query(queryGetAllMovie, movieRowMapper);
        return movieList;
    }
}
