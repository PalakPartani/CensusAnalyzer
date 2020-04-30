package com.bridgelabz.censusanalyzer;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyzer {
    public int loadCensusData(String csvFilePath) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            //java 8 feature
          //  Iterator<IndiaCensusCSV> censusCSVIterator = getCSVFileIterator(reader, IndiaCensusCSV.class);
            Iterator<IndiaStateCode> censusCSVIterator =new OpenCSVBuilder().getCSVFileIterator(reader, IndiaStateCode.class);

            return getCount(censusCSVIterator);

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_WRONG_HEADER);
        }
    }

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        int numOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return numOfEnteries;
    }

    public int loadStateCodeData(String csvFilePath) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            //java 8 feature
            //Iterator<IndiaCensusCSV> censusCSVIterator = getCSVFileIterator(reader, IndiaStateCode.class);
            Iterator<IndiaStateCode> censusCSVIterator =new OpenCSVBuilder().getCSVFileIterator(reader, IndiaStateCode.class);
            return getCount(censusCSVIterator);

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_WRONG_HEADER);
        }
    }

    private <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) {
        CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
        csvToBeanBuilder.withType(csvClass);
        csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
        CsvToBean<E> csvToBean = csvToBeanBuilder.build();
        return csvToBean.iterator();
    }
}
