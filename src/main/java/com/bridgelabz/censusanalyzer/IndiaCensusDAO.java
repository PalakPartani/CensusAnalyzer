package com.bridgelabz.censusanalyzer;

public class IndiaCensusDAO {
    public String state;
    public int areaInSqKm;
    public int densityPerSqKm;
    public int population;

    public IndiaCensusDAO(IndiaCensusCSV next) {
        state=next.state;
        areaInSqKm=next.areaInSqKm;
        densityPerSqKm=next.densityPerSqKm;
        population=next.population;
    }
}
