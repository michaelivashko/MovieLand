package com.mivashko.movieland.dao.impl;

import com.mivashko.movieland.dao.DaoService;
import com.mivashko.movieland.dao.MovieDao;
import com.mivashko.movieland.dao.impl.mapper.MovieRowMapper;
import com.mivashko.movieland.dao.impl.mapper.VerboseMovieRowMapper;
import com.mivashko.movieland.entity.Movie;
import com.mivashko.movieland.entity.SqlParam;
import com.mivashko.movieland.service.ReviewService;
import com.mivashko.movieland.util.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieDaoImpl implements MovieDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DaoService daoService;

    @Autowired
    private String queryGetBySearchParam;

    private final String QUERY_GET_ALL_MOVIE = "SELECT t1.id, t1.name name_rus, t1.name_original name_eng, t1.release_year year, t1.rating," +
            " GROUP_CONCAT(DISTINCT t3.name ORDER BY t3.id DESC SEPARATOR ',') genres FROM movie t1 LEFT JOIN movie_genre t2 " +
            " ON t1.id = t2.movie_id LEFT JOIN genre t3 ON t2.genre_id = t3.id GROUP BY t1.id, t1.name, t1.name_original, t1.release_year, t1.rating";

    private final String QUERY_GET_MOVIES_BY_ID =  "select  m.name name_rus,	m.name_original name_eng,m.description," +
            "m.release_year year, rating,group_concat(g.name separator ', ') as genres,	group_concat(distinct co.name separator ', ') as countries"+
            "from movie m     join movie_genre gm     on m.id = gm.movie_id    join genre g  on gm.genre_id = g.id    join movie_country cm     on m.id = cm.movie_id    join country co"+
            "on co.id = cm.country_id    where m.id = :id    group by 1,2,3,4,5";

    @Autowired
    private ReviewService reviewService;

    private final MovieRowMapper movieRowMapper = new MovieRowMapper();
    private final VerboseMovieRowMapper verboseMovieRowMapper = new VerboseMovieRowMapper();

    @Override
    public List<Movie> getAll(String ratingOrder, String priceOrder) throws Exception {
        log.info("Start query for getting  all movie from DB with sorting");
        SqlParam sqlParam = new SqlParam();
        sqlParam.setRatingOrder(ratingOrder);
        sqlParam.setPriceOrder(priceOrder);
        List<Movie> movies = daoService.executeNamedQuery(QueryBuilder.buildQueryWithSorting(
                QUERY_GET_ALL_MOVIE, sqlParam), new HashMap<>(), movieRowMapper);
        log.info("Finish query for getting  all movie from DB");
        return movies;
    }

    @Override
    public Movie getMovieById(int id) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("Start query for getting  movie with id {} from DB");
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("id", id);
        Movie movie = daoService.executeNamedQueryForSingleResult(QUERY_GET_MOVIES_BY_ID, parameter, verboseMovieRowMapper);
        movie.setReviewList(reviewService.getByMovieId(id));
        log.info("Finish query to get review from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return movie;
    }

    @Override
    public List<Movie> search(SqlParam sqlParam) throws Exception {
        log.info("Start query for search movies");
        MapSqlParameterSource parameter = new MapSqlParameterSource();
       // if (sqlParam.getEngName()!=null) {
        parameter.addValue("name_original", sqlParam.getEngName());
       // if (sqlParam.getYear()!=null) {
        parameter.addValue("release_year", sqlParam.getYear());
        List<Movie> movieList = daoService.executeNamedQuery(queryGetBySearchParam, parameter, movieRowMapper);
        log.info("Finish query for search movie");
        return movieList;
    }
}

