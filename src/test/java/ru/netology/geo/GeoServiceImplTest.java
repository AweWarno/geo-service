package ru.netology.geo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {
    private final GeoServiceImpl geoService = new GeoServiceImpl();

    @Test
    void testByIp_return_null_for_localhost() {
        Location location = geoService.byIp(GeoServiceImpl.LOCALHOST);
        assertNull(location.getCountry());
    }

    @Test
    void testByIp_return_obj_for_Moskow() {
        Location location = geoService.byIp("172.");
        assertEquals(location.getCountry(), Country.RUSSIA);
    }

    @Test
    void testByIp_return_obj_for_NEW_YORK() {
        Location location = geoService.byIp("96.");
        assertEquals(location.getCountry(), Country.USA);
    }
}