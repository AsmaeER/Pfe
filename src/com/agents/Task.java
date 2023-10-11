package com.agents;

import java.awt.Point;
import java.io.Serializable;

public class Task implements Serializable {
    private String description;
    private Point startLocation;
    private Point targetLocation;
    private TaskType type; // Enum to represent task type

    // Define an enum to represent task types
    public enum TaskType {
        PICK_ORDER, // Task to pick an order
        STORE_ARTICLE // Task to store an article
    }

    public Task(String description, Point startLocation, Point targetLocation, TaskType type) {
        this.description = description;
        this.startLocation = startLocation;
        this.targetLocation = targetLocation;
        this.type = type;
    }

    // Add getters and setters for task details
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Point getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Point startLocation) {
        this.startLocation = startLocation;
    }

    public Point getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(Point targetLocation) {
        this.targetLocation = targetLocation;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }
}
