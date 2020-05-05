package com.bridgelabz.censusanalyzer;

import com.bridgelabz.censusanalyzer.csvfiles.IndiaCensusCSV;
import com.bridgelabz.censusanalyzer.csvfiles.UsCensusCSV;

public class CensusDAO {
    public double totalArea;
    public String state;
    public int areaInSqKm;
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
}
