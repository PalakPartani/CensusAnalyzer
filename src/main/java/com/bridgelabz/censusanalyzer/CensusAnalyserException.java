package com.bridgelabz.censusanalyzer;

public class CensusAnalyserException extends RuntimeException {
    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, String type) {
        super(message);
        this.type = ExceptionType.valueOf(type);
    }

    public enum ExceptionType {
        CSV_FILE_PROBLEM, CSV_WRONG_HEADER, UNABLE_TO_PARSE, NO_CENSUS_DATA;
    }

    ExceptionType type;
}