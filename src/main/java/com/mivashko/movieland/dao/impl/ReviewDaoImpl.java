package com.mivashko.movieland.dao.impl;

import com.mivashko.movieland.dao.ReviewDao;
import com.mivashko.movieland.dao.impl.mapper.ReviewRowMapper;
import com.mivashko.movieland.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getReviewsByMovieIdSQL;

    private final ReviewRowMapper reviewRowMapper = new ReviewRowMapper();

    @Override
    public List<Review> getByMovieId(int movieId) {
        return jdbcTemplate.query(getReviewsByMovieIdSQL, new Object[]{movieId}, reviewRowMapper);
    }
}