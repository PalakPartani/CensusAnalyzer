package com.bridgelabz.censusanalyzer;

public class CSVBuilderException extends RuntimeException {
    public enum ExceptionType {
        CSV_FILE_PROBLEM, CSV_WRONG_HEADER, UNABLE_TO_PARSE;
    }

    public ExceptionType type;

    public CSVBuilderException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}