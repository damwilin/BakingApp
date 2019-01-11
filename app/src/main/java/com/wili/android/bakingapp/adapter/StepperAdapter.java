package com.wili.android.bakingapp.adapter;

import android.content.Context;
import android.view.View;

import com.wili.android.bakingapp.data.models.Step;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import moe.feng.common.stepperview.IStepperAdapter;
import moe.feng.common.stepperview.VerticalStepperItemView;

public class StepperAdapter implements IStepperAdapter {
    private List<Step> stepList;

    @NonNull
    @Override
    public CharSequence getTitle(int i) {
        return stepList.get(i).getShortDescription();
    }

    @Nullable
    @Override
    public CharSequence getSummary(int i) {
        return stepList.get(i).getDescription();
    }

    @Override
    public int size() {
        return stepList.size();
    }

    @Override
    public View onCreateCustomView(int i, Context context, VerticalStepperItemView verticalStepperItemView) {
        return null;
    }

    @Override
    public void onShow(int i) {

    }

    @Override
    public void onHide(int i) {

    }
}
