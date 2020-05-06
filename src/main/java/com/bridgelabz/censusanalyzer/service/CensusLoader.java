package com.bridgelabz.censusanalyzer.service;

import com.bridgelabz.censusanalyzer.CensusDAO;
import com.bridgelabz.censusanalyzer.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyzer.jar.CSVBuilderFactory;
import com.bridgelabz.censusanalyzer.jar.ICSVBuilder;
import com.bridgelabz.censusanalyzer.model.IndiaCensusCSV;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusLoader {

    public Map<String, CensusDAO> censusCSVMap;

    public CensusLoader() {
        this.censusCSVMap = new HashMap<>();
    }

    public <E> Map<String, CensusDAO> loadCensusData(String csvFilePath, Class<E> censusCSVClass) {

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, censusCSVClass);

            Iterable<E> csvIterable = () -> censusCSVIterator;
            if (censusCSVClass.getName().equals("com.bridgelabz.censusanalyzer.model.IndiaCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(censusCSV -> censusCSVMap.put(censusCSV.state, new CensusDAO(censusCSV)));
            } else if (censusCSVClass.getName().equals("com.bridgelabz.censusanalyzer.model.UsCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(censusCSV -> censusCSVMap.put(censusCSV.state, new CensusDAO(censusCSV)));
            }
            return censusCSVMap;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_WRONG_HEADER);
        }
    }
}