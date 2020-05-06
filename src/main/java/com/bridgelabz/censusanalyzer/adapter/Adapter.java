package com.bridgelabz.censusanalyzer.adapter;

import com.bridgelabz.censusanalyzer.CensusDAO;
import com.bridgelabz.censusanalyzer.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyzer.jar.CSVBuilderFactory;
import com.bridgelabz.censusanalyzer.jar.ICSVBuilder;
import com.bridgelabz.censusanalyzer.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyzer.model.UsCensusCSV;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class Adapter {

    public abstract Map<String, CensusDAO> loadCensusData(String... csvFilePath) ;

    public <E> Map<String, CensusDAO> loadCensusData(Class<E> censusCsvClass, String csvFilePath) {
        Map<String,CensusDAO> censusMap = new HashMap<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> censusCsvIterator = icsvBuilder.getCSVFileIterator(reader, censusCsvClass);
            Iterable<E> csvIterable = () -> censusCsvIterator;
            if (censusCsvClass.getName().equals("com.bridgelabz.censusanalyzer.model.IndiaCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(censusCsv -> censusMap.put(censusCsv.state, new CensusDAO(censusCsv)));
            } else if (censusCsvClass.getName().equals("com.bridgelabz.censusanalyzer.model.UsCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(UsCensusCSV.class::cast)
                        .forEach(censusCsv -> censusMap.put(censusCsv.state, new CensusDAO(censusCsv)));
            }
            return censusMap;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_TEMPLATE_PROBLEM);
        }
    }
}
