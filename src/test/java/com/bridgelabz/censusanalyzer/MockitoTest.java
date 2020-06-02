package com.bridgelabz.censusanalyzer;

import com.bridgelabz.censusanalyzer.adapter.AdapterFactory;
import com.bridgelabz.censusanalyzer.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyzer.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyzer.service.CensusAnalyzer;
import com.bridgelabz.censusanalyzer.service.SortField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoTest {
    AdapterFactory adapterFactory;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    IndiaCensusCSV indiaCensusCSV;
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String INDIA_STATE_CODE = "./src/test/resources/IndiaStateCode.csv";

    @Before
    public void setUp() throws Exception {
        indiaCensusCSV = new IndiaCensusCSV();
    }

    @Test
    public void testLoadCensusCount() {
        CensusAnalyzer censusAnalyzer = mock(CensusAnalyzer.class);
        when(censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE)).thenReturn(29);
        int data = censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE);
        Assert.assertEquals(29, data);
    }

    @Test
    public void testLoadCensusData_ShouldThrowException() {
        CensusAnalyzer censusAnalyzer = mock(CensusAnalyzer.class);
        censusAnalyzer.loadCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE);
        when(adapterFactory.getCensusData(CensusAnalyzer.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE)).thenThrow(new CensusAnalyserException("Invalid Country", CensusAnalyserException.ExceptionType.INVALID_COUNTRY));
    }

    @Test
    public void testPopulatedState() {
        CensusAnalyzer censusAnalyzer = mock(CensusAnalyzer.class);
        when(censusAnalyzer.getSortedCensusData(SortField.POPULATION)).thenReturn("Kerela");
        String sortedCensusData = censusAnalyzer.getSortedCensusData(SortField.POPULATION);
        Assert.assertEquals("Kerela", sortedCensusData);
    }

    @Test
    public void testDensityState() {
        CensusAnalyzer censusAnalyzer = mock(CensusAnalyzer.class);
        when(censusAnalyzer.getSortedCensusData(SortField.POPULATIONSDENSITY)).thenReturn("Goa");
        String sortedCensusData = censusAnalyzer.getSortedCensusData(SortField.POPULATIONSDENSITY);
        Assert.assertEquals("Goa", sortedCensusData);
    }

    @Test
    public void testStateAlphabetically() {
        CensusAnalyzer censusAnalyzer = mock(CensusAnalyzer.class);
        when(censusAnalyzer.getSortedCensusData(SortField.STATE)).thenReturn("Andhra Pradesh");
        String sortedCensusData = censusAnalyzer.getSortedCensusData(SortField.STATE);
        Assert.assertEquals("Andhra Pradesh", sortedCensusData);
    }
}

