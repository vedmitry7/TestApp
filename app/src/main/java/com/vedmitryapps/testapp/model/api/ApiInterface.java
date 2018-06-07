package com.vedmitryapps.testapp.model.api;

import com.vedmitryapps.testapp.model.City;
import com.vedmitryapps.testapp.model.Institution;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {

    @GET("/bins/kglaa")
    Call<List<City>> getCities();

    @GET("/bins/qtxgi")
    Call<List<Institution>> getInstitutions();

}