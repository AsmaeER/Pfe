package com.agents;

import java.awt.Point;

public class Node {
    private Point position;
    private double cost;
    private double heuristic;
    private Node parent; // Add a parent field to store the parent node

    public Node(Point position, double cost, double heuristic) {
        this.position = position;
        this.cost = cost;
        this.heuristic = heuristic;
        this.parent = null; // Initialize the parent as null
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
