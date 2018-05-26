package com.wili.android.bakingapp.data.models;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("quantity")
    private double quantity;
    @SerializedName("measure")
    private String measureType;
    @SerializedName("ingredient")
    private String name;

    public Ingredient() {
    }

    public double getQuantity() {
        return quantity;
    }

    public String getMeasureType() {
        return measureType;
    }

    public String getName() {
        return name;
    }
}
