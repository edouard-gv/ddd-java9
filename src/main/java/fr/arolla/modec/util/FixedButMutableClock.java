package fr.arolla.modec.util;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

/**
 * Implementation of a clock that always returns the same instant.
 * This is typically used for testing.
 */
public class FixedButMutableClock extends Clock {
    private Instant instant;
    private ZoneId zone;

    public FixedButMutableClock(Instant fixedInstant, ZoneId zone) {
        this.instant = fixedInstant;
        this.zone = zone;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    @Override
    public ZoneId getZone() {
        return zone;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        if (zone.equals(this.zone)) {  // intentional NPE
            return this;
        }
        return new FixedButMutableClock(instant, zone);
    }

    @Override
    public long millis() {
        return instant.toEpochMilli();
    }

    @Override
    public Instant instant() {
        return instant;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FixedButMutableClock) {
            FixedButMutableClock other = (FixedButMutableClock) obj;
            return instant.equals(other.instant) && zone.equals(other.zone);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return instant.hashCode() ^ zone.hashCode();
    }

    @Override
    public String toString() {
        return "FixedMutableClock[" + instant + "," + zone + "]";
    }
}
