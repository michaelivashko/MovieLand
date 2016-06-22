package com.mivashko.movieland.dao.impl;

import com.mivashko.movieland.dao.ReviewDao;
import com.mivashko.movieland.dao.impl.mapper.ReviewRowMapper;
import com.mivashko.movieland.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewDaoImpl implements ReviewDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String queryGetReviewByMovieId;

    private final ReviewRowMapper reviewRowMapper = new ReviewRowMapper();

    @Override
    public List<Review> getByMovieId(int movieId) {
        log.info("Query for review using movie id");
         return jdbcTemplate.query(queryGetReviewByMovieId, new Object[]{movieId}, reviewRowMapper);
    }
}