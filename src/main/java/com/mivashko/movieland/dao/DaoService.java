package com.mivashko.movieland.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface DaoService {
    SqlRowSet executeNamedQuery(String query, MapSqlParameterSource namedParameters) throws Exception;

    ResultSet executeQuery(String query) throws Exception;

    <T> T executeNamedQueryForSingleResult(String query, MapSqlParameterSource namedParameters, RowMapper<T> rowMapper) throws Exception;

    <T> T executeNamedQueryForSingleResult(String query, Map<String, Object> namedParameters, RowMapper<T> rowMapper) throws Exception;

    <T> T executeNamedQueryForSingleResult(String query, MapSqlParameterSource namedParameters, Class<T> t) throws Exception;

    <T> T executeNamedQueryForSingleResult(String query, Map<String, Object> namedParameters, Class<T> t) throws Exception;

    <T> List<T> executeNamedQuery(String query, MapSqlParameterSource namedParameters, RowMapper<T> rowMapper) throws Exception;

    <T> List<T> executeNamedQuery(String query, Map<String, Object> namedParameters, RowMapper<T> rowMapper) throws Exception;

    int executeUpdate(String query) throws Exception;

    void executeUpdates(List<String> queries) throws Exception;

    int executeNamedUpdate(String query, MapSqlParameterSource namedParameters);

    int executeNamedUpdate(String query, Map<String, Object> namedParameters);
}
