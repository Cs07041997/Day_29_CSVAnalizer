package com.bridgelabz.program;

import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

public class Test {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "D:\\.csv extention\\IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CENSUS_FILE_EXTENSION = "D:\\.csv extention\\.sh";
    private static final String INDIA_CENSUS_CSV_FILE_WRONG_DELIMITER = "D:\\.csv extention\\WrongDelimite.csv";
    private static final String INDIA_CENSUS_CSV_FILE_WRONG_HEADER = "D:\\.csv extention\\WrongHeader.csv";
    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "D:\\.csv extention\\IndiaStateCode.csv";

    @Description("Given Indian States Census CSV file, Check to ensure the number of record matches.")
    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) {

        }
    }

    @Description("Given Indian State Census CSV file with incorrect path should return an custom exception.")
    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Description("Given the Indian State Census CSV file with incorrect type, should return Custom Exception.")
    @Test
    public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CENSUS_FILE_EXTENSION);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Description("Given the State Census CSV File when correct but delimiter incorrect Returns a custom Exception.")
    // this test should be fail as we have given incorrect delimiter.
    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_HEADER_AND_DELIMITER, e.type);
        }
    }

    @Description("Given the State Census CSV File when correct but header incorrect return custom exception.")
    // this test should be fail as we have given incorrect delimiter.

    @Test
    public void givenIndiaCensusData_WithWrongHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_WRONG_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_HEADER_AND_DELIMITER, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCSV_ShouldReturnExactCount() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfStateCode = censusAnalyser.loadIndianStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            System.out.println("total num of state code"+numOfStateCode);
            Assert.assertEquals(37, numOfStateCode);
        } catch (CensusAnalyserException e) { }
    }
}
