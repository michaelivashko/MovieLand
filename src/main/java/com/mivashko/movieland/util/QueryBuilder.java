package com.mivashko.movieland.util;
import com.mivashko.movieland.util.json.AdditionSqlParam;

public class QueryBuilder {

    public static String buildQueryWithSorting(String queryGetAllMovie, AdditionSqlParam params) {
        String result = " ORDER BY ";
        if (params.getRatingOrder() != null  &&  params.getPriceOrder() != null  ) {
            result = result + " rating " + params.getRatingOrder() + " , "
            + " price " + params.getRatingOrder();
        }  else {
            return queryGetAllMovie;
        }  return queryGetAllMovie + result;
    }

    public  String buildSearchQuery(AdditionSqlParam searchParams, String queryGetAllMovie) {
        StringBuilder builder = new StringBuilder(queryGetAllMovie);
        if (!searchParams.isEmpty()) {
            builder.append("WHERE ");
            builder.append(addQueryCondition("name_eng", searchParams.getEngName()));
          // builder.append(addQueryCondition("genres", searchParams.getGenre()));
          // builder.append(addQueryCondition("countries", searchParams.getCountry()));
          // builder.append(addQueryCondition("name_rus", searchParams.getRusName()));
            builder.append(addQueryCondition("year", searchParams.getYear()));
            builder.delete(builder.length() - 5, builder.length());
        }    return builder.toString();
    }
    private String addQueryCondition(String field, String value) {
        return (value == null) ? "": field +" LIKE '%" +value +"%' AND ";
    }
}
