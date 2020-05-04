package com.bridgelabz.censusanalyzer;

public class IndiaCensusDAO {
    public double totalArea;
    public String state;
    public int areaInSqKm;
    public double densityPerSqKm;
    public double population;
    public String stateCode;
    public double populationDensity;

    public IndiaCensusDAO(IndiaCensusCSV next) {
        state = next.state;
        areaInSqKm = next.areaInSqKm;
        densityPerSqKm = next.densityPerSqKm;
        population = next.population;
    }

    public IndiaCensusDAO(UsCensusCSV UsCensusCSV) {

        state = UsCensusCSV.state;
        stateCode = UsCensusCSV.stateId;
        population = UsCensusCSV.population;
        totalArea = UsCensusCSV.totalArea;
        densityPerSqKm = UsCensusCSV.totalArea;
        populationDensity = UsCensusCSV.populationDensity;
    }
}
