package com.wili.android.bakingapp.activity;

import com.wili.android.bakingapp.data.models.Step;

public interface MViewModel {
    Step getStep();

    void setStep(Step step);
}
