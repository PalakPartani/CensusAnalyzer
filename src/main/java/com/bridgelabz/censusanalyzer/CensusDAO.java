package com.bridgelabz.censusanalyzer;

import com.bridgelabz.censusanalyzer.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyzer.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyzer.model.UsCensusCSV;
import com.bridgelabz.censusanalyzer.service.CensusAnalyzer;

public class CensusDAO {
    public double totalArea;
    public String state;
    public double areaInSqKm;
    public double densityPerSqKm;
    public double population;
    public String stateCode;
    public double populationDensity;

    public CensusDAO(IndiaCensusCSV next) {
        state = next.state;
        areaInSqKm = next.areaInSqKm;
        densityPerSqKm = next.densityPerSqKm;
        population = next.population;
    }

    public CensusDAO(UsCensusCSV UsCensusCSV) {

        state = UsCensusCSV.state;
        stateCode = UsCensusCSV.stateId;
        population = UsCensusCSV.population;
        totalArea = UsCensusCSV.totalArea;
        densityPerSqKm = UsCensusCSV.totalArea;
        populationDensity = UsCensusCSV.populationDensity;
    }

    public Object getCensusDTO(CensusAnalyzer.Country country) {
        if (country.equals(CensusAnalyzer.Country.INDIA))
            return new IndiaCensusCSV(state, population, populationDensity, totalArea);
        else if (country.equals(CensusAnalyzer.Country.US))
            return new IndiaCensusCSV(state, population, populationDensity, totalArea);
        throw new CensusAnalyserException("No Country data available", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
    }
}
