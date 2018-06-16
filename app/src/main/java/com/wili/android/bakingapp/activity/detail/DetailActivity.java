package com.wili.android.bakingapp.activity.detail;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.wili.android.bakingapp.R;
import com.wili.android.bakingapp.data.models.Recipe;
import com.wili.android.bakingapp.fragment.RecipeDetailFragment;

public class DetailActivity extends AppCompatActivity implements DetailActivityView {
    private DetailActivityPresenter presenter;
    private Recipe recipe;

    private RecipeDetailFragment recipeDetailFragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        presenter = new DetailActivityPresenter(this);
        getRecipeFromIntent();
        initializeFragment();
        displayRecipeDetail(recipe);
    }

    private void getRecipeFromIntent(){
        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra(Recipe.RECIPE_KEY);
        recipe = gson.fromJson(strObj, Recipe.class);
    }
    private void initializeFragment(){
        recipeDetailFragment = new RecipeDetailFragment();
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void displayRecipeDetail(Recipe recipe) {
        recipeDetailFragment.setIngredientList(recipe.getIngredients());
        recipeDetailFragment.setStepList(recipe.getSteps());
        fragmentManager.beginTransaction()
                .add(R.id.ingredients_container, recipeDetailFragment)
                .commit();
    }
}
