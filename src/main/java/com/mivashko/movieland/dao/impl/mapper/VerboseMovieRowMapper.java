package com.mivashko.movieland.dao.impl.mapper;
import com.mivashko.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class VerboseMovieRowMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setNameRus(resultSet.getString("name_rus"));
        movie.setNameEng(resultSet.getString("name_eng"));
        movie.setYear(resultSet.getInt("year"));
        movie.setRating(resultSet.getDouble("rating"));
        movie.setDescription(resultSet.getString("description"));
        String genres = resultSet.getString("genres");
        movie.setGenres(Arrays.asList(genres.split(", ")));
        String countries = resultSet.getString("countries");
        movie.setCountries(Arrays.asList(countries.split(",")));
        return movie;
    }
}
