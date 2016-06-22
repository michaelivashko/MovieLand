package com.mivashko.movieland.service.impl;


import com.mivashko.movieland.dao.ReviewDao;
import com.mivashko.movieland.entity.Review;
import com.mivashko.movieland.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public List<Review> getByMovieId(int movieId) {
        log.info("Retrieving review from DB by mowie id {} ", movieId);
        return reviewDao.getByMovieId(movieId);
    }
}
