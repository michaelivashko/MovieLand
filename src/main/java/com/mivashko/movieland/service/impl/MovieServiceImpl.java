package com.mivashko.movieland.service.impl;

import com.mivashko.movieland.dao.MovieDao;
import com.mivashko.movieland.entity.Movie;
import com.mivashko.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDao movieDao;

    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}