package com.wili.android.bakingapp.activity.detail;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.wili.android.bakingapp.R;
import com.wili.android.bakingapp.data.models.Recipe;
import com.wili.android.bakingapp.fragment.RecipeStepDetailFragment;

public class DetailActivity extends AppCompatActivity implements DetailActivityView {
    private DetailActivityPresenter presenter;
    private Recipe recipe;

    private RecipeStepDetailFragment recipeStepDetailFragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        presenter = new DetailActivityPresenter(this);
        getRecipeFromIntent();
        initializeFragment();
        displayRecipeStepDetail(recipe);
    }

    private void getRecipeFromIntent(){
        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra(Recipe.RECIPE_KEY);
        recipe = gson.fromJson(strObj, Recipe.class);
        System.out.println(recipe.getName().toString());
    }
    private void initializeFragment(){
        recipeStepDetailFragment = new RecipeStepDetailFragment();
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void displayRecipeStepDetail(Recipe recipe) {
        recipeStepDetailFragment.setIngredientList(recipe.getIngredients());
        recipeStepDetailFragment.setStepList(recipe.getSteps());
        fragmentManager.beginTransaction()
                .add(R.id.ingredients_container, recipeStepDetailFragment)
                .commit();
    }
}
