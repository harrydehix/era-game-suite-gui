package de.harrydehix.eragamesuite.games;

public class Mode {
    private final String name;
    private final double weight;

    public Mode(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
