package com.bridgelabz.censusanalyzer.service;

import com.bridgelabz.censusanalyzer.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyzer {
    Map<String, IndiaCensusDAO> censusCSVMap;
    List<IndiaCensusDAO> list;
    // Map<SortField, Comparator<IndiaCensusDAO>> sortMap;

    public CensusAnalyzer() {
        this.list = new ArrayList<>();
        this.censusCSVMap = new HashMap<>();

    }

    public int loadCensusData(String csvFilePath) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            //java 8 feature
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);

            //  Iterable<IndiaStateCodeCSV> csvIterable = () -> censusCSVIterator;
           /* StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> censusCSVMap.get(csvState.state) != null)
                    .collect(censusCSVMap.put(censusCSVIterator.next(),new IndiaCensusDAO(new IndiaCensusCSV())));
           */
            while (censusCSVIterator.hasNext()) {
                IndiaCensusCSV indiaCensusCSV = censusCSVIterator.next();
                censusCSVMap.put(indiaCensusCSV.state, new IndiaCensusDAO(indiaCensusCSV));
            }

            list = censusCSVMap.values().stream().collect(Collectors.toList());
            return censusCSVMap.size();

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_WRONG_HEADER);
        }
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

        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }

    private void sort(Comparator<IndiaCensusDAO> censusComparator) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                IndiaCensusDAO census1 = list.get(j);
                IndiaCensusDAO census2 = list.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    list.set(j, census2);
                    list.set(j + 1, census1);
                }
            }
        }
    }

    public int loadUSCensusData(String csvFilePath) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            //java 8 feature
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<UsCensusCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, UsCensusCSV.class);

            while (censusCSVIterator.hasNext()) {
                UsCensusCSV usCensusCSV = censusCSVIterator.next();
                censusCSVMap.put(usCensusCSV.state, new IndiaCensusDAO(usCensusCSV));
            }

            list = censusCSVMap.values().stream().collect(Collectors.toList());
            return censusCSVMap.size();

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_WRONG_HEADER);
        }
    }
}