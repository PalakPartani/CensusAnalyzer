package com.bridgelabz.censusanalyzer;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSVBuilder<E> implements ICSVBuilder {


    @Override
    public List<E> getCSVFileList(Reader reader, Class csvClass) {
        return this.getCsvToBean(reader, csvClass).parse();
    }

    @Override
    public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) {
        return this.getCsvToBean(reader, csvClass).iterator();

    }

    private CsvToBean getCsvToBean(Reader reader, Class csvClass) {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            return csvToBeanBuilder.build();
        } catch (IllegalStateException e) {
            throw new CSVBuilderException(e.getMessage(), CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

}