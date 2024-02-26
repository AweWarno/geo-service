package ru.netology.sender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

class MessageSenderImplTest {
    private final GeoService geoService = Mockito.mock(GeoService.class);
    private final LocalizationService localizationService = Mockito.mock(LocalizationService.class);

    @Test
    void testSend_ip_russian() {

        Mockito.when(geoService.byIp("172."))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");
        String actual = messageSender.send(headers);
        String expected = localizationService.locale(Country.RUSSIA);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSend_ip_usa() {

        Mockito.when(geoService.byIp("96."))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.");
        String actual = messageSender.send(headers);
        String expected = localizationService.locale(Country.USA);

        Assertions.assertEquals(expected, actual);
    }
}