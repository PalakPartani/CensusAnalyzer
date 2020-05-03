package com.bridgelabz.censusanalyzer;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyzer {
    Map<String, IndiaCensusDTO> censusCSVMap;
    List<IndiaCensusDTO> collect = null;

    public CensusAnalyzer() {
        this.censusCSVMap = new HashMap<>();
    }

    public int loadCensusData(String csvFilePath) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            //java 8 feature
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
            while (censusCSVIterator.hasNext()) {
                IndiaCensusCSV indiaCensusCSV = censusCSVIterator.next();
                censusCSVMap.put(indiaCensusCSV.state, new IndiaCensusDTO(indiaCensusCSV));
            }
            collect = censusCSVMap.values().stream().collect(Collectors.toList());
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
            while (censusCSVIterator.hasNext()) {
                IndiaStateCodeCSV indiaStateCodeCSV = censusCSVIterator.next();
                IndiaCensusDTO indiaStateCode = censusCSVMap.get(indiaStateCodeCSV.state);
                if (indiaStateCode == null)
                    continue;
            }
            return censusCSVMap.size();

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_WRONG_HEADER);
        }
    }
/*
    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
    }*/

    public String getStateWiseSortedCensusData() {
        if (collect == null || collect.size() == 0) {
            throw new CensusAnalyserException("No Census data available", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusDTO> censusComparator = Comparator.comparing(census -> census.state);
        this.sort(censusComparator);
        String sortedStateCensus = new Gson().toJson(collect);
        return sortedStateCensus;
    }

    private void sort(Comparator<IndiaCensusDTO> censusComparator) {
        for (int i = 0; i < collect.size() - 1; i++) {
            for (int j = 0; j < collect.size() - 1 - i; j++) {
                IndiaCensusDTO census1 = collect.get(j);
                IndiaCensusDTO census2 = collect.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    collect.set(j, census2);
                    collect.set(j + 1, census1);

                }
            }
        }
    }
}