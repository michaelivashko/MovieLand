package com.mivashko.movieland.dao.impl.mapper;

import com.mivashko.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieRowMapper implements RowMapper<Movie>{

    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt("id"));
        movie.setName(resultSet.getString("name"));
        movie.setNameOriginal(resultSet.getString("name_original"));
        movie.setReleaseYear(resultSet.getInt("release_year"));
        movie.setDescription(resultSet.getString("description"));
        movie.setRating(resultSet.getDouble("rating"));
        movie.setPrice(resultSet.getDouble("price"));
        String genres = resultSet.getString("genres");
        List<String> genresList = new ArrayList<String>();
        String[] genresArray = genres.split(", ");
        for (String s : genresArray) {
            genresList.add(s);
        }
        movie.setGenres(genresList);
        String countries = resultSet.getString("countries");
        List<String> countriesList = new ArrayList<String>();
        String[] countriesArray = countries.split(", ");
        for (String s : countriesArray) {
            countriesList.add(s);
        }
        movie.setCountries(countriesList);
        return movie;
    }
}
