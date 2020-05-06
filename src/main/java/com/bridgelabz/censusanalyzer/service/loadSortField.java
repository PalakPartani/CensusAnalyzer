package com.bridgelabz.censusanalyzer.service;

import com.bridgelabz.censusanalyzer.CensusDAO;

import java.util.*;

public class loadSortField {
    Map<SortField, Comparator<CensusDAO>> sortMap;

    public loadSortField() {
        this.sortMap = new HashMap<>();
        this.sortMap.put(SortField.STATE, Comparator.comparing(census -> census.state));
        this.sortMap.put(SortField.POPULATION, Comparator.comparing(census -> census.population));
        this.sortMap.put(SortField.POPULATIONSDENSITY, Comparator.comparing(census -> census.densityPerSqKm));
        this.sortMap.put(SortField.TOTALAREA, Comparator.comparing(census -> census.areaInSqKm));
    }
}

