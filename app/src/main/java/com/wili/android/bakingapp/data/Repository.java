package com.wili.android.bakingapp.data;

import com.wili.android.bakingapp.data.models.Recipe;
import com.wili.android.bakingapp.data.network.ApiManager;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private static Repository INSTANCE;
    private ApiManager apiManager;

    private Repository() {
        this.apiManager = ApiManager.getInstance();
    }

    public static Repository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Repository();
        return INSTANCE;
    }

    public LiveData<List<Recipe>> getRecipeListLiveData() {
        final MutableLiveData<List<Recipe>> recipeList = new MutableLiveData<>();
        apiManager.getRecipeList().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                recipeList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });
        return recipeList;
    }

    public Call<List<Recipe>> getRecipeList() {
        return apiManager.getRecipeList();
    }
}
