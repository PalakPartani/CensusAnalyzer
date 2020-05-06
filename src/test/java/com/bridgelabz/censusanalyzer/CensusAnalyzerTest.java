package com.bridgelabz.censusanalyzer;

import com.bridgelabz.censusanalyzer.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyzer.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyzer.model.UsCensusCSV;
import com.bridgelabz.censusanalyzer.service.CensusAnalyzer;
import com.bridgelabz.censusanalyzer.service.SortField;
import com.google.gson.Gson;
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

    private static final String US_CENSUS = "./src/test/resources/USCenusData.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
        int numberOfRecord = censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE);
        Assert.assertEquals(29, numberOfRecord);
    }

    @Test
    public void givenStateCensusCsvFile_whenIncorrect_shouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, WRONG_CSV_FILE_PATH);
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
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, WRONG_CSV_FILE_TYPE);
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
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenStateCensusCsvFile_WhenHeaderIncorrect_shouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(ClassCastException.class);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, WRONG_HEADER_FILE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.type);
        }
    }

    //uc2
    @Test
    public void givenIndianStateCodeCsvFile_WhenIncorrect_ShouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, WRONG_CSV_FILE_PATH);
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
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, WRONG_CSV_FILE_TYPE);
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
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, WRONG_HEADER_FILE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenStateCodeCsvFile_WhenDelimiterIncorrect_ShouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(ClassCastException.class);
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.type);
        }
    }

    //uc3
    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(SortField.STATE);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[censusCSV.length - 1].state);
            // Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);

        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //uc5
    @Test
    public void givenIndiaCensusData_WhenSortedOnPopulation_ShouldReturnResult() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(SortField.POPULATION);
            IndiaCensusCSV[] censusCsv = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Sikkim", censusCsv[censusCsv.length - 1].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusDataWrong_WhenSortedOnPopulation_ShouldReturnResult() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(SortField.POPULATION);
            new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.type);
        }
    }

    //uc6
    @Test
    public void givenIndiaCensusData_WhenSortedOnDensity_ShouldReturnResult() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(SortField.POPULATIONSDENSITY);
            IndiaCensusCSV[] censusCsv = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Arunachal Pradesh", censusCsv[censusCsv.length - 1].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusDataWrong_WhenSortedOnPopulationDensity_ShouldReturnResult() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(SortField.POPULATIONSDENSITY);
            new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_TEMPLATE_PROBLEM, e.type);
        }
    }

    //uc7
    @Test
    public void givenIndiaCensusData_WhenSortedOnArea_ShouldReturnResult() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(SortField.TOTALAREA);
            IndiaCensusCSV[] censusCsv = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Goa", censusCsv[censusCsv.length - 1].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //uc8
    @Test
    public void givenUSCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            int numberOfRecord = censusAnalyzer.loadCensusData(CensusAnalyzer.Country.US, US_CENSUS);
            Assert.assertEquals(51, numberOfRecord);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUsCensusData_WhenSorted_ShouldReturnCorrectResult() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.US, US_CENSUS);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(SortField.POPULATION);
            UsCensusCSV[] censusCsv = new Gson().fromJson(sortedCensusData, UsCensusCSV[].class);
            Assert.assertEquals("California", censusCsv[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUsCensusData_WhenSorted_ShouldReturnLastCorrectResult() {

        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.US, US_CENSUS);
            String sortedCensusData = censusAnalyzer.getUSSortedCensusData(SortField.POPULATION);
            UsCensusCSV[] censusCsv = new Gson().fromJson(sortedCensusData, UsCensusCSV[].class);
            Assert.assertEquals("Wyoming", censusCsv[50].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUsCensusData_WhenSortedOnState_ShouldReturnCorrectResult() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.US, US_CENSUS);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(SortField.STATE);
            UsCensusCSV[] censusCsv = new Gson().fromJson(sortedCensusData, UsCensusCSV[].class);
            Assert.assertEquals("Alabama", censusCsv[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUsCensusData_WhenSortedOnDensity_ShouldReturnCorrectResult() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            censusAnalyzer.loadCensusData(CensusAnalyzer.Country.US, US_CENSUS);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(SortField.POPULATIONSDENSITY);
            UsCensusCSV[] censusCsv = new Gson().fromJson(sortedCensusData, UsCensusCSV[].class);
            Assert.assertEquals("District of Columbia", censusCsv[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}