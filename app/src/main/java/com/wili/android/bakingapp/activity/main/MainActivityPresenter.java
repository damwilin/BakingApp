package com.wili.android.bakingapp.activity.main;

import com.wili.android.bakingapp.data.DataManager;
import com.wili.android.bakingapp.data.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter {
    MainActivityView view;
    DataManager dataManager;

    public MainActivityPresenter(MainActivityView view, DataManager dataManager) {
        this.view = view;
        this.dataManager = dataManager;
    }

    public void loadRecipeList(){
        dataManager.getRecipeList().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                view.displayRecipeList(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });
    }
}
