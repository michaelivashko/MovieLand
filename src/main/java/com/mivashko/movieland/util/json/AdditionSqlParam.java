package com.mivashko.movieland.util.json;

public class AdditionSqlParam {
    private String rusName;
    private String engName;
    private String year;
    private String country;
    private String genre;
    private String ratingOrder;
    private String priceOrder;

    public String getRusName() {
        return rusName;
    }

    public void setRusName(String rusName) {
        this.rusName = rusName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRatingOrder(String ratingOrder) {
        this.ratingOrder = ratingOrder;
    }
    public void setPriceOrder(String priceOrder) {
        this.priceOrder = priceOrder;
    }

    public boolean isEmpty() {
        return (rusName == null) && (engName == null) && (year == null) && (country == null) && (genre == null);
    }

    public String getRatingOrder() {
        return ratingOrder;
    }

    public String getPriceOrder() {
        return priceOrder;
    }
}
