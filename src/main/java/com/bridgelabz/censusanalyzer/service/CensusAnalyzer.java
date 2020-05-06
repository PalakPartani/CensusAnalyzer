package com.bridgelabz.censusanalyzer.service;

import com.bridgelabz.censusanalyzer.*;
import com.bridgelabz.censusanalyzer.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyzer.model.IndiaStateCodeCSV;
import com.bridgelabz.censusanalyzer.model.UsCensusCSV;
import com.bridgelabz.censusanalyzer.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyzer.jar.CSVBuilderFactory;
import com.bridgelabz.censusanalyzer.jar.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyzer {
    Map<String, CensusDAO> censusCSVMap;
    List<CensusDAO> list;

/*    public CensusAnalyzer() {
        this.list = new ArrayList<>();
        this.censusCSVMap = new HashMap<>();
    }*/

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        censusCSVMap = new CensusLoader().loadCensusData(csvFilePath, IndiaCensusCSV.class);
        list = censusCSVMap.values().stream().collect(Collectors.toList());
        return censusCSVMap.size();
    }

    public int loadUSCensusData(String csvFilePath) {
        return new CensusLoader().loadCensusData(csvFilePath, UsCensusCSV.class).size();
    }

    public int loadStateCodeData(String csvFilePath) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            //java 8 feature
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            Iterable<IndiaStateCodeCSV> csvIterable = () -> censusCSVIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> censusCSVMap.get(csvState.state) != null)
                    .forEach(csvState -> censusCSVMap.get(csvState.state));
            return censusCSVMap.size();

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_WRONG_HEADER);
        }
    }

    public String getSortedCensusData(SortField sortField) {
        if (list == null || list.size() == 0) {
            throw new CensusAnalyserException("No Census data available", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        list = censusCSVMap.values().stream().collect(Collectors.toList());
        this.sort(new loadSortField().sortMap.get(sortField).reversed());

        // new loadSortField().sortMap.headMap(sortField);
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }

    private void sort(Comparator<CensusDAO> censusComparator) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                CensusDAO census1 = list.get(j);
                CensusDAO census2 = list.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    list.set(j, census2);
                    list.set(j + 1, census1);
                }
            }
        }
    }
}
