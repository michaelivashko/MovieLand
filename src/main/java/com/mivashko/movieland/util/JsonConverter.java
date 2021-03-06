package com.mivashko.movieland.util;
import com.google.gson.JsonSyntaxException;
import com.mivashko.movieland.entity.Movie;
import com.mivashko.movieland.util.json.AdditionSqlParam;
import com.mivashko.movieland.exceptions.IncorrectJsonParsing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonConverter {
    private final Logger log = LoggerFactory.getLogger(getClass());

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
        } return gson.toJson(list);
    }

    public AdditionSqlParam parse (String json) {
        Gson gson = new Gson();
        try {   return gson.fromJson(json, AdditionSqlParam.class);
        } catch (JsonSyntaxException e) {
            log.info("Error while parsing json");
            throw new IncorrectJsonParsing("Invalid json request", e);
        }
    }

}
