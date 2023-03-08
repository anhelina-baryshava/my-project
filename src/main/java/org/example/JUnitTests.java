package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
public class JUnitTests {

    @Smoke
    @ParameterizedTest
    @ValueSource(strings = {"one", "two", "three"})
    public void paramTest(String parameter){
        System.out.println("Parameter " + parameter);
    }
    @ParameterizedTest
    @NullAndEmptySource
    public void paramNullTest(String parameter){
        System.out.println("Parameter " + parameter);
    }
    @Smoke
    @ParameterizedTest
    @EnumSource(ParamEnum.class)
    public void paramEnumTest(ParamEnum parameter){
        System.out.println("Parameter " + parameter.getDescription());
    }
    @ParameterizedTest
    @MethodSource
    public void paramMethodTest(ParamEnum role, String type){
        System.out.println("Parameter " + role.getDescription());
        System.out.println("create " + type);
    }
    static Stream<Arguments> paramMethodTest(){
        return Stream.of(
                arguments(ParamEnum.MANAGER,"nfkdj"),
                arguments(ParamEnum.LEAD_MANAGER, "khbkhb"),
                arguments(ParamEnum.SENIOR_MANAGER, "hbjh"));
    }
    @ParameterizedTest
    @CsvSource({"test,TEST", "java,JAVA", "paRamEter, PARAMETER"})
    public void paramCsvTest(String input, String expected){
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }
//    @ParameterizedTest
//    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
//    public void paramCsvFileTest(String input, String expected){
//        String actualValue = input.toUpperCase();
//        assertEquals(expected, actualValue);
//    }


    @AfterAll
    public static void afterAll(){
        System.out.println("End");
    }
    @AfterEach
    public void afterEach(){
        System.out.println("After");
    }
}
