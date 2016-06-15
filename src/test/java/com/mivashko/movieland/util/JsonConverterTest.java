package com.mivashko.movieland.util;

import com.mivashko.movieland.entity.Movie;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonConverterTest {

    @Test
    public void toVerboseJsonTest(){
        String expectedJson = "{\"nameRus\":\"Film\",\"nameEng\":\"Film\",\"year\":2016,\"genre\":\"[Genre1, Genre2]\"}";
        Movie movie = new Movie();
        JsonConverter json = new JsonConverter();
        movie.setNameRus("Film");
        movie.setNameEng("Film");
        movie.setYear(2016);
      //  movie.setRating(1.3);
        movie.setGenres(Arrays.asList("Genre1", "Genre2"));
       // movie.setDescription("description");
        movie.setCountries(Arrays.asList("Countries"));
        movie.setReviewList(Arrays.asList("r1", "r1"));
        assertEquals(expectedJson, json.toSimpleJson(movie));
    }
}
