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
}
