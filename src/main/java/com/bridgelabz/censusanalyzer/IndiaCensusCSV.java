package com.bridgelabz.censusanalyzer;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV {

    @CsvBindByName(column = "State", required = true)
    public String state;
    @CsvBindByName(column = "Population", required = true)
    private int population;
    @CsvBindByName(column = "AreaInSqKm", required = true)
    private int areaInSqKm;
    @CsvBindByName(column = "DensityPerSqKm")
    private int densityPerSqKm;

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