package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageSenderImplTest {
    @Test
    public void testRus() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.matches("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        LocalizationServiceImpl locService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(locService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        MessageSenderImpl msi = new MessageSenderImpl(geoService, locService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.123.123");
        String result = msi.send(headers);
        String expected = "Добро пожаловать";
        assertEquals(expected, result);

    }

    @Test
    public void testUSA() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.matches("96.")))
                .thenReturn(new Location("New York", Country.USA, null, 0));
        LocalizationServiceImpl locService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(locService.locale(Country.USA))
                .thenReturn("Welcome");
        MessageSenderImpl msi = new MessageSenderImpl(geoService, locService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.123.123.123");
        String result = msi.send(headers);
        String expected = "Welcome";
        assertEquals(expected, result);
    }
}
