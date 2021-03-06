package com.bridgelabz.censusanalyzer.model;

import com.opencsv.bean.CsvBindByName;

public class UsCensusCSV {

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "State Id", required = true)
    public String stateId;

    @CsvBindByName(column = "Population", required = true)
    public double population;

    @CsvBindByName(column = "Total area", required = true)
    public double totalArea;

    @CsvBindByName(column = "  Population Density", required = true)
    public double populationDensity;

    @Override
    public String toString() {

        return "UsCensusCSV{" +
                "state='" + state + '\'' +
                ", stateId=" + stateId +
                ", population=" + population +
                ", totalArea=" + totalArea +
                ", populationDensity=" + populationDensity +
                '}';
    }

    public UsCensusCSV(String state, double population, double populationDensity, double totalArea) {
        this.state = state;
        this.population = population;
        this.populationDensity = populationDensity;
        this.totalArea = totalArea;
    }

}

