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
    IndiaCensusCSV indiaCensusCSV = new IndiaCensusCSV(state, population, populationDensity, totalArea);
    UsCensusCSV usCensusCSV = new UsCensusCSV(state, population, populationDensity, totalArea);

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
        return (country.equals(CensusAnalyzer.Country.INDIA) ? this.indiaCensusCSV : this.usCensusCSV);
    }

   /* public Object getCensusDTO(CensusAnalyzer.Country country) {
        return (country.equals(CensusAnalyzer.Country.INDIA) ? new IndiaCensusCSV(state, population, populationDensity, totalArea)
                : new UsCensusCSV(state, population, populationDensity, totalArea));
    }*/
}