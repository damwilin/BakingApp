package com.wili.android.bakingapp.data;

import com.wili.android.bakingapp.data.models.Recipe;
import com.wili.android.bakingapp.data.network.ApiManager;

import java.util.List;

import retrofit2.Call;

public class AppDataManager implements DataManager {
    ApiManager apiManager;

    public AppDataManager(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public Call<List<Recipe>> getRecipeList() {
        return apiManager.getRecipeList();
    }
}
