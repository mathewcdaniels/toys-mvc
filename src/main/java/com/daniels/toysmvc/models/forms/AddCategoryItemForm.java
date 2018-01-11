package com.daniels.toysmvc.models.forms;

import com.daniels.toysmvc.models.Category;
import com.daniels.toysmvc.models.Toy;

import javax.validation.constraints.NotNull;

public class AddCategoryItemForm {

    private Category category;
    private Iterable<Toy> toys;

    @NotNull
    private int categoryId;

    @NotNull
    private int toyId;

    // Standard constructor
    public AddCategoryItemForm(Category category, Iterable<Toy> toys) {
        this.category = category;
        this.toys = toys;
    }

    public AddCategoryItemForm() {}

    // Getters & setters

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Iterable<Toy> getToys() {
        return toys;
    }

    public void setToys(Iterable<Toy> toys) {
        this.toys = toys;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getToyId() {
        return toyId;
    }

    public void setToyId(int toyId) {
        this.toyId = toyId;
    }
}
