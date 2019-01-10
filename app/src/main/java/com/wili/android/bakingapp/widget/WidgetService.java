package com.wili.android.bakingapp.widget;


import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.wili.android.bakingapp.data.models.Recipe;

import androidx.annotation.Nullable;

public class WidgetService extends IntentService {
    public static final String RECIPE_WIDGET_ACTION_UPDATE = "com.wili.android.bakingapp.action.update";
    private static final String BUNDLE_RECIPE_WIDGET_DATA = "com.wili.android.bakingapp.recipe_widget_data";

    public WidgetService() {
        super("WidgetService");
    }

    public static void updateRecipeWidgets(Context context, Recipe recipe) {
        Intent intent = new Intent(context, WidgetService.class);
        intent.setAction(RECIPE_WIDGET_ACTION_UPDATE);
        Gson gson = new Gson();
        intent.putExtra(Recipe.RECIPE_KEY, gson.toJson(recipe));
        context.startService(intent);
        Log.d(WidgetService.class.getSimpleName(), "Updating recipe " + recipe.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (RECIPE_WIDGET_ACTION_UPDATE.equals(action) &&
                    intent.getStringExtra(Recipe.RECIPE_KEY) != null) {
                Gson gson = new Gson();
                handleActionUpdateWidgets(gson.fromJson(intent.getStringExtra(Recipe.RECIPE_KEY), Recipe.class));
            }

        }
    }

    private void handleActionUpdateWidgets(Recipe recipe) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingWidget.class));
        BakingWidget.updateRecipeWidgets(this, recipe, appWidgetManager, appWidgetIds);
    }
}