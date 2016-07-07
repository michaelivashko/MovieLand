package com.mivashko.movieland.service.impl;

import com.mivashko.movieland.dao.MovieDao;
import com.mivashko.movieland.entity.Movie;
import com.mivashko.movieland.entity.Review;
import com.mivashko.movieland.util.json.AdditionSqlParam;
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

    public List<Movie> getAll(AdditionSqlParam sqlParam) throws Exception {
        return movieDao.getAll(sqlParam);
    }

    public Movie getMovieById(int movieId) throws Exception {
        Movie movie = movieDao.getMovieById(movieId);
        List<Review> reviews = reviewService.getByMovieId(movieId);
        movie.setReviewList(reviews);
        return  movie;
    }

    @Override
    public List<Movie> search(AdditionSqlParam additionSqlParam) throws Exception {
        return movieDao.search(additionSqlParam);
    }

    @Override
    public byte[] getPoster(int movieId) {
        return movieDao.getPoster(movieId);
    }

}
