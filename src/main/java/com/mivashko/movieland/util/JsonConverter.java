package com.mivashko.movieland.util;
import com.mivashko.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JsonConverter {
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
            Object[] fields = {movie.getName(), movie.getNameEng(), movie.getYear(), movie.getDescription(),
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

    public String verboseJson (Movie movie){
    return " ";
    }


    private String addQuotes(Object value) {
        return "\"" + value + "\"";
    }
}
