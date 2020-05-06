package com.bridgelabz.censusanalyzer.adapter;

import com.bridgelabz.censusanalyzer.CensusDAO;
import com.bridgelabz.censusanalyzer.model.UsCensusCSV;

import java.util.Map;

public class UsCensusAdapter extends Adapter {
    public Map<String, CensusDAO> censusMap;

    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) {
        censusMap = super.loadCensusData(UsCensusCSV.class,csvFilePath[0]);
        return censusMap;
    }
}
