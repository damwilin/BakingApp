package com.wili.android.bakingapp.data.network;

import com.wili.android.bakingapp.data.models.Recipe;

import java.util.List;

import retrofit2.Call;

public interface ApiManager {
    Call<List<Recipe>> getRecipeList();
}
