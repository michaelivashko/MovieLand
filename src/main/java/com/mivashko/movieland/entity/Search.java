package com.mivashko.movieland.entity;

public class Search {
    private String rusName;
    private String engName;
    private String year;
    private String country;
    private String genre;

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

    public boolean isEmpty() {
        return (rusName == null) && (engName == null) && (year == null) && (country == null) && (genre == null);
    }
}
