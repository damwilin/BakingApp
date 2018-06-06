package com.wili.android.bakingapp.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.wili.android.bakingapp.R;
import com.wili.android.bakingapp.adapter.RecipeAdapter;
import com.wili.android.bakingapp.data.AppDataManager;
import com.wili.android.bakingapp.data.DataManager;
import com.wili.android.bakingapp.data.models.Recipe;
import com.wili.android.bakingapp.data.network.ApiManager;
import com.wili.android.bakingapp.data.network.AppApiManager;
import com.wili.android.bakingapp.fragment.RecipeListFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityView {
    private RecipeListFragment recipeListFragment;
    private FragmentManager fragmentManager;

    private MainActivityPresenter presenter;
    private DataManager dataManager;
    private ApiManager apiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiManager = new AppApiManager();
        dataManager = new AppDataManager(apiManager);
        presenter = new MainActivityPresenter(this, dataManager);
        initializeFragment();
        presenter.loadRecipeList();
    }

    private void initializeFragment(){
        recipeListFragment = new RecipeListFragment();
        fragmentManager = getSupportFragmentManager();
    }


    @Override
    public void displayRecipeList(List<Recipe> recipeList) {
        recipeListFragment.setRecipeList(recipeList);
        fragmentManager.beginTransaction()
                .add(R.id.list_container, recipeListFragment)
                .commit();
    }

}
