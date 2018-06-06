package com.wili.android.bakingapp.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Ingredient implements Parcelable {
    @SerializedName("quantity")
    private double quantity;
    @SerializedName("measure")
    private String measureType;
    @SerializedName("ingredient")
    private String name;

    protected Ingredient(Parcel in) {
        quantity = in.readDouble();
        measureType = in.readString();
        name = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(quantity);
        dest.writeString(measureType);
        dest.writeString(name);
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}
