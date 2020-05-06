package com.bridgelabz.censusanalyzer.service;

import com.bridgelabz.censusanalyzer.CensusDAO;
import com.bridgelabz.censusanalyzer.adapter.AdapterFactory;
import com.bridgelabz.censusanalyzer.exception.CensusAnalyserException;
import com.google.gson.Gson;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CensusAnalyzer {
    public enum Country {INDIA, US}

    Map<String, CensusDAO> censusCSVMap;
    List<CensusDAO> list=null;

    public int loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException {
        censusCSVMap = AdapterFactory.getCensusData(country, csvFilePath);
        return censusCSVMap.size();
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
    public String getUSSortedCensusData(SortField sortField) throws CensusAnalyserException {
        if(censusCSVMap.size()==0 || censusCSVMap==null) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
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