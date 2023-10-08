package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @Test
    public void testRussianLocale() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String locale = localizationService.locale(Country.RUSSIA);
        assertEquals("Добро пожаловать", locale);
    }

    @Test
    public void testEnglishLocale() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String locale = localizationService.locale(Country.USA);
        assertEquals("Welcome", locale);
    }
}