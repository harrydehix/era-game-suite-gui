package de.harrydehix.eragamesuite.games;

public class TimeUtils {
    public static double microseconds(long nanoseconds) {
        return nanoseconds / 1e3;
    }

    public static double milliseconds(long nanoseconds) {
        return nanoseconds / 1e6;
    }

    public static double seconds(long nanoseconds) {
        return nanoseconds / 1e9;
    }

    public static double minutes(long nanoseconds) {
        return seconds(nanoseconds) / 60;
    }

    public static double hours(long nanoseconds) {
        return minutes(nanoseconds) / 60;
    }
}
