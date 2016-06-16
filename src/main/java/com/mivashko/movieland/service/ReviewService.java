package com.mivashko.movieland.service;

import com.mivashko.movieland.entity.Review;
import java.util.List;

public interface ReviewService {
    List<Review> getByMovieId(int movieId);
}
