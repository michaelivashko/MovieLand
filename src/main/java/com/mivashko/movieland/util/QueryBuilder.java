package com.mivashko.movieland.util;
import com.mivashko.movieland.entity.SqlParam;

public class QueryBuilder {

    public static String buildQueryWithSorting(String queryGetAllMovie, SqlParam params) {
        String result = " ORDER BY ";

        if (params.getRatingOrder() != null  &&  params.getPriceOrder() != null  ) {
            result = result + " rating " + params.getRatingOrder() + " , "
            + " price " + params.getRatingOrder();
        }
        if (params.getRatingOrder() != null) {
            result = result + " rating " + params.getRatingOrder(); }
        if (params.getPriceOrder() != null) {
            result = result + " price " + params.getRatingOrder();  }
        else {
            return null; }

        return queryGetAllMovie + result;
    }
}
