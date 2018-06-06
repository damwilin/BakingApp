package com.wili.android.bakingapp.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("ingredients")
    private List<Ingredient> ingredients = new ArrayList<>();
    @SerializedName("steps")
    private List<Step> steps = new ArrayList<>();
    @SerializedName("servings")
    private int servings;
    @SerializedName("image")
    private String imageURL;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Recipe() {
    }

    public Recipe(String name) {
        this.name = name;
    }
}
