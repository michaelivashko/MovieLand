package com.mivashko.movieland.dao.impl.mapper;

import com.mivashko.movieland.entity.Movie;
import com.mivashko.movieland.entity.Review;
import com.mivashko.movieland.entity.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        Review review = new Review();
        review.setId(resultSet.getInt("id"));
        Movie movie = new Movie();
        movie.setId(resultSet.getInt("movie_id"));
        review.setMovie(movie);
        User user = new User();
        user.setId(resultSet.getInt("user_id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        review.setUser(user);
        review.setText(resultSet.getString("text"));
        return review;
    }
}