package de.harrydehix.eragamesuite.games;

public class Difficulty {
    private final String name;
    private final double weight;

    public Difficulty(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }


    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return getName();
    }
}
