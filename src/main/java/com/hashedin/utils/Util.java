package com.hashedin.utils;

import com.hashedin.beans.MovieDetails;
import com.hashedin.beans.MoviePlottingDetails;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.BeanUtils;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

    public Map<String, MoviePlottingDetails> readAllDataAtOnce(String file) {
        Map<String, MoviePlottingDetails> detailsMap = new HashMap<>();
        List<MovieDetails> movieDetailsList = new ArrayList<>();
        try {

            FileReader filereader = new FileReader(file);
            CSVParser parser = new CSVParserBuilder().withSeparator('\t').build();
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .withCSVParser(parser)
                    .build();
            List<String[]> allData = csvReader.readAll();

            // print Data
            for (String[] row : allData) {
                MovieDetails movieDetails = new MovieDetails(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10], row[11], row[12], row[13], row[14], row[15], row[16], row[17], row[18], row[19], row[20], row[21]);
                movieDetailsList.add(movieDetails);
                //title	original_title	year	date_published	genre	duration	country	language	director	writer	production_company	actors	description	avg_vote	votes	budget	usa_gross_income	worlwide_gross_income	metascore	reviews_from_users	reviews_from_critics	imdb_title_id

            }
            for (MovieDetails movieDetails : movieDetailsList) {
                if (null != detailsMap.get(movieDetails.getYear())) {
                    MoviePlottingDetails moviePlottingDetails = detailsMap.get(movieDetails.getYear());
                    moviePlottingDetails.setTitle(stringConcat(moviePlottingDetails.getTitle(), movieDetails.getTitle()));
                    moviePlottingDetails.setBudget(stringConcat(moviePlottingDetails.getBudget(), movieDetails.getBudget()));
                    moviePlottingDetails.setCountry(stringConcat(moviePlottingDetails.getCountry(), movieDetails.getCountry()));
                    moviePlottingDetails.setDirector(stringConcat(moviePlottingDetails.getDirector(), movieDetails.getDirector()));
                    moviePlottingDetails.setGenre(stringConcat(moviePlottingDetails.getGenre(), movieDetails.getGenre()));
                    moviePlottingDetails.setProductionCompany(stringConcat(moviePlottingDetails.getProductionCompany(), movieDetails.getProductionCompany()));
                    moviePlottingDetails.setTitleId(stringConcat(moviePlottingDetails.getTitleId(), movieDetails.getTitleId()));
                    moviePlottingDetails.setWriter(stringConcat(moviePlottingDetails.getWriter(), movieDetails.getWriter()));
                    moviePlottingDetails.setYear(movieDetails.getYear());
                    detailsMap.put(movieDetails.getYear(), moviePlottingDetails);
                } else {
                    MoviePlottingDetails moviePlottingDetails = new MoviePlottingDetails();
                    BeanUtils.copyProperties(movieDetails, moviePlottingDetails);
                    detailsMap.put(movieDetails.getYear(), moviePlottingDetails);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detailsMap;
    }

    private String stringConcat(String currentString, String newString) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!currentString.isEmpty() && !newString.isEmpty()) {
            return stringBuilder.append(currentString).append(",").append(newString).toString();
        } else if (!currentString.isEmpty() && newString.isEmpty()) {
            return stringBuilder.append(currentString).toString();
        } else if (currentString.isEmpty() && !newString.isEmpty()) {
            return stringBuilder.append(newString).toString();
        }
        return "";
    }
}
