package com.mivashko.movieland.dao.impl;

//import com.mivashko.movieland.dao.DaoService;
import com.mivashko.movieland.dao.ReviewDao;
import com.mivashko.movieland.dao.impl.mapper.ReviewRowMapper;
import com.mivashko.movieland.entity.Movie;
import com.mivashko.movieland.entity.Review;
import com.mivashko.movieland.util.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class ReviewDaoImpl implements ReviewDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final String QUERY_GET_REVIEW_BY_MOVIE_ID =
        "SELECT t1.id, t1.movie_id, t1.user_id,   t1.text,   t2.first_name," +
        "t2.last_name  FROM review t1  join user t2  on t1.user_id = "+
        "t2.id WHERE t1.movie_id = :movie_id  ORDER BY RAND()   LIMIT 2";

    private final ReviewRowMapper reviewRowMapper = new ReviewRowMapper();

    @Override
    public List<Review> getByMovieId(int movieId) {
        log.info("Query for getting two random review using movie id");
        List<Review>  reviewList = null;
        try {
            MapSqlParameterSource parameter = new MapSqlParameterSource();
            parameter.addValue("movie_id", movieId);
            reviewList = jdbcTemplate.query (QUERY_GET_REVIEW_BY_MOVIE_ID, new Object[]{movieId}, reviewRowMapper );
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Finish for  query for getting review");
        return reviewList;
    }
}