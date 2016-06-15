package com.mivashko.movieland.dao.impl.mapper;

import com.mivashko.movieland.entity.Movie;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieRowMapperTest {

    @Test
    public void testMapRowWithProperMovie() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt(anyString())).thenReturn(2016);
        when(resultSet.getString(anyString())).thenReturn("Кино").thenReturn("Film").thenReturn("Genre1,Genre2");
        when(resultSet.getDouble(anyString())).thenReturn(2.0);

        MovieRowMapper mapper = new MovieRowMapper();
        Movie movie = mapper.mapRow(resultSet, 0);
        assertEquals(movie.getYear(), 2016);
        assertEquals(movie.getNameRus(), "Кино");
        assertEquals(movie.getNameEng(), "Film");
        assertEquals(movie.getRating(), 2.0, 0.001);
        assertEquals(movie.getGenres(), Arrays.asList("Genre1,Genre2"));
    }
}
