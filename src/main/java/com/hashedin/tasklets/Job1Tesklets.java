package com.hashedin.tasklets;

import com.opencsv.CSVWriter;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

@Configuration
public class Job1Tesklets  implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        String input = chunkContext.getStepContext().getJobParameters().get("input").toString();
//        String input = "rohit";
        char str[] = input.toCharArray();

        String[][] arr = new String[input.length()][input.length()];
        for (int row = 0; row < arr.length; row++){
            if(row < 1){
                for (int col = 0; col < arr[row].length; col++){
                    arr[row][col] = str[col]+"";
                }
            }else {
                for (int col = 0; col < row; col++){
                    arr[row][col] = randString(input,row);
                }
            }
        }
        FileSystemResource fileSystemResource = new FileSystemResource("rohit.csv");
        try {
            FileWriter outputfile = new FileWriter(fileSystemResource.getFile());
            CSVWriter writer = new CSVWriter(outputfile);
            for (int row = 0; row < arr.length; row++){
                writer.writeNext(arr[row]);
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return RepeatStatus.FINISHED;
    }

    private String randString(String input, int length){
        Random random = new Random();
        int randomNum = random.nextInt(input.length() - length + 1);
        String answer = input.substring(randomNum, randomNum + length);
        return answer;
    }
}
