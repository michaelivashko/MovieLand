package com.mivashko.movieland.util;

import com.mivashko.movieland.entity.Movie;
import com.mivashko.movieland.entity.Review;
import com.mivashko.movieland.util.json.AdditionSqlParam;
import com.mivashko.movieland.entity.User;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonConverterTest {
    private static JsonConverter jsonConverter;

    @BeforeClass
    public static void setUp() {
        jsonConverter = new JsonConverter();
    }


  @Test
  public void testParseSearchParams() {
      String json = "{\"genre\": \"ggg\", \"country\": \"w\", \"rusName\": \"Test\", \"engName\": \"Test\", \"year\": \"1666\"}";
      AdditionSqlParam params = jsonConverter.parse(json);
      assertEquals(params.getGenre(), "ggg");
      assertEquals(params.getCountry(), "w");
      assertEquals(params.getRusName(), "Test");
      assertEquals(params.getEngName(), "Test");
  }
      @Test
    public void toVerboseJsonTest(){
        String expectedJson = "{\"nameRus\":\"1\",\"nameEng\":\"1\",\"year\":1,\"rating\":1.3,\"genre\":\"[1, 1]\"}";
        Movie movie = new Movie();
        JsonConverter json = new JsonConverter();
        movie.setNameRus("1");
        movie.setNameEng("1");
        movie.setYear(1);
        movie.setRating(1.3);
        movie.setGenres(Arrays.asList("1", "1"));
        movie.setCountries(Arrays.asList("1"));

        Review review = new Review();
        review.setId(1);
        review.setMovie(movie);
        User user = new User();
        user.setId(1);
        user.setFirstName("kakoyto");
        user.setLastName("chuvak");
        review.setUser(user);
        review.setText("reviewText");
        List<Review> reviews = Arrays.asList(review, review);
        movie.setReviewList(reviews);

       // movie.setReviewList(Arrays.asList("1", "1"));
        assertEquals(expectedJson, json.toSimpleJson(movie).toString());
    }











}
