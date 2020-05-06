package com.bridgelabz.censusanalyzer.adapter;

import com.bridgelabz.censusanalyzer.CensusDAO;
import com.bridgelabz.censusanalyzer.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyzer.service.CensusAnalyzer;

import java.util.Map;

public class AdapterFactory {
    public static Map<String, CensusDAO> getCensusData(CensusAnalyzer.Country country, String... csvFilePath) {
        if (country.equals(CensusAnalyzer.Country.INDIA))
            return new IndiaCensusAdaptor().loadCensusData(csvFilePath);
        else if (country.equals(CensusAnalyzer.Country.US))
            return new UsCensusAdapter().loadCensusData(csvFilePath);
        throw new CensusAnalyserException("Invalid Country", CensusAnalyserException.ExceptionType.INVALID_COUNTRY);
    }
}
