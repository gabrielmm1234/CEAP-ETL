package tcc.main;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
    	String csvFile = "/home/gabriel/Desktop/UnB/10-semestre/TG1/monografia/dados/Ano-2017.csv";
    	CSVReader reader = null;
    	ServiceReader serviceReader = new ServiceReader();
        try {
        	reader = new CSVReader(new FileReader(csvFile), ';');
            serviceReader.readFileAndBuildGraph(reader);
            System.out.println("Data Transfer finished.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
