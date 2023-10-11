package com.agents;

import java.awt.Point;

public class State {
    private String taskDescription;
    private Point agentLocation;
    // Add other state variables as needed

    public State(String taskDescription, Point agentLocation) {
        this.taskDescription = taskDescription;
        this.agentLocation = agentLocation;
        // Initialize other state variables
    }

    // Getters and setters for state variables

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Point getAgentLocation() {
        return agentLocation;
    }

    public void setAgentLocation(Point agentLocation) {
        this.agentLocation = agentLocation;
    }

	public State() {
		super();
		// TODO Auto-generated constructor stub
	}
    

    // Implement equals and hashCode methods if necessary
}
