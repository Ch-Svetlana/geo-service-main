package ru.netology.i18n;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTest {
    LocalizationServiceImpl sut;

    @BeforeEach
    public void init(){
        sut = new LocalizationServiceImpl();
    }
    @AfterEach
    public void end(){
        sut = null;
    }

    @Test
    public void testRus(){
        Country country = Country.RUSSIA;
        String result = sut.locale(country);
        String expected = "Добро пожаловать";
        assertEquals(expected, result);
    }
    @MethodSource("source")
    @ParameterizedTest
    public void testNotRus(String c){
        String expected = sut.locale(Country.valueOf(c));
        String result = "Welcome";
        assertEquals(expected, result);
    }
    public static Stream<Arguments> source() {
        return Stream.of(Arguments.of("GERMANY"), Arguments.of("USA", Arguments.of("BRAZIL")));
    }

}
