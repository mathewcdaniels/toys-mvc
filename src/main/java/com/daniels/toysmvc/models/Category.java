package com.daniels.toysmvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    // Fields
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=20)
    private String name;

    @ManyToMany
    //@JoinColumn(name = "category_id")
    private List<Toy> toys = new ArrayList<>();

    // Default constructor
    public Category() {}

    // Standard constructor
    public Category(String name) {
        this.name = name;
    }

    // Getters & setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Toy> getToys() {
        return toys;
    }

    public void addItem(Toy toy) {
        toys.add(toy);
    }
}
