package fr.arolla.modec.service;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class DateServiceImpl implements DateService {

    private Calendar forcedCalendar;

    @Override
    public Calendar getCurrentDate() {
        return (forcedCalendar != null ? forcedCalendar : new GregorianCalendar());
    }
}
