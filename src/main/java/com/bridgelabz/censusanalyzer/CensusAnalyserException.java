package com.bridgelabz.censusanalyzer;

public class CensusAnalyserException extends Exception {
    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    enum ExceptionType {
        CSV_FILE_PROBLEM
    }

    ExceptionType type;
}
