package com.wili.android.bakingapp.activity.main;

import com.wili.android.bakingapp.data.models.Recipe;

import java.util.List;

public interface MainActivityView {
void displayRecipeList(List<Recipe> recipeList);
}
