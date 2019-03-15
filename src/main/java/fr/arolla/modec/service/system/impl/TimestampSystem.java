package fr.arolla.modec.service.system.impl;

import fr.arolla.modec.service.system.Timestamp;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class TimestampSystem implements Timestamp {

    @Override
    public Calendar getCurrentDate() {
        return new GregorianCalendar();
    }
}
