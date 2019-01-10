package com.wili.android.bakingapp.activity.main;

import com.wili.android.bakingapp.data.Repository;
import com.wili.android.bakingapp.data.models.Recipe;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private Repository repository;
    private LiveData<List<Recipe>> recipeList;

    public MainViewModel() {
        repository = Repository.getInstance();
        recipeList = repository.getRecipeListLiveData();
    }

    public LiveData<List<Recipe>> getRecipeList() {
        return this.recipeList;
    }
}
