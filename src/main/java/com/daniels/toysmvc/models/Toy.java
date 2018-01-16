package com.daniels.toysmvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class Toy {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=20)
    private String name;

    @NotNull
    @Size(min=3, message="Description must not be empty")
    private String description;

    @ManyToMany(mappedBy = "toys")
    private List<Category> category;

    private Date acquisitionDate = new Date();

    // Standard constructor
    public Toy(String name, String description, Date acquisitionDate) {
        this.name = name;
        this.description = description;
        this.acquisitionDate = acquisitionDate;
    }

    // Default constructor
    public Toy() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public Date getAcquisitionDate() { return acquisitionDate; }

    public void removeToyFromCategory(Category category) {
        this.getCategory().remove(category);
        category.removeItem(this);
    }
}