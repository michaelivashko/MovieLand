package com.mivashko.movieland.dao;


import com.mivashko.movieland.entity.Review;

import java.util.List;

public interface ReviewDao {
    List<Review> getByMovieId(int movieId);
}
