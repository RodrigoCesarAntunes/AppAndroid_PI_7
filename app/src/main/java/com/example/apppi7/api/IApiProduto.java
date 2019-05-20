package com.example.apppi7.api;

import com.example.apppi7.model.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IApiProduto {

    @GET("/android/rest/produto")
    Call<List<Produto>> GetProduto();
}
