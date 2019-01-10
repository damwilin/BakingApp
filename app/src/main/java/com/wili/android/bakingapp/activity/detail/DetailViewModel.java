package com.wili.android.bakingapp.activity.detail;

import com.wili.android.bakingapp.activity.MViewModel;
import com.wili.android.bakingapp.data.models.Recipe;
import com.wili.android.bakingapp.data.models.Step;

import androidx.lifecycle.ViewModel;

public class DetailViewModel extends ViewModel implements MViewModel {
    private Recipe recipe;
    private Step step;

    public DetailViewModel() {
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        if (recipe != null && recipe.getSteps().size() > 0)
            setStep(recipe.getSteps().get(0));
    }

    @Override
    public Step getStep() {
        return step;
    }

    @Override
    public void setStep(Step step) {
        this.step = step;
    }
}
