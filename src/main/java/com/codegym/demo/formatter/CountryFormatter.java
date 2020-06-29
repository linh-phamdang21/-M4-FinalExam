package com.codegym.demo.formatter;

import com.codegym.demo.model.Country;
import com.codegym.demo.service.country.ICountryService;
import com.codegym.demo.service.country.CountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class CountryFormatter implements Formatter<Country> {
    @Autowired
    private ICountryService countryService;

    @Autowired
    private CountryFormatter(CountryServiceImpl countryService){
        this.countryService = countryService;
    }

    @Override
    public String print(Country object, Locale locale) {
        return null;
    }

    @Override
    public Country parse(String text, Locale locale) throws ParseException {
        return countryService.findById(Long.parseLong(text)).get();
    }
}
