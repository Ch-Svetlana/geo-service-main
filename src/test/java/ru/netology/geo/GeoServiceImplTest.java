package ru.netology.geo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoServiceImplTest {
    GeoServiceImpl sut;


    @BeforeEach
    public void init() {
        sut = new GeoServiceImpl();
    }


    @AfterEach
    public void end() {
        sut = null;
    }

    public static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("127.0.0.1", null),
                Arguments.of("172.0.32.11", "Moscow"),
                Arguments.of("96.44.183.149", "New York"),
                Arguments.of("172.186.2.345", "Moscow"),
                Arguments.of("96.123.123.123", "New York"));
    }

    @MethodSource("source")
    @ParameterizedTest
    public void testIp(String ip, String expected) {
        Location loc = sut.byIp(ip);
        String result = loc.getCity();
        assertEquals(expected, result);
    }
}
