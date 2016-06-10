package com.mivashko.movieland.dao;

import com.mivashko.movieland.entity.Movie;
import java.util.List;

public interface MovieDao {
    List<Movie> getAll();
}
