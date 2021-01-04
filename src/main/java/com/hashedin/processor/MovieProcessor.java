package com.hashedin.processor;


import com.hashedin.beans.MovieDetails;
import com.hashedin.beans.MoviePlottingDetails;
import org.springframework.batch.item.ItemProcessor;

import java.util.Map;

public class MovieProcessor implements ItemProcessor<Map.Entry<String, MoviePlottingDetails>, MoviePlottingDetails> {

    @Override
    public MoviePlottingDetails process(Map.Entry<String, MoviePlottingDetails> moviePlottingDetailsEntry) throws Exception {
        return moviePlottingDetailsEntry.getValue();
    }
}
