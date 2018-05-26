package com.wili.android.bakingapp;

import com.wili.android.bakingapp.data.models.Recipe;
import com.wili.android.bakingapp.data.network.RequestInterface;
import com.wili.android.bakingapp.data.network.RetrofitService;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;

public class RetrofitTest {

    public static Retrofit retrofit;
    public static List<Recipe> recipes;

    @Before
    public void setUp() {
        retrofit = RetrofitService.getService();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        try {
            recipes = requestInterface.getData().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkIsNotNull() {
        Assert.assertNotNull(recipes);
    }

    @Test
    public void checkSize(){
        Assert.assertEquals(4,recipes.size());
    }
}
