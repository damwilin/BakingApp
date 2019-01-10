package com.wili.android.bakingapp.data.network;

import com.wili.android.bakingapp.data.models.Recipe;

import java.util.List;

import retrofit2.Call;

public class ApiManager {
    private static ApiManager INSTANCE;
    private RequestInterface requestInterface;

    private ApiManager() {
        this.requestInterface = RetrofitService.getService(RequestInterface.class);
    }

    public static ApiManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApiManager();
        }
        return INSTANCE;
    }

    public Call<List<Recipe>> getRecipeList() {
        return requestInterface.getData();
    }
}
