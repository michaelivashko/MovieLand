package com.mivashko.movieland.util;
import com.mivashko.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;


@Service
public class JsonConverter {
          public JsonObject toSimpleJson(Movie movie){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("nameRus", movie.getNameRus());
            jsonObject.addProperty("nameEng", movie.getNameEng());
              jsonObject.addProperty("year", movie.getYear());
            jsonObject.addProperty("rating", movie.getRating());
            jsonObject.addProperty("genre", String.valueOf(movie.getGenres()));
            return jsonObject;
        }

    public String toVerboseJson(Movie movie) {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("nameRus", movie.getNameRus());
        jsonObject.addProperty("nameEng", movie.getNameEng());
        jsonObject.addProperty("year", movie.getYear());
        jsonObject.addProperty("country",  String.valueOf(movie.getCountries()));
        jsonObject.addProperty("genre", String.valueOf(movie.getGenres()));
        jsonObject.addProperty("description", movie.getDescription());
        jsonObject.addProperty("reviews", String.valueOf(movie.getReviewList()));
        return gson.toJson(jsonObject);
    }

    public String toJson(List<Movie> movies) {
        Gson gson = new Gson();
        List<JsonObject> list = new ArrayList<>();
        for (Movie movie : movies) {
            list.add(toSimpleJson(movie));
        }
        return gson.toJson(list);
    }

     /*
        JSONObject json = new JSONObject();

        json.("genre", String.valueOf(movie.getGenres()));
        json.put("rating", movie.getRating());
        json.put("year", movie.getYear());
        json.put("engName", movie.getNameEng());
        json.put("rusName", movie.getNameRus());
        return json.toJSONString();
    }
    public String toVerboseJson(Movie movie){
        JSONObject json = new JSONObject();
        json.put("rusName", movie.getNameRus());
        json.put("engName", movie.getNameEng());
        json.put("year", movie.getYear());
        json.put("country", movie.getCountries());
        json.put("genre", String.valueOf(movie.getGenres()));
        json.put("description", movie.getDescription());
        json.put("rating", movie.getRating());
        json.put("reviewList", String.valueOf(movie.getReviewList()));
        return json.toJSONString();
    }
    public String toSimpleJson(List<Movie> movies) {
        StringBuilder gson = new StringBuilder();
        for (Movie movie : movies) {
            gson.append(toSimpleJson(movie));
        } return String.valueOf(gson); }

    // --to delete this
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final String COMMA_SEPARATOR = ",";
    private static final String COLON_SEPARATOR = ":";
    private static final String CURLY_BRACKET_OPEN = "{";
    private static final String CURLY_BRACKET_CLOSE = "}";
    public String JsonManualConverter (List<Movie> movies) {
        StringBuilder stringBuilder = new StringBuilder(addQuotes("movies"));
        stringBuilder.append(":[\n");
        String[] fieldNames = {"name_rus", "name_eng", "year", "description", "rating", "price", "genres", "countries"};
        for (Movie movie : movies) {
            Object[] fields = {movie.getNameRus(), movie.getNameEng(), movie.getYear(), movie.getDescription(),
                    movie.getRating(), movie.getPrice(), movie.getGenres(), movie.getCountries()};
            stringBuilder.append(CURLY_BRACKET_OPEN);
            for (int i = 0; i < fieldNames.length; i++) {
                stringBuilder.append(addQuotes(fieldNames[i]));
                stringBuilder.append(COLON_SEPARATOR);
                stringBuilder.append(addQuotes(fields[i]));
                if (i != fieldNames.length - 1) {stringBuilder.append(COMMA_SEPARATOR); }
            }
            stringBuilder.append(CURLY_BRACKET_CLOSE);
            if (!movie.equals(movies.get(movies.size() - 1))) {
                stringBuilder.append(COMMA_SEPARATOR);
            }  stringBuilder.append("\n");
        }  stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private String addQuotes(Object value) {
        return "\"" + value + "\"";
    }
*/
}
