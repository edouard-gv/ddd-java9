package fr.arolla.ddd9.service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@TestConfiguration
public class DateServiceMock implements DateService {

    private Calendar forcedCalendar;

    //@Override
    public Calendar getCurrentDate() {
        return forcedCalendar;
    }

    public void forceCalendar(Calendar now) {
        this.forcedCalendar = now;
    }
}
