package com.daniels.toysmvc.models;

import javax.persistence.*;


@Entity
public class Toy {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;

    // Default constructor
    public Toy {};

    // Standard constructor
    public Toy(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters & setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String setDescription(String description) {
        this.description = description;
    }
}
