package de.harrydehix.eragamesuite.games;

public class Timer {
    private long startTime;
    private long dt;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        dt = System.nanoTime() - startTime;
    }

    public void add(Timer... timer) {
        for (int i = 0; i < timer.length; i++) {
            dt += timer[i].dt;
        }
    }

    /**
     * Returns the measured time in nanoseconds.
     *
     * @return the measured time in nanoseconds
     */
    public long getMeasuredTime() {
        return dt;
    }
}
