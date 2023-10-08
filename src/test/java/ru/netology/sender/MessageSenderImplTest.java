package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static ru.netology.entity.Country.*;
import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

class MessageSenderImplTest {

    @Test
    public void testRussianMessageSentForRussianIp() {
        GeoService geoService = Mockito.mock(GeoService.class);
        when(geoService.byIp("172.1.1.1")).thenReturn(new Location("Moscow",RUSSIA,"Lenina",15));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        String message = messageSender.send(Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, "172.1.1.1"));
        assertEquals("Добро пожаловать", message);
    }

    @Test
    public void testEnglishMessageSentForNonRussianIp() {
        GeoService geoService = Mockito.mock(GeoService.class);
        when(geoService.byIp("96.1.1.1")).thenReturn(new Location("New-York", USA, "10th", 32));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        when(localizationService.locale(USA)).thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        String message = messageSender.send(Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, "97.1.1.1"));
        assertEquals("Welcome", message);
    }
}