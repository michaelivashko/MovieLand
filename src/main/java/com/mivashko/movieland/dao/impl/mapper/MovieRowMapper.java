package com.mivashko.movieland.dao.impl.mapper;

import com.mivashko.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class MovieRowMapper implements RowMapper<Movie>{
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        log.info("Start mapper to parse movie fields");
        Movie movie = new Movie();
        movie.setNameRus(resultSet.getString("name_rus"));
        movie.setNameEng(resultSet.getString("name_eng"));
        movie.setYear(resultSet.getInt("year"));
        movie.setRating(resultSet.getDouble("rating"));
        String genres = resultSet.getString("genres");
        movie.setGenres(Arrays.asList(genres.split(", ")));
        log.info("Result for Movie parsing: {}", movie.toString());
        return movie;
    }
}
