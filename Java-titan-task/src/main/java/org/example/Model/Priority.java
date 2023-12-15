package org.example.Model;

public enum Priority {
    HIGH("High",3),
    MEDIUM("Medium", 2),
    LOW("Low", 1);

    private final String name;
    private final int value;

    Priority(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
