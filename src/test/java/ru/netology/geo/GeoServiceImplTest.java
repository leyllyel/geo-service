package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

class GeoServiceImplTest {

    @Test
    public void testLocationByRussianIp() {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp("172.1.1.1");
        assertEquals(RUSSIA, location.getCountry());
    }

    @Test
    public void testLocationByAmericanIp() {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp("96.1.1.1");
        assertEquals(USA, location.getCountry());
    }
}