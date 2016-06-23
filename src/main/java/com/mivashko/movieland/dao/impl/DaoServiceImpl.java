package com.mivashko.movieland.dao.impl;

import com.mivashko.movieland.dao.DaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@Repository
public class DaoServiceImpl implements DaoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DaoService.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public <T> T executeNamedQueryForSingleResult(String query, MapSqlParameterSource namedParameters, RowMapper<T> rowMapper) throws Exception {
        return jdbcTemplate.queryForObject(query, namedParameters, rowMapper);
    }

    @Override
    public <T> List<T> executeNamedQuery(String query, MapSqlParameterSource namedParameters, RowMapper<T> rowMapper) throws Exception {
        return jdbcTemplate.query(query, namedParameters, rowMapper);
    }

    @Override
    public <T> List<T> executeNamedQuery(String query, Map<String, Object> namedParameters, RowMapper<T> rowMapper) throws Exception {
        return jdbcTemplate.query(query, namedParameters, rowMapper);
    }

    @Override
    public SqlRowSet executeNamedQuery(String query, MapSqlParameterSource namedParameters) throws Exception {
        return jdbcTemplate.queryForRowSet(query, namedParameters);
    }

    @Override
    public ResultSet executeQuery(String query) throws Exception {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }

    @Override
    public <T> T executeNamedQueryForSingleResult(String query, Map<String, Object> namedParameters, RowMapper<T> rowMapper) throws Exception {
        return jdbcTemplate.queryForObject(query, namedParameters, rowMapper);
    }

    @Override
    public <T> T executeNamedQueryForSingleResult(String query, MapSqlParameterSource namedParameters, Class<T> t) throws Exception {
        return jdbcTemplate.queryForObject(query, namedParameters, t);
    }

    @Override
    public <T> T executeNamedQueryForSingleResult(String query, Map<String, Object> namedParameters, Class<T> t) throws Exception {
        return jdbcTemplate.queryForObject(query, namedParameters, t);
    }

    @Override
    public int executeUpdate(String query) throws Exception {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeUpdate();
    }

    @Override
    public void executeUpdates(List<String> queries) throws Exception {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        try {
            for (String query : queries) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.executeUpdate();

            }
            connection.commit();
        } catch (Exception ex) {
            LOGGER.error("Error occured {}", ex);
        }
    }

    @Override
    public int executeNamedUpdate(String query, MapSqlParameterSource namedParameters) {
        return jdbcTemplate.update(query, namedParameters);
    }

    @Override
    public int executeNamedUpdate(String query, Map<String, Object> namedParameters) {
        return jdbcTemplate.update(query, namedParameters);
    }

}
