package com.bridgelabz.censusanalyzer.csvfiles;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV {
    @CsvBindByName(column = "SrNo", required = true)
    public int srNo;
    @CsvBindByName(column = "StateName", required = true)
    public String state;
    @CsvBindByName(column = "TIN", required = true)
    public int tin;
    @CsvBindByName(column = "StateCode")
    public String stateCode;

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
