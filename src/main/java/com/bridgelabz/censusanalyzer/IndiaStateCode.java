package com.bridgelabz.censusanalyzer;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCode {
    @CsvBindByName(column = "SrNo", required = true)
    private int srNo;
    @CsvBindByName(column = "StateName", required = true)
    private String state;
    @CsvBindByName(column = "TIN", required = true)
    private int tin;
    @CsvBindByName(column = "StateCode")
    private String stateCode;

    @Override
    public String toString() {
        return "IndiaStateCode{" +
                "srNo=" + srNo +
                ", state='" + state + '\'' +
                ", tin=" + tin +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}
