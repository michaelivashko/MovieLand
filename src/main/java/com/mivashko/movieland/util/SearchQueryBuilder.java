package com.mivashko.movieland.util;

import com.mivashko.movieland.entity.Search;

public class SearchQueryBuilder {
    public String buildSearchQuery(Search searchParams, String queryGetAllMovies) {
        StringBuilder builder = new StringBuilder(queryGetAllMovies);
        if (!searchParams.isEmpty()) {
            builder.append("WHERE ");
         //   builder.append(addQueryCondition("genres", searchParams.getGenre()));
         //   builder.append(addQueryCondition("countries", searchParams.getCountry()));
           // builder.append(addQueryCondition("name", searchParams.getRusName()));
            builder.append(addQueryCondition("name_original", searchParams.getEngName()));
          //  builder.append(addQueryCondition("release_year", searchParams.getYear()));
            builder.delete(builder.length() - 5, builder.length());
        }
        return builder.toString();
    }

    private String addQueryCondition(String field, String value) {
        if (value == null) {
            return "";
        }
        return field + " LIKE '%" + value + "%' AND ";
    }


}
