package com.wili.android.bakingapp.data.network;

import com.wili.android.bakingapp.data.models.Recipe;

import java.util.List;

import retrofit2.Call;

public class AppApiManager implements ApiManager{
    private RequestInterface requestInterface;

    public AppApiManager() {
        this.requestInterface = RetrofitService.getService(RequestInterface.class);
    }

    @Override
    public Call<List<Recipe>> getRecipeList() {
        return requestInterface.getData();
    }
}
