package com.wili.android.bakingapp.activity.step;

import com.wili.android.bakingapp.activity.MViewModel;
import com.wili.android.bakingapp.data.models.Step;

import androidx.lifecycle.ViewModel;

public class RecipeStepViewModel extends ViewModel implements MViewModel {
    private Step step;

    public RecipeStepViewModel() {
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }
}
