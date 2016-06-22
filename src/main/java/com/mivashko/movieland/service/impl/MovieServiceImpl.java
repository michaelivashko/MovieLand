package com.mivashko.movieland.service.impl;

import com.mivashko.movieland.dao.MovieDao;
import com.mivashko.movieland.entity.Movie;
import com.mivashko.movieland.entity.Review;
import com.mivashko.movieland.entity.SqlParam;
import com.mivashko.movieland.service.MovieService;
import com.mivashko.movieland.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDao movieDao;

    @Autowired
    private ReviewService reviewService;

    public List<Movie> getAll(String ratingOrder, String priceOrder) throws Exception {
        return movieDao.getAll(ratingOrder, priceOrder);
    }

    public Movie getMovieById(int movieId) throws Exception {
        Movie movie = movieDao.getMovieById(movieId);
        List<Review> reviews = reviewService.getByMovieId(movieId);
        movie.setReviewList(reviews);
        return  movie;
    }

    @Override
    public List<Movie> search(SqlParam sqlParam) throws Exception {
        return movieDao.search(sqlParam);
    }

}
