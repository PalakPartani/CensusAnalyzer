package com.bridgelabz.censusanalyzer.exception;

public class CensusAnalyserException extends RuntimeException {
    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
    public enum ExceptionType {
        CSV_FILE_PROBLEM, CSV_WRONG_HEADER, UNABLE_TO_PARSE, NO_CENSUS_DATA,INVALID_COUNTRY,CSV_TEMPLATE_PROBLEM;
    }
   public ExceptionType type;
}