package com.hashedin.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetails {
    private String title;
    private String originalTitle;
    private String year;
    private String datePublished;
    private String genre;
    private String duration;
    private String country;
    private String language;
    private String director;
    private String writer;
    private String productionCompany;
    private String actors;
    private String description;
    private String avgVote;
    private String votes;
    private String budget;
    private String usaGrossIncome;
    private String worldWideGrossIncome;
    private String metaScore;
    private String reviewsFromUsers;
    private String reviewsFromCritics;
    private String titleId;
}
