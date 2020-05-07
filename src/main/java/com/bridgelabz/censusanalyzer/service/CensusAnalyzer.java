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
    Map<String, CensusDAO> censusCSVMap = null;
    List<CensusDAO> list = null;

    public CensusAnalyzer() {
    }

    public enum Country {INDIA, US}

    private Country country;

    public CensusAnalyzer(Country country) {
        this.country = country;
    }

    public int loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException {
        censusCSVMap = AdapterFactory.getCensusData(country, csvFilePath);
        return censusCSVMap.size();
    }

    public String getSortedCensusData(SortField sortField) {
        if (censusCSVMap == null || censusCSVMap.size() == 0) {
            throw new CensusAnalyserException("No Census data available", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        return new Gson().toJson(list.stream()
                .sorted(loadSortField.sortMap.get(sortField).reversed())
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toList()));
    }
}