package com.bridgelabz.censusanalyzer.adapter;

import com.bridgelabz.censusanalyzer.CensusDAO;
import com.bridgelabz.censusanalyzer.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyzer.jar.CSVBuilderFactory;
import com.bridgelabz.censusanalyzer.jar.ICSVBuilder;
import com.bridgelabz.censusanalyzer.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyzer.model.IndiaStateCodeCSV;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndiaCensusAdaptor extends Adapter {

    Map<String, CensusDAO> censusDAOMap;


    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        censusDAOMap = super.loadCensusData(IndiaCensusCSV.class, csvFilePath[0]);
        this.loadStateCodeData(csvFilePath[1]);
        return censusDAOMap;
    }

    public int loadStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCSV> stateCodeCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            Iterable<IndiaStateCodeCSV> csvIterable = () -> stateCodeCSVIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> censusDAOMap.get(csvState.state) != null)
                    .forEach(csvState -> censusDAOMap.get(csvState.state).stateCode = csvState.stateCode);
            return censusDAOMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_TEMPLATE_PROBLEM);
        }
    }
}
