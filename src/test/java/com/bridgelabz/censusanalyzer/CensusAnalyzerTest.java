package com.bridgelabz.censusanalyzer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyzerTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/test/resource/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.json";
    private static final String WRONG_HEADER_FILE = "./src/test/resources/IndiaStateCode.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
        int numberOfRecord = censusAnalyzer.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(29, numberOfRecord);
    }

    @Test
    public void givenStateCensusCsvFile_whenIncorrect_shouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyzer.loadCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenStateCensusCsvFile_WhenTypeIncorrect_shouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(ClassCastException.class);
            censusAnalyzer.loadCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenStateCensusCsvFile_WhenHeaderIncorrect_shouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(ClassCastException.class);
            censusAnalyzer.loadCensusData(WRONG_HEADER_FILE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_WRONG_HEADER, e.type);
        }
    }
}