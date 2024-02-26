package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {
    private final LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
    @Test
    void testLocale_return_Russian() {
        assertEquals(localizationService.locale(Country.RUSSIA), "Добро пожаловать");
    }

    @Test
    void testLocale_return_English() {
        assertEquals(localizationService.locale(Country.USA), "Welcome");
    }
}