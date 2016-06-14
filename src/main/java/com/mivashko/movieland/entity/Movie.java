package com.mivashko.movieland.entity;

import java.util.List;

public class Movie {

    private int id;
    private String nameRus;
    private String nameEng;
    private int year;
    private String description;
    private double rating;
    private double price;
    private List<String> genres;
    private List<String> countries;
    private List<String> reviewList;

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return nameRus;
    }

    public void setNameRus(String nameRus) {
        this.nameRus = nameRus;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + nameRus + '\'' +
                ", nameOriginal='" + nameEng + '\'' +
                ", releaseYear=" + year +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", genres='" + genres + '\'' +
                ", countries='" + countries + '\'' +
                '}';
    }


    public void setReviewList(List<String> reviewList) {
        this.reviewList = reviewList;
    }

}
