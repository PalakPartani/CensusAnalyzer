package com.bridgelabz.censusanalyzer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyzerTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";

    private static final String WRONG_CSV_FILE_PATH = "./src/test/resource/IndiaStateCensusDataa.csv";

    private static final String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.json";

    private static final String WRONG_HEADER_FILE = "./src/test/resources/IndiaStateCode.csv";

    private static final String INDIA_STATE_CODE = "./src/test/resources/IndiaStateCode.csv";

    private static final String WRONG_DELIMITER = "./src/test/resources/WrongDelimiter.csv";

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
    public void givenStateCensusCsvFile_WhenTypeIncorrect_ShouldReturnException() {
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
    public void givenStateCensusCsvFile_WhenDelimiterIncorrect_ShouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(ClassCastException.class);
            censusAnalyzer.loadCensusData(WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_WRONG_HEADER, e.type);
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

    //uc2
    @Test
    public void givenIndianStateCodeCSVFileReturnsCorrectRecords() {
        CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
        try {
            int numberOfRecord = censusAnalyzer.loadStateCodeData(INDIA_STATE_CODE);
            Assert.assertEquals(37, numberOfRecord);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCodeCsvFile_WhenIncorrect_ShouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyzer.loadStateCodeData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCsvFile_WhenTypeIncorrect_ShouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(ClassCastException.class);
            censusAnalyzer.loadStateCodeData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCsvFile_WhenHeaderIncorrect_shouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(ClassCastException.class);
            censusAnalyzer.loadStateCodeData(WRONG_HEADER_FILE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_WRONG_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCodeCsvFile_WhenDelimiterIncorrect_ShouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(ClassCastException.class);
            censusAnalyzer.loadCensusData(WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_WRONG_HEADER, e.type);
        }
    }
}