package com.bridgelabz.censusanalyzer.model;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV {

    @CsvBindByName(column = "State", required = true)
    public String state;
    @CsvBindByName(column = "Population", required = true)
    public double population;
    @CsvBindByName(column = "AreaInSqKm", required = true)
    public double areaInSqKm;
    @CsvBindByName(column = "DensityPerSqKm")
    public double densityPerSqKm;

    public IndiaCensusCSV(String state, double population, double populationDensity, double totalArea) {
        this.state = state;
        this.population = population;
        this.densityPerSqKm = populationDensity;
        this.areaInSqKm = totalArea;
    }

    public IndiaCensusCSV() {

    }

    @Override
    public String toString() {
        return "IndiaCensusCsv{" +
                "state='" + state + '\'' +
                ", population=" + population +
                ", areaInSqKm=" + areaInSqKm +
                ", densityPerSqKm=" + densityPerSqKm +
                '}';
    }
}