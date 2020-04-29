package com.bridgelabz.censusanalyzer;

public class CensusAnalyserException extends RuntimeException {
    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    enum ExceptionType {
        CSV_FILE_PROBLEM, CSV_WRONG_HEADER;
    }

    ExceptionType type;
}
